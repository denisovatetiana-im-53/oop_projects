package lab6;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Random;

public class Object2 extends JFrame {
    private double[] data;

    public Object2(int n, double min, double max) {
        setTitle("Object2 - Генератор даних (double)");
        setSize(350, 420);
        setLocation(500, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        generateData(n, min, max);
        copyToClipboard();

        String[] columnNames = {"Індекс", "Значення"};
        Object[][] rowData = new Object[n][2];
        for (int i = 0; i < n; i++) {
            rowData[i][0] = i;
            rowData[i][1] = data[i];
        }

        JTable table = new JTable(rowData, columnNames);
        add(new JScrollPane(table), BorderLayout.CENTER);
        
        JLabel lblStatus = new JLabel(" Дані згенеровано та записано в Clipboard.", JLabel.CENTER);
        add(lblStatus, BorderLayout.SOUTH);
    }

    private void generateData(int n, double min, double max) {
        data = new double[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            data[i] = min + (max - min) * rand.nextDouble();
        }
    }

    private void copyToClipboard() {
        StringBuilder sb = new StringBuilder();
        for (double val : data) {
            sb.append(val).append("\n");
        }
        StringSelection selection = new StringSelection(sb.toString());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
    }

    public static void main(String[] args) {
        int n = 10;
        double min = -50.0;
        double max = 50.0;

        if (args.length >= 3) {
            try {
                n = Integer.parseInt(args[0]);
                min = Double.parseDouble(args[1]);
                max = Double.parseDouble(args[2]);
            } catch (NumberFormatException ignored) {}
        }

        int finalN = n;
        double finalMin = min;
        double finalMax = max;
        SwingUtilities.invokeLater(() -> new Object2(finalN, finalMin, finalMax).setVisible(true));
    }
}

