package GUI;

import BUS.BorrowBUS;
import BUS.FaultBUS;
import DTO.BorrowModel;
import DTO.FaultDetailModel;
import DTO.FaultModel;
import Utils.ComboBoxAutoSuggest.AutoSuggestComboBox;
import Utils.TableUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class BorrowUI {

    private JTabbedPane borrowTabbedPane;
    private JTable borrowTable;
    private JButton borrowDeleteAllButton;
    private JButton borrowFilterButton;
    private JTabbedPane borrowTabbedPane2;
    private JButton borrowIdDelBtn;
    private JComboBox borrowIdCbx;
    private JButton maNvxnMuonDelBtn;
    private JComboBox maNvxnMuonCbx;
    private JButton tenNvxnMuonDelBtn;
    private JComboBox tenNvxnMuonCbx;
    private JButton maNvxnTraDelBtn;
    private JComboBox maNvxnTraCbx;
    private JButton tenNvxnTraDelBtn;
    private JComboBox tenNvxnTraCbx;
    private JButton maTheMuonDelBtn;
    private JComboBox maTheMuonCbx;
    private JButton addBorrowButton;
    private JButton deleteBorrowButton;
    private JButton editBorrowButton;
    private JPanel panel1;
    private JButton tenSachDelBtn;
    private JComboBox tenSachCB;
    private JButton tenLoiDelBtn;
    private JComboBox tenLoiCB;
    private JButton soLuongDelBtn;
    private JComboBox soLuongCB;
    private JTable faultTable;
    private JButton faultDeleteAllButton;
    private JButton faultFilterButton;
    private JTabbedPane faultTabbedPane2;
    private JButton faultIDDelBtn;
    private JComboBox faultIDComboBox;
    private JButton faultNameDelBtn;
    private JComboBox faultNameCB;
    private JButton heSoLoiDelBtn;
    private JComboBox heSoLoiCB;
    private JButton addFaultButton;
    private JButton deleteFaultButton;
    private JButton editFaultButton;
    private JButton btnViewBorrowDetail;
    private JButton btnViewFaultDetail;
    private JButton tenDocGiaDelBtn;
    private JComboBox tenDocGiaCbx;
    private JButton btnSubmitBorrow;

    // các model cần thiết
    public static BorrowModel borrowModel = new BorrowModel();

    public static FaultModel faultModel = new FaultModel();

    public static FaultDetailModel faultDetailModel = new FaultDetailModel();

    // cac BUS can thiet
    private FaultBUS faultBUS = new FaultBUS();
    private BorrowBUS borrowBUS = new BorrowBUS();

    public BorrowUI() {

    }

    public void showBorrowInfo(String id, String maThe, String tenDocGia, int soNgayMuon, String btnText,
            String login_id) {
        BorrowInfoUI borrowInfoUI = new BorrowInfoUI(new BorrowUI(), id, maThe, tenDocGia, soNgayMuon, btnText,
                login_id);
        borrowInfoUI.setContentPane(borrowInfoUI.getContentPane());
        borrowInfoUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        borrowInfoUI.setSize(700, 500);
        borrowInfoUI.setLocationRelativeTo(null);
        borrowInfoUI.setVisible(true);
    }

    public void showBorrowDetail(String id, String maThe, long soNgayMuon) {
        BorrowDetailUI borrowDetailUI = new BorrowDetailUI(id, maThe, soNgayMuon);
        borrowDetailUI.setContentPane(borrowDetailUI.getContentPane());
        borrowDetailUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        borrowDetailUI.setSize(700, 500);
        borrowDetailUI.setLocationRelativeTo(null);
        borrowDetailUI.setVisible(true);
    }

    public void showFaultInfo(String id, String tenLoi, String heSo, String btnText) {
        FaultInfor faultDetailUI = new FaultInfor(this, id, tenLoi, heSo, btnText);
        faultDetailUI.setContentPane(faultDetailUI.getContentPane());
        faultDetailUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        faultDetailUI.setSize(700, 500);
        faultDetailUI.setLocationRelativeTo(null);
        faultDetailUI.setVisible(true);
    }

    public void showFaultDetail(String id) {
        FaultDetailUI faultDetailUI = new FaultDetailUI(id);
        faultDetailUI.setContentPane(faultDetailUI.getContentPane());
        faultDetailUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        faultDetailUI.setSize(700, 500);
        faultDetailUI.setLocationRelativeTo(null);
        faultDetailUI.setVisible(true);
    }

    public BorrowUI(String login_id) {
        // xu ly phieu muon
        borrowModel.setEditable(false);
        borrowModel.initModelTable(borrowBUS.getDsMuon());

        borrowTable.setModel(borrowModel);
        TableRowSorter<BorrowModel> sorter = new TableRowSorter<>(borrowModel);
        borrowTable.setRowSorter(sorter);
        borrowTable.getTableHeader().setFont(new Font("Time News Roman", Font.PLAIN, 16));
        borrowTable.getTableHeader().setBackground(Color.WHITE);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < borrowTable.getColumnCount(); i++) {
            borrowTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        borrowTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    System.out.println("double clicked");
                    int[] pos = { borrowTable.getSelectedRow(), borrowTable.getSelectedColumn() };
                    System.out.println(pos[0] + " " + pos[1]);
                    // borrowEditSelected();

                    int selectedRow = borrowTable.getSelectedRow();
                    if (selectedRow < 0) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu mượn muốn sửa thông tin", "Warning",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    //
                    String id = borrowTable.getValueAt(selectedRow, 0).toString();
                    String maThe = borrowTable.getValueAt(selectedRow, 5).toString();
                    String tenDocGia = borrowTable.getValueAt(selectedRow, 6).toString();
                    String ngayMuon = borrowTable.getValueAt(selectedRow, 7).toString();
                    String ngayHenTra = borrowTable.getValueAt(selectedRow, 8).toString();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                    long diff = 0;
                    try {
                        Date firstDate = sdf.parse(ngayMuon);
                        Date secondDate = sdf.parse(ngayHenTra);
                        long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
                        diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                    } catch (ParseException ex) {
                        System.out.println("Co loi");
                    }
                    showBorrowInfo(id, maThe, tenDocGia, (int) diff, "Lưu thông tin", login_id);
                }

            }
        });
        borrowTable.getTableHeader().setReorderingAllowed(false);

        var IDCBTF = AutoSuggestComboBox.createWithDeleteBtn(borrowIdCbx, 0, borrowModel::getColumnValueToString,
                borrowIdDelBtn);
        var maNVMuonCBTF = AutoSuggestComboBox.createWithDeleteBtn(maNvxnMuonCbx, 1,
                borrowModel::getColumnValueToString,
                maNvxnMuonDelBtn);
        var tenNVMuonCBTF = AutoSuggestComboBox.createWithDeleteBtn(tenNvxnMuonCbx, 2,
                borrowModel::getColumnValueToString,
                tenNvxnMuonDelBtn);
        var maNVTraCBTF = AutoSuggestComboBox.createWithDeleteBtn(maNvxnTraCbx, 3, borrowModel::getColumnValueToString,
                maNvxnTraDelBtn);
        var tenNVTraCBTF = AutoSuggestComboBox.createWithDeleteBtn(tenNvxnTraCbx, 4,
                borrowModel::getColumnValueToString,
                tenNvxnTraDelBtn);
        var maTheCBTF = AutoSuggestComboBox.createWithDeleteBtn(maTheMuonCbx, 5, borrowModel::getColumnValueToString,
                maTheMuonDelBtn);
        var tenDgCBTF = AutoSuggestComboBox.createWithDeleteBtn(tenDocGiaCbx, 6, borrowModel::getColumnValueToString,
                tenDocGiaDelBtn);

        borrowDeleteAllButton.addActionListener(e -> {
            IDCBTF.setText("");
            maNVMuonCBTF.setText("");
            tenNVMuonCBTF.setText("");
            maNVTraCBTF.setText("");
            tenNVTraCBTF.setText("");
            maTheCBTF.setText("");
            tenDgCBTF.setText("");
        });

        borrowModel.setFilterField(0, IDCBTF);
        borrowModel.setFilterField(1, maNVMuonCBTF);
        borrowModel.setFilterField(2, tenNVMuonCBTF);
        borrowModel.setFilterField(3, maNVTraCBTF);
        borrowModel.setFilterField(4, tenNVTraCBTF);
        borrowModel.setFilterField(5, maTheCBTF);
        borrowModel.setFilterField(6, tenDgCBTF);

        for (Iterator<TableColumn> it = borrowTable.getColumnModel().getColumns().asIterator(); it.hasNext();) {
            var column = it.next();
            column.setMinWidth(100);
        }

        borrowFilterButton.addActionListener(e -> {
            TableUtils.filter(borrowTable);
        });

        addBorrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maPhieu = borrowBUS.getNewPM();
                showBorrowInfo(maPhieu, "", "", 0, "Thêm phiếu mượn", login_id);
            }
        });

        editBorrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = borrowTable.getSelectedRow();


                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu mượn muốn sửa thông tin", "Warning",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String id = borrowTable.getValueAt(selectedRow, 0).toString();
                if (borrowModel.checkBorrow(id)) {
                    JOptionPane.showMessageDialog(null, "Phiếu mượn đã xác nhận trả", "Warning",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String maThe = borrowTable.getValueAt(selectedRow, 5).toString();
                String tenDocGia = borrowTable.getValueAt(selectedRow, 6).toString();
                String ngayMuon = borrowTable.getValueAt(selectedRow, 7).toString();
                String ngayHenTra = borrowTable.getValueAt(selectedRow, 8).toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                long diff = 0;
                try {
                    Date firstDate = sdf.parse(ngayMuon);
                    Date secondDate = sdf.parse(ngayHenTra);
                    long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
                    diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                } catch (ParseException ex) {
                    System.out.println("Co loi");
                }
                showBorrowInfo(id, maThe, tenDocGia, (int) diff, "Lưu thông tin", login_id);
            }
        });
        deleteBorrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = borrowTable.getSelectedRow();
                String id = borrowTable.getValueAt(selectedRow, 0).toString();
                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu mượn muốn xóa", "Warning",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (!borrowModel.checkBorrow(id)) {
                    JOptionPane.showMessageDialog(null, "Phiếu chưa được xác nhận, không thể xóa!", "Warning",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa phiếu mượn này không?", "Question",
                        JOptionPane.YES_NO_OPTION);
                if (choice != JOptionPane.YES_OPTION) {
                    return;
                }



                if (borrowModel.checkBorrow(id)) {
                    JOptionPane.showMessageDialog(null, "Phiếu đã được xác nhận, không thể xóa!", "Warning",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                borrowBUS.delete(id);
                borrowModel.initModelTable(borrowBUS.getDsMuon());

                JOptionPane.showMessageDialog(null, "Xóa phiếu mượn thành công");

            }
        });

        btnSubmitBorrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = borrowTable.getSelectedRow();
                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu mượn muốn xác nhận trả", "Warning",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String id = borrowTable.getValueAt(selectedRow, 0).toString();

                if (borrowModel.checkBorrow(id)) {
                    JOptionPane.showMessageDialog(null, "Phiếu đã được xác nhận trả!", "Warning",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn xác nhận trả không?", "Question",
                        JOptionPane.YES_NO_OPTION);
                if (choice != JOptionPane.YES_OPTION) {
                    return;
                }



                borrowBUS.submitBorrow(id, login_id);
                borrowModel.initModelTable(borrowBUS.getDsMuon());

                JOptionPane.showMessageDialog(null, "Xác nhận trả thành công!");
            }
        });

        btnViewBorrowDetail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = borrowTable.getSelectedRow();
                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu mượn muốn xem chi tiết", "Warning",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String maThe = borrowTable.getValueAt(selectedRow, 5).toString();
                String ngayMuon = borrowTable.getValueAt(selectedRow, 7).toString();
                String ngayHenTra = borrowTable.getValueAt(selectedRow, 8).toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                long diff = 0;
                try {
                    Date firstDate = sdf.parse(ngayMuon);
                    Date secondDate = sdf.parse(ngayHenTra);
                    long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
                    diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                } catch (ParseException ex) {
                    System.out.println("Co loi");
                }

                String id = borrowTable.getValueAt(selectedRow, 0).toString();
                showBorrowDetail(id, maThe, diff);
            }
        });

        btnViewFaultDetail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = borrowTable.getSelectedRow();
                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu mượn muốn xem chi tiết", "Warning",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String maThe = borrowTable.getValueAt(selectedRow, 5).toString();
                String ngayMuon = borrowTable.getValueAt(selectedRow, 7).toString();
                String ngayHenTra = borrowTable.getValueAt(selectedRow, 8).toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                String id = borrowTable.getValueAt(selectedRow, 0).toString();
                showFaultDetail(id);
            }
        });

        // xu ly lỗi
        faultModel.setEditable(false);
        faultModel.initModelTable(faultBUS.getDsLoi());

        faultTable.setModel(faultModel);
        faultTable.setDefaultEditor(Object.class, null);
        TableRowSorter<FaultModel> sorterFault = new TableRowSorter<>(faultModel);
        faultTable.setRowSorter(sorterFault);
        faultTable.getTableHeader().setFont(new Font("Time News Roman", Font.PLAIN, 16));
        faultTable.getTableHeader().setBackground(Color.WHITE);

        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < faultTable.getColumnCount(); i++) {
            faultTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        faultTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    System.out.println("double clicked fault");
                    int[] pos = { faultTable.getSelectedRow(), faultTable.getSelectedColumn() };
                    System.out.println(pos[0] + " " + pos[1]);
                    int selectedRow = faultTable.getSelectedRow();
                    if (selectedRow < 0) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn lỗi muốn sửa thông tin", "Warning",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    String id = faultTable.getValueAt(selectedRow, 0).toString();
                    String tenLoi = faultTable.getValueAt(selectedRow, 1).toString();
                    String heSo = faultTable.getValueAt(selectedRow, 2).toString();
                    showFaultInfo(id, tenLoi, heSo, "Lưu thông tin");
                }
            }
        });
        faultTable.getTableHeader().setReorderingAllowed(false);

        var faultIDTF = AutoSuggestComboBox.createWithDeleteBtn(faultIDComboBox, 0, faultModel::getColumnValueToString,
                faultIDDelBtn);
        var faultNameTF = AutoSuggestComboBox.createWithDeleteBtn(faultNameCB, 1, faultModel::getColumnValueToString,
                faultNameDelBtn);
        var heSoTF = AutoSuggestComboBox.createWithDeleteBtn(heSoLoiCB, 2, faultModel::getColumnValueToString,
                heSoLoiDelBtn);

        faultDeleteAllButton.addActionListener(e -> {
            faultIDTF.setText("");
            faultNameTF.setText("");
            heSoTF.setText("");
        });

        faultModel.setFilterField(0, faultIDTF);
        faultModel.setFilterField(1, faultNameTF);
        faultModel.setFilterField(2, heSoTF);

        for (Iterator<TableColumn> it = faultTable.getColumnModel().getColumns().asIterator(); it.hasNext();) {
            var column = it.next();
            column.setMinWidth(100);
        }

        faultFilterButton.addActionListener(e -> {
            TableUtils.filter(faultTable);
        });
        addFaultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maLoi = faultBUS.getML();
                showFaultInfo(maLoi, "", "", "Thêm lỗi");
            }
        });
        editFaultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = faultTable.getSelectedRow();
                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn lỗi muốn sửa thông tin", "Warning",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String id = faultTable.getValueAt(selectedRow, 0).toString();
                String tenLoi = faultTable.getValueAt(selectedRow, 1).toString();
                String heSo = faultTable.getValueAt(selectedRow, 2).toString();
                showFaultInfo(id, tenLoi, heSo, "Lưu thông tin");

            }
        });
        deleteFaultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = faultTable.getSelectedRow();
                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn lỗi muốn xóa", "Warning",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa lỗi này không?", "Question",
                        JOptionPane.YES_NO_OPTION);
                if (choice != JOptionPane.YES_OPTION) {
                    return;
                }

                String id = faultTable.getValueAt(selectedRow, 0).toString();
                faultBUS.remove(id);

                JOptionPane.showMessageDialog(null, "Xóa lỗi thành công");
                faultModel.initModelTable(faultBUS.getDsLoi());
            }

        });

    }

    public JPanel getPanel1() {
        return panel1;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Book");
        frame.setContentPane(new BorrowUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1000, 600);
        frame.setVisible(true);
    }
}
