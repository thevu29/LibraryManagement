package Book.GUI;

import Book.BUS.BookBUS;
import Book.DTO.Importer;
import Book.ImporterDataTableModel;
import Utils.BindingListener;
import Utils.ComboBoxAutoSuggest.AutoSuggestComboBox;

import javax.swing.*;
import java.awt.event.*;

public class ImporterDialog extends JDialog {
    private final Importer importer;
    private final ImporterDataTableModel model;
    private final BookBUS bus;
    private final Importer cloneImporter;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel titleLabel;
    private JTextField iIDField;
    private JComboBox iNameCB;
    private JTextPane iDisField;
    private JComboBox iEmailCB;
    private JComboBox iPhoneCB;
    private JTextPane iAddressField;

    public ImporterDialog(Importer importer, ImporterDataTableModel model, BookBUS bus, String title) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);


        this.titleLabel.setText(title);
        this.importer = importer;
        this.model = model;
        this.bus = bus;

        cloneImporter = importer.clone();

        iIDField.setText(importer.getId());

        var iNameField = AutoSuggestComboBox.create(iNameCB, 1, model::getColumnValueToString);
        iNameField.setText(cloneImporter.getName());
        iNameField.getDocument().addDocumentListener(new BindingListener<>(iNameField, cloneImporter,
                cloneImporter::setName));

        var iEmailField = AutoSuggestComboBox.create(iEmailCB, 1, model::getColumnValueToString);
        iEmailField.setText(cloneImporter.getEmail());
        iEmailField.getDocument().addDocumentListener(new BindingListener<>(iEmailField, cloneImporter,
                cloneImporter::setEmail));

        var iPhoneField = AutoSuggestComboBox.create(iPhoneCB, 1, model::getColumnValueToString);
        iPhoneField.setText(cloneImporter.getPhone());
        iPhoneField.getDocument().addDocumentListener(new BindingListener<>(iPhoneField, cloneImporter,
                cloneImporter::setPhone));

        iAddressField.setText(cloneImporter.getAddress());
        iAddressField.getDocument().addDocumentListener(new BindingListener<>(iAddressField, cloneImporter, cloneImporter::setAddress));

        iDisField.setText(cloneImporter.getDescription());
        iDisField.getDocument().addDocumentListener(new BindingListener<>(iDisField, cloneImporter, cloneImporter::setDescription));




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
        if (bus.validateImporter(cloneImporter)) {
            importer.cloneFrom(cloneImporter);
            bus.commitImporter(importer);
            dispose();
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
