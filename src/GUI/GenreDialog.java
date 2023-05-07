package GUI;

import BUS.BookBUS;
import DTO.Genre;
import DTO.GenreDataTableModel;
import Utils.BindingListener;
import Utils.ComboBoxAutoSuggest.AutoSuggestComboBox;

import javax.swing.*;
import java.awt.event.*;

public class GenreDialog extends JDialog {
    private final Genre genre;
    private final GenreDataTableModel model;
    private final BookBUS bus;
    private final Genre clonedGenre;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField gIDField;
    private JLabel titleLabel;
    private JComboBox<String> gNameCB;
    private JTextPane gDisField;

    public GenreDialog(Genre genre, GenreDataTableModel model, BookBUS bus, String title) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        this.titleLabel.setText(title);
        this.genre = genre;
        this.model = model;
        this.bus = bus;

        clonedGenre = genre.clone();

        gIDField.setText(genre.getId());

        var gNameField = AutoSuggestComboBox.create(gNameCB, 1, model::getColumnValueToString);
        gNameField.setText(clonedGenre.getName());
        gNameField.getDocument().addDocumentListener(new BindingListener<>(gNameField,clonedGenre,
                clonedGenre::setName));

        gDisField.setText(clonedGenre.getDescription());
        gDisField.getDocument().addDocumentListener(new BindingListener<>(gDisField, clonedGenre, clonedGenre::setDescription));

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
        int mode = 0;
        if (clonedGenre.getName().trim().equals(genre.getName())) {
            mode = 1;
            System.out.println("as");
        }
        if (bus.validateGenre(clonedGenre, mode)) {
            genre.cloneFrom(clonedGenre);
            bus.commitGenre(genre);
            dispose();
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }


}
