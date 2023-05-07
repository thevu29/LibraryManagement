package GUI;

import BUS.BookBUS;
import DTO.*;
import Utils.ComboBoxAutoSuggest.AutoSuggestComboBox;
import Utils.TableUtils;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

public class BookGUI {
    private final GenreDataTableModel genreDataTableModel;
    private final PublisherDataTableModel publisherTableModel;
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTable bookTable;
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
    private JButton addBookBtn;
    private JButton bookDelBtn;
    private JButton editBookBtn;
    private JButton languageDelBtn;
    private JComboBox languageCB;
    private JTable authorTable;
    private JTable genreTable;
    private JTable publisherTable;
    private JButton authorTest;
    private JTable importerTB;
    private JButton authorAdd;
    private JComboBox authorIDCB;
    private JButton authorIDBtn;
    private JComboBox authorNameCB;
    private JButton authorNameDelBtn;
    private JComboBox authorSexCB;
    private JButton authorSexDelBtn;
    private JComboBox authorEmailCB;
    private JButton authorEmailDelBtn;
    private JButton authorDesDelBtn;
    private JButton authorFilterDelAllBtn;
    private JButton authorFilterBtn;
    private JComboBox authorDesCB;
    private JButton editAuthorBtn;
    private JComboBox genreIDCB;
    private JButton genreIDBtn;
    private JComboBox genreNameCB;
    private JButton genreNameDel;
    private JComboBox genreDesCB;
    private JButton genreDesDelBtn;
    private JButton genreFilterDelAllBtn;
    private JButton genreFilterBtn;
    private JButton genreAddBtn;
    private JButton genreEditBtn;
    private JComboBox pIDCB;
    private JButton pIDDelBtn;
    private JComboBox pNameCB;
    private JButton pNameDelBtn;
    private JComboBox pEmailCB;
    private JButton pEmailDelBtn;
    private JComboBox pPhoneCB;
    private JButton pPhoneDelBtn;
    private JButton pDelAll;
    private JButton pFilter;
    private JButton pEditBtn;
    private JButton pAddBtn;
    private JButton pDelBtn;
    private JButton iIDDelBtn;
    private JComboBox iNameCB;
    private JButton iNameBtn;
    private JComboBox iPhoneCB;
    private JButton iPhoneBtn;
    private JComboBox iAddressCB;
    private JButton iAddressBtn;
    private JComboBox iDesCB;
    private JButton iDesBtn;
    private JButton iDelAllBtn;
    private JButton iFilterBtn;
    private JButton iAddBtn;
    private JButton iEditBtn;
    private JButton iDeleleBtn;
    private JComboBox iIDCB;
    private JComboBox iEmailCB;
    private JButton iEmailBtn;
    private JButton delAuthorBtn;
    private JButton genreDelButton;

    private JTextField bookIDTF;

    private final BookDataTableModel bookDataModel;
    private final AuthorDataTableModel authorDataTableModel;
    private final BookBUS bookBUS;
    private ImporterDataTableModel importerTableModel;

    public BookGUI(BookBUS bus) {
        this.bookDataModel = bus.getBookDataTableModel();
        this.authorDataTableModel = bus.getAuthorDataTableModel();
        this.genreDataTableModel = bus.getGenreDataTableModel();
        this.publisherTableModel = bus.getPublisherDataTableModel();
        this.importerTableModel = bus.getImporterDataTableModel();
        this.bookBUS = bus;

        setupBookPane();
        setupAuthorPane();
        setupGenrePane();
        setupPublisherPane();
        setupImporterPane();


    }

