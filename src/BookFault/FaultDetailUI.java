package BookFault;

import Utils.ComboBoxAutoSuggest.AutoSuggestComboBox;
import Utils.TableUtils;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

public class FaultDetailUI {
    private JTabbedPane tabbedPane1;
    private JTable bookTable;
    private JButton bookDeleteAllButton;
    private JButton bookFilterButton;
    private JTabbedPane tabbedPane2;
    private JButton maChiTietDelBtn;
    private JComboBox maChiTietCB;
    private JButton maLoiDelBtn;
    private JComboBox maLoiCB;
    private JButton tenDocGiaDelBtn;
    private JComboBox tenDocGiaCB;
    private JButton tenSachDelBtn;
    private JComboBox tenSachCB;
    private JButton tenLoiDelBtn;
    private JComboBox tenLoiCB;
    private JButton soLuongDelBtn;
    private JComboBox soLuongCB;
    private JButton tienDenDelBtn;
    private JComboBox tienDenCB;
    private JButton addRowButton;
    private JButton thêmChiTiếtLỗiButton;
    private JButton xóaChiTiếtButton;
    private JButton chỉnhSửaChiTiếtButton;
    private JPanel panel1;

    private FaultDetailModel faultDetailModel = new FaultDetailModel();

    public FaultDetailUI() {
        faultDetailModel.setEditable(false);

        faultDetailModel.addTestData();
        // table2.setModel(test);
        bookTable.setModel(faultDetailModel);
        TableRowSorter<FaultDetailModel> sorter = new TableRowSorter<>(faultDetailModel);
        bookTable.setRowSorter(sorter);
        bookTable.getTableHeader().setFont(new Font("Time News Roman", Font.PLAIN, 16));
        bookTable.getTableHeader().setBackground(Color.WHITE);

        addRowButton.addActionListener(e -> faultDetailModel.addTestData());

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

        var maLoiChiTietTF = AutoSuggestComboBox.createWithDelete(maChiTietCB, 0,
                faultDetailModel::getColumnValueToString, maChiTietDelBtn);
        var maLoiTF = AutoSuggestComboBox.createWithDelete(maLoiCB, 1, faultDetailModel::getColumnValueToString,
                maLoiDelBtn);
        var tenDocGiaTF = AutoSuggestComboBox.createWithDelete(tenDocGiaCB, 2, faultDetailModel::getColumnValueToString,
                tenDocGiaDelBtn);
        var tenSachTF = AutoSuggestComboBox.createWithDelete(tenSachCB, 3, faultDetailModel::getColumnValueToString,
                tenSachDelBtn);
        var tenLoiTF = AutoSuggestComboBox.createWithDelete(tenLoiCB, 4, faultDetailModel::getColumnValueToString,
                tenLoiDelBtn);
        var soLuongTF = AutoSuggestComboBox.createWithDelete(soLuongCB, 5, faultDetailModel::getColumnValueToString,
                soLuongDelBtn);
        var tienDenTF = AutoSuggestComboBox.createWithDelete(tienDenCB, 6, faultDetailModel::getColumnValueToString,
                tienDenDelBtn);

        bookDeleteAllButton.addActionListener(e -> {
            maLoiChiTietTF.setText("");
            maLoiTF.setText("");
            tenDocGiaTF.setText("");
            tenSachTF.setText("");
            tenLoiTF.setText("");
            soLuongTF.setText("");
            tienDenTF.setText("");
        });

        faultDetailModel.setFilterField(0, maLoiChiTietTF);
        faultDetailModel.setFilterField(1, maLoiTF);
        faultDetailModel.setFilterField(2, tenDocGiaTF);
        faultDetailModel.setFilterField(3, tenSachTF);
        faultDetailModel.setFilterField(4, tenLoiTF);
        faultDetailModel.setFilterField(5, soLuongTF);
        faultDetailModel.setFilterField(6, tienDenTF);

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
        JFrame frame = new JFrame("FaultDetail");
        frame.setContentPane(new FaultDetailUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
