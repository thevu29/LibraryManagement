package Statistics.GUI;

import Borrow.GUI.BorrowChart;
import Customer.GUI.MembershipStatisticsForm;
import sellBook.GUI.Chart;
import sellBook.GUI.ChartCTHD;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class StatisticsForm {
    private MembershipStatisticsForm membershipStatisticsForm = new MembershipStatisticsForm();
    private BorrowChart borrow = new BorrowChart();
    private Chart sell_ticket = new Chart();
    private ChartCTHD cthd = new ChartCTHD();
    public StatisticsForm() {
        membershipPanel.add(membershipStatisticsForm.getMainPanel());

        tabbedPane1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(tabbedPane1.getSelectedIndex() == 0){
                    membershipPanel.add(membershipStatisticsForm.getMainPanel());
                }
                else if(tabbedPane1.getSelectedIndex() == 1){
                    pnlPhieuMuon.add(borrow.getMain());
                }
                else if(tabbedPane1.getSelectedIndex()==2){
                    pnlHoaDon.add(sell_ticket.getMain());
                }
                else if(tabbedPane1.getSelectedIndex()==3){
                    pnlCTHD.add(cthd.getMain());
                }
            }
        });
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
    private JPanel pnlPhieuMuon;
    private JPanel pnlHoaDon;
    private JPanel pnlCTHD;
}
