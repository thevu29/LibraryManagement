package NhanVien.form;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NhanVienC {
    private JComboBox gender;
    private JPanel main;
    private JButton resetButton;
    private JButton lưuButton;
    private JTextField ID;
    private JTextField name;
    private JTextField phone;
    private JTextField daywork;
    private JTextField brith;
    private JTextField password1;
    private JTextField address;
    private JTextField email;
    private JComboBox shift;
    private JComboBox position;
    private JComboBox wordplace;
    private JTextField salary;
    private JTextField password2;

    public JPanel getMain() {
        return main;
    }

    public NhanVienC() {
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name.setText("");
                ID.setText("");
                position.setSelectedIndex(0);
                daywork.setText("");
                address.setText("");
                wordplace.setSelectedIndex(0);
                brith.setText("");
                daywork.setText("");
                email.setText("");
                gender.setSelectedIndex(0);
                password1.setText("");
                password2.setText("");
                phone.setText("");
                shift.setSelectedIndex(0);
                salary.setText("");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("NhanVienC");
        frame.setContentPane(new NhanVienC().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
