package sellBook;

import javax.swing.*;
import java.awt.event.*;

public class GioHangEditorDialog extends JDialog {
    private GioHangTableModel gioHangTableModel;
    private GioHangModel cart;
    private GioHangModel cloneCart;
    private JPanel contentPane;
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
    private JButton btnRemove;
    private JButton btnCancel;
    public GioHangEditorDialog(){

        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public GioHangEditorDialog(GioHangModel cart, GioHangTableModel cartTableModel) {
        this.gioHangTableModel = cartTableModel;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(btnRemove);

        var clone = cart.clone();
        this.cart = cart;
        this.cloneCart = clone;
        idBookTF.setText(String.valueOf(clone.getId()) );
        bookNameTF.setText(clone.getTenSach());
        conditionTF.setText("ok");
        statusTF.setText("ok");
        priceTF.setText(String.valueOf(clone.getGia()));
        authorTF.setText(String.join(",",clone.getTacGia()));
        publishTF.setText(String.join(",",clone.getNxb()));
        loaiTF.setText(String.join(",",clone.getTheLoai()));

        btnCancel.addActionListener(new ActionListener() {
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
        GioHangEditorDialog dialog = new GioHangEditorDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
