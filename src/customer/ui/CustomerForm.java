package customer.ui;

import Utils.ComboBoxAutoSuggest.AutoSuggestComboBox;
import customer.model.Customer;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CustomerForm {
    private ArrayList<Customer> customerList = new ArrayList<>();
    private DefaultTableModel tblModel;
    private TableRowSorter<DefaultTableModel> sorter;
    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtEmail;

    public CustomerForm() {
        initTable();
        renderToTable();
        initComboBox();

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCustomerInfo("", "", "", "", "", "", "", "Thêm khách hàng");
            }
        });

        btnDelete.addActionListener(new ActionListener() {
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
                for (Customer customer : customerList) {
                    if (customer.getCustomerId().equals(id)) {
                        customerList.remove(customer);
                        JOptionPane.showMessageDialog(null, "Xóa khách hàng thành công");
                        renderToTable();
                        return;
                    }
                }
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblCustomers.getSelectedRow();
                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng muốn sửa thông tin", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String id = tblCustomers.getValueAt(selectedRow, 0).toString();
                for (Customer customer : customerList) {
                    if (customer.getCustomerId().equals(id)) {
                        String name = customer.getCustomerName();
                        String dob = customer.getCustomerDOB();
                        String gender = customer.getCustomerGender();
                        String address = customer.getCustomerAddress();
                        String email = customer.getCustomerEmail();
                        String phone = customer.getCustomerPhone();

                        showCustomerInfo(id, name, dob, gender, address, email, phone, "Lưu thông tin");
                        return;
                    }
                }
            }
        });

        createFilterTextField();
        btnFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filter(txtId.getText());
            }
        });
    }

    public void filter(String text) {
        sorter = new TableRowSorter<DefaultTableModel>(tblModel);
        tblCustomers.setRowSorter(sorter);

        if (text.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter(text));
        }
    }

    public void createFilterTextField() {
        txtId = AutoSuggestComboBox.createWithDelete(cbxCustomerId, btnDeleteId);
        txtName = AutoSuggestComboBox.createWithDelete(cbxCustomerName, btnDeleteName);
        txtEmail = AutoSuggestComboBox.createWithDelete(cbxCustomerEmail, btnDeleteEmail);
    }

    public void createFilterTextField(JTextField txtId, JTextField txtName, JTextField txtEmail) {
        this.txtId = txtId;
        this.txtName = txtName;
        this.txtEmail = txtEmail;
    }

    public void showCustomerInfo(String id, String name, String dob, String gender, String address, String email, String phone, String btnText) {
        CustomerInfoForm customerInfoForm = new CustomerInfoForm(this, id, name, dob, gender, address, email, phone, btnText);
        customerInfoForm.setContentPane(customerInfoForm.getContentPane());
        customerInfoForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        customerInfoForm.setSize(500, 600);
        customerInfoForm.setLocationRelativeTo(null);
        customerInfoForm.setVisible(true);
    }

    public void initComboBox() {
        cbxCustomerId.removeAllItems();
        cbxCustomerName.removeAllItems();
        cbxCustomerEmail.removeAllItems();

        for (Customer customer : customerList) {
            cbxCustomerId.addItem(customer.getCustomerId());
            cbxCustomerName.addItem(customer.getCustomerName());
            cbxCustomerEmail.addItem(customer.getCustomerEmail());
        }

        cbxCustomerId.setSelectedIndex(-1);
        cbxCustomerName.setSelectedIndex(-1);
        cbxCustomerEmail.setSelectedIndex(-1);
    }

    public void initTable() {
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

        for (int i = 1; i <= 10; i++) {
            String tmp = "CUS00" + i;
            customerList.add((new Customer(tmp, "Thế Vũ", "Nam", "29-08-2003", "HCM", "aaa@gmail.com", "0123456789")));
        }
    }

    public void renderToTable() {
        tblModel.setRowCount(0);

        for (Customer customer : customerList) {
            tblModel.addRow(new Object[]{customer.getCustomerId(), customer.getCustomerName(), customer.getCustomerEmail(), "Bạc"});
        }

        tblModel.fireTableDataChanged();
        tblCustomers.repaint();
    }

    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    public JComboBox getCbxCustomerId() {
        return cbxCustomerId;
    }

    public JButton getBtnDeleteId() {
        return btnDeleteId;
    }

    public JComboBox getCbxCustomerName() {
        return cbxCustomerName;
    }

    public JButton getBtnDeleteName() {
        return btnDeleteName;
    }

    public JComboBox getCbxCustomerEmail() {
        return cbxCustomerEmail;
    }

    public JButton getBtnDeleteEmail() {
        return btnDeleteEmail;
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
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnEdit;
    private JButton btnReset;
    private JTabbedPane tabbedPane2;
    private JTable tblCustomers;
    private JComboBox cbxCustomerId;
    private JButton btnDeleteId;
    private JButton btnFilter;
    private JComboBox cbxCustomerName;
    private JButton btnDeleteName;
    private JComboBox cbxCustomerEmail;
    private JButton btnDeleteEmail;
}
