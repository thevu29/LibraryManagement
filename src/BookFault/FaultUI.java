package BookFault;

import javax.swing.*;

public class FaultUI {
    private JButton timKiemButton;
    private JTable table_main;
    private JButton btn_update;
    private JButton btn_close;
    private JButton btn_delete;
    private JPanel fault;
    private JPanel panel_search;
    private JPanel panel_table;
    private JPanel panel_button;
    private JButton btn_add;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;

    private FaultModel model = new FaultModel();

    public FaultUI() {
        table_main.setModel(model);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("BookFaultUI");
        frame.setContentPane(new FaultUI().fault);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
