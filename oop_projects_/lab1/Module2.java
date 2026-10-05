package lab1;

import javax.swing.*;
import java.awt.*;

public class Module2 {
    public static int showSecondDialog(JFrame parent) {
        JDialog dlg2 = new JDialog(parent, "Вікно 2 (B1)", true);
        dlg2.setLayout(new FlowLayout());
        dlg2.setSize(320, 120);
        dlg2.setLocationRelativeTo(parent);

        JButton btnBack = new JButton("< Назад");
        JButton btnOk = new JButton("Так");
        JButton btnCancel = new JButton("Відміна");

        final int[] action = {0}; 

        btnBack.addActionListener(e -> {
            action[0] = 1; 
            dlg2.dispose();
        });

        btnOk.addActionListener(e -> {
            action[0] = 0; 
            dlg2.dispose();
        });

        btnCancel.addActionListener(e -> {
            action[0] = 0; 
            dlg2.dispose();
        });

        dlg2.add(btnBack);
        dlg2.add(btnOk);
        dlg2.add(btnCancel);
        dlg2.setVisible(true);

        return action[0];
    }
}