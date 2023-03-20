package MainGUI;

import Book.BookEditorDialog;
import Book.BookGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow {
    private JPanel panel1;
    private JPanel contentPanel;
    private JButton button1;

    public MainWindow() {

        var test = new BookGUI();
        contentPanel.add("Book",test.getPanel1());
        contentPanel.add("Dialog",new BookEditorDialog().getMainPanel());

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var card = (CardLayout) contentPanel.getLayout();
                card.show(contentPanel, "Dialog");
            }
        });


    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainWindow");
        frame.setContentPane(new MainWindow().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
