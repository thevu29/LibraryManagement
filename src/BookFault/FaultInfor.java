package BookFault;

import javax.swing.*;

public class FaultInfor {
    private JTextField txtCustomerId;
    private JTextField txtCustomerName;
    private JTextField txtCustomerDOB;
    private JButton btnSave;
    private JButton btnReset;
    private JPanel panel_main;

    public static void main(String[] args) {
        JFrame frame = new JFrame("FaultInfor");
        frame.setContentPane(new FaultInfor().panel_main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
