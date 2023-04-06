package Customer.ui;

import Customer.model.CustomerList;
import Customer.model.Membership;
import Utils.ComboBoxAutoSuggest.AutoSuggestComboBox;
import Customer.model.Customer;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CustomerForm {
    private CustomerList customerList = new CustomerList();
    private DefaultTableModel tblCustomerModel;
    private TableRowSorter<DefaultTableModel> customerSorter;
    private JTextField txtCusId;
    private JTextField txtCusName;
    private JTextField txtCusEmail;

    private ArrayList<Membership> membershipList;
    private DefaultTableModel tblMembershipModel;
    private TableRowSorter<DefaultTableModel> membershipSorter;
    private JTextField txtMemberId;
    private JTextField txtMemberName;
    private JTextField txtMemberEmail;

    public CustomerForm() {
        handleCustomer();
    }

    public void handleMembership() {

    }

    public void initMembershipTable() {
        tblMembershipModel = new DefaultTableModel();

    }

    public void handleCustomer() {
        initCustomerTable();
        customerList.renderToCustomerTable(tblCustomerModel);

        btnAddCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCustomerInfo("", "", "", "", "", "", "", "", "Thêm khách hàng");
            }
        });

        btnDeleteCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblCustomers.getSelectedRow();
                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng muốn xóa", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa khách hàng này không?", "Question", JOptionPane.YES_NO_OPTION);
                if (choice != JOptionPane.YES_OPTION) {
                    return;
                }

                String id = tblCustomers.getValueAt(selectedRow, 0).toString();
                for (Customer customer : customerList.getCustomerList()) {
                    if (customer.getCustomerId().equals(id)) {
                        customerList.deleteCustomer(customer.getCustomerId());
                        JOptionPane.showMessageDialog(null, "Xóa khách hàng thành công");
                        customerList.renderToCustomerTable(tblCustomerModel);
                        return;
                    }
                }
            }
        });

        btnEditCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblCustomers.getSelectedRow();
                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng muốn sửa thông tin", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String id = tblCustomers.getValueAt(selectedRow, 0).toString();
                for (Customer customer : customerList.getCustomerList()) {
                    if (customer.getCustomerId().equals(id)) {
                        String name = customer.getCustomerName();
                        String dob = customer.getCustomerDOB();
                        String gender = customer.getCustomerGender();
                        String address = customer.getCustomerAddress();
                        String email = customer.getCustomerEmail();
                        String phone = customer.getCustomerPhone();
                        String membership = customer.getMembership();

                        showCustomerInfo(id, name, dob, gender, address, email, phone, membership, "Lưu thông tin");
                        return;
                    }
                }
            }
        });

        txtCusId = AutoSuggestComboBox.createWithDelete(cbxCustomerId, 0, this::initCustomerSuggestion, btnDeleteCusId);
        txtCusName = AutoSuggestComboBox.createWithDelete(cbxCustomerName, 1, this::initCustomerSuggestion, btnDeleteCusName);
        txtCusEmail = AutoSuggestComboBox.createWithDelete(cbxCustomerEmail, 2, this::initCustomerSuggestion, btnDeleteCusEmail);

        btnFilterCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterCustomer();
            }
        });

        btnResetCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtCusId.setText("");
                txtCusName.setText("");
                txtCusEmail.setText("");
                customerSorter.setRowFilter(null);
            }
        });
    }

    public ArrayList<String> initCustomerSuggestion(int col) {
        ArrayList<String> suggestion = new ArrayList<>();
            for (Customer customer : customerList.getCustomerList()) {
            if (col == 0) {
                suggestion.add(customer.getCustomerId());
            } else if (col == 1) {
                suggestion.add(customer.getCustomerName());
            } else if (col == 2) {
                suggestion.add(customer.getCustomerEmail());
            }
        }
        return suggestion;
    }

    public void filterCustomer() {
        customerSorter = new TableRowSorter<DefaultTableModel>(tblCustomerModel);
        tblCustomers.setRowSorter(customerSorter);

        String id = txtCusId.getText();
        String name = txtCusName.getText();
        String email = txtCusEmail.getText();

        RowFilter<TableModel, Object> filter = new RowFilter<TableModel, Object>() {
            public boolean include(Entry<? extends TableModel, ? extends Object> entry) {
                String rowId = entry.getStringValue(0);
                String rowName = entry.getStringValue(1);
                String rowEmail = entry.getStringValue(2);

                return rowId.toLowerCase().contains(id.toLowerCase()) && rowName.toLowerCase().contains(name.toLowerCase())
                        && rowEmail.toLowerCase().contains(email.toLowerCase());
            }
        };

        customerSorter.setRowFilter(filter);
    }

    public void showCustomerInfo(String id, String name, String dob, String gender, String address, String email, String phone,
                                    String membership, String btnText) {
        CustomerInfoForm customerInfoForm = new CustomerInfoForm(this, id, name, dob, gender, address, email, phone, membership, btnText);
        customerInfoForm.setContentPane(customerInfoForm.getContentPane());
        customerInfoForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        customerInfoForm.setSize(500, 600);
        customerInfoForm.setLocationRelativeTo(null);
        customerInfoForm.setVisible(true);
    }

    public void initCustomerTable() {
        tblCustomerModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String[] columns = new String[]{"Mã khách hàng", "Tên khách hàng", "Email", "Loại thành viên"};
        tblCustomerModel.setColumnIdentifiers(columns);

        tblCustomers.setModel(tblCustomerModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < columns.length; i++) {
            tblCustomers.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

//    public void renderToCustomerTable() {
//        tblCustomerModel.setRowCount(0);
//
//        for (Customer customer : customerList.getCustomerList()) {
//            tblCustomerModel.addRow(new Object[]{customer.getCustomerId(), customer.getCustomerName(), customer.getCustomerEmail(),
//                    customer.getMembership()});
//        }
//
//        tblCustomerModel.fireTableDataChanged();
//        tblCustomers.repaint();
//    }

    public CustomerList getCustomerListInstance() {
        return customerList;
    }

    public DefaultTableModel getTblCustomerModel() {
        return tblCustomerModel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CustomerForm");
        frame.setContentPane(new CustomerForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 500);
        frame.getContentPane().requestFocusInWindow();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JButton btnAddCustomer;
    private JButton btnDeleteCustomer;
    private JButton btnEditCustomer;
    private JButton btnResetCustomer;
    private JTabbedPane tabbedPane2;
    private JTable tblCustomers;
    private JComboBox cbxCustomerId;
    private JButton btnDeleteCusId;
    private JButton btnFilterCustomer;
    private JComboBox cbxCustomerName;
    private JButton btnDeleteCusName;
    private JComboBox cbxCustomerEmail;
    private JButton btnDeleteCusEmail;
    private JButton btnAddMember;
    private JButton btnDeleteMember;
    private JButton btnEditMember;
    private JButton btnResetMember;
    private JTable tblMemberships;
    private JTabbedPane tabbedPane3;
    private JButton btnFilterMembership;
    private JComboBox comboBox1;
    private JButton btnDeleteMemId;
    private JComboBox comboBox2;
    private JButton btnDeleteMemName;
    private JComboBox comboBox3;
    private JButton btnDeleteCusMembership;
}
