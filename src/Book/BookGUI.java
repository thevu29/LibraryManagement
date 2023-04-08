package Book;

import Utils.ComboBoxAutoSuggest.AutoSuggestComboBox;
import Utils.TableUtils;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;

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
    private JButton editBookBtn;
    private JButton languageDelBtn;
    private JComboBox languageCB;

    private JTextField bookIDTF;

    private final BookDataTableModel bookDataModel = new BookDataTableModel();

    public BookGUI() {
        bookDataModel.setEditable(false);

        bookDataModel.addTestData();
        bookTable.setModel(bookDataModel);
        TableRowSorter<BookDataTableModel> sorter
                = new TableRowSorter<>(bookDataModel);
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
                    bookEditSelected();
                }
            }
        });
        bookTable.getTableHeader().setReorderingAllowed(false);


        var bookIDTF = AutoSuggestComboBox.createWithDeleteBtn(bookIDComboBox, 0 ,bookDataModel::getColumnValueToString, bookIDDelBtn);
        var bookNameTF = AutoSuggestComboBox.createWithDeleteBtn(bookNameCB, 1,  bookDataModel::getColumnValueToString, bookNameDelBtn);
        var authorTF = AutoSuggestComboBox.createWithDeleteBtn(authorCB, 2,  bookDataModel::getColumnValueToString, authorDelBtn);
        var publisherTF = AutoSuggestComboBox.createWithDeleteBtn(publisherCB, 3,  bookDataModel::getColumnValueToString, publisherDelBtn);
        var genreTF = AutoSuggestComboBox.createWithDeleteBtn(genreCB, 4,  bookDataModel::getColumnValueToString, genreDelBtn);
        var locationTF = AutoSuggestComboBox.createWithDeleteBtn(locationCB, 5,  bookDataModel::getColumnValueToString, locationDelBtn);
        var priceTF = AutoSuggestComboBox.createWithDeleteBtn(priceCB, 6,  bookDataModel::getColumnValueToString, priceDelBtn);
        var statusField = AutoSuggestComboBox.createWithDeleteBtn(statusCB, 7, bookDataModel::getColumnValueToString, statusDelBtn);
        var languageField = AutoSuggestComboBox.createWithDeleteBtn(statusCB, 8, bookDataModel::getColumnValueToString, languageDelBtn);

        bookDeleteAllButton.addActionListener(e -> {
            bookIDTF.setText("");
            bookNameTF.setText("");
            authorTF.setText("");
            publisherTF.setText("");
            genreTF.setText("");
            locationTF.setText("");
            priceTF.setText("");
            statusField.setText("");
            languageField.setText("");
        });

        bookDataModel.setFilterField(0, bookIDTF);
        bookDataModel.setFilterField(1, bookNameTF);
        bookDataModel.setFilterField(2, authorTF);
        bookDataModel.setFilterField(3, publisherTF);
        bookDataModel.setFilterField(4, genreTF);
        bookDataModel.setFilterField(5, locationTF);
        bookDataModel.setFilterField(6, priceTF);
        bookDataModel.setFilterField(7, statusField);
        bookDataModel.setFilterField(8, languageField);

        for (Iterator<TableColumn> it = bookTable.getColumnModel().getColumns().asIterator(); it.hasNext(); ) {
            var column = it.next();
            column.setMinWidth(100);
        }


        bookFilterButton.addActionListener(e -> {
            TableUtils.filter(bookTable);
        });
        editBookBtn.addActionListener(e -> {
            bookEditSelected();
        });

    }

    private void bookEditSelected() {
        var rowsSelected = bookTable.getSelectedRows();
        for (var row: rowsSelected) {
            var coords = bookTable.getRowSorter().convertRowIndexToModel(row);
            var book = bookDataModel.get(coords);
            var dialog = new BookEditorDialog(book, bookDataModel);
            dialog.pack();
            dialog.setVisible(true);
        }
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
