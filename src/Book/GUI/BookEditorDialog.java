package Book.GUI;

import Book.BUS.BookBUS;
import Book.DTO.Book;
import Book.DTO.BookAuthor;
import Book.DTO.BookGenre;
import Utils.BindingListener;
import Utils.ComboBoxAutoSuggest.AutoSuggestComboBox;
import Book.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Objects;

public class BookEditorDialog extends JDialog {
    private final BookDataTableModel bookDataTableModel;
    private final BookBUS bus;
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
    private JPanel genrePanel;
    private JButton xácNhậnButton;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    //TODO Add Author and Publisher model

//    public BookEditorDialog() {
//        this(Book.createBlankBook(), , "Chỉnh sửa sách");
//    }

    private Book book;
    private Book clonedBook;

    public BookEditorDialog(Book book, BookBUS bus, String title, int mode) {
        this.bookDataTableModel = bus.getBookDataTableModel();
        this.bus = bus;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        this.setTitle(title);

        if (mode == 1) {
            bookSerialCB.setEnabled(false);
        }

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

        var locationField = AutoSuggestComboBox.create(locationCB, 8, bookDataTableModel::getColumnValueToString);
        locationField.setText(String.valueOf(clonedBook.getLocation()));
        locationField.getDocument().addDocumentListener(new BindingListener<>(languageField,clonedBook, clonedBook::setLocation));

        var publisherField = AutoSuggestComboBox.create(publisherCB, 110, bus.getPublisherDataTableModel()::getColumnValueToString);
        publisherField.setText(String.valueOf(clonedBook.getPublisher().toDialogString()));
        publisherField.getDocument().addDocumentListener(new BindingListener<>(languageField,clonedBook, clonedBook::setLocation));


        var yearField = AutoSuggestComboBox.create(publishYearCB, 9, bookDataTableModel::getColumnValueToString);
        yearField.setText(String.valueOf(clonedBook.getPublishYear()));
        yearField.getDocument().addDocumentListener(new BindingListener<>(languageField,clonedBook, clonedBook::setPublishYear));


        descriptionField.setText(String.valueOf(clonedBook.getDescription()));
        descriptionField.getDocument().addDocumentListener(new BindingListener<>(descriptionField, clonedBook, clonedBook::setDescription));

        setupAuthorCB();
        setupGenreCB();


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
                createNewCB();
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createGenreCB();
            }
        });
    }


    private void setupAuthorCB() {
        setupAuthorField(authorCB1,0);

        for (int i = 1; i < clonedBook.getAuthors().size(); i++) {
            setupAuthorPos(i);
        }
    }

    private void setupGenreCB() {
        setupGenreField(genreCB1,0);

        for (int i = 1; i < clonedBook.getGenre().size(); i++) {
            setupGenrePos(i);
        }
    }

    private void setupAuthorField(JComboBox<String> authorCB, int pos) {
        var authorField = AutoSuggestComboBox.create(authorCB, 99, bus.getAuthorDataTableModel()::getColumnValueToString);
        var author = clonedBook.getAuthors().get(pos);
        if (Objects.isNull(author)) {
            authorField.setText("");
            clonedBook.getAuthors().add(new BookAuthor("", ""));
        }
        else {
            authorField.setText(author.toDetailString());
        }

        authorField.getDocument().addDocumentListener(new BindingListener<>(authorField, clonedBook, s -> {
            var idName = s.split(",");
            if (idName.length == 2) {
                clonedBook.setAuthor(pos, idName[0], idName[1]);
                System.out.println(Arrays.toString(idName));
            }
            else {
                clonedBook.setAuthor(pos, "", "");
            }
        }));
    }

    private void setupAuthorPos(int pos) {
        var newCB = createAuthorCB(clonedBook.getAuthors().get(pos).toDetailString());
        setupAuthorField(newCB, pos);
    }

    private void setupGenrePos(int pos) {
        var newCB = createGenreCB(clonedBook.getGenre().get(pos).toString());
        setupGenreField(newCB, pos);
    }

    private void createNewCB() {
        clonedBook.getAuthors().add(new BookAuthor("", ""));
        setupAuthorPos(clonedBook.getAuthors().size()-1);
    }

    private void createGenreCB() {
        clonedBook.getGenre().add(new BookGenre("", ""));
        setupGenrePos(clonedBook.getGenre().size()-1);
    }


    private JComboBox<String> createGenreCB(String val) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.LINE_START;
        var comboBox = new JComboBox<String>();
        comboBox.setEditable(true);
        var tf = (JTextField) comboBox.getEditor().getEditorComponent();
        tf.setText(val);
        genrePanel.add(comboBox, constraints);
        genrePanel.revalidate();
        return comboBox;
    }

    private void setupGenreField(JComboBox<String> genreCB, int pos) {
        var genreField = AutoSuggestComboBox.create(genreCB, 1, bus.getGenreDataTableModel()::getColumnValueToString);
        var genre = clonedBook.getGenre().get(pos);
        if (Objects.isNull(genre)) {
            genreField.setText("");
            clonedBook.getAuthors().add(new BookAuthor("", ""));
        }
        else {
            genreField.setText(genre.toString());
        }

        genreField.getDocument().addDocumentListener(new BindingListener<>(genreField, clonedBook, s -> {
            var idName = s.split(",");
            if (idName.length == 2) {
                clonedBook.getGenre().get(pos).setId(idName[0]);
                clonedBook.getGenre().get(pos).setName(idName[1]);
            }
            else {
                clonedBook.getGenre().get(pos).setId("");
                clonedBook.getGenre().get(pos).setName("");
            }
        }));
    }




    private JComboBox<String> createAuthorCB() {
        return createAuthorCB("");
    }

    private JComboBox<String> createAuthorCB(String value) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.LINE_START;
        var comboBox = new JComboBox<String>();
        comboBox.setEditable(true);
        var tf = (JTextField) comboBox.getEditor().getEditorComponent();
        tf.setText(value);
        tacGiaPanel.add(comboBox, constraints);
        tacGiaPanel.revalidate();
        return comboBox;
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

}
