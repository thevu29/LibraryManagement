package Borrow.GUI;

import Borrow.BorrowModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BorrowInfoUI extends JFrame {
    private JButton buttonOk;
    private JButton buttonCancel;
    private JPanel mainPanel;
    private JPanel sachPanel;
    private JButton button1;


    private JPanel panel1;
    private JTextField txtMaPhieuMuon;
    private JTextField txtNgayMuon;
    private JTextField txtNgayTra;
    private JButton btnReset;
    private JButton btnSave;
    private JComboBox cbxTenDocGia;
    private JComboBox cbxSach;

    private BorrowUI borrowUI;
    private boolean isEditMode;

    public BorrowInfoUI(){

    }

    public BorrowInfoUI(BorrowUI borrowUI,String id, String tenDocGia, String ngayMuon, String ngayTra, ArrayList<String> sachMuon, String btnText) {
//        setContentPane(panel1);
//        getRootPane().setDefaultButton(buttonOk);
//
//        buttonOk.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                onOK();
//            }
//        });
//
//        buttonCancel.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                onCancel();
//            }
//        });
//
//        // call onCancel() when cross is clicked
//        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
//        addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent e) {
//                onCancel();
//            }
//        });

        // call onCancel() on ESCAPE
        panel1.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.gridx = 0;
                constraints.fill = GridBagConstraints.HORIZONTAL;
                constraints.anchor = GridBagConstraints.LINE_START;
                var comboBox = new JComboBox<String>();
                comboBox.setEditable(true);
                sachPanel.add(comboBox, constraints);
                sachPanel.revalidate();
            }
        });

        this.borrowUI = borrowUI;
        isEditMode = btnText.equals("Lưu thông tin") ? true : false;
        setInfo(id, tenDocGia,ngayMuon,ngayTra,null,btnText);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isEditMode) {
                    editBorrow();
                } else {
                    addBorrow();
                }
            }
        });

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isEditMode) {
                    txtMaPhieuMuon.setText("");
                }
                txtNgayMuon.setText("");
                txtNgayTra.setText("");
                cbxSach.setSelectedIndex(0);
                cbxTenDocGia.setSelectedIndex(0);
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public void addBorrow() {
        if (!validateEmpty()) {
            return;
        }

        String id = txtMaPhieuMuon.getText();

        BorrowModel borrowModel = BorrowUI.borrowModel;

        if(borrowModel.checkID(id)){
            JOptionPane.showMessageDialog(null, "Mã mượn đã tồn tại", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String maMuon = txtMaPhieuMuon.getText();
        String ngayMuon = txtNgayMuon.getText();
        String ngayTra = txtNgayTra.getText();
        String tenDocGia = cbxTenDocGia.getItemAt(cbxTenDocGia.getSelectedIndex()).toString();


        borrowModel.addData(maMuon,tenDocGia,ngayMuon,ngayTra,null);
        JOptionPane.showMessageDialog(null, "Thêm phiếu mượn thành công");
        dispose();
    }

    public void editBorrow() {
//        if (!validateEmpty()) {
//            return;
//        }
//
//        String id = txtFaultId.getText();
//        String tenLoi = txtTenLoi.getText();
//        double heSo = Double.parseDouble(txtHeSo.getText());
//
//        FaultModel faultModel = BorrowUI.faultModel;
//
//        faultModel.updateData(id,tenLoi,heSo);
//        JOptionPane.showMessageDialog(null, "Cập nhập lỗi thành công");
//        dispose();
    }

    public boolean validateEmpty() {
//        StringBuilder sb = new StringBuilder();
//
//        if (txtFaultId.getText().equals("")) {
//            sb.append("Mã khách hàng không được để trống \n");
//        }
//        if (txtTenLoi.getText().equals("")) {
//            sb.append("Tên khách hàng không được để trống \n");
//        }
//        if (txtHeSo.getText().equals("")) {
//            sb.append("Ngày sinh không được để trống \n");
//        }
//        if (txtCustomerAddress.getText().equals("")) {
//            sb.append("Địa chỉ không được để trống \n");
//        }
//        if (txtCustomerEmail.getText().equals("")) {
//            sb.append("Email không được để trống \n");
//        }
//        if (txtCustomerPhone.getText().equals("")) {
//            sb.append("Số điện thoại không được để trống \n");
//        }
//
//        if (sb.length() > 0) {
//            JOptionPane.showMessageDialog(null, sb.toString(), "Warning", JOptionPane.WARNING_MESSAGE);
//            return false;
//        }
        return true;
    }

//    public void showRegisAndExpireDate() {
//        lblRegisDate.setVisible(true);
//        lblExpireDate.setVisible(true);
//        txtRegisDate.setVisible(true);
//        txtExpireDate.setVisible(true);
//    }
//
//    public void hideRegisAndExpireDate() {
//        lblRegisDate.setVisible(false);
//        lblExpireDate.setVisible(false);
//        txtRegisDate.setVisible(false);
//        txtExpireDate.setVisible(false);
//    }

    public void setInset() {
        Insets inset = new Insets(4, 10, 4, 10);
//        txtFaultId.setMargin(inset);
//        txtTenLoi.setMargin(inset);
//        txtHeSo.setMargin(inset);
    }

    public void setInfo(String id, String tenDocGia, String ngayMuon,String ngayTra,ArrayList<String> sach,String btnText) {

        txtMaPhieuMuon.setText(id);
//        txtCustomerAddress.setText(address);
//        txtCustomerEmail.setText(email);
//        txtCustomerPhone.setText(phone);
//
//        int index = gender.equals("Nữ") ? 1 : 0;
//        cbxGender.setSelectedIndex(index);
//
//        for (int i = 0; i < cbxMembership.getItemCount(); i++) {
//            if (cbxMembership.getItemAt(i).equals(membership)) {
//                cbxMembership.setSelectedIndex(i);
//                break;
//            }
//        }
//
        btnSave.setText(btnText);
        txtMaPhieuMuon.setEnabled(!isEditMode);
    }

//    public void initMembershipValues() {
//        for (Membership membership : customerForm.getMembershipListInstance().getMembershipList()) {
//            cbxMembership.addItem(membership.getMembershipName());
//        }
//    }
//
//    public void initGenderValues() {
//        cbxGender.addItem("Nam");
//        cbxGender.addItem("Nữ");
//    }

    public JPanel getContentPane() {
        return panel1;
    }


}
