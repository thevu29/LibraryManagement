package BookFault;

import javax.swing.*;

public class BookFaultUI {
    private JButton timKiemButton;
    private JTable table_main;
    private JButton btn_update;
    private JButton btn_close;
    private JButton btn_delete;
    private JPanel bookFault;
    private JPanel panel_search;
    private JPanel panel_table;
    private JPanel panel_button;

    private BookFaultModel model = new BookFaultModel();

    public BookFaultUI() {
        table_main.setModel(model);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("BookFaultUI");
        frame.setContentPane(new BookFaultUI().bookFault);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
