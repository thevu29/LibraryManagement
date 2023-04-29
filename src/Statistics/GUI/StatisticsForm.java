package Statistics.GUI;

import Customer.GUI.MembershipStatisticsForm;

import javax.swing.*;

public class StatisticsForm {
    private MembershipStatisticsForm membershipStatisticsForm = new MembershipStatisticsForm();

    public StatisticsForm() {
        membershipPanel.add(membershipStatisticsForm.getMainPanel());
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("StatisticsForm");
        frame.setContentPane(new StatisticsForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JPanel membershipPanel;
}
