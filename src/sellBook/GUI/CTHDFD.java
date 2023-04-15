package sellBook.GUI;

import sellBook.BUS.CTHDBus;
import sellBook.DTO.CTHD;

import javax.swing.*;
import java.awt.event.*;

public class CTHDFD extends JDialog {
    private JPanel contentPane;
    private JButton btnRemove;
    private JButton buttonCancel;
    private JPanel mainPanel;
    private JTextField txtMaSeri;
    private JTextField txtHeSo;
    private JTextField txtMaPhieu;
    private JTextField txtMaChiTiet;
    private JButton btnInsert;
    private JButton btnUpdate;

    private CTHD cthd ;
    private CTHDGUI gui ;
    private CTHDBus bus =new CTHDBus();

    public CTHDFD(CTHDGUI gd){
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(btnRemove);

        this.gui = gd;
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        btnInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maCTHD = txtMaChiTiet.getText();
                String maHD = txtMaPhieu.getText();
                Double heSo = Double.parseDouble(txtHeSo.getText());
                String maSeri = txtMaSeri.getText();
                CTHD ct = new CTHD(maCTHD,maHD,heSo,maSeri);
                int smt = bus.insert(ct);
                if(smt>0){
                    JOptionPane.showMessageDialog(null,"Them CTHD THANH CONG");
                    gui.showAll();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Them San Pham khong thanh cong");
                }
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maCTHD = txtMaChiTiet.getText();
                String maHD = txtMaPhieu.getText();
                Double heSo = Double.parseDouble(txtHeSo.getText());
                String maSeri = txtMaSeri.getText();
                CTHD ct = new CTHD(maCTHD,maHD,heSo,maSeri);
                int smt = bus.update(ct);
                if(smt>0){
                    JOptionPane.showMessageDialog(null,"Cap Nhat CTHD THANH CONG");
                    gui.showAll();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Cap Nhat CTHD khong thanh cong");
                }
            }
        });
        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maCTHD = txtMaChiTiet.getText();
                int smt = bus.remove(maCTHD);
                if(smt>0){
                    JOptionPane.showMessageDialog(null,"Xoa CTHD THANH CONG");
                    gui.showAll();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Xoa CTHD KHONG THANH CONG");
                }
            }
        });
    }

    public CTHDFD(CTHD cthd,CTHDGUI gui) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(btnRemove);
        this.cthd = cthd;
        this.gui = gui;

        txtMaChiTiet.setText(cthd.getMa_chiTiet());
        txtHeSo.setText(String.valueOf(cthd.getHe_so()) );
        txtMaPhieu.setText(cthd.getMa_phieu());
        txtMaSeri.setText(cthd.getMa_series());

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        btnInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maCTHD = txtMaChiTiet.getText();
                String maHD = txtMaPhieu.getText();
                Double heSo = Double.parseDouble(txtHeSo.getText());
                String maSeri = txtMaSeri.getText();
                CTHD ct = new CTHD(maCTHD,maHD,heSo,maSeri);
                int smt = bus.insert(ct);
                if(smt>0){
                    JOptionPane.showMessageDialog(null,"Them CTHD THANH CONG");
                    gui.showAll();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Them San Pham khong thanh cong");
                }
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maCTHD = txtMaChiTiet.getText();
                String maHD = txtMaPhieu.getText();
                Double heSo = Double.parseDouble(txtHeSo.getText());
                String maSeri = txtMaSeri.getText();
                CTHD ct = new CTHD(maCTHD,maHD,heSo,maSeri);
                int smt = bus.update(ct);
                if(smt>0){
                    JOptionPane.showMessageDialog(null,"Cap Nhat CTHD THANH CONG");
                    gui.showAll();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Cap Nhat San Pham khong thanh cong");
                }
            }
        });

        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maCTHD = txtMaChiTiet.getText();
                int smt = bus.remove(maCTHD);
                if(smt>0){
                    JOptionPane.showMessageDialog(null,"Xoa CTHD THANH CONG");
                    gui.showAll();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Xoa CTHD KHONG THANH CONG");
                }
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }



    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
