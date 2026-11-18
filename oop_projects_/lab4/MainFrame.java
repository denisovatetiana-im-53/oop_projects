package lab4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class MainFrame extends JFrame {
    private MyEditor editor;
    private Point ptStart, ptCurrent;
    private boolean isDrawing = false;

    public MainFrame() {
        editor = new MyEditor();
        editor.start(new RectShape()); 

        setTitle("Лабораторна робота №4 - Варіант 10");
        setSize(950, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuObjects = new JMenu("Об'єкти");
        
        JMenuItem itemPoint = new JMenuItem("Крапка");
        JMenuItem itemLine = new JMenuItem("Лінія");
        JMenuItem itemRect = new JMenuItem("Прямокутник");
        JMenuItem itemEllipse = new JMenuItem("Еліпс");
        JMenuItem itemLineOO = new JMenuItem("Лінія з кружечками");
        JMenuItem itemCube = new JMenuItem("Куб");

        itemPoint.addActionListener(e -> editor.start(new PointShape()));
        itemLine.addActionListener(e -> editor.start(new LineShape()));
        itemRect.addActionListener(e -> editor.start(new RectShape()));
        itemEllipse.addActionListener(e -> editor.start(new EllipseShape()));
        itemLineOO.addActionListener(e -> editor.start(new LineOOShape()));
        itemCube.addActionListener(e -> editor.start(new CubeShape()));

        menuObjects.add(itemPoint);
        menuObjects.add(itemLine);
        menuObjects.add(itemRect);
        menuObjects.add(itemEllipse);
        menuObjects.add(itemLineOO);
        menuObjects.add(itemCube);
        menuBar.add(menuObjects);
        setJMenuBar(menuBar);

        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        JButton btnPoint = new JButton(createShapeIcon("Крапка"));
        btnPoint.setToolTipText("Крапка");
        btnPoint.addActionListener(e -> editor.start(new PointShape()));

        JButton btnLine = new JButton(createShapeIcon("Лінія"));
        btnLine.setToolTipText("Лінія");
        btnLine.addActionListener(e -> editor.start(new LineShape()));

        JButton btnRect = new JButton(createShapeIcon("Прямокутник"));
        btnRect.setToolTipText("Прямокутник");
        btnRect.addActionListener(e -> editor.start(new RectShape()));

        JButton btnEllipse = new JButton(createShapeIcon("Еліпс"));
        btnEllipse.setToolTipText("Еліпс");
        btnEllipse.addActionListener(e -> editor.start(new EllipseShape()));

        JButton btnLineOO = new JButton(createShapeIcon("Лінія з круж."));
        btnLineOO.setToolTipText("Лінія з кружечками");
        btnLineOO.addActionListener(e -> editor.start(new LineOOShape()));

        JButton btnCube = new JButton(createShapeIcon("Куб"));
        btnCube.setToolTipText("Каркас куба");
        btnCube.addActionListener(e -> editor.start(new CubeShape()));

        toolBar.add(btnPoint);
        toolBar.add(btnLine);
        toolBar.add(btnRect);
        toolBar.add(btnEllipse);
        toolBar.add(btnLineOO);
        toolBar.add(btnCube);
        add(toolBar, BorderLayout.NORTH);

        JPanel drawPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                editor.paint(g2d);

                if (isDrawing && ptStart != null && ptCurrent != null) {
                    Shape proto = editor.getCurrentPrototype();
                    if (proto != null) {
                        proto.set(ptStart.x, ptStart.y, ptCurrent.x, ptCurrent.y);
                        proto.showRubber(g2d);
                    }
                }
            }
        };

        drawPanel.setBackground(Color.WHITE);
        drawPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ptStart = e.getPoint();
                ptCurrent = ptStart;
                isDrawing = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (isDrawing) {
                    isDrawing = false;
                    editor.addShape(ptStart.x, ptStart.y, e.getX(), e.getY());
                    drawPanel.repaint();
                }
            }
        });

        drawPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isDrawing) {
                    ptCurrent = e.getPoint();
                    drawPanel.repaint();
                }
            }
        });

        add(drawPanel, BorderLayout.CENTER);
    }

    private ImageIcon createShapeIcon(String type) {
        int size = 30;
        BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(new Color(245, 245, 245));
        g2d.fillRect(0, 0, size, size);
        g2d.setColor(new Color(200, 200, 200));
        g2d.drawRect(0, 0, size - 1, size - 1);

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1.2f));

        switch (type) {
            case "Крапка":
                g2d.fillRect(14, 14, 3, 3);
                break;
            case "Лінія":
                g2d.drawLine(6, 24, 24, 6);
                break;
            case "Прямокутник":
                g2d.setColor(Color.WHITE);
                g2d.fillRect(5, 8, 20, 14);
                g2d.setColor(Color.BLACK);
                g2d.drawRect(5, 8, 20, 14);
                break;
            case "Еліпс":
                g2d.drawOval(5, 8, 20, 14);
                break;
            case "Лінія з круж.":
                g2d.drawLine(6, 24, 24, 6);
                g2d.fillOval(4, 22, 5, 5);
                g2d.fillOval(23, 4, 5, 5);
                break;
            case "Куб":
                g2d.drawRect(8, 11, 14, 14);
                g2d.drawRect(4, 5, 14, 14);
                g2d.drawLine(4, 5, 8, 11);
                g2d.drawLine(18, 5, 22, 11);
                g2d.drawLine(4, 19, 8, 25);
                g2d.drawLine(18, 19, 22, 25);
                break;
        }
        g2d.dispose();
        return new ImageIcon(img);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}