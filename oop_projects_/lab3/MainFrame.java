package lab3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    private static final int MAX_SHAPES = 111;
    private Shape[] pcshape = new Shape[MAX_SHAPES];
    private int shapeCount = 0;

    private String currentShapeType = "Прямокутник";
    private int startX, startY, currentX, currentY;
    private boolean isDrawing = false;

    public MainFrame() {
        setTitle("Лабораторна робота №3 - Варіант 10 (Ж=11)");
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

        JButton btnPoint = new JButton("Крапка");
        btnPoint.setToolTipText("Створити крапку");
        btnPoint.addActionListener(e -> setShapeType("Крапка"));

        JButton btnLine = new JButton("Лінія");
        btnLine.setToolTipText("Створити лінію");
        btnLine.addActionListener(e -> setShapeType("Лінія"));

        JButton btnRect = new JButton("Прямокутник");
        btnRect.setToolTipText("Створити прямокутник");
        btnRect.addActionListener(e -> setShapeType("Прямокутник"));

        JButton btnEllipse = new JButton("Еліпс");
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

    private void setShapeType(String type) {
        this.currentShapeType = type;
        updateTitle(type);
    }

    private void updateTitle(String shapeName) {
        setTitle("Графічний редактор (Лаб. №3) | Поточний режим: " + shapeName); 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
