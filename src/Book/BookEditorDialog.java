package Book;

import Utils.BindingListener;
import Utils.ComboBoxAutoSuggest.AutoSuggestComboBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BookEditorDialog extends JDialog {
    private final BookDataTableModel bookDataTableModel;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox<String> bookSerialCB;
    private JButton button2;
    private JPanel tacGiaPanel;
    private JButton button1;
    private JTextPane textPane1;
    private JPanel mainPanel;
    private JComboBox<String> bookNameCB;
    private JComboBox conditionCB;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    //TODO Add Author and Publisher model

    public BookEditorDialog() {
        this(Book.createTestBook(), new BookDataTableModel());
    }

    private Book book;
    private Book clonedBook;

    public BookEditorDialog(Book book, BookDataTableModel bookDataTableModel) {
        this.bookDataTableModel = bookDataTableModel;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);


        var clonedBook = book.clone();
        this.book = book;
        this.clonedBook = clonedBook;

        var bookSerialField = AutoSuggestComboBox.create(bookSerialCB, 0, bookDataTableModel::getColumnValueToString);
        bookSerialField.setText(clonedBook.getId());
        bookSerialField.getDocument().addDocumentListener(new BindingListener<>(clonedBook, clonedBook::setId));

        var bookNameField = AutoSuggestComboBox.create(bookNameCB, 0, bookDataTableModel::getColumnValueToString);
        bookNameField.setText(clonedBook.getName());
        bookNameField.getDocument().addDocumentListener(new BindingListener<>(clonedBook, clonedBook::setName));



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
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.gridx = 0;
                constraints.fill = GridBagConstraints.HORIZONTAL;
                constraints.anchor = GridBagConstraints.LINE_START;
                var comboBox = new JComboBox<String>();
                comboBox.setEditable(true);
                tacGiaPanel.add(comboBox, constraints);
                tacGiaPanel.revalidate();
            }
        });
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
