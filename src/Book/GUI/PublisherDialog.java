package Book.GUI;

import Book.BUS.BookBUS;
import Book.DTO.Publisher;
import Book.PublisherDataTableModel;
import Utils.BindingListener;
import Utils.ComboBoxAutoSuggest.AutoSuggestComboBox;

import javax.swing.*;
import java.awt.event.*;

public class PublisherDialog extends JDialog {
    private final Publisher publisher;
    private final PublisherDataTableModel model;
    private final BookBUS bus;
    private final Publisher clonedPublisher;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel titleLabel;
    private JTextField pIDField;
    private JComboBox<String> pNameCB;
    private JTextPane pDisField;
    private JComboBox<String> pEmailCB;
    private JComboBox<String> pPhoneCB;
    private JTextPane pAddressField;

    public PublisherDialog(Publisher publisher, PublisherDataTableModel model, BookBUS bus, String title) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);


        this.titleLabel.setText(title);
        this.publisher = publisher;
        this.model = model;
        this.bus = bus;

        clonedPublisher = publisher.clone();

        pIDField.setText(publisher.getId());

        var pNameField = AutoSuggestComboBox.create(pNameCB, 1, model::getColumnValueToString);
        pNameField.setText(clonedPublisher.getName());
        pNameField.getDocument().addDocumentListener(new BindingListener<>(pNameField, clonedPublisher,
                clonedPublisher::setName));

        var pEmailField = AutoSuggestComboBox.create(pEmailCB, 1, model::getColumnValueToString);
        pEmailField.setText(clonedPublisher.getEmail());
        pEmailField.getDocument().addDocumentListener(new BindingListener<>(pEmailField, clonedPublisher,
                clonedPublisher::setEmail));

        var pPhoneField = AutoSuggestComboBox.create(pPhoneCB, 1, model::getColumnValueToString);
        pPhoneField.setText(clonedPublisher.getPhone());
        pPhoneField.getDocument().addDocumentListener(new BindingListener<>(pPhoneField, clonedPublisher,
                clonedPublisher::setPhone));

        pAddressField.setText(clonedPublisher.getAddress());
        pAddressField.getDocument().addDocumentListener(new BindingListener<>(pAddressField, clonedPublisher, clonedPublisher::setAddress));

        pDisField.setText(clonedPublisher.getDescription());
        pDisField.getDocument().addDocumentListener(new BindingListener<>(pDisField, clonedPublisher, clonedPublisher::setDescription));


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
        if (bus.validatePublisher(clonedPublisher)) {
            publisher.cloneFrom(clonedPublisher);
            bus.commitPublisher(publisher);
            dispose();
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }


}
