package Borrow.GUI;

import Borrow.BUS.FaultBUS;
import Borrow.BorrowDetailModel;
import Borrow.BorrowModel;
import Borrow.FaultDetailModel;
import Borrow.FaultModel;
import Utils.ComboBoxAutoSuggest.AutoSuggestComboBox;
import Utils.TableUtils;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class BorrowUI {

    private JTabbedPane borrowTabbedPane;
    private JTable borrowTable;
    private JButton borrowDeleteAllButton;
    private JButton borrowFilterButton;
    private JTabbedPane borrowTabbedPane2;
    private JButton borrowIDDelBtn;
    private JComboBox borrowIDCB;
    private JButton tenNhanVienDelBtn;
    private JComboBox tenNhanVienCB;
    private JButton tenDocGiaDelBtn;
    private JComboBox tenDocGiaCB;
    private JButton ngayMuonDelBtn;
    private JComboBox ngayMuonCB;
    private JButton ngayTraDelBtn;
    private JComboBox ngayTraCB;
    private JButton tongTienDelBtn;
    private JComboBox tongTienCB;
    private JButton addRowButton;
    private JButton addBorrowButton;
    private JButton deleteBorrowButton;
    private JButton editBorrowButton;
    private JPanel panel1;
    private JTable borrowDetailTable;
    private JComboBox IDComboBox;
    private JButton maPhieuMuonDelBtn;
    private JComboBox maPhieuMuonCB;
    private JButton tenSachDelBtn;
    private JComboBox tenSachCB;
    private JButton tenLoiDelBtn;
    private JComboBox tenLoiCB;
    private JButton soLuongDelBtn;
    private JComboBox soLuongCB;
    private JButton giaTienDelBtn;
    private JComboBox giaTienCB;
    private JButton IDDelBtn;
    private JButton borrowDetailFilterBtn;
    private JButton borrowDetailDelAll;
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
    private JTable faultDetailTable;
    private JButton bookDeleteAllButton;
    private JButton bookFilterButton;
    private JTabbedPane tabbedPane2;
    private JButton maChiTietDelBtn;
    private JComboBox maChiTietCB;
    private JButton maLoiDelBtn;
    private JComboBox maLoiCB;
    private JButton tienDenDelBtn;
    private JComboBox tienDenCB;
    private JButton addFaultDetailBtn;
    private JButton deleteFaultDetailBtn;
    private JButton updateFaultDetailBtn;

//    các model cần thiết
    public  static BorrowModel borrowModel = new BorrowModel();

    public static BorrowDetailModel borrowDetailModel = new BorrowDetailModel();

    public static FaultModel faultModel = new FaultModel();

    public static FaultDetailModel faultDetailModel = new FaultDetailModel();

//    cac BUS can thiet
    private FaultBUS faultBUS = new FaultBUS();

    public void showBorrowInfo(String id, String tenDocGia, String ngayMuon, String ngayTra, ArrayList<String> sachMuon, String btnText) {
        BorrowInfoUI borrowInfoUI = new BorrowInfoUI(new BorrowUI(),id,tenDocGia,ngayMuon,ngayTra,sachMuon, btnText);
        borrowInfoUI.setContentPane(borrowInfoUI.getContentPane());
        borrowInfoUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        borrowInfoUI.setSize(500, 700);
        borrowInfoUI.setLocationRelativeTo(null);
        borrowInfoUI.setVisible(true);
    }
    public void showFaultInfo(String id, String tenLoi, String heSo,String btnText) {
        FaultInfor faultDetailUI = new FaultInfor(this, id, tenLoi, heSo, btnText);
        faultDetailUI.setContentPane(faultDetailUI.getContentPane());
        faultDetailUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        faultDetailUI.setSize(500, 700);
        faultDetailUI.setLocationRelativeTo(null);
        faultDetailUI.setVisible(true);
    }

    public void showFaultDetailInfo(String id,String btnText) {
        FaultDetailInfoUI faultDetailInfoUI = new FaultDetailInfoUI(new FaultDetailUI(), id, btnText);
        faultDetailInfoUI.setContentPane(faultDetailInfoUI.getContentPane());
        faultDetailInfoUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        faultDetailInfoUI.setSize(500, 700);
        faultDetailInfoUI.setLocationRelativeTo(null);
        faultDetailInfoUI.setVisible(true);
    }

    public BorrowUI() {
//        xu ly phieu muon
        borrowModel.setEditable(false);

        borrowModel.addTestData();
        borrowTable.setModel(borrowModel);
        TableRowSorter<BorrowModel> sorter = new TableRowSorter<>(borrowModel);
        borrowTable.setRowSorter(sorter);
        borrowTable.getTableHeader().setFont(new Font("Time News Roman", Font.PLAIN, 16));
        borrowTable.getTableHeader().setBackground(Color.WHITE);

        addRowButton.addActionListener(e -> borrowModel.addTestData());

        borrowTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    System.out.println("double clicked");
                    int[] pos = { borrowTable.getSelectedRow(), borrowTable.getSelectedColumn() };
                    System.out.println(pos[0] + " " + pos[1]);
                    borrowEditSelected();
                }
            }
        });
        borrowTable.getTableHeader().setReorderingAllowed(false);

        var IDCBTF = AutoSuggestComboBox.createWithDeleteBtn(borrowIDCB, 0, borrowModel::getColumnValueToString, borrowIDDelBtn);
        var tenNhanVienCBTF = AutoSuggestComboBox.createWithDeleteBtn(tenNhanVienCB, 1,
                borrowModel::getColumnValueToString, tenNhanVienDelBtn);
        var tenDocGiaCBTF = AutoSuggestComboBox.createWithDeleteBtn(tenDocGiaCB, 2, borrowModel::getColumnValueToString,
                tenDocGiaDelBtn);
        var ngayMuonCBTF = AutoSuggestComboBox.createWithDeleteBtn(ngayMuonCB, 3, borrowModel::getColumnValueToString,
                ngayMuonDelBtn);
        var ngayTraCBTF = AutoSuggestComboBox.createWithDeleteBtn(ngayTraCB, 4, borrowModel::getColumnValueToString,
                ngayTraDelBtn);
        var tongTienCBTF = AutoSuggestComboBox.createWithDeleteBtn(tongTienCB, 5, borrowModel::getColumnValueToString,
                tongTienDelBtn);

        borrowDeleteAllButton.addActionListener(e -> {
            IDCBTF.setText("");
            tenNhanVienCBTF.setText("");
            tenDocGiaCBTF.setText("");
            ngayMuonCBTF.setText("");
            ngayTraCBTF.setText("");
            tongTienCBTF.setText("");
        });

        borrowModel.setFilterField(0, IDCBTF);
        borrowModel.setFilterField(1, tenNhanVienCBTF);
        borrowModel.setFilterField(2, tenDocGiaCBTF);
        borrowModel.setFilterField(3, ngayMuonCBTF);
        borrowModel.setFilterField(4, ngayTraCBTF);
        borrowModel.setFilterField(5, tongTienCBTF);

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

                showBorrowInfo("","","","",null,"Thêm phiếu mượn");
            }
        });
        editBorrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(1234);
                int selectedRow = borrowTable.getSelectedRow();
                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu mượn muốn sửa thông tin", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
