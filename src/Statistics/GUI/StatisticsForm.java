package Statistics.GUI;

import Borrow.GUI.BorrowChart;
import Book.BUS.BookBUS;
import Book.GUI.BookChart;
import Customer.GUI.MembershipStatisticsForm;
import sellBook.GUI.Chart;
import sellBook.GUI.ChartCTHD;
import org.jfree.data.general.DefaultPieDataset;
import sellBook.BUS.SellTicketBus;
import sellBook.GUI.SellChart;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.time.Year;
import java.util.ArrayList;

public class StatisticsForm {
    private MembershipStatisticsForm membershipStatisticsForm;
    private BookChart bookChart;
    private SellChart sellChart;
    private BookBUS bookBUS;
    private SellTicketBus sellTicketBus;
    private MembershipStatisticsForm membershipStatisticsForm = new MembershipStatisticsForm();
    private BorrowChart borrow = new BorrowChart();
    private Chart sell_ticket = new Chart();
    private ChartCTHD cthd = new ChartCTHD();


    public StatisticsForm() {
        membershipStatisticsForm = new MembershipStatisticsForm();
        membershipPanel.add(membershipStatisticsForm.getMainPanel());

        bookBUS = new BookBUS();
        DefaultPieDataset dataset = bookBUS.thonngKeTinhTrangSach();
        ArrayList<String> dsOpt = new ArrayList<>();
        dsOpt.add("THỐNG KÊ TÌNH TRẠNG SÁCH");
        bookChart = new BookChart(dataset, dsOpt);
        bookPanel.add(bookChart.getMain());

        sellTicketBus = new SellTicketBus();
        dsOpt.clear();
        dsOpt.add("THỐNG KÊ SỐ LƯỢNG PHIẾU BÁN THEO NĂM");
        dsOpt.add("THỐNG KÊ THU NHẬP THEO NĂM");
        int nam = Year.now().getValue();
        sellChart = new SellChart(sellTicketBus.thongKeTheoNam(nam), "Tháng", dsOpt);
        sellPanel.add(sellChart.getMain());

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
    private JPanel bookPanel;
    private JPanel sellPanel;
}
