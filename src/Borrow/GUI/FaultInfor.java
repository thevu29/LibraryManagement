package Borrow.GUI;

import Borrow.BUS.FaultBUS;
import Borrow.DTO.Fault;
import Borrow.FaultModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FaultInfor extends JFrame {
    private BorrowUI borrowUI;
    private boolean isEditMode;

    private FaultBUS faultBUS = new FaultBUS();

    public FaultInfor(BorrowUI borrowUI, String id, String tenLoi, String heSo,String btnText) {
        this.borrowUI = borrowUI;
        isEditMode = btnText.equals("Lưu thông tin") ? true : false;
        setInfo(id, tenLoi,heSo ,btnText);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isEditMode) {
                    editFault();
                } else {
                    addFault();
                }
            }
        });

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isEditMode) {
                    txtFaultId.setText("");
                }
                txtTenLoi.setText("");
                txtHeSo.setText("");

            }
        });


    }

    public void addFault() {
        if (!validateEmpty()) {
            return;
        }

        String id = txtFaultId.getText();

        FaultModel faultModel = BorrowUI.faultModel;

        if(faultModel.checkID(id)){
            JOptionPane.showMessageDialog(null, "Mã lỗi đã tồn tại", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String tenLoi = txtTenLoi.getText();
        double heSo = Double.parseDouble(txtHeSo.getText());
        int isDeleted = 1;

        faultModel.addData(id,tenLoi,heSo);
        faultBUS.insert(new Fault(id,tenLoi,heSo));
        JOptionPane.showMessageDialog(null, "Thêm lỗi thành công");
        dispose();
    }

    public void editFault() {
        if (!validateEmpty()) {
            return;
        }

        String id = txtFaultId.getText();
        String tenLoi = txtTenLoi.getText();
        double heSo = Double.parseDouble(txtHeSo.getText());
        int isDeleted = 1;

        FaultModel faultModel = BorrowUI.faultModel;

        faultBUS.update(new Fault(id,tenLoi,heSo));
        faultModel.initModelTable(faultBUS.getDsLoi());
        JOptionPane.showMessageDialog(null, "Cập nhập lỗi thành công");
        dispose();
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
    

    public void setInset() {
        Insets inset = new Insets(4, 10, 4, 10);
        txtFaultId.setMargin(inset);
        txtTenLoi.setMargin(inset);
        txtHeSo.setMargin(inset);
    }

    public void setInfo(String id, String tenLoi, String heSo,String btnText) {
        txtFaultId.setText(id);
        txtTenLoi.setText(tenLoi);
        txtHeSo.setText(heSo);

        txtFaultId.setEnabled(!isEditMode);
    }



    public JPanel getContentPane() {
        return panel1;
    }
    private JTextField txtFaultId;
    private JTextField txtTenLoi;
    private JTextField txtHeSo;
    private JButton btnSave;
    private JButton btnReset;
    private JPanel panel1;


}
