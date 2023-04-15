package sellBook;

import javax.swing.*;
import java.awt.event.*;

public class CTHDFormDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel mainPanel;
    private JPanel tacGiaPanel;
    private JTextField authorTF;
    private JTextField loaiTF;
    private JTextField publishTF;
    private JTextField namXuatBanTF;
    private JTextField languageTF;
    private JTextField totalPageTF;
    private JTextPane descriptionField;
    private JTextField conditionTF;
    private JTextField bookNameTF;
    private JTextField statusTF;
    private JTextField priceTF;
    private JTextField idBookTF;

    public CTHDFormDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

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
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        CTHDFormDialog dialog = new CTHDFormDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
