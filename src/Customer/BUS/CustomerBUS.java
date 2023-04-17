package Customer.BUS;

import Customer.DAO.CustomerDAO;
import Customer.DTO.Customer;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class CustomerBUS {
    private ArrayList<Customer> customerList;
    private CustomerDAO cusDAO = new CustomerDAO();

    public CustomerBUS() {
        customerList = cusDAO.createList();
    }

    public boolean validateEmpty(String id, String name, String dob, String address, String email, String phone, String membership,
                                 String registrationDate, String expirationDate) {
        StringBuilder sb = new StringBuilder();

        if (id.equals("")) {
            sb.append("Mã khách hàng không được để trống \n");
        }
        if (name.equals("")) {
            sb.append("Tên khách hàng không được để trống \n");
        }
        if (dob.equals("")) {
            sb.append("Ngày sinh không được để trống \n");
        }
        if (address.equals("")) {
            sb.append("Địa chỉ không được để trống \n");
        }
        if (email.equals("")) {
            sb.append("Email không được để trống \n");
        }
        if (phone.equals("")) {
            sb.append("Số điện thoại không được để trống \n");
        }

        if (!membership.equals("Bình thường")) {
            if (registrationDate.equals("")) {
                sb.append("Ngày đăng ký không được để trống \n");
            }
            if (expirationDate.equals("")) {
                sb.append("Ngày hết hạn không được để trosnog \n");
            }
        }

        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(null, sb.toString(), "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean validateAdd(Customer customer, String id, String name, String dob, String address, String email, String phone,
                               String membership, String registrationDate, String expirationDate) {
        if (!validateEmpty(id, name, dob, address, email, phone, membership, registrationDate, expirationDate)) {
            return false;
        }

        for (Customer cus : customerList) {
            if (cus.getCustomerId().equals(id)) {
                JOptionPane.showMessageDialog(null, "Mã khách hàng đã tồn tại", "Warning", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }

        if(cusDAO.addCustomer(customer)) {
            JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công");
            return true;
        }
        return false;
    }

    public boolean validateDelete(String id) {
        cusDAO.deleteCustomer(id);
        customerList = cusDAO.createList();
        return true;
    }

    public boolean validateUpdate(Customer customer, String id, String name, String dob, String address, String email, String phone,
                                  String membership, String registrationDate, String expirationDate) {
        if (!validateEmpty(id, name, dob, address, email, phone, membership, registrationDate, expirationDate)) {
            return false;
        }

        cusDAO.updateCustomer(customer);
        this.customerList = cusDAO.createList();
        return true;
    }

    public void renderToTable(DefaultTableModel tblModel) {
        tblModel.setRowCount(0);

        customerList = cusDAO.createList();
        for (Customer customer : customerList) {
            if (!customer.isDeleted()) {
                tblModel.addRow(new Object[]{customer.getCustomerId(), customer.getCustomerName(), customer.getCustomerEmail(),
                        customer.getMembership()});
            }
        }

        tblModel.fireTableDataChanged();
    }

    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(ArrayList<Customer> customerList) {
        this.customerList = customerList;
    }

    public int getCustomerListLength() {
        return customerList.size();
    }
}