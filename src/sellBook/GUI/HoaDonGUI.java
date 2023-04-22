package sellBook.GUI;

import sellBook.BUS.SellTicketBus;
import sellBook.DTO.HoaDon;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class HoaDonGUI {
    private JPanel main;
    private JTable tblCheckOut;
    private JButton btnRemove;
    private JButton CẬPNHẬTHÓAĐƠNButton;
    private JButton THÀNHCÔNGButton;
    private JButton btnAdd;
    private JButton bookDeleteAllButton;
    private JButton btnFilter;
    private JTabbedPane tab;
    private JButton btnXoaMaHD;
    private JComboBox cboMaHD;
    private JButton btnXoaMaNV;
    private JComboBox cboMaNV;
    private JComboBox cboMaKH;
    private JButton btnXoaMaKH;

    DefaultTableModel dtm = new DefaultTableModel();
    public static List<HoaDon> dshd = new ArrayList<>();
    SellTicketBus bus = new SellTicketBus();

    public HoaDonGUI() {
        initTable();
        initTabPane();
        tblCheckOut.setDefaultEditor(Object.class, null);
        tblCheckOut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    System.out.println("double clicked");
                    int[] pos = {tblCheckOut.getSelectedRow(), tblCheckOut.getSelectedColumn()};
                    String maHD = String.valueOf(tblCheckOut.getValueAt(pos[0],pos[0])) ;
                    //Khoi tao
                    var cthd = new CTHDGUI(maHD);
                    JFrame frame = new JFrame("ChiTietHoaDon");
                    frame.setContentPane(cthd.getMain());
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);
                }
            }
        });
        //khi thay doi tab

        tab.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (tab.getSelectedIndex() == 0) { // Check if maHD tab is selected
                    // Clear existing items in maHDComboBox

                    cboMaHD.removeAllItems();

                    // Call getAllMaHD method from SellTicketBus and add the returned values to maHDComboBox
                    List<String> maHDs = bus.getAllMaHD();
                    for (String maHD : maHDs) {
                        cboMaHD.addItem(maHD);
                    }
                    rmvListenerBtnFilter();
                    btnFilter.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String selected = String.valueOf(cboMaHD.getSelectedItem()) ;
                            changeTable(bus.filterMaHD(selected));
                        }

                    });

                }
                else if (tab.getSelectedIndex() == 1) { // Check if maHD tab is selected
                    // Clear existing items in maHDComboBox
                    cboMaNV.removeAllItems();
                    // Call getAllMaHD method from SellTicketBus and add the returned values to maHDComboBox
                    List<String> maNVs = bus.getAllMaNV();
                    for (String maNV : maNVs) {
                        cboMaNV.addItem(maNV);
                    }
                    rmvListenerBtnFilter();
                    btnFilter.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String selected = String.valueOf(cboMaNV.getSelectedItem()) ;
                            changeTable(bus.filterMaNV(selected));
                        }
                    });
                }
                else if (tab.getSelectedIndex() == 2) { // Check if maHD tab is selected
                    // Clear existing items in maHDComboBox
                    cboMaNV.removeAllItems();
                    // Call getAllMaHD method from SellTicketBus and add the returned values to maHDComboBox
                    List<String> maKHs = bus.getAllMaKH();
                    for (String maKH : maKHs) {
                        cboMaKH.addItem(maKH);
                    }
                    rmvListenerBtnFilter();
                    btnFilter.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String selected = String.valueOf(cboMaKH.getSelectedItem()) ;
                            changeTable(bus.filterMaKH(selected));
                        }
                    });


                }
            }
        });

        //action listerner lọc

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var dialog = new HoaDonFD(HoaDonGUI.this);
                dialog.pack();
                dialog.setVisible(true);
            }
        });
        THÀNHCÔNGButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] pos = {tblCheckOut.getSelectedRow(), tblCheckOut.getSelectedColumn()};
                if(pos[0]==-1){
                    JOptionPane.showMessageDialog(null,"Can phai chon 1 hang ");
                }
                else{
                    String maHD = String.valueOf(tblCheckOut.getValueAt(pos[0],0)) ;
                    int smt =bus.remove(maHD);
                    if(smt>0){
                        JOptionPane.showMessageDialog(null,"Xoa Hoa Don thanh cong");
                        showAll();
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Xoa Hoa don KHONG THANH CONG");
                    }
                }


            }
        });
        CẬPNHẬTHÓAĐƠNButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] pos = {tblCheckOut.getSelectedRow(), tblCheckOut.getSelectedColumn()};
                if(pos[0]==-1){
                    JOptionPane.showMessageDialog(null,"Can phai chon 1 hang ");
                }
                else {
                    String maHD = String.valueOf(tblCheckOut.getValueAt(pos[0],0));

                    var dialog = new HoaDonFD(maHD,HoaDonGUI.this);
                    dialog.pack();
                    dialog.setVisible(true);
                }

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("HoaDonGUI");
        frame.setContentPane(new HoaDonGUI().main);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    private void rmvListenerBtnFilter(){
        // Xóa tất cả các sự kiện trên JButton
        ActionListener[] listeners = btnFilter.getActionListeners();
        for (ActionListener listener : listeners) {
            btnFilter.removeActionListener(listener);
        }
    }
    private void initTabPane(){
        cboMaHD.removeAllItems();

        // Call getAllMaHD method from SellTicketBus and add the returned values to maHDComboBox
        List<String> maHDs = bus.getAllMaHD();
        for (String maHD : maHDs) {
            cboMaHD.addItem(maHD);
        }
        btnFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = String.valueOf(cboMaHD.getSelectedItem()) ;
                changeTable(bus.filterMaHD(selected));
            }

        });
    }

    private void changeTable(List<HoaDon> dshd){

        dtm.setRowCount(0);
        String[] columns = {"Mã Phieu", "Ma Nhan Vien", "Ma Khach Hang"};
        dtm.setColumnIdentifiers(columns);
        if(!dshd.isEmpty()){
            for(HoaDon hd:dshd){
                Object[] t = {hd.getMa_phieu(), hd.getMa_nv(), hd.getMa_KH()};
                dtm.addRow(t);
            }
        }

        tblCheckOut.setModel(dtm);
    }
    private void initTable(){
        String[] columns = {"Mã Phieu", "Ma Nhan Vien", "Ma Khach Hang"};
        dtm.setColumnIdentifiers(columns);

        dshd =  bus.getAllSellTicket();
        if(!dshd.isEmpty()){
            for(HoaDon hd:dshd){
                Object[] t = {hd.getMa_phieu(), hd.getMa_nv(), hd.getMa_KH()};
                dtm.addRow(t);
            }
        }

        tblCheckOut.setModel(dtm);
    }
    public void showAll(){
        changeTable(bus.getAllSellTicket());
    }

}