package BookFault;

import Borrow.BorrowUI;
import Customer.model.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FaultInfor extends JFrame {
    private FaultUI faultUI;
    private boolean isEditMode;

    public FaultInfor(FaultUI faultUI, String id, String tenLoi, String heSo,String btnText) {

        this.faultUI = faultUI;
        isEditMode = btnText.equals("Lưu thông tin") ? true : false;
        setInfo(id, tenLoi,heSo);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isEditMode) {
                    editFaultInfo();
                } else {
                    addFault();
                }
            }
        });

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isEditMode) {
                    txtFaultId.setText("");
                }
                txtTenLoi.setText("");
                txtHeSo.setText("");
            }
        });


    }

    public void addFault() {
        if (!validateEmpty()) {
            return;
        }

        String id = txtFaultId.getText();

        FaultModel faultModel = BorrowUI.faultModel;

        if(faultModel.checkID(id)){
            JOptionPane.showMessageDialog(null, "Mã lỗi đã tồn tại", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String tenLoi = txtTenLoi.getText();
        double heSo = Double.parseDouble(txtHeSo.getText());

        faultModel.addData(id,tenLoi,heSo);
        JOptionPane.showMessageDialog(null, "Thêm lỗi thành công");
        dispose();
    }

    public void editFaultInfo() {
        if (!validateEmpty()) {
            return;
        }

        String id = txtFaultId.getText();
        String tenLoi = txtTenLoi.getText();
        double heSo = Double.parseDouble(txtHeSo.getText());

        FaultModel faultModel = BorrowUI.faultModel;

        faultModel.updateData(id,tenLoi,heSo);
        JOptionPane.showMessageDialog(null, "Cập nhập lỗi thành công");
        dispose();
    }

    public boolean validateEmpty() {
//        StringBuilder sb = new StringBuilder();
//
//        if (txtFaultId.getText().equals("")) {
//            sb.append("Mã khách hàng không được để trống \n");
//        }
//        if (txtTenLoi.getText().equals("")) {
//            sb.append("Tên khách hàng không được để trống \n");
//        }
//        if (txtHeSo.getText().equals("")) {
//            sb.append("Ngày sinh không được để trống \n");
//        }
//        if (txtCustomerAddress.getText().equals("")) {
//            sb.append("Địa chỉ không được để trống \n");
//        }
//        if (txtCustomerEmail.getText().equals("")) {
//            sb.append("Email không được để trống \n");
//        }
//        if (txtCustomerPhone.getText().equals("")) {
//            sb.append("Số điện thoại không được để trống \n");
//        }
//
//        if (sb.length() > 0) {
//            JOptionPane.showMessageDialog(null, sb.toString(), "Warning", JOptionPane.WARNING_MESSAGE);
//            return false;
//        }
        return true;
    }

//    public void showRegisAndExpireDate() {
//        lblRegisDate.setVisible(true);
//        lblExpireDate.setVisible(true);
//        txtRegisDate.setVisible(true);
//        txtExpireDate.setVisible(true);
//    }
//
//    public void hideRegisAndExpireDate() {
//        lblRegisDate.setVisible(false);
//        lblExpireDate.setVisible(false);
//        txtRegisDate.setVisible(false);
//        txtExpireDate.setVisible(false);
//    }

    public void setInset() {
        Insets inset = new Insets(4, 10, 4, 10);
        txtFaultId.setMargin(inset);
        txtTenLoi.setMargin(inset);
        txtHeSo.setMargin(inset);
    }

    public void setInfo(String id, String tenLoi, String heSo) {
        txtFaultId.setText(id);
        txtTenLoi.setText(tenLoi);
        txtHeSo.setText(heSo);
//        txtCustomerAddress.setText(address);
//        txtCustomerEmail.setText(email);
//        txtCustomerPhone.setText(phone);
//
//        int index = gender.equals("Nữ") ? 1 : 0;
//        cbxGender.setSelectedIndex(index);
//
//        for (int i = 0; i < cbxMembership.getItemCount(); i++) {
//            if (cbxMembership.getItemAt(i).equals(membership)) {
//                cbxMembership.setSelectedIndex(i);
//                break;
//            }
//        }
//
//        btnSave.setText(btnText);
        txtFaultId.setEnabled(!isEditMode);
    }

//    public void initMembershipValues() {
//        for (Membership membership : customerForm.getMembershipListInstance().getMembershipList()) {
//            cbxMembership.addItem(membership.getMembershipName());
//        }
//    }
//
//    public void initGenderValues() {
//        cbxGender.addItem("Nam");
//        cbxGender.addItem("Nữ");
//    }

    public JPanel getContentPane() {
        return panel1;
    }
    private JTextField txtFaultId;
    private JTextField txtTenLoi;
    private JTextField txtHeSo;
    private JButton btnSave;
    private JButton btnReset;
    private JPanel panel1;


}