//
                String id = borrowTable.getValueAt(selectedRow, 0).toString();
//                String tenLoi = faultTable.getValueAt(selectedRow, 1).toString();
//                String maLoi = faultTable.getValueAt(selectedRow, 2).toString();
                showBorrowInfo(id,"","","",null,"Lưu thông tin");
            }
        });
        deleteBorrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = borrowTable.getSelectedRow();
                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu mượn muốn xóa", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa phiếu mượn này không?", "Question", JOptionPane.YES_NO_OPTION);
                if (choice != JOptionPane.YES_OPTION) {
                    return;
                }

                String id = borrowTable.getValueAt(selectedRow, 0).toString();
                borrowModel.deleteTestData(id);
//                System.out.println(id);

                JOptionPane.showMessageDialog(null, "Xóa lỗi thành công");
                borrowModel.renderTable();
            }
        });


//        xu ly chi tiet phieuu muon
        borrowDetailModel.setEditable(false);

        borrowDetailModel.addTestData();
        // table2.setModel(testExcel);
        borrowDetailTable.setModel(borrowDetailModel);
        TableRowSorter<BorrowDetailModel> sorterBorrowDetail = new TableRowSorter<>(borrowDetailModel);
        borrowDetailTable.setRowSorter(sorterBorrowDetail);
        borrowDetailTable.getTableHeader().setFont(new Font("Time News Roman", Font.PLAIN, 16));
        borrowDetailTable.getTableHeader().setBackground(Color.WHITE);

        addRowButton.addActionListener(e -> borrowDetailModel.addTestData());

        borrowDetailTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    System.out.println("double clicked");
                    int[] pos = { borrowDetailTable.getSelectedRow(), borrowDetailTable.getSelectedColumn() };
                    System.out.println(pos[0] + " " + pos[1]);
                }
            }
        });
        borrowDetailTable.getTableHeader().setReorderingAllowed(false);

        var maChiTietIDTF = AutoSuggestComboBox.createWithDeleteBtn(IDComboBox, 0,
                borrowDetailModel::getColumnValueToString, IDDelBtn);
        var maPhieuMuonIDTF = AutoSuggestComboBox.createWithDeleteBtn(maPhieuMuonCB, 1,
                borrowDetailModel::getColumnValueToString, maPhieuMuonDelBtn);
        var tenSachIDTF = AutoSuggestComboBox.createWithDeleteBtn(tenSachCB, 2, borrowDetailModel::getColumnValueToString,
                tenSachDelBtn);
        var tenLoiIDTF = AutoSuggestComboBox.createWithDeleteBtn(tenLoiCB, 3, borrowDetailModel::getColumnValueToString,
                tenLoiDelBtn);
        var soLuongIDTF = AutoSuggestComboBox.createWithDeleteBtn(soLuongCB, 4, borrowDetailModel::getColumnValueToString,
                soLuongDelBtn);
        var giaTienIDTF = AutoSuggestComboBox.createWithDeleteBtn(giaTienCB, 5, borrowDetailModel::getColumnValueToString,
                giaTienDelBtn);

        borrowDetailDelAll.addActionListener(e -> {
            maChiTietIDTF.setText("");
            maPhieuMuonIDTF.setText("");
            tenSachIDTF.setText("");
            tenLoiIDTF.setText("");
            soLuongIDTF.setText("");
            giaTienIDTF.setText("");
        });

        borrowDetailModel.setFilterField(0, maChiTietIDTF);
        borrowDetailModel.setFilterField(1, maPhieuMuonIDTF);
        borrowDetailModel.setFilterField(2, tenSachIDTF);
        borrowDetailModel.setFilterField(3, tenLoiIDTF);
        borrowDetailModel.setFilterField(4, soLuongIDTF);
        borrowDetailModel.setFilterField(5, giaTienIDTF);

        for (Iterator<TableColumn> it = borrowDetailTable.getColumnModel().getColumns().asIterator(); it.hasNext();) {
            var column = it.next();
            column.setMinWidth(100);
        }

        borrowDetailFilterBtn.addActionListener(e -> {
            TableUtils.filter(borrowDetailTable);
        });


