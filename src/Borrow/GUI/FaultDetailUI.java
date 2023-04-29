package Borrow.GUI;

import Borrow.BUS.BorrowBUS;
import Borrow.BUS.FaultDetailBUS;
import Borrow.BorrowModel;
import Borrow.FaultDetailModel;
import Utils.ComboBoxAutoSuggest.AutoSuggestComboBox;
import Utils.TableUtils;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

public class FaultDetailUI extends JFrame {
    private JTable faultDetailTable;
    private JButton bookDeleteAllButton;
    private JButton bookFilterButton;
    private JTabbedPane tabbedPane2;
    private JButton maSachDelBtn;
    private JComboBox maSachCbx;
    private JButton tenSachDelBtn;
    private JComboBox tenSachCbx;
    private JButton maLoiDelBtn;
    private JComboBox maLoiCbx;
    private JButton tenLoiDelBtn;
    private JComboBox tenLoiCbx;
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnUpdate;
    private JPanel panel1;

    public void showFaultDetailInfo(String id, String maSach, String tenSach, String maLoi, String tenLoi, int soLuong,
            double tongTien, String btnText) {
        FaultDetailInfoUI faultDetailInfoUI = new FaultDetailInfoUI(id, maSach, tenSach, maLoi, tenLoi, soLuong,
                tongTien, btnText);
        faultDetailInfoUI.setContentPane(faultDetailInfoUI.getContentPane());
        faultDetailInfoUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        faultDetailInfoUI.setSize(500, 700);
        faultDetailInfoUI.setLocationRelativeTo(null);
        faultDetailInfoUI.setVisible(true);
    }

    public static FaultDetailModel faultDetailModel = new FaultDetailModel();

    public BorrowModel borrowModel = BorrowUI.borrowModel;

    public BorrowBUS borrowBUS = new BorrowBUS();
    private FaultDetailBUS faultDetailBUS = new FaultDetailBUS();

    public FaultDetailUI() {
    }

    public FaultDetailUI(String id) {
        faultDetailTable.setModel(faultDetailModel);
        faultDetailTable.setDefaultEditor(Object.class, null);
        faultDetailModel.initModelTable(faultDetailBUS.getDsLoiCT(id));

        faultDetailTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    System.out.println("double clicked fault");
                    int[] pos = { faultDetailTable.getSelectedRow(), faultDetailTable.getSelectedColumn() };
                    System.out.println(pos[0] + " " + pos[1]);
                    int selectedRow = faultDetailTable.getSelectedRow();
                    if (selectedRow < 0) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn thông tin muốn sửa ", "Warning",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    //
                    String maPhieu = faultDetailTable.getValueAt(selectedRow, 0).toString();
                    String maSach = faultDetailTable.getValueAt(selectedRow, 1).toString();
                    String tenSach = faultDetailTable.getValueAt(selectedRow, 2).toString();
                    String maLoi = faultDetailTable.getValueAt(selectedRow, 3).toString();
                    String tenLoi = faultDetailTable.getValueAt(selectedRow, 4).toString();
                    int soLuong = Integer.parseInt(faultDetailTable.getValueAt(selectedRow, 5).toString());
                    double tienPhat = Double.parseDouble(faultDetailTable.getValueAt(selectedRow, 6).toString());
                    showFaultDetailInfo(maPhieu, maSach, tenSach, maLoi, tenLoi, soLuong, tienPhat, "Lưu thông tin");
                }
            }
        });
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFaultDetailInfo(id, "", "", "", "", 0, 0, "Thêm lỗi sách");
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = faultDetailTable.getSelectedRow();
                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn thông tin muốn sửa ", "Warning",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                //
                String maPhieu = faultDetailTable.getValueAt(selectedRow, 0).toString();
                String maSach = faultDetailTable.getValueAt(selectedRow, 1).toString();
                String tenSach = faultDetailTable.getValueAt(selectedRow, 2).toString();
                String maLoi = faultDetailTable.getValueAt(selectedRow, 3).toString();
                String tenLoi = faultDetailTable.getValueAt(selectedRow, 4).toString();
                int soLuong = Integer.parseInt(faultDetailTable.getValueAt(selectedRow, 5).toString());
                double tienPhat = Double.parseDouble(faultDetailTable.getValueAt(selectedRow, 6).toString());
                showFaultDetailInfo(maPhieu, maSach, tenSach, maLoi, tenLoi, soLuong, tienPhat, "Lưu thông tin");
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = faultDetailTable.getSelectedRow();
                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn chi tiết lỗi muốn xóa", "Warning",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn chắc muốn xóa?", "Question",
                        JOptionPane.YES_NO_OPTION);
                if (choice != JOptionPane.YES_OPTION) {
                    return;
                }

                String id = faultDetailTable.getValueAt(selectedRow, 0).toString();
                String maSach = faultDetailTable.getValueAt(selectedRow, 1).toString();
                String maLoi = faultDetailTable.getValueAt(selectedRow, 3).toString();
                faultDetailBUS.deleteField(id, maSach, maLoi);
                faultDetailModel.initModelTable(faultDetailBUS.getDsLoiCT(id));
                borrowModel.initModelTable(borrowBUS.getDsMuon());

                JOptionPane.showMessageDialog(null, "Xóa phiếu mượn thành công");

            }
        });

        faultDetailTable.getTableHeader().setReorderingAllowed(false);


        var maSachCBTF = AutoSuggestComboBox.createWithDeleteBtn(maSachCbx, 1, faultDetailModel::getColumnValueToString,
                maSachDelBtn);
        var tenSachCBTF = AutoSuggestComboBox.createWithDeleteBtn(tenSachCbx, 2,
                faultDetailModel::getColumnValueToString,
                tenSachDelBtn);
        var maLoiCBTF = AutoSuggestComboBox.createWithDeleteBtn(maLoiCbx, 3, faultDetailModel::getColumnValueToString,
                maLoiDelBtn);
        var tenLoiCBTF = AutoSuggestComboBox.createWithDeleteBtn(tenLoiCbx, 4, faultDetailModel::getColumnValueToString,
                tenLoiDelBtn);

        bookDeleteAllButton.addActionListener(e -> {
            maLoiCBTF.setText("");
            tenLoiCBTF.setText("");
            maSachCBTF.setText("");
            tenSachCBTF.setText("");
        });

        faultDetailModel.setFilterField(1, maSachCBTF);
        faultDetailModel.setFilterField(2, tenSachCBTF);
        faultDetailModel.setFilterField(3, maLoiCBTF);
        faultDetailModel.setFilterField(4, tenLoiCBTF);


        for (Iterator<TableColumn> it = faultDetailTable.getColumnModel().getColumns().asIterator(); it.hasNext();) {
            var column = it.next();
            column.setMinWidth(100);
        }

        bookFilterButton.addActionListener(e -> {
            TableUtils.filter(faultDetailTable);
        });
    }

    public JPanel getContentPane() {
        return panel1;
    }

}
