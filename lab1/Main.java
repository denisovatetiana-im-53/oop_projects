package lab1;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private String selectedGroupText = "";

    private final JPanel drawPanel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (!selectedGroupText.isEmpty()) {
                g.setFont(new Font("SansSerif", Font.BOLD, 18));
                g.drawString("Обрана група: " + selectedGroupText, 40, 60);
            }
        }
    };

    public Main() {
        setTitle("Лабораторна робота №1 / №2");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Меню");
        
        JMenuItem itemWork1 = new JMenuItem("Робота1");
        JMenuItem itemWork2 = new JMenuItem("Робота2");

        itemWork1.addActionListener(e -> Module1.runDialogs(Main.this));

        itemWork2.addActionListener(e -> {
            String result = Module2.showListDialog(Main.this);
            if (result != null) {
                selectedGroupText = result;
                drawPanel.repaint();
            }
        });

        menu.add(itemWork1);
        menu.add(itemWork2);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        add(drawPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}