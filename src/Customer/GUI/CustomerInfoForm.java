package Customer.GUI;

import Customer.BUS.CustomerBUS;
import Customer.DTO.Customer;
import Customer.DTO.MembershipType;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class CustomerInfoForm extends JFrame {
    private CustomerForm customerForm;
    private boolean isEditMode;
    private CustomerBUS cusBus = new CustomerBUS();

    public CustomerInfoForm(CustomerForm customerForm, Customer customer, String btnText) {
        this.customerForm = customerForm;
        isEditMode = btnText.equals("Lưu thông tin") ? true : false;
        initGenderValues();
        initMembershipValues();
        setInset();
        setInfo(customer, btnText);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isEditMode) {
                    updateCustomerInfo();
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
        String id = txtCustomerId.getText();
        String name = txtCustomerName.getText();
        String dob = reverseDate(txtCustomerDOB.getText());
        String address = txtCustomerAddress.getText();
        String cccd = txtCCCD.getText();
        String email = txtCustomerEmail.getText();
        String phone = txtCustomerPhone.getText();
        String gender = cbxGender.getSelectedIndex() == 0 ? "Nam" : "Nữ";
        String membership = cbxMembership.getItemAt(cbxMembership.getSelectedIndex()).toString();
        String registrationDate = reverseDate(txtRegisDate.getText());
        String expirationDate = reverseDate(txtExpireDate.getText());

        Customer customer = new Customer(id, name, dob, address, cccd, email, phone, gender, membership, registrationDate, expirationDate, false);
        if (cusBus.validateAdd(customer)) {
            dispose();
            customerForm.getCustomerBUS().renderToTable(customerForm.getTblCustomerModel());
        }
    }

    public void updateCustomerInfo() {
        String id = txtCustomerId.getText();
        String name = txtCustomerName.getText();
        String dob = reverseDate(txtCustomerDOB.getText());
        String address = txtCustomerAddress.getText();
        String cccd = txtCCCD.getText();
        String email = txtCustomerEmail.getText();
        String phone = txtCustomerPhone.getText();
        String gender = cbxGender.getSelectedIndex() == 0 ? "Nam" : "Nữ";
        String membership = cbxMembership.getItemAt(cbxMembership.getSelectedIndex()).toString();
        String registrationDate = reverseDate(txtRegisDate.getText());
        String expirationDate = reverseDate(txtExpireDate.getText());

        Customer customer = new Customer(id, name, dob, address, cccd, email, phone, gender, membership, registrationDate, expirationDate, false);
        if (cusBus.validateUpdate(customer)) {
            dispose();
            customerForm.getCustomerBUS().renderToTable(customerForm.getTblCustomerModel());
        }
    }

    public String reverseDate(String date) {
        if (date.equals("")) {
            return "";
        }

        String newDate = "";
        String[] arr = date.split("-");
        newDate = arr[2] + "-" + arr[1] + "-" + arr[0];
        return newDate;
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
        txtCCCD.setMargin(inset);
        txtCustomerAddress.setMargin(inset);
        txtCustomerEmail.setMargin(inset);
        txtCustomerPhone.setMargin(inset);
        txtRegisDate.setMargin(inset);
        txtExpireDate.setMargin(inset);
    }

    public void setInfo(Customer customer, String btnText) {
        if (!isEditMode) {
            int length = cusBus.getCustomerListLength() + 1;
            String id = String.format("%03d", length);
            id = "CUS" + id;
            txtCustomerId.setText(id);
        } else {
            txtCustomerId.setText(customer.getCustomerId());
        }

        txtCustomerName.setText(customer.getCustomerName());
        txtCCCD.setText(customer.getCccd());
        String dob = reverseDate(customer.getCustomerDOB());
        txtCustomerDOB.setText(dob);
        txtCustomerAddress.setText(customer.getCustomerAddress());
        txtCustomerEmail.setText(customer.getCustomerEmail());
        txtCustomerPhone.setText(customer.getCustomerPhone());

        int index = customer.getCustomerGender().equals("Nữ") ? 1 : 0;
        cbxGender.setSelectedIndex(index);

        cbxMembership.setSelectedItem(customer.getMembership());
        if (customer.getMembership().equals("Bình thường") || customer.getMembership().equals("")) {
            hideRegisAndExpireDate();
        } else {
            showRegisAndExpireDate();
            String regisDate = reverseDate(customer.getRegistrationDate());
            String expireDate = reverseDate(customer.getExpirationDate());
            txtRegisDate.setText(regisDate);
            txtExpireDate.setText(expireDate);
        }

        btnSave.setText(btnText);
    }

    public void initMembershipValues() {
        cbxMembership.addItem("Bình thường");
        for (MembershipType membership : customerForm.getMembershipTypeBUS().getMembershipTypeList()) {
            cbxMembership.addItem(membership.getMembershipTypeName());
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
    private JTextField txtCCCD;
}