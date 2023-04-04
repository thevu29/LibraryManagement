package Borrow;

import javax.swing.*;

public class BorrowDetailUI {
    private JButton timKiemButton;
    private JTable table_main;
    private JButton btn_update;
    private JButton btn_close;
    private JButton btn_delete;
    private JPanel panel_main;
    private JPanel panel_search;
    private JPanel panel_table;
    private JPanel panel_btn;

    private BorrowDetailModel model = new BorrowDetailModel();

    public BorrowDetailUI() {
        table_main.setModel(model);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("BorrowDetailUI");
        frame.setContentPane(new BorrowDetailUI().panel_main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
