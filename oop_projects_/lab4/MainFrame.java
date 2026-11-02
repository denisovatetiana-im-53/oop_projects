package lab4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    private MyEditor editor; 
    private Point ptStart, ptCurrent;
    private boolean isDrawing = false;

    public MainFrame() {
        editor = new MyEditor();
        editor.start(new RectShape()); 

        setTitle("Лабораторна робота №4 - Варіант 10");
        setSize(900, 650);
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

        JButton btnPoint = new JButton("Крапка");
        btnPoint.setToolTipText("Малювати крапку");
        btnPoint.addActionListener(e -> editor.start(new PointShape()));

        JButton btnLine = new JButton("Лінія");
        btnLine.setToolTipText("Малювати лінію");
        btnLine.addActionListener(e -> editor.start(new LineShape()));

        JButton btnRect = new JButton("Прямокутник");
        btnRect.setToolTipText("Малювати прямокутник");
        btnRect.addActionListener(e -> editor.start(new RectShape()));

        JButton btnEllipse = new JButton("Еліпс");
        btnEllipse.setToolTipText("Малювати еліпс");
        btnEllipse.addActionListener(e -> editor.start(new EllipseShape()));

        JButton btnLineOO = new JButton("Лінія з круж.");
        btnLineOO.setToolTipText("Малювати лінію з кружечками на кінцях");
        btnLineOO.addActionListener(e -> editor.start(new LineOOShape()));

        JButton btnCube = new JButton("Куб");
        btnCube.setToolTipText("Малювати каркас куба");
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}