//        xu ly lỗi
        faultModel.setEditable(false);
        faultModel.initModelTable(faultBUS.getDsLoi());

        faultTable.setModel(faultModel);
        TableRowSorter<FaultModel> sorterFault
                = new TableRowSorter<>(faultModel);
        faultTable.setRowSorter(sorterFault);
        faultTable.getTableHeader().setFont(new Font("Time News Roman", Font.PLAIN, 16));
        faultTable.getTableHeader().setBackground(Color.WHITE);

        addRowButton.addActionListener(e -> faultModel.addTestData());

        faultTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    System.out.println("double clicked fault");
                    int[] pos = {faultTable.getSelectedRow(), faultTable.getSelectedColumn()};
                    System.out.println(pos[0] + " " + pos[1]);
                    int selectedRow = faultTable.getSelectedRow();
                    if (selectedRow < 0) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn lỗi muốn sửa thông tin", "Warning", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    String id = faultTable.getValueAt(selectedRow, 0).toString();
                    String tenLoi = faultTable.getValueAt(selectedRow, 1).toString();
                    String heSo = faultTable.getValueAt(selectedRow, 2).toString();
                    showFaultInfo(id, tenLoi,heSo,"Lưu thông tin");
                }
            }
        });
        faultTable.getTableHeader().setReorderingAllowed(false);

        var faultIDTF = AutoSuggestComboBox.createWithDeleteBtn(faultIDComboBox, 0 ,faultModel::getColumnValueToString, faultIDDelBtn);
        var faultNameTF = AutoSuggestComboBox.createWithDeleteBtn(faultNameCB, 1,  faultModel::getColumnValueToString, faultNameDelBtn);
        var heSoTF = AutoSuggestComboBox.createWithDeleteBtn(heSoLoiCB, 2,  faultModel::getColumnValueToString, heSoLoiDelBtn);

        faultDeleteAllButton.addActionListener(e -> {
            faultIDTF.setText("");
            faultNameTF.setText("");
            heSoTF.setText("");
        });

        faultModel.setFilterField(0, faultIDTF);
        faultModel.setFilterField(1, faultNameTF);
        faultModel.setFilterField(2, heSoTF);

        for (Iterator<TableColumn> it = faultTable.getColumnModel().getColumns().asIterator(); it.hasNext(); ) {
            var column = it.next();
            column.setMinWidth(100);
        }

        faultFilterButton.addActionListener(e -> {
            TableUtils.filter(faultTable);
        });
        addFaultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFaultInfo("","","","Thêm lỗi");
            }
        });
        editFaultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = faultTable.getSelectedRow();
                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn lỗi muốn sửa thông tin", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String id = faultTable.getValueAt(selectedRow, 0).toString();
                String tenLoi = faultTable.getValueAt(selectedRow, 1).toString();
                String heSo = faultTable.getValueAt(selectedRow, 2).toString();
                showFaultInfo(id, tenLoi,heSo,"Lưu thông tin");

            }
        });
        deleteFaultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = faultTable.getSelectedRow();
                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn lỗi muốn xóa", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa lỗi này không?", "Question", JOptionPane.YES_NO_OPTION);
                if (choice != JOptionPane.YES_OPTION) {
                    return;
                }

                String id = faultTable.getValueAt(selectedRow, 0).toString();
                faultBUS.remove(id);
                faultModel.initModelTable(faultBUS.getDsLoi());

                JOptionPane.showMessageDialog(null, "Xóa lỗi thành công");
                faultModel.renderTable();
            }

        });

