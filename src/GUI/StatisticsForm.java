package GUI;

import BUS.BookBUS;
import BUS.BorrowBUS;
import org.jfree.data.general.DefaultPieDataset;
import BUS.SellTicketBus;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.time.Year;
import java.util.ArrayList;

public class StatisticsForm {
    private MembershipStatisticsForm membershipStatisticsForm;
    private BookChart bookChart;
//    private SellChart sellChart;
    private BookBUS bookBUS;
    private SellTicketBus sellTicketBus;
    private final BorrowBUS borrowBUS;
    //    private MembershipStatisticsForm membershipStatisticsForm = new MembershipStatisticsForm();
    private BorrowChart borrow;
    private SellChart sell_ticket;
    private ChartCTHD cthd = new ChartCTHD();


    public StatisticsForm() {
        this(new BookBUS(), new SellTicketBus(), new BorrowBUS());
    }

    public StatisticsForm(BookBUS bookBUS, SellTicketBus sellTicketBus, BorrowBUS borrowBUS) {
        this.bookBUS = bookBUS;
        this.sellTicketBus = sellTicketBus;
        this.borrowBUS = borrowBUS;

        borrow = new BorrowChart(borrowBUS);

        membershipStatisticsForm = new MembershipStatisticsForm();
        membershipPanel.add(membershipStatisticsForm.getMainPanel());

        DefaultPieDataset dataset = bookBUS.thonngKeTinhTrangSach();
        ArrayList<String> dsOpt = new ArrayList<>();
        dsOpt.add("THỐNG KÊ TÌNH TRẠNG SÁCH");
        bookChart = new BookChart(dataset, dsOpt);
        bookPanel.add(bookChart.getMain());

        dsOpt.clear();
        dsOpt.add("THỐNG KÊ SỐ LƯỢNG PHIẾU BÁN THEO NĂM");
        dsOpt.add("THỐNG KÊ THU NHẬP THEO NĂM");
        int nam = Year.now().getValue();
        sell_ticket = new SellChart(sellTicketBus);
        pnlHoaDon.add(sell_ticket.getMain());

        tabbedPane1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(tabbedPane1.getSelectedIndex() == 0){
                    membershipPanel.add(membershipStatisticsForm.getMainPanel());
                }
                else if(tabbedPane1.getSelectedIndex() == 1){
                    pnlPhieuMuon.add(borrow.getMain());
                }
                else if(tabbedPane1.getSelectedIndex() == 2){
                    pnlHoaDon.add(sell_ticket.getMain());
                }
                else if(tabbedPane1.getSelectedIndex() == 3){
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
}
