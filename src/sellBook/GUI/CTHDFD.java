package sellBook.GUI;

import sellBook.BUS.CTHDBus;
import sellBook.BUS.SellTicketBus;
import sellBook.DTO.CTHD;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class CTHDFD extends JDialog {
    private JPanel contentPane;
    private JButton btnRemove;
    private JButton buttonCancel;
    private JPanel mainPanel;
    private JTextField txtMaSeri;
    private JTextField txtHeSo;
    private JComboBox cboMaPhieu;
    private JTextField txtMaChiTiet;
    private JButton btnInsert;
    private JButton btnUpdate;
    private JPanel tacGiaPanel;
    private JTextField txtTenSach;
    private JTextField txtTongTien;

    private CTHD cthd ;
    private CTHDGUI gui ;
    private CTHDBus bus =new CTHDBus();
    private SellTicketBus busHD = new SellTicketBus();

    public CTHDFD(CTHDGUI gd,String maHD,HoaDonGUI guiHD){

        List<String> dsMaHD = busHD.getAllMaHD();


        cboMaPhieu.addItem(maHD);

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(btnRemove);
        txtHeSo.setText("1");

        btnRemove.setEnabled(false);
        btnUpdate.setEnabled(false);
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

                String maHD = String.valueOf(cboMaPhieu.getSelectedItem()) ;
                double heSo = 1;
                if(!txtHeSo.getText().isEmpty()){
                    heSo = Double.parseDouble(txtHeSo.getText());
                }
                String maSeri = txtMaSeri.getText();
                CTHD ct = new CTHD();
                ct.setMa_phieu(maHD);
                ct.setMa_series(maSeri);
                ct.setHe_so(heSo);
                ct.setTenSach(txtTenSach.getText());
                if(ct.getTenSach().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Ma series chua dung");
                }
                else{
                    int smt = bus.insert(ct);
                    if(smt>0){

                        JOptionPane.showMessageDialog(null,"Them CTHD THANH CONG");
                        gui.showAll();
                        guiHD.showAll();
                        //Cap Nhat Trang Thai Sach
                        smt = bus.updateStatusBook(ct.getMa_series(),"SOLD");
                        if(smt==0){
                            JOptionPane.showMessageDialog(null,"Cap Nhat Trang Thai Sach Loi");
                        }
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Them San Pham khong thanh cong");
                    }
                }

            }
        });
        eventTxtMaSeri();
        eventTxtHeSo();
    }


    public CTHDFD(CTHD cthd,CTHDGUI gui,HoaDonGUI guiHd) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(btnRemove);
        this.cthd = cthd;
        this.gui = gui;

        double tien = bus.tienSach(cthd.getMa_series());
        double hs = cthd.getHe_so();
        txtTongTien.setText(String.valueOf(tien*(1-hs)));

        btnInsert.setEnabled(false);

        txtHeSo.setText(String.valueOf(cthd.getHe_so()) );
        cboMaPhieu.addItem(cthd.getMa_phieu());
        txtMaSeri.setText(cthd.getMa_series());

        txtMaSeri.setEditable(false);
        txtTenSach.setText(cthd.getTenSach());
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });


        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maHD = String.valueOf(cboMaPhieu.getSelectedItem()) ;
                Double heSo = Double.parseDouble(txtHeSo.getText());
                String maSeri = txtMaSeri.getText();

                double tienSach = bus.tinhTienSach(maHD,maSeri);
                if(txtTenSach.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Ma Seri Khong Dung");
                }
                else{
                    CTHD ct = new CTHD(maHD,heSo,maSeri);
                    int smt = bus.update(ct);
                    if(smt>0){
                        JOptionPane.showMessageDialog(null,"Cap Nhat CTHD THANH CONG");
                        gui.showAll();
                        guiHd.showAll();
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Cap Nhat San Pham khong thanh cong");
                    }
                }

            }
        });

        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maHD = String.valueOf(cboMaPhieu.getSelectedItem());
                String maSeri = txtMaSeri.getText();

                int dialogResult = JOptionPane.showConfirmDialog(null,"Bạn có muốn xóa không ?","Remove", JOptionPane.YES_NO_OPTION);
                if(dialogResult == JOptionPane.YES_OPTION){
                    int smt = bus.remove(maHD,maSeri);
                    if(smt>0){
                        JOptionPane.showMessageDialog(null,"Xoa CTHD THANH CONG");

                        gui.showAll();
                        guiHd.showAll();
                        smt = bus.updateStatusBook(maSeri,"AVAILABLE");
                        if(smt==0){
                            JOptionPane.showMessageDialog(null,"Cap Nhat Trang Thai Sach Loi");
                        }
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Xoa CTHD KHONG THANH CONG");
                    }
                }

            }
        });
        eventTxtMaSeri();
        eventTxtHeSo();

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

    public void eventTxtHeSo(){
        txtHeSo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if(!txtTenSach.getText().isEmpty() && !txtHeSo.getText().isEmpty()){
                    double tien = bus.tienSach(txtMaSeri.getText());
                    double hs = Double.parseDouble(txtHeSo.getText());
                    txtTongTien.setText(String.valueOf(tien*(1-hs)));
                }
            }
        });
    }

    public void eventTxtMaSeri(){
        txtMaSeri.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                String tenSach = bus.goiYTenSach(txtMaSeri.getText());
                txtTenSach.setText(tenSach);
                if(!tenSach.isEmpty()){
                    double tien = bus.tienSach(txtMaSeri.getText());
                    double hs = Double.parseDouble(txtHeSo.getText());
                    txtTongTien.setText(String.valueOf(tien*(1-hs)));
                }
            }
        });
    }

}
