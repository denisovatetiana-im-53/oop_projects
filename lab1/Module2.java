package lab1;
import javax.swing.*;
import java.awt.*;

public class Module2 {

    public static String showListDialog(JFrame parent) {
        JDialog dlg = new JDialog(parent, "Вибір групи (B2)", true);
        dlg.setLayout(new BorderLayout());
        dlg.setSize(300, 220);
        dlg.setLocationRelativeTo(parent);

        String[] groups = {"ІМ-51", "ІП-42", "ІO-43", "ІM-53", "І0-52"};
        JList<String> list = new JList<>(groups);
        list.setSelectedIndex(0);

        JPanel btnPanel = new JPanel();
        JButton btnOk = new JButton("Так");
        JButton btnCancel = new JButton("Відміна");

        final String[] result = {null};

        btnOk.addActionListener(e -> {
            result[0] = list.getSelectedValue();
            dlg.dispose();
        });

        btnCancel.addActionListener(e -> dlg.dispose());

        btnPanel.add(btnOk);
        btnPanel.add(btnCancel);

        dlg.add(new JScrollPane(list), BorderLayout.CENTER);
        dlg.add(btnPanel, BorderLayout.SOUTH);

        dlg.setVisible(true);

        return result[0];
    }
}