//        xu ly chi tiet loi
        faultDetailModel.setEditable(false);

        faultDetailModel.addTestData();
        // table2.setModel(testExcel);
        faultDetailTable.setModel(faultDetailModel);
        TableRowSorter<FaultDetailModel> sorter3 = new TableRowSorter<>(faultDetailModel);
        faultDetailTable.setRowSorter(sorter3);
        faultDetailTable.getTableHeader().setFont(new Font("Time News Roman", Font.PLAIN, 16));
        faultDetailTable.getTableHeader().setBackground(Color.WHITE);

        addRowButton.addActionListener(e -> faultDetailModel.addTestData());

        faultDetailTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    System.out.println("double clicked");
                    int[] pos = { faultDetailTable.getSelectedRow(), faultDetailTable.getSelectedColumn() };
                    System.out.println(pos[0] + " " + pos[1]);
                }
            }
        });
        faultDetailTable.getTableHeader().setReorderingAllowed(false);

        var maLoiChiTietTF = AutoSuggestComboBox.createWithDeleteBtn(maChiTietCB, 0,
                faultDetailModel::getColumnValueToString, maChiTietDelBtn);
        var maLoiTF = AutoSuggestComboBox.createWithDeleteBtn(maLoiCB, 1, faultDetailModel::getColumnValueToString,
                maLoiDelBtn);
        var tenDocGiaTF = AutoSuggestComboBox.createWithDeleteBtn(tenDocGiaCB, 2, faultDetailModel::getColumnValueToString,
                tenDocGiaDelBtn);
        var tenSachTF = AutoSuggestComboBox.createWithDeleteBtn(tenSachCB, 3, faultDetailModel::getColumnValueToString,
                tenSachDelBtn);
        var tenLoiTF = AutoSuggestComboBox.createWithDeleteBtn(tenLoiCB, 4, faultDetailModel::getColumnValueToString,
                tenLoiDelBtn);
        var soLuongTF = AutoSuggestComboBox.createWithDeleteBtn(soLuongCB, 5, faultDetailModel::getColumnValueToString,
                soLuongDelBtn);
        var tienDenTF = AutoSuggestComboBox.createWithDeleteBtn(tienDenCB, 6, faultDetailModel::getColumnValueToString,
                tienDenDelBtn);

        bookDeleteAllButton.addActionListener(e -> {
            maLoiChiTietTF.setText("");
            maLoiTF.setText("");
            tenDocGiaTF.setText("");
            tenSachTF.setText("");
            tenLoiTF.setText("");
            soLuongTF.setText("");
            tienDenTF.setText("");
        });

        faultDetailModel.setFilterField(0, maLoiChiTietTF);
        faultDetailModel.setFilterField(1, maLoiTF);
        faultDetailModel.setFilterField(2, tenDocGiaTF);
        faultDetailModel.setFilterField(3, tenSachTF);
        faultDetailModel.setFilterField(4, tenLoiTF);
        faultDetailModel.setFilterField(5, soLuongTF);
        faultDetailModel.setFilterField(6, tienDenTF);

        for (Iterator<TableColumn> it = faultDetailTable.getColumnModel().getColumns().asIterator(); it.hasNext();) {
            var column = it.next();
            column.setMinWidth(100);
        }

        bookFilterButton.addActionListener(e -> {
            TableUtils.filter(faultDetailTable);
        });


        addFaultDetailBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFaultDetailInfo("","Thêm thông tin");
            }
        });
        updateFaultDetailBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = faultDetailTable.getSelectedRow();
                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn lỗi chi tiết muốn sửa thông tin", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String id = faultDetailTable.getValueAt(selectedRow, 0).toString();
                showFaultDetailInfo(id,  "Lưu thông tin");
            }
        });
        deleteFaultDetailBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = faultDetailTable.getSelectedRow();
                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn lỗi chi tiết muốn xóa", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa lỗi chi tiết này không?", "Question", JOptionPane.YES_NO_OPTION);
                if (choice != JOptionPane.YES_OPTION) {
                    return;
                }

                String id = faultDetailTable.getValueAt(selectedRow, 0).toString();
                faultDetailModel.deleteTestData(id);

                JOptionPane.showMessageDialog(null, "Xóa lỗi thành công");
                faultDetailModel.renderTable();
            }
        });
    }

//    private BookBUS bookBUS = new BookBUS();
    public void borrowEditSelected(){
//        var rowsSelected = borrowTable.getSelectedRows();
//        for (var row: rowsSelected) {
//            var coords = borrowTable.getRowSorter().convertRowIndexToModel(row);
//            bookBUS.openBookEditDialog(coords);
//        }
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Book");
        frame.setContentPane(new BorrowUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
