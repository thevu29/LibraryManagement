package GUI;

import BUS.BorrowBUS;
import BUS.BorrowDetailBUS;
import DTO.BorrowDetailModel;
import DTO.BorrowModel;
import DTO.BorrowDetail;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class BorrowDetailInfo extends JFrame {
    private JPanel panel1;
    private JComboBox cbxMaSach;
    private JButton btnReset;
    private JButton btnSave;
    private JTextField txtTenSach;
    private JTextField txtMaPhieu;
    private JTextField txtTienTamTinh;
    private JTextField txtTongTien;

    private BorrowUI borrowUI;
    private boolean isEditMode;

    private BorrowDetailBUS borrowDetailBUS = new BorrowDetailBUS();
    private BorrowBUS borrowBUS = new BorrowBUS();

    private BorrowDetailModel borrowDetailModel = BorrowDetailUI.borrowDetailModel;
    private BorrowModel borrowModel = BorrowUI.borrowModel;

    public BorrowDetailInfo(){
    }

    public BorrowDetailInfo(BorrowUI borrowUI,String maPhieu,String maSach,String tenSach,String maThe,long soNgayMuon, String btnText) {
        this.borrowUI = borrowUI;

        khoiTaoSach();
        isEditMode = btnText.equals("Lưu thông tin") ? true : false;
        setInfo(maPhieu,maSach,tenSach,maThe,soNgayMuon,btnText);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isEditMode) {
                    editBorrow(maSach);
                } else {
                    addBorrow();
                }
            }
        });

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtTenSach.setText("");
                cbxMaSach.setSelectedIndex(0);
            }
        });


        cbxMaSach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = cbxMaSach.getSelectedItem().toString();
                String tenSach = "";
                if (!selectedItem.equals("Chọn sách")) {
                    tenSach = borrowDetailBUS.getNameBook(selectedItem);
                    double giaSach =  borrowDetailBUS.getPriceBook(selectedItem);
                    double tienTamTinh = borrowDetailBUS.getTienTamTinh(giaSach,(int)soNgayMuon);
                    txtTienTamTinh.setText(tienTamTinh+"");
                    int giaGiam = borrowDetailBUS.getGiaGiam(maThe);
                    double tienTong = tienTamTinh * (100 - giaGiam)/100;
                    txtTongTien.setText(tienTong+"");
                }else{
                    txtTienTamTinh.setText(0+"");
                    txtTongTien.setText(0+"");
                }
                txtTenSach.setText(tenSach);


            }
        });
    }



    public void addBorrow() {
        if (!validateEmpty()) {
            return;
        }

        String id = txtMaPhieu.getText();
        String maSach = cbxMaSach.getItemAt(cbxMaSach.getSelectedIndex()).toString();
        Double tienTamTinh = Double.parseDouble(txtTienTamTinh.getText());
        Double tienTong = Double.parseDouble(txtTongTien.getText());

        if(borrowDetailModel.checkIdAdd(id,maSach)){
            JOptionPane.showMessageDialog(null, "Sách đã được mượn");
            return;
        }

        borrowDetailBUS.insert(new BorrowDetail(id,maSach,"",tienTamTinh,tienTong));

        borrowDetailModel.initModelTable(borrowDetailBUS.getDsMuonCT(id));
        borrowModel.initModelTable(borrowBUS.getDsMuon());


        JOptionPane.showMessageDialog(null, "Thêm sách mượn thành công");

        dispose();
    }

    public void editBorrow(String maSachChuaCN) {
        if (!validateEmpty()) {
            return;
        }
        String id = txtMaPhieu.getText();
        String maSach = cbxMaSach.getItemAt(cbxMaSach.getSelectedIndex()).toString();
        Double tienTamTinh = Double.parseDouble(txtTienTamTinh.getText());
        Double tienTong = Double.parseDouble(txtTongTien.getText());

        if(borrowDetailModel.checkIdUpdate(maSachChuaCN,id,maSach)){
            JOptionPane.showMessageDialog(null, "Sách đã được mượn");
            return;
        }

        borrowDetailBUS.update((new BorrowDetail(id,maSach,"",tienTamTinh,tienTong)),maSachChuaCN);
        borrowDetailModel.initModelTable(borrowDetailBUS.getDsMuonCT(id));
        borrowModel.initModelTable(borrowBUS.getDsMuon());


        JOptionPane.showMessageDialog(null, "Cập nhập lỗi thành công");
        dispose();
    }

    public boolean validateEmpty() {
        StringBuilder sb = new StringBuilder();

        if(cbxMaSach.getSelectedIndex()==0){
            sb.append("Vui lòng chọn mã sách mượn \n");
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(null, sb.toString(), "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }



    public void setInset() {
        Insets inset = new Insets(4, 10, 4, 10);
    }

    public void setInfo(String maPhieu,String maSach,String tenSach,String maThe,long soNgayMuon,String btnText) {
        txtMaPhieu.setText(maPhieu);

        for (int i = 0; i < cbxMaSach.getItemCount(); i++) {
            if (cbxMaSach.getItemAt(i).equals(maSach)) {
                cbxMaSach.setSelectedIndex(i);
                break;
            }
        }


        txtTenSach.setText(tenSach);

        double giaSach = maSach == "" ? 0 : borrowDetailBUS.getPriceBook(maSach);
        double tienTamTinh = borrowDetailBUS.getTienTamTinh(giaSach,(int)soNgayMuon);
        txtTienTamTinh.setText(tienTamTinh+"");
        int giaGiam = borrowDetailBUS.getGiaGiam(maThe);
        double tienTong = tienTamTinh * (100 - giaGiam)/100;
        txtTongTien.setText(tienTong+"");


        btnSave.setText(btnText);
        txtMaPhieu.setEnabled(false);
    }


    public void khoiTaoSach() {
        ArrayList<String> dsMaSach = borrowDetailBUS.getDsMaSach();
        cbxMaSach.addItem("Chọn sách");
        for (String ma:
                dsMaSach) {
            cbxMaSach.addItem(ma);
        }
    }

    public JPanel getContentPane() {
        return panel1;
    }
}
