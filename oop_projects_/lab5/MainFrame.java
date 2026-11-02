package lab5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
    private final MyEditor editor;
    private final MyTable tableWindow;
    private int startX, startY, currentX, currentY;
    private boolean isDrawing = false;

    public MainFrame() {
        setTitle("Лабораторна робота №5 - Варіант 10 ");
        setSize(950, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        editor = MyEditor.getInstance();
        
        tableWindow = new MyTable(this);
        editor.setTableWindow(tableWindow);

        tableWindow.setTableEventListener(new TableEventListener() {
            @Override
            public void onShapeSelected(int index) {
                editor.selectShape(index);
                repaint(); 
            }

            @Override
            public void onShapeDeleted(int index) {
                editor.deleteShape(index);
                repaint();
            }
        });

        JMenuBar menuBar = new JMenuBar();
        JMenu menuObjects = new JMenu("Об'єкти");
        JMenu menuFile = new JMenu("Файл");
        JMenu menuWindow = new JMenu("Вікно");

        JMenuItem itemPoint = new JMenuItem("Крапка");
        JMenuItem itemLine = new JMenuItem("Лінія");
        JMenuItem itemRect = new JMenuItem("Прямокутник");
        JMenuItem itemEllipse = new JMenuItem("Еліпс");

        itemPoint.addActionListener(e -> editor.start(new PointShape()));
        itemLine.addActionListener(e -> editor.start(new LineShape()));
        itemRect.addActionListener(e -> editor.start(new RectShape()));
        itemEllipse.addActionListener(e -> editor.start(new EllipseShape()));

        menuObjects.add(itemPoint);
        menuObjects.add(itemLine);
        menuObjects.add(itemRect);
        menuObjects.add(itemEllipse);

        JMenuItem itemLoad = new JMenuItem("Завантажити з файлу");
        itemLoad.addActionListener(e -> {
            editor.loadFromFile();
            tableWindow.refreshTableFromEditor();
            repaint();
            JOptionPane.showMessageDialog(this, "Дані успішно завантажено з файлу shapes.txt!");
        });
        menuFile.add(itemLoad);

        JMenuItem itemTable = new JMenuItem("Відкрити таблицю");
        itemTable.addActionListener(e -> tableWindow.setVisible(true));
        menuWindow.add(itemTable);

        menuBar.add(menuFile);
        menuBar.add(menuObjects);
        menuBar.add(menuWindow);
        setJMenuBar(menuBar);

        JPanel canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                editor.paint(g2d);

                if (isDrawing) {
                    g2d.setColor(Color.BLUE);
                    g2d.setStroke(new BasicStroke(1.0f));
                    g2d.drawRect(Math.min(startX, currentX), Math.min(startY, currentY),
                            Math.abs(currentX - startX), Math.abs(currentY - startY));
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

                editor.addShape(startX, startY, currentX, currentY);
                canvas.repaint();
            }
        });

        canvas.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                currentX = e.getX();
                currentY = e.getY();
                canvas.repaint();
            }
        });

        add(canvas, BorderLayout.CENTER);
        
        editor.loadFromFile();
        tableWindow.refreshTableFromEditor();
        tableWindow.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}