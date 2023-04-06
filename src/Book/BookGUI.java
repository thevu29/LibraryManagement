package Book;

import Utils.ComboBoxAutoSuggest.AutoSuggestComboBox;
import Utils.TableUtils;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;

public class BookGUI {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTable bookTable;
    private JButton addRowButton;
    private JButton bookDeleteAllButton;
    private JButton bookFilterButton;
    private JTabbedPane tabbedPane2;
    private JButton bookIDDelBtn;
    private JComboBox<String> bookIDComboBox;
    private JComboBox<String> bookNameCB;
    private JButton bookNameDelBtn;
    private JComboBox<String> authorCB;
    private JButton authorDelBtn;
    private JComboBox<String> publisherCB;
    private JButton publisherDelBtn;
    private JComboBox<String> genreCB;
    private JButton genreDelBtn;
    private JComboBox<String> locationCB;
    private JButton locationDelBtn;
    private JComboBox<String> priceCB;
    private JButton priceDelBtn;
    private JComboBox<String> statusCB;
    private JButton statusDelBtn;
    private JButton thêmSáchButton;
    private JButton xóaSáchButton;
    private JButton chỉnhSửaSáchButton;

    private JTextField bookIDTF;

    private final BookDataTableModel bookDataModel = new BookDataTableModel();

    public BookGUI() {
        bookDataModel.setEditable(false);

        bookDataModel.addTestData();
//        table2.setModel(test);
        bookTable.setModel(bookDataModel);
        TableRowSorter<BookDataTableModel> sorter = new TableRowSorter<>(bookDataModel);
        bookTable.setRowSorter(sorter);
        bookTable.getTableHeader().setFont(new Font("Time News Roman", Font.PLAIN, 16));
        bookTable.getTableHeader().setBackground(Color.WHITE);

        addRowButton.addActionListener(e -> bookDataModel.addTestData());

        bookTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    System.out.println("double clicked");
                    int[] pos = {bookTable.getSelectedRow(), bookTable.getSelectedColumn()};
                    System.out.println(pos[0] + " " + pos[1]);
                }
            }
        });
        bookTable.getTableHeader().setReorderingAllowed(false);

        var bookIDTF = AutoSuggestComboBox.createWithDelete(bookIDComboBox, 0 ,bookDataModel::getColumnValueToString, bookIDDelBtn);
        var bookNameTF = AutoSuggestComboBox.createWithDelete(bookNameCB, 1, bookDataModel::getColumnValueToString, bookNameDelBtn);
        var authorTF = AutoSuggestComboBox.createWithDelete(authorCB, 2, bookDataModel::getColumnValueToString, authorDelBtn);
        var publisherTF = AutoSuggestComboBox.createWithDelete(publisherCB, 3, bookDataModel::getColumnValueToString, publisherDelBtn);
        var genreTF = AutoSuggestComboBox.createWithDelete(genreCB, 4, bookDataModel::getColumnValueToString, genreDelBtn);
        var locationTF = AutoSuggestComboBox.createWithDelete(locationCB, 5, bookDataModel::getColumnValueToString, locationDelBtn);
        var priceTF = AutoSuggestComboBox.createWithDelete(priceCB, 6, bookDataModel::getColumnValueToString, priceDelBtn);
        var statusField = AutoSuggestComboBox.createWithDelete(statusCB, 7, bookDataModel::getColumnValueToString, statusDelBtn);

        bookDeleteAllButton.addActionListener(e -> {
            bookIDTF.setText("");
            bookNameTF.setText("");
            authorTF.setText("");
            publisherTF.setText("");
            genreTF.setText("");
            locationTF.setText("");
            priceTF.setText("");
            statusField.setText("");
        });

        bookDataModel.setFilterField(0, bookIDTF);
        bookDataModel.setFilterField(1, bookNameTF);
        bookDataModel.setFilterField(2, authorTF);
        bookDataModel.setFilterField(3, publisherTF);
        bookDataModel.setFilterField(4, genreTF);
        bookDataModel.setFilterField(5, locationTF);
        bookDataModel.setFilterField(6, priceTF);
        bookDataModel.setFilterField(7, statusField);

        for (Iterator<TableColumn> it = bookTable.getColumnModel().getColumns().asIterator(); it.hasNext(); ) {
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
        frame.setContentPane(new BookGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
