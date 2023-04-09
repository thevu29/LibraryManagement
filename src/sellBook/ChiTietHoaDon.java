package sellBook;

import Utils.ComboBoxAutoSuggest.AutoSuggestComboBox;
import Utils.TableUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChiTietHoaDon {
    private JButton XÓAKHỎIHÓAĐƠNButton;
    private JButton testAdd;
    private JTable tableCart;
    private JButton bookDeleteAllButton;
    private JButton btnFilter;
    private JTabbedPane tabbedPane2;
    private JButton btnXoa;
    private JComboBox cboIDHD;
    private JButton bookNameDelBtn;
    private JComboBox cboTenSach;
    private JButton priceDelBtn;
    private JComboBox priceCB;
    private JButton statusDelBtn;
    private JComboBox statusCB;
    private JPanel main;
    private JScrollPane tblCTHD;
    private JComboBox cboCTHD;
    CTHDTableModel cthdTbl = new CTHDTableModel();
    public ChiTietHoaDon() {
        initTable();

        var idHD =  AutoSuggestComboBox.createWithDeleteBtn(cboIDHD,0,cthdTbl::getColumnValueToString,btnXoa);

        cthdTbl.setFilterField(0,idHD);

        tableCart.setDefaultEditor(Object.class, null);

        testAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cthdTbl.add();
            }
        });


        btnFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableUtils.filter(tableCart);
            }
        });
        tableCart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    tableCart.setDefaultEditor(Object.class, null);
                    System.out.println("double clicked");
                    int[] pos = {tableCart.getSelectedRow(), tableCart.getSelectedColumn()};
                    System.out.println(pos[0] + " " + pos[1]);
                    var dialog = new CTHDFormDialog();
                    dialog.pack();
                    dialog.setVisible(true);
                }
            }
        });
    }

    public void initTable(){
        tableCart.setModel(cthdTbl);
    }

   

    public static void main(String[] args) {
        JFrame frame = new JFrame("ChiTietHoaDon");
        frame.setContentPane(new ChiTietHoaDon().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
