package Login.GUI;

import javax.swing.*;
import java.awt.*;

public class LoginForm {
    public LoginForm() {
        setInset();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("LoginForm");
        frame.setContentPane(new LoginForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void setInset() {
        Insets inset = new Insets(4, 10, 4, 10);
        txtUsername.setMargin(inset);
        txtPassword.setMargin(inset);
        btnLogin.setMargin(inset);
    }

    private JPanel mainPanel;
    private JButton btnLogin;
    private JLabel lblBanner;
    private JPanel bannerPanel;
    private JTextField txtUsername;
    private JTextField txtPassword;
}
