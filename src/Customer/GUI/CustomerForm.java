package Customer.GUI;

import Customer.BUS.CustomerBUS;
import Customer.BUS.MembershipBUS;
import Customer.DTO.Membership;
import Customer.DTO.MembershipType;
import Customer.BUS.MembershipTypeBUS;
import Utils.ComboBoxAutoSuggest.AutoSuggestComboBox;
import Customer.DTO.Customer;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class CustomerForm {
    private CustomerBUS customerBUS = new CustomerBUS();
    private DefaultTableModel tblCustomerModel;
    private TableRowSorter<DefaultTableModel> customerSorter;
    private JTextField txtCusId;
    private JTextField txtCusName;
    private JTextField txtCusEmail;
    private JTextField txtCusMembership;

    private MembershipBUS membershipBUS = new MembershipBUS();
    private DefaultTableModel tblMembershipModel;
    private TableRowSorter<DefaultTableModel> membershipSorter;
    private JTextField txtMembershipId;
    private JTextField txtCustomerId;
    private JTextField txtTypeId;

    private MembershipTypeBUS membershipTypeBUS = new MembershipTypeBUS();
    private DefaultTableModel tblMembershipTypeModel;
    private TableRowSorter<DefaultTableModel> membershipTypeSorter;
    private JTextField txtMembershipTypeName;

    public CustomerForm() {
        handleCustomer();
        handleMembership();
        handleMembershipType();
    }

    public void handleMembershipType() {
        initMembershipTypeTable();
        membershipTypeBUS.renderToTable(tblMembershipTypeModel);

        btnAddMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MembershipType membershipType = new MembershipType("", 0, false);
                showMembershipTypeInfo(membershipType, "Thêm loại thành viên");
            }
        });

        btnDeleteMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblMembershipTypes.getSelectedRow();
                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn loại thành viên muốn xóa", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa loại thành viên này không?", "Question", JOptionPane.YES_NO_OPTION);
                if (choice != JOptionPane.YES_OPTION) {
                    return;
                }

                String name = tblMembershipTypes.getValueAt(selectedRow, 0).toString();
                if (membershipTypeBUS.validateDelete(name)) {
                    membershipTypeBUS.renderToTable(tblMembershipTypeModel);
                }
            }
        });

        btnEditMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblMembershipTypes.getSelectedRow();
                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn loại thành viên muốn sửa thông tin", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String name = tblMembershipTypes.getValueAt(selectedRow, 0).toString();
                int discount = Integer.parseInt(tblMembershipTypes.getValueAt(selectedRow, 1).toString());

                MembershipType membershipType = new MembershipType(name, discount, false);
                showMembershipTypeInfo(membershipType, "Lưu thông tin");
            }
        });

        txtMembershipTypeName = AutoSuggestComboBox.createWithDeleteBtn(cbxMemName, 0, this::initMembershipTypeSuggestion, btnDeleteMemName);

        btnFilterMembershipType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterMembershipType();
            }
        });

        btnResetMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtMembershipTypeName.setText("");
                membershipTypeSorter.setRowFilter(null);
            }
        });
    }

    public void filterMembershipType() {
        membershipTypeSorter = new TableRowSorter<DefaultTableModel>(tblMembershipTypeModel);
        tblMembershipTypes.setRowSorter(membershipTypeSorter);

        String name = txtMembershipTypeName.getText().toLowerCase();

        RowFilter<DefaultTableModel, Object> rowFilter = new RowFilter<DefaultTableModel, Object>() {
            @Override
            public boolean include(Entry<? extends DefaultTableModel, ?> entry) {
                String rowName = entry.getStringValue(0).toLowerCase();

                if (name.equals("")) {
                    return true;
                }

                return rowName.equals(name);
            }
        };

        membershipTypeSorter.setRowFilter(rowFilter);
    }

    public ArrayList<String> initMembershipTypeSuggestion(int col) {
        ArrayList<String> suggestion = new ArrayList<>();
        for (MembershipType membershipType : membershipTypeBUS.getMembershipTypeList()) {
            if (!membershipType.isDeleted()) {
                suggestion.add(membershipType.getMembershipTypeName());
            }
        }
        return suggestion;
    }

    public void showMembershipTypeInfo(MembershipType membershipType, String btnText) {
        MembershipInfoForm membershipInfoForm = new MembershipInfoForm(this, membershipType, btnText);
        membershipInfoForm.setContentPane(membershipInfoForm.getContentPane());
        membershipInfoForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        membershipInfoForm.setSize(400,400);
        membershipInfoForm.setLocationRelativeTo(null);
        membershipInfoForm.setVisible(true);
    }

    public void initMembershipTypeTable() {
        tblMembershipTypeModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String[] cols = new String[]{"Tên loại thành viên", "Giảm giá(%)"};
        tblMembershipTypeModel.setColumnIdentifiers(cols);

        tblMembershipTypes.setModel(tblMembershipTypeModel);
        tblMembershipTypes.getTableHeader().setFont(new Font("Time News Roman", Font.PLAIN, 16));

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < cols.length; i++) {
            tblMembershipTypes.getColumnModel().getColumn(i).setCellRenderer(center);
        }
    }

    public void handleMembership() {
        initMembershipTable();
        membershipBUS.renderToTable(tblMembershipModel);

        txtMembershipId = AutoSuggestComboBox.createWithDeleteBtn(cbxMembershipId, 0, this::initMembershipSuggestion, btnDeleteMemId);
        txtCusId = AutoSuggestComboBox.createWithDeleteBtn(cbxCusId, 1, this::initMembershipSuggestion, btnDeleteCustomerId);
        txtTypeId = AutoSuggestComboBox.createWithDeleteBtn(cbxTypeId, 2, this::initMembershipSuggestion, btnDeleteTypeId);

        btnFilterMembership.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterMembership();
            }
        });

        btnResetMembership.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtMembershipId.setText("");
                txtCusId.setText("");
                txtTypeId.setText("");
                membershipSorter.setRowFilter(null);
            }
        });
    }

    public void filterMembership() {
        membershipSorter = new TableRowSorter<DefaultTableModel>(tblMembershipModel);
        tblMemberships.setRowSorter(membershipSorter);

        String memId = txtMembershipId.getText().toLowerCase();
        String cusId = txtCusId.getText().toLowerCase();
        String typeId = txtTypeId.getText().toLowerCase();

        RowFilter<DefaultTableModel, Object> rowFilter = new RowFilter<DefaultTableModel, Object>() {
            @Override
            public boolean include(Entry<? extends DefaultTableModel, ?> entry) {
                String rowMemId = entry.getStringValue(0).toLowerCase();
                String rowCusId = entry.getStringValue(1).toLowerCase();
                String rowTypeId = entry.getStringValue(2).toLowerCase();

                if (typeId.equals("")) {
                    return rowMemId.contains(memId) && rowCusId.contains(cusId);
                }

                return rowMemId.contains(memId) && rowCusId.contains(cusId) && rowTypeId.equals(typeId);
            }
        };

        membershipSorter.setRowFilter(rowFilter);
    }

    public ArrayList<String> initMembershipSuggestion(int col) {
        ArrayList<String> suggestion = new ArrayList<>();
        for (Membership membership : membershipBUS.getMembershipList()) {
            if (!membership.isDeleted()) {
                if (col == 0) {
                    suggestion.add(membership.getMembershipId());
                } else if (col == 1) {
                    suggestion.add(membership.getCustomerId());
                } else if (col == 2) {
                    suggestion.add(membership.getMembershipTypeName());
                }
            }
        }
        return suggestion;
    }

    public void initMembershipTable() {
        tblMembershipModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String[] cols = new String[]{"Mã thẻ", "Mã khách hàng", "Dạng thẻ", "Ngày đăng ký", "Ngày hết hạn"};
        tblMembershipModel.setColumnIdentifiers(cols);

        tblMemberships.setModel(tblMembershipModel);
        tblMemberships.getTableHeader().setFont(new Font("Time News Roman", Font.PLAIN, 16));

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
                if (customerBUS.validateDelete(id)) {
                    customerBUS.renderToTable(tblCustomerModel);
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

        btnImportExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "xlsx");
                fileChooser.setFileFilter(filter);

                int res = fileChooser.showOpenDialog(null);
                if (res == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    customerBUS.importExcel(selectedFile.getAbsolutePath());
                    customerBUS.renderToTable(tblCustomerModel);
                }
            }
        });

        btnExportExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerBUS.exportExcel();
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

                if (membership.equals("")) {
                    return rowId.contains(id) && rowName.contains(name) && rowEmail.contains(email);
                }

                return rowId.contains(id) && rowName.contains(name) && rowEmail.contains(email) && rowMember.equals(membership);
            }
        };

        customerSorter.setRowFilter(filter);
    }

    public ArrayList<String> initCustomerSuggestion(int col) {
        ArrayList<String> suggestion = new ArrayList<>();
        for (Customer customer : customerBUS.getCustomerList()) {
            if (!customer.isDeleted()) {
                if (col == 0) {
                    suggestion.add(customer.getCustomerId());
                } else if (col == 1) {
                    suggestion.add(customer.getCustomerName());
                } else if (col  == 2) {
                    suggestion.add(customer.getCustomerEmail());
                } else if (col == 3) {
                    suggestion.add(customer.getMembership());
                }
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
        tblCustomers.getTableHeader().setFont(new Font("Time News Roman", Font.PLAIN, 16));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < columns.length; i++) {
            tblCustomers.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public MembershipTypeBUS getMembershipTypeBUS() {
        return membershipTypeBUS;
    }

    public DefaultTableModel getTblMembershipTypeModelModel() {
        return tblMembershipTypeModel;
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
    private JTable tblMembershipTypes;
    private JTabbedPane tabbedPane3;
    private JButton btnFilterMembershipType;
    private JComboBox cbxMemName;
    private JButton btnDeleteMemName;
    private JComboBox cbxCusMembership;
    private JButton btnDeleteCusMembership;
    private JButton btnImportExcel;
    private JButton btnExportExcel;
    private JTable tblMemberships;
    private JButton btnDeleteMemId;
    private JButton btnDeleteCustomerId;
    private JButton btnDeleteTypeId;
    private JButton btnResetMembership;
    private JButton btnFilterMembership;
    private JComboBox cbxMembershipId;
    private JComboBox cbxCusId;
    private JComboBox cbxTypeId;
}