package Customer.GUI;

import Customer.DTO.MembershipType;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MembershipInfoForm extends JFrame {
    private CustomerForm customerForm;
    private boolean isEditMode;

    public MembershipInfoForm(CustomerForm customerForm, String name, float discount, String btnText) {
        this.customerForm = customerForm;
        isEditMode = btnText.equals("Lưu thông tin") ? true : false;
        setInset();
        setInfo(name, discount, btnText);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isEditMode) {
                    editMembership();
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
        if (!validateEmpty()) {
            return;
        }

        String name = txtMembershipName.getText();
        for (MembershipType membership : customerForm.getMembershipListInstance().getMembershipList()) {
            if (membership.getMembershipName().equals(name)) {
                JOptionPane.showMessageDialog(null, "Tên loại thành viên đã tồn tại", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        float discount = Float.parseFloat(txtDiscount.getText());

        MembershipType membership = new MembershipType(name, discount, false);
        customerForm.getMembershipListInstance().addMembership(membership);

        JOptionPane.showMessageDialog(null, "Thêm loại thành viên thành công");
        dispose();
        customerForm.getMembershipListInstance().renderToTable(customerForm.getTblMembershipModelModel());
    }

    public void editMembership() {
        if (!validateEmpty()) {
            return;
        }

        String name = txtMembershipName.getText();
        float discount = Float.parseFloat(txtDiscount.getText());

        MembershipType mem = new MembershipType(name, discount, false);
        customerForm.getMembershipListInstance().editMembership(mem);

        JOptionPane.showMessageDialog(null, "Sửa thông tin loại thành viên thành công");
        dispose();
        customerForm.getMembershipListInstance().renderToTable(customerForm.getTblMembershipModelModel());
    }

    public boolean validateEmpty() {
        StringBuilder sb = new StringBuilder();

        if (txtMembershipName.getText().equals("")) {
            sb.append("Tên loại thành viên không được để trống \n");
        }
        if (txtDiscount.getText().equals("")) {
            sb.append("Giảm giá không được để trống \n");
        }

        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(null, sb.toString(), "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public void setInset() {
        Insets inset = new Insets(4, 10, 4, 10);
        txtMembershipName.setMargin(inset);
        txtDiscount.setMargin(inset);
    }

    public void setInfo(String name, float discount, String btnText) {
        txtMembershipName.setText(name);

        if (!isEditMode) {
            txtDiscount.setText("");
        } else {
            txtDiscount.setText(discount + "");
        }

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