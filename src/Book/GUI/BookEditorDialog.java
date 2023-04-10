package Book.GUI;

import Book.DTO.Book;
import Utils.BindingListener;
import Utils.ComboBoxAutoSuggest.AutoSuggestComboBox;
import Book.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class BookEditorDialog extends JDialog {
    private final BookDataTableModel bookDataTableModel;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox<String> bookSerialCB;
    private JButton button2;
    private JPanel tacGiaPanel;
    private JButton button1;
    private JTextPane descriptionField;
    private JPanel mainPanel;
    private JComboBox<String> bookNameCB;
    private JComboBox<String> conditionCB;
    private JComboBox<String> statusCB;
    private JComboBox<String> priceCB;
    private JComboBox<String> authorCB1;
    private JComboBox<String> genreCB1;
    private JComboBox<String> publisherCB;
    private JComboBox<String> publishYearCB;
    private JComboBox<String> languageCB;
    private JComboBox<String> totalPageCB;
    private JComboBox locationCB;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    //TODO Add Author and Publisher model

    public BookEditorDialog() {
        this(Book.createBlankBook(), new BookDataTableModel(), "Chỉnh sửa sách");
    }

    private Book book;
    private Book clonedBook;

    public BookEditorDialog(Book book, BookDataTableModel bookDataTableModel, String title) {
        this.bookDataTableModel = bookDataTableModel;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        this.setTitle(title);


        var clonedBook = book.clone();
        this.book = book;
        this.clonedBook = clonedBook;

        var bookSerialField = AutoSuggestComboBox.create(bookSerialCB, 0, bookDataTableModel::getColumnValueToString);
        bookSerialField.setText(clonedBook.getId());
        bookSerialField.getDocument().addDocumentListener(new BindingListener<>(bookSerialField,clonedBook,
                clonedBook::setId, "[0-9]+"));

        var bookNameField = AutoSuggestComboBox.create(bookNameCB, 0, bookDataTableModel::getColumnValueToString);
        bookNameField.setText(clonedBook.getName());
        bookNameField.getDocument().addDocumentListener(new BindingListener<>(bookNameField,clonedBook, clonedBook::setName));

        var statusField = AutoSuggestComboBox.createIgnored(statusCB,Arrays.stream(EBookStatus.values()).map(Enum::toString).toList());
        statusField.setText(String.valueOf(clonedBook.getBookStatus()));
        statusField.getDocument().addDocumentListener(new BindingListener<>(statusField,clonedBook, clonedBook::setBookStatus));

        var priceField = AutoSuggestComboBox.create(priceCB, 6, bookDataTableModel::getColumnValueToString);
        priceField.setText(String.valueOf(clonedBook.getPrice()));
        priceField.getDocument().addDocumentListener(new BindingListener<>(priceField,clonedBook, clonedBook::setPrice));


        var languageField = AutoSuggestComboBox.create(languageCB, 8, bookDataTableModel::getColumnValueToString);
        languageField.setText(String.valueOf(clonedBook.getLanguage()));
        languageField.getDocument().addDocumentListener(new BindingListener<>(languageField,clonedBook, clonedBook::setLanguage));

        descriptionField.setText(String.valueOf(clonedBook.getDescription()));
        descriptionField.getDocument().addDocumentListener(new BindingListener<>(descriptionField, clonedBook, clonedBook::setDescription));

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAuthorCB();
            }
        });
    }

    private void createAuthorCB() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.LINE_START;
        var comboBox = new JComboBox<String>();
        comboBox.setEditable(true);
        tacGiaPanel.add(comboBox, constraints);
        tacGiaPanel.revalidate();
    }

    private void createAuthorCB(String value, ArrayList<String> authorList) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.LINE_START;
        var comboBox = new JComboBox<String>();
        comboBox.setEditable(true);
        tacGiaPanel.add(comboBox, constraints);
        tacGiaPanel.revalidate();
    }

    private void onOK() {
        // add your code here
        this.book.cloneFrom(this.clonedBook);
        this.bookDataTableModel.fireTableDataChanged();
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        BookEditorDialog dialog = new BookEditorDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
