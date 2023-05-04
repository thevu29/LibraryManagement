package Login.GUI;

import Login.BUS.LoginBUS;
import MainForm.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm {
    private MainWindow mainWindow;
    private LoginBUS loginBUS = new LoginBUS();

    public LoginForm() {
        setInset();

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = txtPassword.getText();

                if(loginBUS.login(username, password)) {
                    mainWindow = new MainWindow(username);
                    mainWindow.openMainWindow(username);
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(mainPanel);
                    frame.dispose();
                }
            }
        });
    }

    public void setInset() {
        Insets inset = new Insets(4, 10, 4, 10);
        txtUsername.setMargin(inset);
        txtPassword.setMargin(inset);
        btnLogin.setMargin(inset);
    }

    public void openLoginForm() {
        JFrame frame = new JFrame("LoginForm");
        frame.setContentPane(new LoginForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("LoginForm");
        frame.setContentPane(new LoginForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel mainPanel;
    private JButton btnLogin;
    private JLabel lblBanner;
    private JPanel bannerPanel;
    private JTextField txtUsername;
    private JTextField txtPassword;
}
