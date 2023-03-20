package customer.ui;

import customer.model.Customer;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

public class CustomerForm {
    private ArrayList<Customer> customerList = new ArrayList<>();
    private DefaultTableModel tblModel;
    private boolean changeTxtSearch = false;

    public CustomerForm() {
        txtSearch.setMargin(new Insets(6, 10, 6, 10));
        txtSearch.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (!changeTxtSearch) {
                    txtSearch.setText("");
                    changeTxtSearch = true;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtSearch.getText().equals("")) {
                    txtSearch.setText("Tìm kiếm");
                    changeTxtSearch = false;
                } else {
                    changeTxtSearch = true;
                }
            }
        });

        initTable();
        initTableData();
    }

    private void initTable() {
        tblModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String[] columns = new String[]{"Mã khách hàng", "Tên khách hàng", "Email", "Thành viên"};
        tblModel.setColumnIdentifiers(columns);

        tblCustomers.setModel(tblModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < columns.length; i++) {
            tblCustomers.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private void initTableData() {
        tblModel.setRowCount(0);

        for (int i = 1; i <= 30; i++) {
            String tmp = "CUS00" + i;
            customerList.add((new Customer(tmp, "Thế Vũ", "Nam", "29-08-2003", "HCM", "aaa@gmail.com", "0123456789")));
        }

        for (Customer customer : customerList) {
            tblModel.addRow(new Object[]{customer.getCustomerId(), customer.getCustomerName(), customer.getCustomerEmail(), "Bạc"});
        }

        tblModel.fireTableDataChanged();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CustomerForm");
        frame.setContentPane(new CustomerForm().jpanel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
        frame.setSize(1000, 500);
        frame.getContentPane().requestFocusInWindow();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel jpanel1;
    private JTable tblCustomers;
    private JButton btnAddUser;
    private JTextField txtSearch;
    private JButton btnEdit;
    private JButton btnDelete;
}
