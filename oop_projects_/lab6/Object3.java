package lab6;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Object3 extends JFrame {
    private List<Double> sortedData = new ArrayList<>();

    public Object3() {
        setTitle("Object3 - Сортування даних");
        setSize(350, 420);
        setLocation(880, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        readFromClipboardAndSort();

        String[] columnNames = {"Індекс", "Відсортоване значення"};
        Object[][] rowData = new Object[sortedData.size()][2];
        for (int i = 0; i < sortedData.size(); i++) {
            rowData[i][0] = i;
            rowData[i][1] = sortedData.get(i);
        }

        JTable table = new JTable(rowData, columnNames);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JLabel lblStatus = new JLabel(" Дані зчитано з Clipboard та відсортовано.", JLabel.CENTER);
        add(lblStatus, BorderLayout.SOUTH);
    }

    private void readFromClipboardAndSort() {
        try {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            if (clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
                String text = (String) clipboard.getData(DataFlavor.stringFlavor);
                if (text != null && !text.isEmpty()) {
                    String[] lines = text.split("\n");
                    for (String line : lines) {
                        if (!line.trim().isEmpty()) {
                            sortedData.add(Double.parseDouble(line.trim()));
                        }
                    }
                    Collections.sort(sortedData);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Помилка читання з Clipboard: " + e.getMessage(), "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Object3().setVisible(true));
    }
}