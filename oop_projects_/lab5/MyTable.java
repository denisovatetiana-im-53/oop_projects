package lab5;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyTable extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;
    private TableEventListener listener;

    public MyTable(JFrame parent) {
        setTitle("Таблиця об'єктів (з бонусами)");
        setSize(500, 350);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); 

        String[] columnNames = {"№", "Назва", "x1", "y1", "x2", "y2"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(tableModel);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1 && listener != null) {
                    listener.onShapeSelected(selectedRow);
                }
            }
        });

        JButton btnDelete = new JButton("Видалити вибраний об'єкт");
        btnDelete.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1 && listener != null) {
                listener.onShapeDeleted(selectedRow);
                refreshTableFromEditor(); 
            }
        });

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(btnDelete, BorderLayout.SOUTH);
    }

    public void setTableEventListener(TableEventListener listener) {
        this.listener = listener;
    }

    public void addRow(int index, Shape s) {
        tableModel.addRow(new Object[]{
            index, s.getName(), s.getX1(), s.getY1(), s.getX2(), s.getY2()
        });
    }

    public void clearTable() {
        tableModel.setRowCount(0);
    }

    public void refreshTableFromEditor() {
        clearTable();
        MyEditor editor = MyEditor.getInstance();
        Shape[] shapes = editor.getShapes();
        int count = editor.getShapeCount();
        for (int i = 0; i < count; i++) {
            if (shapes[i] != null) {
                addRow(i, shapes[i]);
            }
        }
    }
}