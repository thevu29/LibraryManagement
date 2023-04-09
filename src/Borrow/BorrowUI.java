package Borrow;

import Utils.ComboBoxAutoSuggest.AutoSuggestComboBox;
import Utils.TableUtils;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

public class BorrowUI {

    private JTabbedPane tabbedPane1;
    private JTable bookTable;
    private JButton bookDeleteAllButton;
    private JButton bookFilterButton;
    private JTabbedPane tabbedPane2;
    private JButton IDDelBtn;
    private JComboBox IDCB;
    private JButton tenNhanVienDelBtn;
    private JComboBox tenNhanVienCB;
    private JButton tenDocGiaDelBtn;
    private JComboBox tenDocGiaCB;
    private JButton ngayMuonDelBtn;
    private JComboBox ngayMuonCB;
    private JButton ngayTraDelBtn;
    private JComboBox ngayTraCB;
    private JButton tongTienDelBtn;
    private JComboBox tongTienCB;
    private JButton addRowButton;
    private JButton thêmPhiếuMượnButton;
    private JButton xóaPhiếuMượnButton;
    private JButton chỉnhSửaPhiếuMượnButton;
    private JPanel panel1;

    private final BorrowModel borrowModel = new BorrowModel();

    public BorrowUI() {
        borrowModel.setEditable(false);

        borrowModel.addTestData();
        // table2.setModel(test);
        bookTable.setModel(borrowModel);
        TableRowSorter<BorrowModel> sorter = new TableRowSorter<>(borrowModel);
        bookTable.setRowSorter(sorter);
        bookTable.getTableHeader().setFont(new Font("Time News Roman", Font.PLAIN, 16));
        bookTable.getTableHeader().setBackground(Color.WHITE);

        addRowButton.addActionListener(e -> borrowModel.addTestData());

        bookTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    System.out.println("double clicked");
                    int[] pos = { bookTable.getSelectedRow(), bookTable.getSelectedColumn() };
                    System.out.println(pos[0] + " " + pos[1]);
                }
            }
        });
        bookTable.getTableHeader().setReorderingAllowed(false);

        var IDCBTF = AutoSuggestComboBox.createWithDelete(IDCB, 0, borrowModel::getColumnValueToString, IDDelBtn);
        var tenNhanVienCBTF = AutoSuggestComboBox.createWithDelete(tenNhanVienCB, 1,
                borrowModel::getColumnValueToString, tenNhanVienDelBtn);
        var tenDocGiaCBTF = AutoSuggestComboBox.createWithDelete(tenDocGiaCB, 2, borrowModel::getColumnValueToString,
                tenDocGiaDelBtn);
        var ngayMuonCBTF = AutoSuggestComboBox.createWithDelete(ngayMuonCB, 3, borrowModel::getColumnValueToString,
                ngayMuonDelBtn);
        var ngayTraCBTF = AutoSuggestComboBox.createWithDelete(ngayTraCB, 4, borrowModel::getColumnValueToString,
                ngayTraDelBtn);
        var tongTienCBTF = AutoSuggestComboBox.createWithDelete(tongTienCB, 5, borrowModel::getColumnValueToString,
                tongTienDelBtn);

        bookDeleteAllButton.addActionListener(e -> {
            IDCBTF.setText("");
            tenNhanVienCBTF.setText("");
            tenDocGiaCBTF.setText("");
            ngayMuonCBTF.setText("");
            ngayTraCBTF.setText("");
            tongTienCBTF.setText("");
        });

        borrowModel.setFilterField(0, IDCBTF);
        borrowModel.setFilterField(1, tenNhanVienCBTF);
        borrowModel.setFilterField(2, tenDocGiaCBTF);
        borrowModel.setFilterField(3, ngayMuonCBTF);
        borrowModel.setFilterField(4, ngayTraCBTF);
        borrowModel.setFilterField(5, tongTienCBTF);

        for (Iterator<TableColumn> it = bookTable.getColumnModel().getColumns().asIterator(); it.hasNext();) {
            var column = it.next();
            column.setMinWidth(100);
        }

        bookFilterButton.addActionListener(e -> {
            TableUtils.filter(bookTable);
        });
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Book");
        frame.setContentPane(new BorrowUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
