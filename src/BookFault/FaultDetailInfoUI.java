package BookFault;

import Customer.model.Customer;
import Customer.model.Membership;
import Customer.ui.CustomerForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FaultDetailInfoUI extends JFrame {
    private JButton btnSave;
    private JButton btnReset;
    private JTextField txtMaLoiChiTiet;
    private JComboBox cbxMaMuonChiTiet;
    private JComboBox cbxMaDocGia;
    private JComboBox cbxMaSach;
    private JComboBox cbxTenLoi;
    private JPanel panel1;
    private JTextField txtSoLuong;

    private FaultDetailUI faultDetailUI;
    private boolean isEditMode;

    public FaultDetailInfoUI(FaultDetailUI faultDetailUI, String id, String btnText) {

        this.faultDetailUI = faultDetailUI;
        isEditMode = btnText.equals("Lưu thông tin") ? true : false;

        setInset();
        setInfo(id,  btnText);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isEditMode) {
                    editFaultDetail();
                } else {
                    addFaultDetail();
                }
            }
        });

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isEditMode) {
                    txtMaLoiChiTiet.setText("");
                }
            }
        });


    }

    public void addFaultDetail() {
        if (!validateEmpty()) {
            return;
        }

//        String id = txtCustomerId.getText();
//        for (Customer customer : customerForm.getCustomerListInstance().getCustomerList()) {
//            if (customer.getCustomerId().equals(id)) {
//                JOptionPane.showMessageDialog(null, "Mã khách hàng đã tồn tại", "Warning", JOptionPane.WARNING_MESSAGE);
//                return;
//            }
//        }
//
//        String name = txtCustomerName.getText();
//        String dob = txtCustomerDOB.getText();
//        String address = txtCustomerAddress.getText();
//        String email = txtCustomerEmail.getText();
//        String phone = txtCustomerPhone.getText();
//        String gender = cbxGender.getSelectedIndex() == 0 ? "Nam" : "Nữ";
//        String membership = cbxMembership.getItemAt(cbxMembership.getSelectedIndex()).toString();
//
//        Customer customer = new Customer(id, name, dob, gender, address, email, phone, membership);
//        customerForm.getCustomerListInstance().addCustomer(customer);
//
//        JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công");
//        dispose();
//        customerForm.getCustomerListInstance().renderToTable(customerForm.getTblCustomerModel());
    }

    public void editFaultDetail() {
        if (!validateEmpty()) {
            return;
        }

//        String id = txtCustomerId.getText();
//        String name = txtCustomerName.getText();
//        String dob = txtCustomerDOB.getText();
//        String address = txtCustomerAddress.getText();
//        String email = txtCustomerEmail.getText();
//        String phone = txtCustomerPhone.getText();
//        String gender = cbxGender.getSelectedIndex() == 0 ? "Nam" : "Nữ";
//        String membership = cbxMembership.getItemAt(cbxMembership.getSelectedIndex()).toString();
//
//        Customer cus = new Customer(id, name, dob, gender, address, email, phone, membership);
//        customerForm.getCustomerListInstance().editCustomer(cus);
//
//        JOptionPane.showMessageDialog(null, "Sửa thông tin khách hàng thành công");
//        dispose();
//        customerForm.getCustomerListInstance().renderToTable(customerForm.getTblCustomerModel());
    }

    public boolean validateEmpty() {
        StringBuilder sb = new StringBuilder();

//        if (txtCustomerId.getText().equals("")) {
//            sb.append("Mã khách hàng không được để trống \n");
//        }
//        if (txtCustomerName.getText().equals("")) {
//            sb.append("Tên khách hàng không được để trống \n");
//        }
//        if (txtCustomerDOB.getText().equals("")) {
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
//
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
    }

    public void setInfo(String id, String btnText) {
        txtMaLoiChiTiet.setText(id);


        btnSave.setText(btnText);
        txtMaLoiChiTiet.setEnabled(!isEditMode);
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
}
