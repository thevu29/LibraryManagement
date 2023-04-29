package Borrow.GUI;

import Borrow.BUS.FaultDetailBUS;
import Borrow.FaultDetailModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FaultDetailUI extends JFrame {
    private JTable faultDetailTable;
    private JButton bookDeleteAllButton;
    private JButton bookFilterButton;
    private JTabbedPane tabbedPane2;
    private JButton maChiTietDelBtn;
    private JComboBox maChiTietCB;
    private JButton maLoiDelBtn;
    private JComboBox maLoiCB;
    private JButton tenDocGiaDelBtn;
    private JComboBox tenDocGiaCB;
    private JButton tenSachDelBtn;
    private JComboBox tenSachCB;
    private JButton tenLoiDelBtn;
    private JComboBox tenLoiCB;
    private JButton soLuongDelBtn;
    private JComboBox soLuongCB;
    private JButton tienDenDelBtn;
    private JComboBox tienDenCB;
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnUpdate;
    private JPanel panel1;

    public void showFaultDetailInfo(String id, String maSach,String tenSach,String maLoi,String tenLoi,int soLuong,double tongTien,String btnText) {
        FaultDetailInfoUI faultDetailInfoUI = new FaultDetailInfoUI( id, maSach,tenSach,maLoi,tenLoi,soLuong,tongTien,btnText);
        faultDetailInfoUI.setContentPane(faultDetailInfoUI.getContentPane());
        faultDetailInfoUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        faultDetailInfoUI.setSize(500, 700);
        faultDetailInfoUI.setLocationRelativeTo(null);
        faultDetailInfoUI.setVisible(true);
    }
    public static FaultDetailModel faultDetailModel = new FaultDetailModel();
    private FaultDetailBUS faultDetailBUS = new FaultDetailBUS();

    public FaultDetailUI(){}

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
                    int[] pos = {faultDetailTable.getSelectedRow(), faultDetailTable.getSelectedColumn()};
                    System.out.println(pos[0] + " " + pos[1]);
                    int selectedRow = faultDetailTable.getSelectedRow();
                    if (selectedRow < 0) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn thông tin muốn sửa ", "Warning", JOptionPane.WARNING_MESSAGE);
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
                    showFaultDetailInfo(maPhieu,maSach,tenSach,maLoi,tenLoi,soLuong,tienPhat,"Lưu thông tin");
                }
            }
        });
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFaultDetailInfo(id,"","","","",0,0,"Thêm lỗi sách");
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = faultDetailTable.getSelectedRow();
                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn thông tin muốn sửa ", "Warning", JOptionPane.WARNING_MESSAGE);
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
                showFaultDetailInfo(maPhieu,maSach,tenSach,maLoi,tenLoi,soLuong,tienPhat,"Lưu thông tin");
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = faultDetailTable.getSelectedRow();
                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn chi tiết lỗi muốn xóa", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn chắc muốn xóa?", "Question", JOptionPane.YES_NO_OPTION);
                if (choice != JOptionPane.YES_OPTION) {
                    return;
                }

                String id = faultDetailTable.getValueAt(selectedRow, 0).toString();
                String maSach = faultDetailTable.getValueAt(selectedRow, 1).toString();
                String maLoi = faultDetailTable.getValueAt(selectedRow, 3).toString();
                faultDetailBUS.deleteField(id,maSach,maLoi);
                faultDetailModel.initModelTable(faultDetailBUS.getDsLoiCT(id));

                JOptionPane.showMessageDialog(null, "Xóa phiếu mượn thành công");

            }
        });
    }

    public JPanel getContentPane() {
        return panel1;
    }


}
