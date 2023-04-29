package Borrow.GUI;

import Borrow.BUS.BorrowBUS;
import Borrow.BUS.FaultBUS;
import Borrow.BorrowModel;
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
    private BorrowBUS borrowBUS = new BorrowBUS();
    public BorrowModel borrowModel = BorrowUI.borrowModel;

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

        String tenLoi = txtTenLoi.getText();
        double heSo = Double.parseDouble(txtHeSo.getText());
        int isDeleted = 1;

        faultBUS.insert(new Fault(id,tenLoi,heSo));
        faultModel.initModelTable(faultBUS.getDsLoi());
        borrowModel.initModelTable(borrowBUS.getDsMuon());
        JOptionPane.showMessageDialog(null, "Thêm lỗi thành công");
        dispose();
        txtFaultId.setEnabled(true);
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
        borrowModel.initModelTable(borrowBUS.getDsMuon());
        JOptionPane.showMessageDialog(null, "Cập nhập lỗi thành công");
        dispose();
    }

    public boolean validateEmpty() {
        StringBuilder sb = new StringBuilder();

        if (txtFaultId.getText().equals("")) {
            sb.append("Mã lỗi không được để trống \n");
        }
        if (txtTenLoi.getText().equals("")) {
            sb.append("Tên lỗi không được để trống \n");
        }
        if (txtHeSo.getText().equals("")) {
            sb.append("Hệ số không được để trống \n");
        }else{
            try
            {
                double heSo = Double.parseDouble(txtHeSo.getText());
                if(heSo<=0){
                    sb.append("Hệ số không hợp lệ \n");
                }
            }
            catch (NumberFormatException e)
            {
                sb.append("Hệ số không hợp lệ \n");
            }
        }


        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(null, sb.toString(), "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
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

        txtFaultId.setEnabled(false);
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
