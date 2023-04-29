package Borrow.GUI;

import Borrow.BUS.FaultDetailBUS;
import Borrow.FaultDetailModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;

public class FaultDetailInfoUI extends JFrame {
    private JButton btnSave;
    private JButton btnReset;
    private JComboBox cbxMaSach;
    private JComboBox cbxMaLoi;
    private JPanel panel1;
    private JTextField txtMaPhieuMuon;
    private JTextField txtTenSach;
    private JTextField txtTenLoi;
    private JTextField txtSoLuong;
    private JTextField txtTongTien;

    private FaultDetailUI faultDetailUI;
    private boolean isEditMode;

    private FaultDetailBUS faultDetailBUS = new FaultDetailBUS();
    private FaultDetailModel faultDetailModel = FaultDetailUI.faultDetailModel;

    public FaultDetailInfoUI(String id, String maSach,String tenSach,String maLoi,String tenLoi,int soLuong,double tongTien,String btnText) {

        isEditMode = btnText.equals("Lưu thông tin") ? true : false;
        setInset();
        initMaLoi();
        initMaSach(id);
        setInfo(id,  maSach,tenSach,maLoi,tenLoi,soLuong,tongTien,btnText);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isEditMode) {
                    editFaultDetail(maSach,maLoi);
                } else {
                    addFaultDetail();
                }
            }
        });

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cbxMaSach.setSelectedIndex(0);
                cbxMaLoi.setSelectedIndex(0);
                txtTenSach.setText("");
                txtTongTien.setText("");
                txtSoLuong.setText("");
            }
        });


        cbxMaSach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maSach = cbxMaSach.getSelectedItem().toString();
                String tenSach = faultDetailBUS.getNameBook(maSach);
                txtTenSach.setText(tenSach);
                setFieldTongTien();
            }
        });
        cbxMaLoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maLoi = cbxMaLoi.getSelectedItem().toString();
                String tenLoi = faultDetailBUS.getTenLoi(maLoi);
                txtTenLoi.setText(tenLoi);
                setFieldTongTien();
            }
        });

        txtSoLuong.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                setFieldTongTien();
            }
        });
    }

    public void addFaultDetail() {
        if (!validateEmpty()) {
            return;
        }

        String maPhieu = txtMaPhieuMuon.getText();
        String maSeri = cbxMaSach.getSelectedItem().toString();
        String maLoi = cbxMaLoi.getSelectedItem().toString();
        int so_luong = Integer.parseInt(txtSoLuong.getText());

        if (faultDetailModel.checkTrung(maSeri,maLoi)) {
            JOptionPane.showMessageDialog(null, "Đã tồn tại sách có lỗi này trong CSDL!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        faultDetailBUS.addField(maPhieu,maSeri,maLoi,so_luong);
        faultDetailModel.initModelTable(faultDetailBUS.getDsLoiCT(maPhieu));

        JOptionPane.showMessageDialog(null, "Thêm chi tiết lỗi thành công");
        dispose();
    }

    public void editFaultDetail(String maSachTruoc,String maLoiTruoc) {
        if (!validateEmpty()) {
            return;
        }

        String maPhieu = txtMaPhieuMuon.getText();
        String maSeri = cbxMaSach.getSelectedItem().toString();
        String maLoi = cbxMaLoi.getSelectedItem().toString();
        int so_luong = Integer.parseInt(txtSoLuong.getText());

        if (faultDetailModel.checkTrungCN(maSachTruoc,maLoiTruoc,maSeri,maLoi)) {
            JOptionPane.showMessageDialog(null, "Đã tồn tại sách có lỗi này trong CSDL!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        faultDetailBUS.updateField(maSachTruoc,maLoiTruoc,maPhieu,maSeri,maLoi,so_luong);
        faultDetailModel.initModelTable(faultDetailBUS.getDsLoiCT(maPhieu));

        JOptionPane.showMessageDialog(null, "Cập nhập chi tiết lỗi thành công");
        dispose();
    }

    public boolean validateEmpty() {
        StringBuilder sb = new StringBuilder();

        if(cbxMaSach.getSelectedItem().toString().equals("Chọn mã sách")){
            sb.append("Vui lòng chọn sách \n");
        }

        if(cbxMaLoi.getSelectedItem().toString().equals("Chọn lỗi sách")){
            sb.append("Vui lòng chọn mã lỗi \n");
        }
        int soLuong = 0;
        try{
            soLuong = Integer.parseInt(txtSoLuong.getText());
        }catch (Exception ex){
            sb.append("Số lượng không hợp lệ \n");
        }

        if(soLuong<1){
            sb.append("Số lượng không hợp lệ \n");
        }

        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(null, sb.toString(), "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public void setFieldTongTien(){
        String maLoi = cbxMaLoi.getSelectedItem().toString();
        String maSach = cbxMaSach.getSelectedItem().toString();
        boolean c1 = !maLoi.equals("Chọn lỗi sách");
        boolean c2 = !maSach.equals("Chọn mã sách");
        int soLuong = 0;
        boolean c3 = true;
        try{
            soLuong = Integer.parseInt(txtSoLuong.getText());
        }catch (Exception ex){
            c3 = false;
        }
        c3 = soLuong>0;
        if(c1&&c2&&c3){
            txtTongTien.setText(faultDetailBUS.getTongTien(maLoi,maSach,soLuong)+"");
        }
        else{
            txtTongTien.setText("");
        }
    }


    public void setInset() {
        Insets inset = new Insets(4, 10, 4, 10);
    }

    public void setInfo(String id, String maSach,String tenSach,String maLoi,String tenLoi,int soLuong,double tongTien,String btnText) {
        txtMaPhieuMuon.setText(id);

        txtTenLoi.setText(tenLoi);
        txtTenSach.setText(tenSach);
        txtSoLuong.setText(soLuong+"");
        txtTongTien.setText(tongTien+"");

        for (int i = 0; i < cbxMaSach.getItemCount(); i++) {
            if (cbxMaSach.getItemAt(i).equals(maSach)) {
                cbxMaSach.setSelectedIndex(i);
                break;
            }
        }

        for (int i = 0; i < cbxMaLoi.getItemCount(); i++) {
            if (cbxMaLoi.getItemAt(i).equals(maLoi)) {
                cbxMaLoi.setSelectedIndex(i);
                break;
            }
        }

        btnSave.setText(btnText);
    }

    public void initMaLoi() {
        cbxMaLoi.addItem("Chọn lỗi sách");
        for (String item:
             faultDetailBUS.getDsMaLoi()) {
            cbxMaLoi.addItem(item);
        }
    }

    public void initMaSach(String maPhieu) {
        cbxMaSach.addItem("Chọn mã sách");
        for (String item:
                faultDetailBUS.getDsMaSach(maPhieu)) {
            cbxMaSach.addItem(item);
        }
    }

    public JPanel getContentPane() {
        return panel1;
    }
}
