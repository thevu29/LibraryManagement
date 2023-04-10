package Customer.model;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class CustomerList {
    private ArrayList<Customer> customerList = new ArrayList<>();

    public CustomerList() {
        for (int i = 1; i <= 5; i++) {
            String tmp = "CUS00" + i;
            customerList.add((new Customer(tmp, "Thế Vũ", "29-08-2003", "Nam", "HCM", "aaa@gmail.com", "0123456789", "Bình thường")));
        }
    }

    public CustomerList(ArrayList<Customer> customerList) {
        this.customerList = customerList;
    }

    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    public boolean deleteCustomer(String id) {
        for (Customer customer : customerList) {
            if (customer.getCustomerId().equals(id)) {
                customerList.remove(customer);
                return true;
            }
        }
        return false;
    }

    public boolean editCustomer(Customer cus) {
        for (Customer customer : customerList) {
            if (customer.getCustomerId().equals(cus.getCustomerId())) {
                customer.setCustomerName(cus.getCustomerName());
                customer.setCustomerDOB(cus.getCustomerDOB());
                customer.setCustomerAddress(cus.getCustomerAddress());
                customer.setCustomerEmail(cus.getCustomerEmail());
                customer.setCustomerPhone(cus.getCustomerPhone());
                customer.setCustomerGender(cus.getCustomerGender());
                customer.setMembership(cus.getMembership());
                return true;
            }
        }
        return false;
    }

    public void renderToTable(DefaultTableModel tblModel) {
        tblModel.setRowCount(0);

        for (Customer customer : customerList) {
            tblModel.addRow(new Object[]{customer.getCustomerId(), customer.getCustomerName(), customer.getCustomerEmail(),
                    customer.getMembership()});
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