package sellBook;

import javax.swing.*;

public class test {
    private JPanel maintest;
    private JPanel dsAndFilter;
    private JComboBox textField1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JTable table1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JTextField textField5;
    private JTextField textField6;
    private JScrollPane tblCart;

    public static void main(String[] args) {
        JFrame frame = new JFrame("test");
        frame.setContentPane(new test().maintest);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
