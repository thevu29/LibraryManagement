package NhanVien.form;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NhanVienC extends JFrame {
    public NhanVienC() {
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name.setText("");
                ID.setText("");
                position.setSelectedIndex(0);
                daywork.setText("");
                address.setText("");
                workplace.setSelectedIndex(0);
                birth.setText("");
                daywork.setText("");
                email.setText("");
                gender.setSelectedIndex(0);
                password.setText("");
                phone.setText("");
                shift.setSelectedIndex(0);
                salary.setText("");
            }
        });
    }

    public JPanel getMain() {
        return main;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("NhanVienC");
        frame.setContentPane(new NhanVienC().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JTextField ID;
    private JTextField name;
    private JTextField address;
    private JComboBox gender;
    private JComboBox shift;
    private JButton lưuButton;
    private JButton resetButton;
    private JTextField phone;
    private JTextField daywork;
    private JTextField birth;
    private JTextField password;
    private JTextField email;
    private JComboBox position;
    private JComboBox workplace;
    private JPanel main;
    private JTextField salary;
}
