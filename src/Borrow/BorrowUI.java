package Borrow;

import javax.swing.*;

public class BorrowUI {
    private JPanel panel_btn;
    private JButton btn_delete;
    private JButton btn_detail;
    private JPanel panel_table;
    private JScrollPane sp;
    private JTable table_main;
    private JPanel panel_search;
    private JButton tìmKiếmButton;
    private JPanel panel_main;
    private JButton btn_update;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField6;
    private JTextField textField7;

    private BorrowModel model = new BorrowModel();

    public BorrowUI(){
        table_main.setModel(model);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("BorrowUI");
        frame.setContentPane(new BorrowUI().panel_main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
