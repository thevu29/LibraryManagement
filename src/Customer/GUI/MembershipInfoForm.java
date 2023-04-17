package Customer.GUI;

import Customer.DTO.MembershipType;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MembershipInfoForm extends JFrame {
    private CustomerForm customerForm;
    private boolean isEditMode;

    public MembershipInfoForm(CustomerForm customerForm, MembershipType membershipType, String btnText) {
        this.customerForm = customerForm;
        isEditMode = btnText.equals("Lưu thông tin") ? true : false;
        setInset();
        setInfo(membershipType, btnText);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isEditMode) {
                    updateMembership();
                } else {
                    addMembership();
                }
            }
        });

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isEditMode) {
                    txtMembershipName.setText("");
                }
                txtDiscount.setText("");
            }
        });
    }

    public void addMembership() {
        String name = txtMembershipName.getText();
        int discount = Integer.parseInt(txtDiscount.getText());

        MembershipType membership = new MembershipType(name, discount, false);

        if (customerForm.getMembershipTypeBUS().validateAdd(membership)) {
            dispose();
            customerForm.getMembershipTypeBUS().renderToTable(customerForm.getTblMembershipTypeModelModel());
        }
    }

    public void updateMembership() {
        String name = txtMembershipName.getText();
        int discount = Integer.parseInt(txtDiscount.getText());

        MembershipType mem = new MembershipType(name, discount, false);
        if (customerForm.getMembershipTypeBUS().validateUpdate(mem)) {
            dispose();
            customerForm.getMembershipTypeBUS().renderToTable(customerForm.getTblMembershipTypeModelModel());
        }
    }

    public void setInset() {
        Insets inset = new Insets(4, 10, 4, 10);
        txtMembershipName.setMargin(inset);
        txtDiscount.setMargin(inset);
    }

    public void setInfo(MembershipType membershipType, String btnText) {
        txtMembershipName.setText(membershipType.getMembershipTypeName());
        txtDiscount.setText(membershipType.getDiscount() + "");

        btnSave.setText(btnText);
        txtMembershipName.setEnabled(!isEditMode);
    }

    public JPanel getContentPane() {
        return mainPanel;
    }

    private JPanel mainPanel;
    private JTextField txtMembershipName;
    private JTextField txtDiscount;
    private JButton btnSave;
    private JButton btnReset;
}