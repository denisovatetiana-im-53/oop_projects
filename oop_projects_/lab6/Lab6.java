package lab6;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Lab6 extends JFrame {
    private JTextField tfN = new JTextField("10", 10);
    private JTextField tfMin = new JTextField("-50.0", 10);
    private JTextField tfMax = new JTextField("50.0", 10);

    public Lab6() {
        setTitle("Lab6 - Головний Менеджер (Варіант 2)");
        setSize(380, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(100, 100);
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel(" Кількість елементів (n):"));
        add(tfN);
        add(new JLabel(" Мінімальне значення (Min):"));
        add(tfMin);
        add(new JLabel(" Максимальне значення (Max):"));
        add(tfMax);

        JButton btnStart = new JButton("Запустити систему");
        add(btnStart);

        btnStart.addActionListener(e -> startSystem());
    }

private void startSystem() {
        try {
            int n = Integer.parseInt(tfN.getText().trim());
            double min = Double.parseDouble(tfMin.getText().trim());
            double max = Double.parseDouble(tfMax.getText().trim());

            String classpath = System.getProperty("java.class.path");

            ProcessBuilder pb2 = new ProcessBuilder("java", "-cp", classpath, "lab6.Object2", String.valueOf(n), String.valueOf(min), String.valueOf(max));
            pb2.start();

            Thread.sleep(800);

            ProcessBuilder pb3 = new ProcessBuilder("java", "-cp", classpath, "lab6.Object3");
            pb3.start();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Будь ласка, введіть коректні числові значення!", "Помилка", JOptionPane.ERROR_MESSAGE);
        } catch (IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(this, "Помилка запуску процесів: " + ex.getMessage(), "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Lab6().setVisible(true));
    }
}