package lab1;

import javax.swing.*;
import java.awt.*;

public class Module1 {
    public static int showFirstDialog(JFrame parent) {
        JDialog dlg1 = new JDialog(parent, "Вікно 1 (B1)", true);
        dlg1.setLayout(new FlowLayout());
        dlg1.setSize(280, 120);
        dlg1.setLocationRelativeTo(parent);

        JButton btnNext = new JButton("Далі >");
        JButton btnCancel = new JButton("Відміна");

        final int[] action = {0}; 

        btnNext.addActionListener(e -> {
            action[0] = 1;
            dlg1.dispose();
        });

        btnCancel.addActionListener(e -> {
            action[0] = 0;
            dlg1.dispose();
        });

        dlg1.add(btnNext);
        dlg1.add(btnCancel);
        dlg1.setVisible(true);

        return action[0];
    }
}