package sellBook.GUI;

import sellBook.BUS.CTHDBus;
import sellBook.DAO.SellTicketDao;
import sellBook.DTO.CTHD;

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

public class CTHDGUI {
    private JPanel main;
    private JButton btnRemove;
    private JButton btnUpdate;
    private JButton btnFinish;
    private JTable tblCTHD;
    private JButton bookDeleteAllButton;
    private JButton btnFilter;
    private JTabbedPane tab;
    private JComboBox cboMaCTHD;
    private JButton btnXoaMaHD;
    private JComboBox cboMaHD;
    private JButton btnXoaHeSo;
    private JComboBox cboHeSo;
    private JComboBox cboMaSeri;
    private JButton btnXoaMaSeri;
    private JButton btnAdd;
    private JTextField txtTongHD;
    private String maHD;
    DefaultTableModel dtm = new DefaultTableModel();
    CTHDBus bus = new CTHDBus();

    public static List<CTHD> dsCTHD = new ArrayList<>();


    public CTHDGUI(String maHD) {
        tblCTHD.setDefaultEditor(Object.class, null);
        this.maHD = maHD;
        initTable();
        tab.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (tab.getSelectedIndex() == 0) {
                    cboMaHD.removeAllItems();
                    List<String> maHDs = bus.getAllMaHD(maHD);
                    for (String maHD : maHDs) {
                        cboMaHD.addItem(maHD);
                    }
                    cboMaHD.setEditable(false);
                    rmvListerBtnFilter();

                    btnFilter.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dsCTHD = bus.getDsCTHD(maHD);

                            changeTable();
                        }
                    });

                }
                else if (tab.getSelectedIndex() == 1) {
                    cboHeSo.removeAllItems();
                    List<Double> heSo = bus.getAllHeSo(maHD);
                    for (Double so : heSo) {
                        cboHeSo.addItem(so);
                    }
//                    btnFilter.removeActionListener(btnFilter.getActionListeners()[0]);
                    rmvListerBtnFilter();

                    btnFilter.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Double selected = Double.valueOf(cboHeSo.getSelectedItem().toString());
                            dsCTHD = bus.filterHeSo(maHD, selected);
                            changeTable();
                        }
                    });
                }

                else if (tab.getSelectedIndex() == 2) {
                    cboMaSeri.removeAllItems();
                    List<String> maSeries = bus.getAllMaSeries(maHD);
                    for (String seri : maSeries) {
                        cboMaSeri.addItem(seri);
                    }
                    rmvListerBtnFilter();

                    btnFilter.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String selected = cboMaSeri.getSelectedItem().toString();
                            dsCTHD = bus.filterMaSeri(maHD, selected);
                            changeTable();
                        }
                    });
                }
            }
        });
        initTab();
        tblCTHD.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    System.out.println("double clicked");
                    int[] pos = {tblCTHD.getSelectedRow(), tblCTHD.getSelectedColumn()};
                    String maHD = String.valueOf(tblCTHD.getValueAt(pos[0],0)) ;
                    //Khoi tao
                }
            }
        });
        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] pos = {tblCTHD.getSelectedRow(), tblCTHD.getSelectedColumn()};
                if(tblCTHD.getSelectedRow()!=-1){
                    String maHD = String.valueOf(tblCTHD.getValueAt(pos[0],0)) ;
                    String maSeri = String.valueOf(tblCTHD.getValueAt(pos[0],2)) ;
                    int smt = bus.remove(maHD,maSeri);
                    if(smt==0){
                        JOptionPane.showMessageDialog(null,"Xoa CTHD khong thanh cong! :>>");

                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Xoa CTHD THANH CONG");
                        showAll();
                        bus.updateStatusBook(maSeri,"AVAILABLE");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Can chon dong truoc khi xoa");
                }

            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(tblCTHD.getSelectedRow()==-1){
                    JOptionPane.showMessageDialog(null,"Can Chon 1 Hang De Cap Nhat");
                }
                else{
                    int[] pos = {tblCTHD.getSelectedRow(), tblCTHD.getSelectedColumn()};
                    String maSeri = String.valueOf(tblCTHD.getValueAt(pos[0],2)) ;
                    System.out.println(maSeri);
                    List<CTHD> ds = bus.filterMaCTHD(maHD,maSeri);
                    CTHD temp = ds.get(0);
                    CTHDFD dialog = new CTHDFD(temp,CTHDGUI.this);
                    dialog.pack();
                    dialog.setVisible(true);
                }

            }
        });
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CTHDFD dialog = new CTHDFD(CTHDGUI.this,maHD);
                dialog.pack();
                dialog.setVisible(true);
            }
        });
    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("CTHDGUI");
        frame.setContentPane(new CTHDGUI("1").main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void showAll(){
        dsCTHD = bus.getDsCTHD(maHD);
        changeTable();
    }

    private void changeTable(){
        dtm.setRowCount(0);
        String[] columns = {"Ma Hoa Don", "He So","Ma Seri","Ten Sach","Tong Tien"};
        dtm.setColumnIdentifiers(columns);
        for (CTHD ct : dsCTHD) {
            Object[] t = { ct.getMa_phieu(), ct.getHe_so(), ct.getMa_series(),ct.getTenSach(),ct.getTienSach()};
            dtm.addRow(t);
        }
        tblCTHD.setModel(dtm);

    }

    private void rmvListerBtnFilter(){
        // Xóa tất cả các sự kiện trên JButton
        ActionListener[] listeners = btnFilter.getActionListeners();
        for (ActionListener listener : listeners) {
            btnFilter.removeActionListener(listener);
        }
    }
    private void initTab(){
        if (tab.getSelectedIndex() == 0) {
            cboMaHD.removeAllItems();
            cboMaHD.setEditable(false);
            cboMaHD.setMaximumRowCount(2);
            List<String> maHDs = bus.getAllMaHD(maHD);
            for (String maHD : maHDs) {
                cboMaHD.addItem(maHD);
            }
            btnFilter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selected = String.valueOf(cboMaHD.getSelectedItem()) ;
                    dsCTHD = bus.getDsCTHD(maHD);
                    changeTable();
                }

            });

        }
        SellTicketDao temp = new SellTicketDao();
        txtTongHD.setText(String.valueOf(temp.tinhTongHoaDon(maHD)));
    }
    private void initTable(){
        List<CTHD> dsct = bus.getDsCTHD(maHD);

        String[] columns = {"Ma Hoa Don", "He So","Ma Seri","Ten Sach","Tong Tien"};
        dtm.setColumnIdentifiers(columns);

        for(CTHD ct:dsct){
            Object[] t = {ct.getMa_phieu(), ct.getHe_so(),ct.getMa_series(),ct.getTenSach(),ct.getTienSach()};
            dtm.addRow(t);
        }
        tblCTHD.setModel(dtm);

    }

    public JPanel getMain() {
        return main;
    }

    public void setMain(JPanel main) {
        this.main = main;
    }
}
