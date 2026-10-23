package lab2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    private static final int N = 110;
    private final Shape[] pcshape = new Shape[N]; 
    private int shapeCount = 0;

    private int currentType = 1; 
    private Point ptStart, ptCurrent;
    private boolean isDrawing = false;

    private JCheckBoxMenuItem itemPoint, itemLine, itemRect, itemEllipse;

    public MainFrame() {
        setTitle("Лабораторна робота №2 - Графічний редактор (Вар 10)");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();

        JMenu menuFile = new JMenu("Файл");
        JMenuItem itemExit = new JMenuItem("Вихід");
        itemExit.addActionListener(e -> System.exit(0));
        menuFile.add(itemExit);

        JMenu menuObjects = new JMenu("Об’єкти");
        itemPoint = new JCheckBoxMenuItem("Крапка", true);
        itemLine = new JCheckBoxMenuItem("Лінія");
        itemRect = new JCheckBoxMenuItem("Прямокутник");
        itemEllipse = new JCheckBoxMenuItem("Еліпс");

        itemPoint.addActionListener(e -> selectShape(1));
        itemLine.addActionListener(e -> selectShape(2));
        itemRect.addActionListener(e -> selectShape(3));
        itemEllipse.addActionListener(e -> selectShape(4));

        menuObjects.add(itemPoint);
        menuObjects.add(itemLine);
        menuObjects.add(itemRect);
        menuObjects.add(itemEllipse);

        JMenu menuHelp = new JMenu("Довідка");
        JMenuItem itemAbout = new JMenuItem("Про програму");
        itemAbout.addActionListener(e -> 
            JOptionPane.showMessageDialog(this, "Графічний редактор Lab2\nВаріант №10", "Довідка", JOptionPane.INFORMATION_MESSAGE)
        );
        menuHelp.add(itemAbout);
        menuBar.add(menuFile);
        menuBar.add(menuObjects);
        menuBar.add(menuHelp);
        setJMenuBar(menuBar);

        JPanel drawPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                for (int i = 0; i < shapeCount; i++) {
                    if (pcshape[i] != null) {
                        pcshape[i].show(g);
                    }
                }
                if (isDrawing && ptStart != null && ptCurrent != null) {
                    g.setColor(Color.BLUE);
                    switch (currentType) {
                        case 1: 
                            g.fillRect(ptStart.x, ptStart.y, 2, 2);
                            break;
                        case 2: 
                            g.drawLine(ptStart.x, ptStart.y, ptCurrent.x, ptCurrent.y);
                            break;
                        case 3: 
                            int rx = Math.min(ptStart.x, ptCurrent.x);
                            int ry = Math.min(ptStart.y, ptCurrent.y);
                            int rWidth = Math.abs(ptCurrent.x - ptStart.x);
                            int rHeight = Math.abs(ptCurrent.y - ptStart.y);
                            g.drawRect(rx, ry, rWidth, rHeight);
                            break;
                        case 4: 
                            int ex = Math.abs(ptCurrent.x - ptStart.x);
                            int ey = Math.abs(ptCurrent.y - ptStart.y);
                            g.drawOval(ptStart.x - ex, ptStart.y - ey, ex * 2, ey * 2);
                            break;
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
                    if (shapeCount < N) {
                        Shape shape = createShape(currentType);
                        if (shape != null) {
                            shape.set(ptStart.x, ptStart.y, e.getX(), e.getY());
                            pcshape[shapeCount++] = shape;
                        }
                    }
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

        add(drawPanel);
    }

    private void selectShape(int type) {
        currentType = type;
        itemPoint.setSelected(type == 1);
        itemLine.setSelected(type == 2);
        itemRect.setSelected(type == 3);
        itemEllipse.setSelected(type == 4);
    }

    private Shape createShape(int type) {
        return switch (type) {
            case 1 -> new PointShape();
            case 2 -> new LineShape();
            case 3 -> new RectShape();
            case 4 -> new EllipseShape();
            default -> null;
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}