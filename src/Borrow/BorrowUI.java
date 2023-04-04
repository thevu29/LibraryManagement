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
