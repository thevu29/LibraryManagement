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

public class BorrowDetailUI {
    private JTabbedPane tabbedPane1;
    private JTable borrowDetailTable;
    private JButton bookDeleteAllButton;
    private JButton bookFilterButton;
    private JTabbedPane tabbedPane2;
    private JButton IDDelBtn;
    private JComboBox IDComboBox;
    private JButton maPhieuMuonDelBtn;
    private JComboBox maPhieuMuonCB;
    private JButton tenSachDelBtn;
    private JComboBox tenSachCB;
    private JButton tenLoiDelBtn;
    private JComboBox tenLoiCB;
    private JButton soLuongDelBtn;
    private JComboBox soLuongCB;
    private JButton giaTienDelBtn;
    private JComboBox giaTienCB;
    private JButton addRowButton;
    private JButton thêmPhiếuMượnButton;
    private JButton xóaPhiếuMượnButton;
    private JButton chỉnhSửaPhiếuMượnButton;
    private JPanel panel1;

    private static BorrowDetailModel borrowDetailModel = new BorrowDetailModel();

    public BorrowDetailUI() {
        borrowDetailModel.setEditable(false);

        borrowDetailModel.addTestData();
        // table2.setModel(test);
        borrowDetailTable.setModel(borrowDetailModel);
        TableRowSorter<BorrowDetailModel> sorter = new TableRowSorter<>(borrowDetailModel);
        borrowDetailTable.setRowSorter(sorter);
        borrowDetailTable.getTableHeader().setFont(new Font("Time News Roman", Font.PLAIN, 16));
        borrowDetailTable.getTableHeader().setBackground(Color.WHITE);

        addRowButton.addActionListener(e -> borrowDetailModel.addTestData());

        borrowDetailTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    System.out.println("double clicked");
                    int[] pos = { borrowDetailTable.getSelectedRow(), borrowDetailTable.getSelectedColumn() };
                    System.out.println(pos[0] + " " + pos[1]);
                }
            }
        });
        borrowDetailTable.getTableHeader().setReorderingAllowed(false);

        var maChiTietIDTF = AutoSuggestComboBox.createWithDeleteBtn(IDComboBox, 0,
                borrowDetailModel::getColumnValueToString, IDDelBtn);
        var maPhieuMuonIDTF = AutoSuggestComboBox.createWithDeleteBtn(maPhieuMuonCB, 1,
                borrowDetailModel::getColumnValueToString, maPhieuMuonDelBtn);
        var tenSachIDTF = AutoSuggestComboBox.createWithDeleteBtn(tenSachCB, 2, borrowDetailModel::getColumnValueToString,
                tenSachDelBtn);
        var tenLoiIDTF = AutoSuggestComboBox.createWithDeleteBtn(tenLoiCB, 3, borrowDetailModel::getColumnValueToString,
                tenLoiDelBtn);
        var soLuongIDTF = AutoSuggestComboBox.createWithDeleteBtn(soLuongCB, 4, borrowDetailModel::getColumnValueToString,
                soLuongDelBtn);
        var giaTienIDTF = AutoSuggestComboBox.createWithDeleteBtn(giaTienCB, 5, borrowDetailModel::getColumnValueToString,
                giaTienDelBtn);

        bookDeleteAllButton.addActionListener(e -> {
            maChiTietIDTF.setText("");
            maPhieuMuonIDTF.setText("");
            tenSachIDTF.setText("");
            tenLoiIDTF.setText("");
            soLuongIDTF.setText("");
            giaTienIDTF.setText("");
        });

        borrowDetailModel.setFilterField(0, maChiTietIDTF);
        borrowDetailModel.setFilterField(1, maPhieuMuonIDTF);
        borrowDetailModel.setFilterField(2, tenSachIDTF);
        borrowDetailModel.setFilterField(3, tenLoiIDTF);
        borrowDetailModel.setFilterField(4, soLuongIDTF);
        borrowDetailModel.setFilterField(5, giaTienIDTF);

        for (Iterator<TableColumn> it = borrowDetailTable.getColumnModel().getColumns().asIterator(); it.hasNext();) {
            var column = it.next();
            column.setMinWidth(100);
        }

        bookFilterButton.addActionListener(e -> {
            TableUtils.filter(borrowDetailTable);
        });
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Book");
        frame.setContentPane(new BorrowDetailUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
