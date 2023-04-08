package sellBook;

import Utils.ComboBoxAutoSuggest.AutoSuggestComboBox;
import Utils.TableUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DanhSachGioHang {
    private JPanel main;
    private JButton XÓAKHỎIGIỎHÀNGButton;
    private JButton CẬPNHẬTGIỎHÀNGButton;
    private JButton TIÉPTỤCMUASÁCHButton;
    private JButton testAdd;
    private JButton bookDeleteAllButton;
    private JButton btnFilter;
    private JTabbedPane tabbedPane2;
    private JButton btnXoa;
    private JComboBox bookIDCB;
    private JButton bookNameDelBtn;
    private JComboBox bookNameCB;
    private JButton authorDelBtn;
    private JComboBox authorCB;
    private JButton publisherDelBtn;
    private JComboBox publisherCB;
    private JButton genreDelBtn;
    private JComboBox genreCB;
    private JButton priceDelBtn;
    private JComboBox priceCB;
    private JButton statusDelBtn;
    private JComboBox statusCB;
    private JButton languageDelBtn;
    private JComboBox languageCB;
    private JButton themThuButton;
    private JButton btnCapNhat;
    private JComboBox<String> idFilterCB;
    private JButton locBtn;
    private JTable tableCart;
    GioHangTableModel dtm = new GioHangTableModel();

    public DanhSachGioHang(){
        initTable();
//
//

        var bookIDTF = AutoSuggestComboBox.createWithDeleteBtn(bookIDCB, 0 ,dtm::getColumnValueToString, btnXoa);
        var bookNameTF = AutoSuggestComboBox.createWithDeleteBtn(bookNameCB, 1,  dtm::getColumnValueToString, bookNameDelBtn);
//
//        tableCart.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
//                    int row = tableCart.getSelectedRow();
//                    Object value = tableCart.getValueAt(row, 0); // Lấy giá trị ở cột đầu tiên (cột "Ten Sach")
//                    System.out.println("Selected value: " + value);
//
//                    // Chặn sự chỉnh sửa của hàng
//
//                }
//            }
//        });
//
//        locBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                TableUtils.filter(tableCart);
//            }
//        });
//        btnCapNhat.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int selectedRows[] = tableCart.getSelectedRows();
//                for(int i:selectedRows) {
//                     var vtri = tableCart.getRowSorter().convertRowIndexToModel(i);
//
//
//                }
//            }
//        });
        testAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dtm.add();
            }
        });
        tableCart.setDefaultEditor(Object.class, null);
        tableCart.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    tableCart.setDefaultEditor(Object.class, null);
                }
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
                    cartEditSelected();
                }
            }
        });

        dtm.setFilterField(0,bookIDTF);
        dtm.setFilterField(1,bookNameTF);

        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tableCart.getSelectedRow();

                dtm.removeFilterField(row);

            }
        });
        btnFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableUtils.filter(tableCart);
            }
        });
    }

    private void cartEditSelected(){
        var rowsSelected = tableCart.getSelectedRows();
        //Dùng for là để chọn nhiều dòng
        for (var row: rowsSelected){
            var coords = tableCart.convertRowIndexToModel(row);
            System.out.println(coords);
            var cart = dtm.get(coords);
            var dialog = new GioHangEditorDialog(cart, dtm);
            dialog.pack();
            dialog.setVisible(true);
        }
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("DanhSachGioHang");
        frame.setContentPane(new DanhSachGioHang().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
    public void initTable(){
////        String[] collumns = new String[]{"Ten Sach","Gia","So Luong","Tong Tien"};
////        dtm.setColumnIdentifiers(collumns);
////        for(int i=1;i<20;i++){
////            dtm.addRow(new Object[]{"Sach "+i, i, i,i+1});
////        }
        tableCart.setModel(dtm);
////        tableCart.setDefaultEditor(Object.class, null);
    }

}




