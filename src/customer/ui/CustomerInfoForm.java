package customer.ui;

import javax.swing.*;
import java.awt.*;
public class CustomerInfoForm {
    public CustomerInfoForm() {
        Insets inset = new Insets(4, 10, 4, 10);
        txtCustomerId.setMargin(inset);
        txtCustomerName.setMargin(inset);
        txtCustomerDOB.setMargin(inset);
        txtCustomerGender.setMargin(inset);
        txtCustomerAddress.setMargin(inset);
        txtCustomerEmail.setMargin(inset);
        txtCustomerPhone.setMargin(inset);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CustomerInfoForm");
        frame.setContentPane(new CustomerInfoForm().jpanel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel jpanel1;
    private JTextField txtCustomerId;
    private JTextField txtCustomerName;
    private JTextField txtCustomerDOB;
    private JTextField txtCustomerGender;
    private JTextField txtCustomerAddress;
    private JTextField txtCustomerEmail;
    private JTextField txtCustomerPhone;
    private JButton btnSave;
    private JButton btnReset;
}
