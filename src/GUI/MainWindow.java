package GUI;

import BUS.BookBUS;
import BUS.BorrowBUS;
import BUS.LoginBUS;
import com.formdev.flatlaf.FlatLightLaf;
import BUS.SellTicketBus;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow {
    private BookBUS bookForm;
    private CustomerForm customerForm;
    private NhanVienadmin employeeForm;
    private HoaDonGUI sellForm;
    private BorrowUI borrowForm;
    private StatisticsForm statisticsForm;
    private LoginBUS loginBUS = new LoginBUS();
    private String employeeId;
    private BorrowBUS borrowBus;
    private SellTicketBus sellTicketBus;

    static class ChayChu extends Thread {
        private String chuChay = "Ứng dụng quản lý thư viện             ";
        private Object ob;
        public ChayChu(Object ob){
            this.ob = ob;
        }
        @Override
        public  void run(){
            while(ob!=null){
                chuChay = chuChay.substring(1)+chuChay.substring(0,1);
                ((JFrame)ob).setTitle(chuChay);
                try{
                    Thread.sleep(150);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public MainWindow(String employeeId) {
        this.employeeId = employeeId;
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
                    bookForm.resetData();
                } else if (e.getSource() == lblCustomer) {
                    card.show(contentPanel, "Customer");
                } else if (e.getSource() == lblSell) {
                    card.show(contentPanel, "Sell");
                } else if (e.getSource() == lblEmployee) {
                    card.show(contentPanel, "Employee");
                } else if (e.getSource() == lblBorrow) {
                    card.show(contentPanel, "Borrow");
                } else if (e.getSource() == lblStatistics) {
                    var statisticsForm = new StatisticsForm(bookForm, sellTicketBus, borrowBus);
                    contentPanel.add("Statistics", statisticsForm.getMainPanel());
                    card.show(contentPanel, "Statistics");
                }
            }
        };
        lblBook.addMouseListener(showContent);
        lblCustomer.addMouseListener(showContent);
        lblSell.addMouseListener(showContent);
        lblBorrow.addMouseListener(showContent);
        lblEmployee.addMouseListener(showContent);
        lblStatistics.addMouseListener(showContent);

        if (!loginBUS.findNhanVienChucVu(this.employeeId).equals("Quản lý")) {
            lblEmployee.setVisible(false);
            lblStatistics.setVisible(false);
        }

        lblLogout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
                loginBUS.logout(frame);
                LoginForm loginForm = new LoginForm();
                loginForm.openLoginForm();
            }
        });
    }

    public void initContentPanel() {
        bookForm = new BookBUS();
        sellTicketBus = new SellTicketBus();
        borrowBus = new BorrowBUS();

        customerForm = new CustomerForm();
        employeeForm = new NhanVienadmin();
        sellForm = new HoaDonGUI(this.employeeId);
        borrowForm = new BorrowUI(this.employeeId);
//        statisticsForm = new StatisticsForm(bookForm, sellTicketBus, borrowBus);

        contentPanel.add("Book", bookForm.getPanel());
        contentPanel.add("Customer", customerForm.getContentPanel());
        contentPanel.add("Borrow", borrowForm.getPanel1());
        contentPanel.add("Employee", employeeForm.getMain());
        contentPanel.add("Sell", sellForm.getMain());
//        contentPanel.add("Statistics", statisticsForm.getMainPanel());
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
                } else if (e.getSource() == lblStatistics) {
                    lblStatistics.setForeground(Color.RED);
                } else if (e.getSource() == lblLogout) {
                    lblLogout.setForeground(Color.RED);
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
                } else if (e.getSource() == lblStatistics) {
                    lblStatistics.setForeground(Color.BLACK);
                } else if (e.getSource() == lblLogout) {
                    lblLogout.setForeground(Color.BLACK);
                }
            }
        };
        lblBook.addMouseListener(hover);
        lblEmployee.addMouseListener(hover);
        lblCustomer.addMouseListener(hover);
        lblBorrow.addMouseListener(hover);
        lblSell.addMouseListener(hover);
        lblStatistics.addMouseListener(hover);
        lblLogout.addMouseListener(hover);
    }

    public void setCursor() {
        lblBook.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblEmployee.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblCustomer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblBorrow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblSell.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblStatistics.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public void setMargin() {
        Border border = BorderFactory.createEmptyBorder(28, 0, 0, 0);
        lblBook.setBorder(BorderFactory.createEmptyBorder(12, 0, 0, 0));
        lblEmployee.setBorder(border);
        lblCustomer.setBorder(border);
        lblBorrow.setBorder(border);
        lblSell.setBorder(border);
        lblStatistics.setBorder(border);
        lblLogout.setBorder(BorderFactory.createEmptyBorder(0, 0, 24, 0));
    }

    public void openMainWindow(String employeeId) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        JFrame frame = new JFrame("MainWindow");
        frame.setContentPane(new MainWindow(employeeId).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1350, 650);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        JFrame frame = new JFrame();
        frame.setContentPane(new MainWindow("NV002").mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1350, 650);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        ChayChu cc = new ChayChu(frame);
        cc.start();
    }

    private JPanel mainPanel;
    private JPanel contentPanel;
    private JPanel taskbar;
    private JLabel lblBook;
    private JLabel lblEmployee;
    private JLabel lblCustomer;
    private JLabel lblBorrow;
    private JLabel lblSell;
    private JLabel lblStatistics;
    private JLabel lblLogout;
}