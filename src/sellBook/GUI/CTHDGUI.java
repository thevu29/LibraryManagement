package sellBook.GUI;

import sellBook.BUS.CTHDBus;
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
    private JButton XÓAKHỎIHÓAĐƠNButton;
    private JButton CẬPNHẬTGIỎHÀNGButton;
    private JButton THÀNHCÔNGButton;
    private JButton testAdd;
    private JTable tblCTHD;
    private JButton bookDeleteAllButton;
    private JButton btnFilter;
    private JTabbedPane tab;
    private JButton btnXoaMaCTHD;
    private JComboBox cboMaCTHD;
    private JButton btnXoaMaHD;
    private JComboBox cboMaHD;
    private JButton btnXoaHeSo;
    private JComboBox cboHeSo;
    private JComboBox cboMaSeri;
    private JButton btnXoaMaSeri;
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
                if (tab.getSelectedIndex() == 0) { // Check if maHD tab is selected
                    // Clear existing items in maHDComboBox
                    cboMaCTHD.removeAllItems();
                    List<String> maCTHDs = bus.getAllMaCTHD(maHD);
                    for (String maCTHD : maCTHDs) {
                        cboMaCTHD.addItem(maCTHD);
                    }
                    rmvListerBtnFilter();
                    btnFilter.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String selected = String.valueOf(cboMaCTHD.getSelectedItem()) ;
                            dsCTHD = bus.filterMaCTHD(maHD,selected);
                            System.out.println(CTHDGUI.dsCTHD.size());
                            changeTable();
                        }

                    });

                }
                else if (tab.getSelectedIndex() == 1) {
                    cboMaHD.removeAllItems();
                    List<String> maHDs = bus.getAllMaHD(maHD);
                    for (String maHD : maHDs) {
                        cboMaHD.addItem(maHD);
                    }

                }
                else if (tab.getSelectedIndex() == 2) {
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

                else if (tab.getSelectedIndex() == 3) {
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
                    String maHD = String.valueOf(tblCTHD.getValueAt(pos[0],pos[1])) ;
                    //Khoi tao
                }
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

    private void changeTable(){
        dtm.setRowCount(0);
        String[] columns = {"Mã Chi Tiet", "Ma Hoa Don", "He So","Ma Seri"};
        dtm.setColumnIdentifiers(columns);
        for (CTHD ct : dsCTHD) {
            Object[] t = {ct.getMa_chiTiet(), ct.getMa_phieu(), ct.getHe_so(), ct.getMa_series()};
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
            cboMaCTHD.removeAllItems();
            List<String> maCTHDs = bus.getAllMaCTHD(maHD);
            for (String maCTHD : maCTHDs) {
                cboMaCTHD.addItem(maCTHD);
            }
            btnFilter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selected = String.valueOf(cboMaCTHD.getSelectedItem()) ;
                    dsCTHD = bus.filterMaCTHD(maHD,selected);
                    changeTable();
                }

            });

        }



    }
    private void initTable(){
        List<CTHD> dsct = bus.getDsHD(maHD);

        String[] columns = {"Mã Chi Tiet", "Ma Hoa Don", "He So","Ma Seri"};
        dtm.setColumnIdentifiers(columns);

        for(CTHD ct:dsct){
            Object[] t = {ct.getMa_chiTiet(), ct.getMa_phieu(), ct.getHe_so(),ct.getMa_series()};
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