    private void setupImporterPane() {
        TableRowSorter<ImporterDataTableModel> sorter
                = new TableRowSorter<>(importerTableModel);
        importerTB.setRowSorter(sorter);
        importerTB.getTableHeader().setFont(new Font("Time News Roman", Font.PLAIN, 16));
        importerTB.getTableHeader().setBackground(Color.WHITE);
        importerTB.setModel(importerTableModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < importerTB.getColumnCount(); i++) {
            importerTB.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        var iIDTF = AutoSuggestComboBox.createWithDeleteBtn(iIDCB, 0, importerTableModel::getColumnValueToString, iIDDelBtn);
        var iNameTF = AutoSuggestComboBox.createWithDeleteBtn(iNameCB, 1, importerTableModel::getColumnValueToString, iNameBtn);
        var iPhoneTF = AutoSuggestComboBox.createWithDeleteBtn(iPhoneCB, 2, importerTableModel::getColumnValueToString, iPhoneBtn);
        var iEmailTF = AutoSuggestComboBox.createWithDeleteBtn(iEmailCB, 3, importerTableModel::getColumnValueToString, iEmailBtn);
        var iAddressTF = AutoSuggestComboBox.createWithDeleteBtn(iAddressCB, 4, importerTableModel::getColumnValueToString, iAddressBtn);
        var iDesTF  = AutoSuggestComboBox.createWithDeleteBtn(iDesCB, 5, importerTableModel::getColumnValueToString, iDesBtn);

        iDelAllBtn.addActionListener(event -> {
            iIDTF.setText("");
            iNameTF.setText("");
            iPhoneTF.setText("");
            iEmailTF.setText("");
            iAddressTF.setText("");
            iDesTF.setText("");
        });


        importerTableModel.setFilterField(0, iIDTF);
        importerTableModel.setFilterField(1, iNameTF);
        importerTableModel.setFilterField(2, iPhoneTF);
        importerTableModel.setFilterField(3, iEmailTF);
        importerTableModel.setFilterField(4, iAddressTF);
        importerTableModel.setFilterField(5, iDesTF);

        for (Iterator<TableColumn> it = publisherTable.getColumnModel().getColumns().asIterator(); it.hasNext(); ) {
            var column = it.next();
            column.setMinWidth(100);
        }


        iFilterBtn.addActionListener(e -> {
            TableUtils.filter(importerTB);
        });
        iEditBtn.addActionListener(e -> {
            var rowsSelected = publisherTable.getSelectedRows();
            for (var row : rowsSelected) {
                var coords = publisherTable.getRowSorter().convertRowIndexToModel(row);
                bookBUS.openNewImporterDialog(coords);
            }
        });

        iAddBtn.addActionListener(e -> {
            bookBUS.openNewImporterDialog();
        });

        iDeleleBtn.addActionListener(e -> {
            var rowsSelected = importerTB.getSelectedRows();
            for (var row : rowsSelected) {
                var coords = importerTB.getRowSorter().convertRowIndexToModel(row);
                bookBUS.openImporterDeleteDialog(coords);
            }
            bookBUS.updateImporterDataModel();
        });

        importerTB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    var rowsSelected = importerTB.getSelectedRows();
                    for (var row : rowsSelected) {
                        var coords = publisherTable.getRowSorter().convertRowIndexToModel(row);
                        bookBUS.openNewImporterDialog(coords);
                    }
                }

            }
        });



    }

    private void setupPublisherPane() {
        TableRowSorter<PublisherDataTableModel> sorter
                = new TableRowSorter<>(publisherTableModel);
        publisherTable.setRowSorter(sorter);
        publisherTable.getTableHeader().setFont(new Font("Time News Roman", Font.PLAIN, 16));
        publisherTable.getTableHeader().setBackground(Color.WHITE);
        publisherTable.setModel(publisherTableModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < publisherTable.getColumnCount(); i++) {
            publisherTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        var pIDTF = AutoSuggestComboBox.createWithDeleteBtn(pIDCB, 0, publisherTableModel::getColumnValueToString, pIDDelBtn);
        var pNameTF = AutoSuggestComboBox.createWithDeleteBtn(pNameCB, 1, publisherTableModel::getColumnValueToString, pNameDelBtn);
        var pEmailTF = AutoSuggestComboBox.createWithDeleteBtn(pEmailCB, 3, publisherTableModel::getColumnValueToString, pEmailDelBtn);
        var pPhoneTF = AutoSuggestComboBox.createWithDeleteBtn(pPhoneCB, 4, publisherTableModel::getColumnValueToString, pPhoneDelBtn);

        pDelAll.addActionListener(event -> {
            pIDTF.setText("");
            pNameTF.setText("");
            pEmailTF.setText("");
            pPhoneTF.setText("");
        });


        publisherTableModel.setFilterField(0, pIDTF);
        publisherTableModel.setFilterField(1, pNameTF);
        publisherTableModel.setFilterField(2, pEmailTF);
        publisherTableModel.setFilterField(3, pPhoneTF);

        for (Iterator<TableColumn> it = publisherTable.getColumnModel().getColumns().asIterator(); it.hasNext(); ) {
            var column = it.next();
            column.setMinWidth(100);
        }


        pFilter.addActionListener(e -> {
            TableUtils.filter(publisherTable);
        });
        pEditBtn.addActionListener(e -> {
            var rowsSelected = publisherTable.getSelectedRows();
            for (var row : rowsSelected) {
                var coords = publisherTable.getRowSorter().convertRowIndexToModel(row);
                bookBUS.openNewPublisherDialog(coords);
            }
        });

        pAddBtn.addActionListener(e -> {
            bookBUS.openNewPublisherDialog();
        });

        pDelBtn.addActionListener(e -> {
            var rowsSelected = publisherTable.getSelectedRows();
            for (var row : rowsSelected) {
                var coords = publisherTable.getRowSorter().convertRowIndexToModel(row);
                bookBUS.openPublisherDeleteDialog(coords);
            }
            bookBUS.updatePublisherDataModel();
        });

        publisherTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    var rowsSelected = publisherTable.getSelectedRows();
                    for (var row : rowsSelected) {
                        var coords = publisherTable.getRowSorter().convertRowIndexToModel(row);
                        bookBUS.openNewPublisherDialog(coords);
                    }
                }

            }
        });


    }

    private void setupAuthorPane() {
        TableRowSorter<AuthorDataTableModel> sorter
                = new TableRowSorter<>(authorDataTableModel);
        authorTable.setRowSorter(sorter);
        authorTable.getTableHeader().setFont(new Font("Time News Roman", Font.PLAIN, 16));
        authorTable.getTableHeader().setBackground(Color.WHITE);
        authorTable.setModel(authorDataTableModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < authorTable.getColumnCount(); i++) {
            authorTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        var authorIDTF = AutoSuggestComboBox.createWithDeleteBtn(authorIDCB, 0, authorDataTableModel::getColumnValueToString, authorIDBtn);
        var authorNameTF = AutoSuggestComboBox.createWithDeleteBtn(authorNameCB, 1, authorDataTableModel::getColumnValueToString, authorNameDelBtn);
        var authorSexTF = AutoSuggestComboBox.createWithDeleteBtn(authorSexCB, 2, authorDataTableModel::getColumnValueToString, authorSexDelBtn);
        var authorEmailTF = AutoSuggestComboBox.createWithDeleteBtn(authorEmailCB, 3, authorDataTableModel::getColumnValueToString, authorEmailDelBtn);
        var authorDesTF = AutoSuggestComboBox.createWithDeleteBtn(authorDesCB, 4, authorDataTableModel::getColumnValueToString, authorDesDelBtn);

        authorFilterDelAllBtn.addActionListener(event -> {
            authorIDTF.setText("");
            authorNameTF.setText("");
            authorSexTF.setText("");
            authorDesTF.setText("");
            authorEmailTF.setText("");
        });


        authorDataTableModel.setFilterField(0, authorIDTF);
        authorDataTableModel.setFilterField(1, authorNameTF);
        authorDataTableModel.setFilterField(2, authorSexTF);
        authorDataTableModel.setFilterField(3, authorEmailTF);
        authorDataTableModel.setFilterField(4, authorDesTF);

        for (Iterator<TableColumn> it = authorTable.getColumnModel().getColumns().asIterator(); it.hasNext(); ) {
            var column = it.next();
            column.setMinWidth(100);
        }


        authorFilterBtn.addActionListener(e -> {
            TableUtils.filter(authorTable);
        });
        editAuthorBtn.addActionListener(e -> {
            var rowsSelected = authorTable.getSelectedRows();
            for (var row : rowsSelected) {
                var coords = authorTable.getRowSorter().convertRowIndexToModel(row);
                bookBUS.openNewAuthorDialog(coords);
            }
        });

        delAuthorBtn.addActionListener(e -> {
            var rowsSelected = authorTable.getSelectedRows();
            for (var row : rowsSelected) {
                var coords = authorTable.getRowSorter().convertRowIndexToModel(row);
                bookBUS.openAuthorDeleteDialog(coords);
            }
            bookBUS.updateAuthorDataModel();
        });

        authorAdd.addActionListener(e -> {
            bookBUS.openNewAuthorDialog();
        });



        authorTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    var rowsSelected = authorTable.getSelectedRows();
                    for (var row : rowsSelected) {
                        var coords = authorTable.getRowSorter().convertRowIndexToModel(row);
                        bookBUS.openNewAuthorDialog(coords);
                    }
                }
            }
        });

    }


    private void setupGenrePane() {
        TableRowSorter<GenreDataTableModel> sorter
                = new TableRowSorter<>(genreDataTableModel);
        genreTable.setRowSorter(sorter);
        genreTable.getTableHeader().setFont(new Font("Time News Roman", Font.PLAIN, 16));
        genreTable.getTableHeader().setBackground(Color.WHITE);
        genreTable.setModel(genreDataTableModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < genreTable.getColumnCount(); i++) {
            genreTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        var genreIDTF = AutoSuggestComboBox.createWithDeleteBtn(genreIDCB, 0, genreDataTableModel::getColumnValueToString, genreIDBtn);
        var genreNameTF = AutoSuggestComboBox.createWithDeleteBtn(genreNameCB, 1, genreDataTableModel::getColumnValueToString, genreNameDel);
        var genreDesTF = AutoSuggestComboBox.createWithDeleteBtn(genreDesCB, 2, genreDataTableModel::getColumnValueToString, genreDesDelBtn);

        genreFilterDelAllBtn.addActionListener(event -> {
            genreIDTF.setText("");
            genreNameTF.setText("");
            genreDesTF.setText("");
        });


        genreDataTableModel.setFilterField(0, genreIDTF);
        genreDataTableModel.setFilterField(1, genreNameTF);
        genreDataTableModel.setFilterField(2, genreDesTF);

        for (Iterator<TableColumn> it = genreTable.getColumnModel().getColumns().asIterator(); it.hasNext(); ) {
            var column = it.next();
            column.setMinWidth(100);
        }


        genreFilterBtn.addActionListener(e -> {
            TableUtils.filter(genreTable);
        });
        genreEditBtn.addActionListener(e -> {
            var rowsSelected = genreTable.getSelectedRows();
            for (var row : rowsSelected) {
                var coords = genreTable.getRowSorter().convertRowIndexToModel(row);
                bookBUS.openNewGenreDialog(coords);
            }
        });

        genreAddBtn.addActionListener(e -> {
            bookBUS.openNewGenreDialog();
        });

        genreDelButton.addActionListener(e -> {
            var rowsSelected = genreTable.getSelectedRows();
            for (var row : rowsSelected) {
                var coords = genreTable.getRowSorter().convertRowIndexToModel(row);
                bookBUS.openGenreDeleteDialog(coords);
            }
            bookBUS.updateGenreDataModel();
        });

        genreTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    var rowsSelected = genreTable.getSelectedRows();
                    for (var row : rowsSelected) {
                        var coords = genreTable.getRowSorter().convertRowIndexToModel(row);
                        bookBUS.openNewGenreDialog(coords);
                    }
                }
            }
        });


    }

    private void setupBookPane() {
        bookDataModel.setEditable(false);

        bookTable.setModel(bookDataModel);
        TableRowSorter<BookDataTableModel> sorter
                = new TableRowSorter<>(bookDataModel);
        bookTable.setRowSorter(sorter);
        bookTable.getTableHeader().setFont(new Font("Time News Roman", Font.PLAIN, 16));
        bookTable.getTableHeader().setBackground(Color.WHITE);



        bookTable.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < bookTable.getColumnCount(); i++) {
            bookTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        var bookIDTF = AutoSuggestComboBox.createWithDeleteBtn(bookIDComboBox, 0, bookDataModel::getColumnValueToString, bookIDDelBtn);
        var bookNameTF = AutoSuggestComboBox.createWithDeleteBtn(bookNameCB, 1, bookDataModel::getColumnValueToString, bookNameDelBtn);
        var authorTF = AutoSuggestComboBox.createWithDeleteBtn(authorCB, 2, bookDataModel::getColumnValueToString, authorDelBtn);
        var publisherTF = AutoSuggestComboBox.createWithDeleteBtn(publisherCB, 3, bookDataModel::getColumnValueToString, publisherDelBtn);
        var genreTF = AutoSuggestComboBox.createWithDeleteBtn(genreCB, 4, bookDataModel::getColumnValueToString, genreDelBtn);
        var locationTF = AutoSuggestComboBox.createWithDeleteBtn(locationCB, 5, bookDataModel::getColumnValueToString, locationDelBtn);
        var priceTF = AutoSuggestComboBox.createWithDeleteBtn(priceCB, 6, bookDataModel::getColumnValueToString, priceDelBtn);
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

        addBookBtn.addActionListener(e -> {
            bookBUS.openNewBookDialog();
        });

        bookTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    bookEditSelected();
                }
            }
        });

        bookDelBtn.addActionListener(e -> {
            var rowsSelected = bookTable.getSelectedRows();
            for (var row : rowsSelected) {
                var coords = bookTable.getRowSorter().convertRowIndexToModel(row);
                bookBUS.openBookDeleteDialog(coords);
            }
            bookBUS.updateBookDataModel();
        });

    }

    private void bookEditSelected() {
        var rowsSelected = bookTable.getSelectedRows();
        for (var row : rowsSelected) {
            var coords = bookTable.getRowSorter().convertRowIndexToModel(row);
            bookBUS.openNewBookDialog(coords);
        }
    }

    public JPanel getPanel1() {
        return panel1;
    }

}
