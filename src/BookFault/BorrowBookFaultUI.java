package BookFault;

import javax.swing.*;

public class BorrowBookFaultUI {
    private JPanel panel_search;
    private JButton timKiemButton;
    private JPanel panel_table;
    private JTable table_main;
    private JPanel panel_button;
    private JButton btn_update;
    private JButton btn_close;
    private JButton btn_delete;
    private JButton btn_add;
    private JPanel borrowBookFault;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;

    private BorrowBookFaultModel model = new BorrowBookFaultModel();

    public BorrowBookFaultUI(){
        table_main.setModel(model);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("BorrowBookFaultUI");
        frame.setContentPane(new BorrowBookFaultUI().borrowBookFault);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
