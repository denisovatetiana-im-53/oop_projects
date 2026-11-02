package lab3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class MainFrame extends JFrame {
    private static final int MAX_SHAPES = 111;
    private final Shape[] pcshape = new Shape[MAX_SHAPES];
    private int shapeCount = 0;

    private String currentShapeType = "Прямокутник";
    private int startX, startY, currentX, currentY;
    private boolean isDrawing = false;

    public MainFrame() {
        setTitle("Лабораторна робота №3 - Варіант 10 ");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Файл");
        JMenu objectsMenu = new JMenu("Об'єкти"); 
        JMenu helpMenu = new JMenu("Довідка");

        JMenuItem itemPoint = new JMenuItem("Крапка");
        JMenuItem itemLine = new JMenuItem("Лінія");
        JMenuItem itemRect = new JMenuItem("Прямокутник");
        JMenuItem itemEllipse = new JMenuItem("Еліпс");

        itemPoint.addActionListener(e -> setShapeType("Крапка"));
        itemLine.addActionListener(e -> setShapeType("Лінія"));
        itemRect.addActionListener(e -> setShapeType("Прямокутник"));
        itemEllipse.addActionListener(e -> setShapeType("Еліпс"));

        objectsMenu.add(itemPoint);
        objectsMenu.add(itemLine);
        objectsMenu.add(itemRect);
        objectsMenu.add(itemEllipse);

        menuBar.add(fileMenu);
        menuBar.add(objectsMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);

        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        JButton btnPoint = new JButton(createShapeIcon("Крапка"));
        btnPoint.setToolTipText("Створити крапку");
        btnPoint.addActionListener(e -> setShapeType("Крапка"));

        JButton btnLine = new JButton(createShapeIcon("Лінія"));
        btnLine.setToolTipText("Створити лінію");
        btnLine.addActionListener(e -> setShapeType("Лінія"));

        JButton btnRect = new JButton(createShapeIcon("Прямокутник"));
        btnRect.setToolTipText("Створити прямокутник");
        btnRect.addActionListener(e -> setShapeType("Прямокутник"));

        JButton btnEllipse = new JButton(createShapeIcon("Еліпс"));
        btnEllipse.setToolTipText("Створити еліпс");
        btnEllipse.addActionListener(e -> setShapeType("Еліпс"));

        toolBar.add(btnPoint);
        toolBar.add(btnLine);
        toolBar.add(btnRect);
        toolBar.add(btnEllipse);
        add(toolBar, BorderLayout.NORTH);

        JPanel canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                for (int i = 0; i < shapeCount; i++) {
                    if (pcshape[i] != null) {
                        pcshape[i].show(g2d);
                    }
                }

                if (isDrawing) {
                    float[] dash = {4f, 4f};
                    g2d.setStroke(new BasicStroke(1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1f, dash, 0f));
                    g2d.setColor(Color.BLACK);

                    if (currentShapeType.equals("Лінія")) {
                        g2d.drawLine(startX, startY, currentX, currentY);
                    } else if (currentShapeType.equals("Прямокутник")) {
                        int w = Math.abs(currentX - startX);
                        int h = Math.abs(currentY - startY);
                        g2d.drawRect(startX - w, startY - h, w * 2, h * 2);
                    } else if (currentShapeType.equals("Еліпс")) {
                        int x = Math.min(startX, currentX);
                        int y = Math.min(startY, currentY);
                        g2d.drawOval(x, y, Math.abs(currentX - startX), Math.abs(currentY - startY));
                    }
                }
            }
        };

        canvas.setBackground(Color.WHITE);
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startX = e.getX();
                startY = e.getY();
                isDrawing = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (!isDrawing) return;
                currentX = e.getX();
                currentY = e.getY();
                isDrawing = false;

                if (shapeCount < MAX_SHAPES) {
                    Shape newShape = null;
                    switch (currentShapeType) {
                        case "Крапка": newShape = new PointShape(); break;
                        case "Лінія": newShape = new LineShape(); break;
                        case "Прямокутник": newShape = new RectShape(); break;
                        case "Еліпс": newShape = new EllipseShape(); break;
                    }
                    if (newShape != null) {
                        newShape.set(startX, startY, currentX, currentY);
                        pcshape[shapeCount++] = newShape;
                    }
                }
                canvas.repaint();
            }
        });

        canvas.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                currentX = e.getX();
                currentY = e.getY();
                canvas.repaint();
            }
        });

        add(canvas, BorderLayout.CENTER);
        updateTitle("Прямокутник");
    }

    private ImageIcon createShapeIcon(String type) {
        int size = 28;
        BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setColor(new Color(245, 245, 245));
        g2d.fillRect(0, 0, size, size);
        g2d.setColor(new Color(200, 200, 200));
        g2d.drawRect(0, 0, size - 1, size - 1);

        g2d.setColor(Color.BLACK);
        switch (type) {
            case "Крапка":
                g2d.fillRect(13, 13, 3, 3);
                break;
            case "Лінія":
                g2d.drawLine(6, 22, 22, 6);
                break;
            case "Прямокутник":
                g2d.setColor(Color.GRAY);
                g2d.fillRect(5, 7, 18, 14);
                g2d.setColor(Color.BLACK);
                g2d.drawRect(5, 7, 18, 14);
                break;
            case "Еліпс":
                g2d.setColor(Color.WHITE);
                g2d.fillOval(5, 7, 18, 14);
                g2d.setColor(Color.BLACK);
                g2d.drawOval(5, 7, 18, 14);
                break;
        }
        g2d.dispose();
        return new ImageIcon(img);
    }

    private void setShapeType(String type) {
        this.currentShapeType = type;
        updateTitle(type);
    }

    private void updateTitle(String shapeName) {
        setTitle("Лаб. №3 | Режим вводу: " + shapeName); 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}