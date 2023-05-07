package Borrow.GUI;

import Borrow.BUS.BorrowBUS;
import Borrow.BorrowModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class BorrowInfoUI extends JFrame {
    private JButton buttonOk;
    private JButton buttonCancel;
    private JPanel sachPanel;
    private JButton button1;


    private JPanel panel1;
    private JTextField txtMaPhieuMuon;
    private JTextField txtSoNgayMuon;
    private JTextField txtNgayHenTra;
    private JButton btnReset;
    private JButton btnSave;
    private JComboBox cbxMaThe;
    private JTextField txtTenDocGia;
    private JComboBox cbxSach;

    private BorrowUI borrowUI;
    private boolean isEditMode;

    private BorrowBUS borrowBUS = new BorrowBUS();

    private BorrowModel borrowModel = BorrowUI.borrowModel;

    public BorrowInfoUI(){
    }

    public BorrowInfoUI(BorrowUI borrowUI,String id, String maThe,String tenDocGia,int soNgayMuon, String btnText,String login_id) {
        this.borrowUI = borrowUI;

        khoiTaoThe();
        isEditMode = btnText.equals("Lưu thông tin") ? true : false;
        setInfo(id,maThe,tenDocGia,soNgayMuon,btnText);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isEditMode) {
                    editBorrow(login_id);
                } else {
                    addBorrow(login_id);
                    txtMaPhieuMuon.setEnabled(true);
                }
            }
        });

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtTenDocGia.setText("");
                txtSoNgayMuon.setText("");
                cbxMaThe.setSelectedIndex(0);
            }
        });

        cbxMaThe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = cbxMaThe.getSelectedItem().toString();
                String tenNhanVien = "";
                if (!selectedItem.equals("Chọn thẻ thành viên")) {
                    tenNhanVien = borrowBUS.getNameMember(selectedItem);
                }
                txtTenDocGia.setText(tenNhanVien);
            }
        });
    }



    public void addBorrow(String login_id) {
        if (!validateEmpty()) {
            return;
        }

        String id = txtMaPhieuMuon.getText();
        String maThe = cbxMaThe.getItemAt(cbxMaThe.getSelectedIndex()).toString();
        int soNgayMuon = Integer.parseInt(txtSoNgayMuon.getText());

        borrowBUS.add(id,maThe,login_id,soNgayMuon);

        borrowModel.initModelTable(borrowBUS.getDsMuon());

        JOptionPane.showMessageDialog(null, "Thêm phiếu mượn thành công");
        dispose();
    }

    public void editBorrow(String login_id) {
        if (!validateEmpty()) {
            return;
        }
        String id = txtMaPhieuMuon.getText();
        String maThe = cbxMaThe.getItemAt(cbxMaThe.getSelectedIndex()).toString();
        int soNgayMuon = Integer.parseInt(txtSoNgayMuon.getText());

        borrowBUS.updateField(id,maThe,soNgayMuon);

        borrowModel.initModelTable(borrowBUS.getDsMuon());
        JOptionPane.showMessageDialog(null, "Cập nhập lỗi thành công");
        dispose();
    }

    public boolean validateEmpty() {
        StringBuilder sb = new StringBuilder();

        if (txtSoNgayMuon.getText().equals("")) {
            sb.append("Số ngày mượn không được để trống \n");
        }else{
            try {
                int soNgayMuon = Integer.parseInt(txtSoNgayMuon.getText());
                if(soNgayMuon<1){
                    sb.append("Số ngày mượn không hợp lệ \n");

                }
            }catch (Exception ex){
                sb.append("Số ngày mượn không hợp lệ \n");
            }
        }

        if(cbxMaThe.getSelectedIndex()==0){
            sb.append("Vui lòng chọn mã thẻ mượn \n");
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

    public void setInfo(String id, String maThe,String tenDocGia,int soNgayMuon,String btnText) {

        txtMaPhieuMuon.setText(id);

        ArrayList<String> dsMaThe = borrowBUS.getDsMaThe();
        int index = 0;
        int i =1;
        for (String ma:
                dsMaThe) {

            if(ma.equals(maThe)){
                index = i;
                break;
            }
            i+=1;
        }

        cbxMaThe.setSelectedIndex(index);

        txtTenDocGia.setText(tenDocGia);

        if(soNgayMuon!=0){
            txtSoNgayMuon.setText(soNgayMuon+"");
        }

        btnSave.setText(btnText);
        txtMaPhieuMuon.setEnabled(false);
    }


    public void khoiTaoThe() {
        ArrayList<String> dsMaThe = borrowBUS.getDsMaThe();
        cbxMaThe.addItem("Chọn thẻ thành viên");
        for (String ma:
             dsMaThe) {
            cbxMaThe.addItem(ma);
        }
    }

    public JPanel getContentPane() {
        return panel1;
    }
}
