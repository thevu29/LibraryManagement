package Borrow.GUI;

import Borrow.BUS.BorrowBUS;
import Borrow.BUS.BorrowDetailBUS;
import Borrow.BorrowDetailModel;
import Borrow.BorrowModel;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BorrowDetailUI extends JFrame {
    private JTable borrowDetailTable;
    private JButton bookDeleteAllButton;
    private JButton bookFilterButton;
    private JTabbedPane tabbedPane2;
    private JButton IDDelBtn;
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
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnUpdate;
    private JPanel panel1;

    private BorrowUI borrowUI;



    public BorrowDetailUI(){

        borrowDetailTable.addComponentListener(new ComponentAdapter() {
        });
    }
    public void showBorrowDetailInfo(String maPhieu,String maSach,String tenSach,String maThe,long soNgayMon,String btnText) {
        BorrowDetailInfo borrowDetailInfo = new BorrowDetailInfo(new BorrowUI(),maPhieu,maSach,tenSach,maThe,soNgayMon, btnText);
        borrowDetailInfo.setContentPane(borrowDetailInfo.getContentPane());
        borrowDetailInfo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        borrowDetailInfo.setSize(500, 300);
        borrowDetailInfo.setLocationRelativeTo(null);
        borrowDetailInfo.setVisible(true);
    }

    public static BorrowDetailModel borrowDetailModel = new BorrowDetailModel();

    public BorrowModel borrowModel = BorrowUI.borrowModel;

    public static BorrowDetailBUS borrowDetailBUS = new BorrowDetailBUS();

    public BorrowBUS borrowBUS = new BorrowBUS();




    public BorrowDetailUI(String id,String maThe,long soNgayMuon) {
        borrowDetailTable.setModel(borrowDetailModel);
        borrowDetailTable.setDefaultEditor(Object.class, null);

        borrowDetailModel.initModelTable(borrowDetailBUS.getDsMuonCT(id));

        borrowDetailTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    System.out.println("double clicked fault");
                    int[] pos = {borrowDetailTable.getSelectedRow(), borrowDetailTable.getSelectedColumn()};
                    System.out.println(pos[0] + " " + pos[1]);
                    int selectedRow = borrowDetailTable.getSelectedRow();
                    if (selectedRow < 0) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn lỗi muốn sửa thông tin", "Warning", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    String id = borrowDetailTable.getValueAt(selectedRow, 0).toString();
                    String maSach = borrowDetailTable.getValueAt(selectedRow, 1).toString();
                    String tenSach = borrowDetailTable.getValueAt(selectedRow, 2).toString();
                    showBorrowDetailInfo(id, maSach,tenSach,maThe,soNgayMuon,"Lưu thông tin");
                }
            }
        });


        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showBorrowDetailInfo(id,"","",maThe,soNgayMuon,"Thêm sách mượn");
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = borrowDetailTable.getSelectedRow();
                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn thông tin sách muốn sửa ", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
//
                String maSach = borrowDetailTable.getValueAt(selectedRow, 1).toString();
                String tenSach = borrowDetailTable.getValueAt(selectedRow, 2).toString();
                showBorrowDetailInfo(id,maSach,tenSach,maThe,soNgayMuon,"Lưu thông tin");
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = borrowDetailTable.getSelectedRow();
                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn sách mượn muốn xóa", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn chắc muốn xóa?", "Question", JOptionPane.YES_NO_OPTION);
                if (choice != JOptionPane.YES_OPTION) {
                    return;
                }

                String id = borrowDetailTable.getValueAt(selectedRow, 0).toString();
                String maSach = borrowDetailTable.getValueAt(selectedRow, 1).toString();
                System.out.println(id);

                borrowDetailBUS.delete(id,maSach);
                borrowDetailModel.initModelTable(borrowDetailBUS.getDsMuonCT(id));
                borrowModel.initModelTable(borrowBUS.getDsMuon());

                JOptionPane.showMessageDialog(null, "Xóa phiếu mượn thành công");

            }
        });

    }

    public JPanel getContentPane() {
        return panel1;
    }
}
