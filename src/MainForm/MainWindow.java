package MainForm;

import Book.BUS.BookBUS;
import Customer.GUI.CustomerForm;
import NhanVien.form.NhanVienadmin;
import sellBook.GUI.HoaDonGUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow {
    public MainWindow() {
        setMargin();
        setCursor();
        hover();
        initContentPanel();

        MouseAdapter showContent = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CardLayout card = (CardLayout) contentPanel.getLayout();

                if (e.getSource() == lblBook) {
                    card.show(contentPanel, "Book");
                } else if (e.getSource() == lblCustomer) {
                    card.show(contentPanel, "Customer");
                } else if (e.getSource() == lblSell) {
                    card.show(contentPanel, "Sell");
                } else if (e.getSource() == lblEmployee) {
                    card.show(contentPanel, "Employee");
                }
            }
        };
        lblBook.addMouseListener(showContent);
        lblCustomer.addMouseListener(showContent);
        lblSell.addMouseListener(showContent);
        lblBorrow.addMouseListener(showContent);
        lblEmployee.addMouseListener(showContent);
    }

    public void initContentPanel() {
        var bookForm = new BookBUS();
        CustomerForm customerForm = new CustomerForm();
        NhanVienadmin employeeForm = new NhanVienadmin();
        HoaDonGUI sellForm = new HoaDonGUI();

        contentPanel.add("Book", bookForm.getPanel());
        contentPanel.add("Customer", customerForm.getContentPanel());
        contentPanel.add("Employee", employeeForm.getMainPanel());
        contentPanel.add("Sell", sellForm.getMain());
    }

    public void hover() {
        MouseAdapter hover = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (e.getSource() == lblBook) {
                    lblBook.setForeground(Color.RED);
                } else if (e.getSource() == lblEmployee) {
                    lblEmployee.setForeground(Color.RED);
                } else if (e.getSource() == lblCustomer) {
                    lblCustomer.setForeground(Color.RED);
                } else if (e.getSource() == lblBorrow) {
                    lblBorrow.setForeground(Color.RED);
                } else if (e.getSource() == lblSell) {
                    lblSell.setForeground(Color.RED);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (e.getSource() == lblBook) {
                    lblBook.setForeground(Color.BLACK);
                } else if (e.getSource() == lblEmployee) {
                    lblEmployee.setForeground(Color.BLACK);
                } else if (e.getSource() == lblCustomer) {
                    lblCustomer.setForeground(Color.BLACK);
                } else if (e.getSource() == lblBorrow) {
                    lblBorrow.setForeground(Color.BLACK);
                } else if (e.getSource() == lblSell) {
                    lblSell.setForeground(Color.BLACK);
                }
            }
        };

        lblBook.addMouseListener(hover);
        lblEmployee.addMouseListener(hover);
        lblCustomer.addMouseListener(hover);
        lblBorrow.addMouseListener(hover);
        lblSell.addMouseListener(hover);
    }

    public void setCursor() {
        lblBook.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblEmployee.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblCustomer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblBorrow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblSell.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public void setMargin() {
        Border border = BorderFactory.createEmptyBorder(28, 0, 0, 0);
        lblBook.setBorder(BorderFactory.createEmptyBorder(12, 0, 0, 0));
        lblEmployee.setBorder(border);
        lblCustomer.setBorder(border);
        lblBorrow.setBorder(border);
        lblSell.setBorder(border);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainWindow");
        frame.setContentPane(new MainWindow().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1350, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel mainPanel;
    private JPanel contentPanel;
    private JPanel taskbar;
    private JLabel lblBook;
    private JLabel lblEmployee;
    private JLabel lblCustomer;
    private JLabel lblBorrow;
    private JLabel lblSell;
}