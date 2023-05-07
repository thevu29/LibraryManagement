package GUI;

import DTO.AuthorDataTableModel;
import BUS.BookBUS;
import DTO.Author;
import Utils.BindingListener;
import Utils.ComboBoxAutoSuggest.AutoSuggestComboBox;
import Utils.EGender;

import javax.swing.*;
import java.awt.event.*;

public class AuthorDialog extends JDialog {
    private final Author author;
    private final AuthorDataTableModel model;
    private final BookBUS bus;
    private final Author clonedAuthor;
    private final int mode;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField authorIDField;
    private JComboBox<String> authorNameCB;
    private JComboBox<String> sexCB;
    private JComboBox<String> emailCB;
    private JTextPane descriptionField;
    private JLabel titleLabel;

    public AuthorDialog(Author author, AuthorDataTableModel model, BookBUS bus, String title, int mode) {
        this.mode = mode;
        this.titleLabel.setText(title);
        this.author = author;
        this.model = model;
        this.bus = bus;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        clonedAuthor = author.clone();

        authorIDField.setEditable(false);


        authorIDField.setText(clonedAuthor.getId());
        authorIDField.getDocument().addDocumentListener(new BindingListener<>(authorIDField,clonedAuthor,
                clonedAuthor::setId));
        var authorNameField = AutoSuggestComboBox.create(authorNameCB, 1, model::getColumnValueToString);
        authorNameField.setText(clonedAuthor.getName());
        authorNameField.getDocument().addDocumentListener(new BindingListener<>(authorNameField,clonedAuthor,
                clonedAuthor::setName));


        DefaultComboBoxModel<String> sexModel = new DefaultComboBoxModel<>();
        sexModel.addElement("Nam");
        sexModel.addElement("Nữ");
        sexCB.setModel(sexModel);
        int selectedIndex = author.getGender() == EGender.NAM ? 0 : 1;
        sexCB.setSelectedIndex(selectedIndex);
        sexCB.addItemListener(e -> {
            if (e.getItem()=="Nữ") {
                clonedAuthor.setGender("NU");
            }
            else {
                clonedAuthor.setGender("NAM");
            }
        });

        var emailField = AutoSuggestComboBox.create(emailCB, 3, model::getColumnValueToString);
        emailField.setText(author.getEmail());
        emailField.getDocument().addDocumentListener(new BindingListener<>(emailField, clonedAuthor, clonedAuthor::setEmail));

        descriptionField.setText(clonedAuthor.getDescription());
        descriptionField.getDocument().addDocumentListener(new BindingListener<>(descriptionField, clonedAuthor, clonedAuthor::setDescription));

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
    }

    private void onOK() {
        // add your code here

        if (bus.validateAuthor(clonedAuthor, mode)) {
            author.cloneFrom(clonedAuthor);
            bus.commitAuthor(author);
            dispose();
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
