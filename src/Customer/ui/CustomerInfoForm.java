package Customer.ui;

import Customer.model.Customer;
import Customer.model.Membership;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerInfoForm extends JFrame {
    private CustomerForm customerForm;
    private boolean isEditMode;

    public CustomerInfoForm(CustomerForm customerForm, String id, String name, String dob, String gender, String address, String email,
                                String phone, String membership, String btnText) {

        this.customerForm = customerForm;
        isEditMode = btnText.equals("Lưu thông tin") ? true : false;
        initGenderValues();
        initMembershipValues();
        setInset();
        setInfo(id, name, dob, gender, address, email, phone, membership, btnText);
        hideRegisAndExpireDate();

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isEditMode) {
                    editCustomerInfo();
                } else {
                    addCustomer();
                }
            }
        });

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isEditMode) {
                    txtCustomerId.setText("");
                }
                txtCustomerName.setText("");
                txtCustomerDOB.setText("");
                cbxGender.setSelectedIndex(0);
                txtCustomerAddress.setText("");
                txtCustomerEmail.setText("");
                txtCustomerPhone.setText("");
                cbxMembership.setSelectedIndex(0);
            }
        });

        cbxMembership.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = cbxMembership.getSelectedItem().toString();
                if (selectedItem.equals("Bình thường")) {
                    hideRegisAndExpireDate();
                } else {
                    showRegisAndExpireDate();
                }
            }
        });
    }

    public void addCustomer() {
        if (!validateEmpty()) {
            return;
        }

        String id = txtCustomerId.getText();
        for (Customer customer : customerForm.getCustomerListInstance().getCustomerList()) {
            if (customer.getCustomerId().equals(id)) {
                JOptionPane.showMessageDialog(null, "Mã khách hàng đã tồn tại", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        String name = txtCustomerName.getText();
        String dob = txtCustomerDOB.getText();
        String address = txtCustomerAddress.getText();
        String email = txtCustomerEmail.getText();
        String phone = txtCustomerPhone.getText();
        String gender = cbxGender.getSelectedIndex() == 0 ? "Nam" : "Nữ";
        String membership = cbxMembership.getItemAt(cbxMembership.getSelectedIndex()).toString();

        Customer customer = new Customer(id, name, dob, gender, address, email, phone, membership);
        customerForm.getCustomerListInstance().addCustomer(customer);

        JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công");
        dispose();
        customerForm.getCustomerListInstance().renderToTable(customerForm.getTblCustomerModel());
    }

    public void editCustomerInfo() {
        if (!validateEmpty()) {
            return;
        }

        String id = txtCustomerId.getText();
        String name = txtCustomerName.getText();
        String dob = txtCustomerDOB.getText();
        String address = txtCustomerAddress.getText();
        String email = txtCustomerEmail.getText();
        String phone = txtCustomerPhone.getText();
        String gender = cbxGender.getSelectedIndex() == 0 ? "Nam" : "Nữ";
        String membership = cbxMembership.getItemAt(cbxMembership.getSelectedIndex()).toString();

        Customer cus = new Customer(id, name, dob, address, email, phone, gender, membership);
        customerForm.getCustomerListInstance().editCustomer(cus);

        JOptionPane.showMessageDialog(null, "Sửa thông tin khách hàng thành công");
        dispose();
        customerForm.getCustomerListInstance().renderToTable(customerForm.getTblCustomerModel());
    }

    public boolean validateEmpty() {
        StringBuilder sb = new StringBuilder();

        if (txtCustomerId.getText().equals("")) {
            sb.append("Mã khách hàng không được để trống \n");
        }
        if (txtCustomerName.getText().equals("")) {
            sb.append("Tên khách hàng không được để trống \n");
        }
        if (txtCustomerDOB.getText().equals("")) {
            sb.append("Ngày sinh không được để trống \n");
        }
        if (txtCustomerAddress.getText().equals("")) {
            sb.append("Địa chỉ không được để trống \n");
        }
        if (txtCustomerEmail.getText().equals("")) {
            sb.append("Email không được để trống \n");
        }
        if (txtCustomerPhone.getText().equals("")) {
            sb.append("Số điện thoại không được để trống \n");
        }

        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(null, sb.toString(), "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public void showRegisAndExpireDate() {
        lblRegisDate.setVisible(true);
        lblExpireDate.setVisible(true);
        txtRegisDate.setVisible(true);
        txtExpireDate.setVisible(true);
    }

    public void hideRegisAndExpireDate() {
        lblRegisDate.setVisible(false);
        lblExpireDate.setVisible(false);
        txtRegisDate.setVisible(false);
        txtExpireDate.setVisible(false);
    }

    public void setInset() {
        Insets inset = new Insets(4, 10, 4, 10);
        txtCustomerId.setMargin(inset);
        txtCustomerName.setMargin(inset);
        txtCustomerDOB.setMargin(inset);
        txtCustomerAddress.setMargin(inset);
        txtCustomerEmail.setMargin(inset);
        txtCustomerPhone.setMargin(inset);
        txtRegisDate.setMargin(inset);
        txtExpireDate.setMargin(inset);
    }

    public void setInfo(String id, String name, String dob, String gender, String address, String email, String phone, String membership,
                        String btnText) {
        txtCustomerId.setText(id);
        txtCustomerName.setText(name);
        txtCustomerDOB.setText(dob);
        txtCustomerAddress.setText(address);
        txtCustomerEmail.setText(email);
        txtCustomerPhone.setText(phone);

        int index = gender.equals("Nữ") ? 1 : 0;
        cbxGender.setSelectedIndex(index);

        for (int i = 0; i < cbxMembership.getItemCount(); i++) {
            if (cbxMembership.getItemAt(i).equals(membership)) {
                cbxMembership.setSelectedIndex(i);
                break;
            }
        }

        btnSave.setText(btnText);
        txtCustomerId.setEnabled(!isEditMode);
    }

    public void initMembershipValues() {
        for (Membership membership : customerForm.getMembershipListInstance().getMembershipList()) {
            cbxMembership.addItem(membership.getMembershipName());
        }
    }

    public void initGenderValues() {
        cbxGender.addItem("Nam");
        cbxGender.addItem("Nữ");
    }

    public JPanel getContentPane() {
        return jpanel1;
    }

    private JPanel jpanel1;
    private JTextField txtCustomerId;
    private JTextField txtCustomerName;
    private JTextField txtCustomerDOB;
    private JTextField txtCustomerAddress;
    private JTextField txtCustomerEmail;
    private JTextField txtCustomerPhone;
    private JButton btnSave;
    private JButton btnReset;
    private JComboBox cbxGender;
    private JComboBox cbxMembership;
    private JTextField txtRegisDate;
    private JTextField txtExpireDate;
    private JLabel lblRegisDate;
    private JLabel lblExpireDate;
}