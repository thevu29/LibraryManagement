package customer.ui;

import Utils.ComboBoxAutoSuggest.AutoSuggestComboBox;
import customer.model.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerInfoForm extends JFrame {
    private CustomerForm customerForm;
    private boolean isEditMode;

    public CustomerInfoForm(CustomerForm customerForm, String id, String name, String dob, String gender, String address, String email, String phone, String btnText) {
        this.customerForm = customerForm;
        isEditMode = btnText.equals("Lưu thông tin") ? true : false;
        initGenderValues();
        setInset();
        setInfo(id, name, dob, gender, address, email, phone, btnText);

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
    }

        public void addCustomer() {
    //        if (!validateEmpty()) {
    //            return;
    //        }
    //
    //        String id = txtCustomerId.getText();
    //
    //        for (Customer customer : customerForm.getCustomerList()) {
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

    //        Customer customer = new Customer(id, name, dob, gender, address, email, phone);
            Customer customer = new Customer("CUS011", "Minh Nam", "Nam", "29-08-2003", "HCM", "bbb@gmail.com", "0123456789");
            customerForm.getCustomerList().add(customer);

            JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công");
            dispose();
            customerForm.renderToTable();
            customerForm.initComboBox();
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

        for (Customer customer : customerForm.getCustomerList()) {
            if (customer.getCustomerId().equals(id)) {
                customer.setCustomerName(name);
                customer.setCustomerDOB(dob);
                customer.setCustomerAddress(address);
                customer.setCustomerEmail(email);
                customer.setCustomerPhone(phone);
                customer.setCustomerGender(gender);

                JOptionPane.showMessageDialog(null, "Sửa thông tin khách hàng thành công");
                dispose();
                customerForm.renderToTable();
                return;
            }
        }
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

    public void setInset() {
        Insets inset = new Insets(4, 10, 4, 10);
        txtCustomerId.setMargin(inset);
        txtCustomerName.setMargin(inset);
        txtCustomerDOB.setMargin(inset);
        txtCustomerAddress.setMargin(inset);
        txtCustomerEmail.setMargin(inset);
        txtCustomerPhone.setMargin(inset);
    }

    public void setInfo(String id, String name, String dob, String gender, String address, String email, String phone, String btnText) {
        txtCustomerId.setText(id);
        txtCustomerName.setText(name);
        txtCustomerDOB.setText(dob);
        txtCustomerAddress.setText(address);
        txtCustomerEmail.setText(email);
        txtCustomerPhone.setText(phone);

        int i = gender.equals("Nữ") ? 1 : 0;
        cbxGender.setSelectedIndex(i);

        btnSave.setText(btnText);
        txtCustomerId.setEnabled(!isEditMode);
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
}
