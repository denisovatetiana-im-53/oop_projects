package lab1;
import javax.swing.*;
import java.awt.*;

public class Module1 {

    private static int currentStep = 1;

    public static void runDialogs(JFrame parent) {
        currentStep = 1;
        while (currentStep != 0) {
            if (currentStep == 1) {
                showFirstDialog(parent);
            } else if (currentStep == 2) {
                showSecondDialog(parent);
            }
        }
    }

    private static void showFirstDialog(JFrame parent) {
        JDialog dlg1 = new JDialog(parent, "Вікно 1 (B1)", true);
        dlg1.setLayout(new FlowLayout());
        dlg1.setSize(280, 120);
        dlg1.setLocationRelativeTo(parent);

        JButton btnNext = new JButton("Далі >");
        JButton btnCancel = new JButton("Відміна");

        btnNext.addActionListener(e -> {
            currentStep = 2;
            dlg1.dispose();
        });

        btnCancel.addActionListener(e -> {
            currentStep = 0;
            dlg1.dispose();
        });

        dlg1.add(btnNext);
        dlg1.add(btnCancel);
        dlg1.setVisible(true);
    }

    private static void showSecondDialog(JFrame parent) {
        JDialog dlg2 = new JDialog(parent, "Вікно 2 (B1)", true);
        dlg2.setLayout(new FlowLayout());
        dlg2.setSize(320, 120);
        dlg2.setLocationRelativeTo(parent);

        JButton btnBack = new JButton("< Назад");
        JButton btnOk = new JButton("Так");
        JButton btnCancel = new JButton("Відміна");

        btnBack.addActionListener(e -> {
            currentStep = 1;
            dlg2.dispose();
        });

        btnOk.addActionListener(e -> {
            currentStep = 0;
            dlg2.dispose();
        });

        btnCancel.addActionListener(e -> {
            currentStep = 0;
            dlg2.dispose();
        });

        dlg2.add(btnBack);
        dlg2.add(btnOk);
        dlg2.add(btnCancel);
        dlg2.setVisible(true);
    }
}