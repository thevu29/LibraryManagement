package sellBook;

import Utils.ComboBoxAutoSuggest.AutoSuggestComboBox;
import Utils.TableUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HoaDon {
    private JPanel main;
    private JButton XÓAKHỎIHÓAĐƠNButton;
    private JButton CẬPNHẬTGIỎHÀNGButton;
    private JButton THÀNHCÔNGButton;
    private JButton testAdd;
    private JTable tblCheckOut;
    private JButton bookDeleteAllButton;
    private JButton btnFilter;
    private JTabbedPane tabbedPane2;
    private JButton btnIdXoa;
    private JComboBox cboIdHD;
    private JButton btnXoaTenKH;
    private JComboBox cboTenKH;
    private JButton authorDelBtn;
    private JComboBox authorCB;
    private JButton priceDelBtn;
    private JComboBox priceCB;
    private JButton statusDelBtn;
    private JComboBox statusCB;
    HDTableModel hdTBL = new HDTableModel();

    public HoaDon(){
        initTable();
        var txtId = AutoSuggestComboBox.createWithDeleteBtn(cboIdHD,0,hdTBL::getColumnValueToString,btnIdXoa);
        var txtTenKH = AutoSuggestComboBox.createWithDeleteBtn(cboTenKH,1,hdTBL::getColumnValueToString,btnXoaTenKH);



        hdTBL.setFilterField(0,txtId);
        hdTBL.setFilterField(1,txtTenKH);

        tblCheckOut.setDefaultEditor(Object.class, null);

        testAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hdTBL.add();
            }
        });
        btnFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableUtils.filter(tblCheckOut);
            }
        });
        tblCheckOut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    tblCheckOut.setDefaultEditor(Object.class, null);
                    System.out.println("double clicked");
                    int[] pos = {tblCheckOut.getSelectedRow(), tblCheckOut.getSelectedColumn()};
                    System.out.println(pos[0] + " " + pos[1]);
                    var cthd = new ChiTietHoaDon();
                    JFrame frame = new JFrame("ChiTietHoaDon");
                    frame.setContentPane(cthd.getMain());
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);
                }
            }
        });
    }

    private void CTHDSelected(){
        var rowsSelected = tblCheckOut.getSelectedRows();

        for (var row: rowsSelected){
            var coords = tblCheckOut.convertRowIndexToModel(row);
            System.out.println(coords);
            var cart = hdTBL.get(coords);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("HoaDon");
        frame.setContentPane(new HoaDon().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void initTable(){
        tblCheckOut.setModel(hdTBL);
    }
}
