package Customer.GUI;

import Customer.BUS.CustomerBUS;
import Customer.DTO.MembershipType;
import Customer.BUS.MembershipBUS;
import Utils.ComboBoxAutoSuggest.AutoSuggestComboBox;
import Customer.DTO.Customer;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CustomerForm {
    private CustomerBUS customerBUS = new CustomerBUS();
    private DefaultTableModel tblCustomerModel;
    private TableRowSorter<DefaultTableModel> customerSorter;
    private JTextField txtCusId;
    private JTextField txtCusName;
    private JTextField txtCusEmail;
    private JTextField txtCusMembership;

    private MembershipBUS membershipList = new MembershipBUS();
    private DefaultTableModel tblMembershipModel;
    private TableRowSorter<DefaultTableModel> membershipSorter;
    private JTextField txtMemberId;
    private JTextField txtMemberName;

    public CustomerForm() {
        handleCustomer();
        handleMembership();
    }

    public void handleMembership() {
        initMembershipTable();
        membershipList.renderToTable(tblMembershipModel);

        btnAddMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMembershipInfo("", 0, "Thêm loại thành viên");
            }
        });

        btnDeleteMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblMemberships.getSelectedRow();
                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn loại thành viên muốn xóa", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa loại thành viên này không?", "Question", JOptionPane.YES_NO_OPTION);
                if (choice != JOptionPane.YES_OPTION) {
                    return;
                }

                String id = tblMemberships.getValueAt(selectedRow, 0).toString();
                membershipList.deleteMembership(id);
                JOptionPane.showMessageDialog(null, "Xóa thành công");
                membershipList.renderToTable(tblMembershipModel);
            }
        });

        btnEditMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblMemberships.getSelectedRow();
                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn loại thành viên muốn sửa thông tin", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String name = tblMemberships.getValueAt(selectedRow, 1).toString();
                float discount = Float.parseFloat(tblMemberships.getValueAt(selectedRow, 2).toString());
                showMembershipInfo(name, discount, "Lưu thông tin");
            }
        });

        txtMemberId = AutoSuggestComboBox.createWithDeleteBtn(cbxMemId, 0, this::initMembershipSuggestion, btnDeleteMemId);
        txtMemberName = AutoSuggestComboBox.createWithDeleteBtn(cbxMemName, 1, this::initMembershipSuggestion, btnDeleteMemName);

        btnFilterMembership.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterMembership();
            }
        });

        btnResetMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtMemberId.setText("");
                txtMemberName.setText("");
                membershipSorter.setRowFilter(null);
            }
        });
    }

    public void filterMembership() {
        membershipSorter = new TableRowSorter<DefaultTableModel>(tblMembershipModel);
        tblMemberships.setRowSorter(membershipSorter);

        String id = txtMemberId.getText().toLowerCase();
        String name = txtMemberName.getText().toLowerCase();

        RowFilter<DefaultTableModel, Object> rowFilter = new RowFilter<DefaultTableModel, Object>() {
            @Override
            public boolean include(Entry<? extends DefaultTableModel, ?> entry) {
                String rowId = entry.getStringValue(0).toLowerCase();
                String rowName = entry.getStringValue(1).toLowerCase();

                return rowId.contains(id) && rowName.contains(name);
            }
        };

        membershipSorter.setRowFilter(rowFilter);
    }

    public ArrayList<String> initMembershipSuggestion(int col) {
        ArrayList<String> suggestion = new ArrayList<>();
        for (MembershipType membership : membershipList.getMembershipList()) {
            if (col == 0) {
                suggestion.add(membership.getMembershipName());
            } else if (col == 1) {
                suggestion.add(membership.getMembershipName());
            }
        }

        return suggestion;
    }

    public void showMembershipInfo(String name, float discount, String btnText) {
        MembershipInfoForm membershipInfoForm = new MembershipInfoForm(this, name, discount, btnText);
        membershipInfoForm.setContentPane(membershipInfoForm.getContentPane());
        membershipInfoForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        membershipInfoForm.setSize(400,400);
        membershipInfoForm.setLocationRelativeTo(null);
        membershipInfoForm.setVisible(true);
    }

    public void initMembershipTable() {
        tblMembershipModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String[] cols = new String[]{"Tên loại thành viên", "Giảm giá(%)"};
        tblMembershipModel.setColumnIdentifiers(cols);

        tblMemberships.setModel(tblMembershipModel);

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < cols.length; i++) {
            tblMemberships.getColumnModel().getColumn(i).setCellRenderer(center);
        }
    }

    public void handleCustomer() {
        initCustomerTable();
        customerBUS.renderToTable(tblCustomerModel);

        btnAddCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer customer = new Customer("", "", "", "", "", "", "", "", "", "", "", false);
                showCustomerInfo(customer, "Thêm khách hàng");
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
                customerBUS.validateDelete(id);

                JOptionPane.showMessageDialog(null, "Xóa khách hàng thành công");
                customerBUS.renderToTable(tblCustomerModel);
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
                for (Customer customer : customerBUS.getCustomerList()) {
                    if (customer.getCustomerId().equals(id)) {
                        String name = customer.getCustomerName();
                        String dob = customer.getCustomerDOB();
                        String cccd = customer.getCccd();
                        String gender = customer.getCustomerGender();
                        String address = customer.getCustomerAddress();
                        String email = customer.getCustomerEmail();
                        String phone = customer.getCustomerPhone();
                        String membership = customer.getMembership();
                        String regisDate = customer.getRegistrationDate();
                        String expireDate = customer.getExpirationDate();

                        Customer cus = new Customer(id, name, dob, address, cccd, email, phone, gender, membership, regisDate, expireDate, false);
                        showCustomerInfo(cus, "Lưu thông tin");
                        return;
                    }
                }
            }
        });

        txtCusId = AutoSuggestComboBox.createWithDeleteBtn(cbxCustomerId, 0, this::initCustomerSuggestion, btnDeleteCusId);
        txtCusName = AutoSuggestComboBox.createWithDeleteBtn(cbxCustomerName, 1, this::initCustomerSuggestion, btnDeleteCusName);
        txtCusEmail = AutoSuggestComboBox.createWithDeleteBtn(cbxCustomerEmail, 2, this::initCustomerSuggestion, btnDeleteCusEmail);
        txtCusMembership = AutoSuggestComboBox.createWithDeleteBtn(cbxCusMembership, 3, this::initCustomerSuggestion, btnDeleteCusMembership);

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
                txtCusMembership.setText("");
                customerSorter.setRowFilter(null);
            }
        });
    }

    public void filterCustomer() {
        customerSorter = new TableRowSorter<DefaultTableModel>(tblCustomerModel);
        tblCustomers.setRowSorter(customerSorter);

        String id = txtCusId.getText().toLowerCase();
        String name = txtCusName.getText().toLowerCase();
        String email = txtCusEmail.getText().toLowerCase();
        String membership = txtCusMembership.getText().toLowerCase();

        RowFilter<DefaultTableModel, Object> filter = new RowFilter<DefaultTableModel, Object>() {
            public boolean include(Entry<? extends DefaultTableModel, ? extends Object> entry) {
                String rowId = entry.getStringValue(0).toLowerCase();
                String rowName = entry.getStringValue(1).toLowerCase();
                String rowEmail = entry.getStringValue(2).toLowerCase();
                String rowMember = entry.getStringValue(3).toLowerCase();

                return rowId.contains(id) && rowName.contains(name) && rowEmail.contains(email) && rowMember.contains(membership);
            }
        };

        customerSorter.setRowFilter(filter);
    }

    public ArrayList<String> initCustomerSuggestion(int col) {
        ArrayList<String> suggestion = new ArrayList<>();
        for (Customer customer : customerBUS.getCustomerList()) {
            if (col == 0) {
                suggestion.add(customer.getCustomerId());
            } else if (col == 1) {
                suggestion.add(customer.getCustomerName());
            } else if (col == 2) {
                suggestion.add(customer.getCustomerEmail());
            } else if (col == 3) {
                suggestion.add(customer.getMembership());
            }
        }
        return suggestion;
    }

    public void showCustomerInfo(Customer customer, String btnText) {
        CustomerInfoForm customerInfoForm = new CustomerInfoForm(this, customer, btnText);
        customerInfoForm.setContentPane(customerInfoForm.getContentPane());
        customerInfoForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        customerInfoForm.setSize(500, 750);
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

    public MembershipBUS getMembershipListInstance() {
        return membershipList;
    }

    public DefaultTableModel getTblMembershipModelModel() {
        return tblMembershipModel;
    }

    public CustomerBUS getCustomerBUS() {
        return customerBUS;
    }

    public DefaultTableModel getTblCustomerModel() {
        return tblCustomerModel;
    }

    public JPanel getContentPanel() {
        return mainPanel;
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
    private JComboBox cbxMemId;
    private JButton btnDeleteMemId;
    private JComboBox cbxMemName;
    private JButton btnDeleteMemName;
    private JComboBox cbxCusMembership;
    private JButton btnDeleteCusMembership;
}