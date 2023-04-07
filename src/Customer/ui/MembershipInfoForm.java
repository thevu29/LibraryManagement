package Customer.ui;

import Customer.model.Membership;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MembershipInfoForm extends JFrame {
    private CustomerForm customerForm;
    private boolean isEditMode;

    public MembershipInfoForm(CustomerForm customerForm, String id, String name, float discount, String btnText) {
        this.customerForm = customerForm;
        isEditMode = btnText.equals("Lưu thông tin") ? true : false;
        setInset();
        setInfo(id, name, discount, btnText);

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
                    txtMembershipId.setText("");
                }
                txtMembershipName.setText("");
                txtDiscount.setText("");
            }
        });
    }

    public void addMembership() {
        if (!validateEmpty()) {
            return;
        }

        String id = txtMembershipId.getText();
        for (Membership membership : customerForm.getMembershipListInstance().getMembershipList()) {
            if (membership.getMembershipId().equals(id)) {
                JOptionPane.showMessageDialog(null, "Mã loại thành viên đã tồn tại", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        String name = txtMembershipName.getText();
        float discount = Float.parseFloat(txtDiscount.getText());

        Membership membership = new Membership(id, name, discount);
        customerForm.getMembershipListInstance().addMembership(membership);

        JOptionPane.showMessageDialog(null, "Thêm loại thành viên thành công");
        dispose();
        customerForm.getMembershipListInstance().renderToTable(customerForm.getTblMembershipModelModel());
    }

    public void editMembership() {
        if (!validateEmpty()) {
            return;
        }

        String id = txtMembershipId.getText();
        String name = txtMembershipName.getText();
        float discount = Float.parseFloat(txtDiscount.getText());

        Membership mem = new Membership(id, name, discount);
        customerForm.getMembershipListInstance().editMembership(mem);

        JOptionPane.showMessageDialog(null, "Sửa thông tin loại thành viên thành công");
        dispose();
        customerForm.getMembershipListInstance().renderToTable(customerForm.getTblMembershipModelModel());
    }

    public boolean validateEmpty() {
        StringBuilder sb = new StringBuilder();

        if (txtMembershipId.getText().equals("")) {
            sb.append("Mã loại thành viên không được để trống \n");
        }
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
        txtMembershipId.setMargin(inset);
        txtMembershipName.setMargin(inset);
        txtDiscount.setMargin(inset);
    }

    public void setInfo(String id, String name, float discount, String btnText) {
        txtMembershipId.setText(id);
        txtMembershipName.setText(name);

        if (!isEditMode) {
            txtDiscount.setText("");
        } else {
            txtDiscount.setText(discount + "");
        }

        btnSave.setText(btnText);
        txtMembershipId.setEnabled(!isEditMode);
    }

    public JPanel getContentPane() {
        return mainPanel;
    }

    private JPanel mainPanel;
    private JTextField txtMembershipId;
    private JTextField txtMembershipName;
    private JTextField txtDiscount;
    private JButton btnSave;
    private JButton btnReset;
}