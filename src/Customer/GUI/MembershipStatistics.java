package Customer.GUI;

import Customer.BUS.CustomerBUS;
import Customer.BUS.MembershipTypeBUS;
import Customer.DTO.MembershipType;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class MembershipStatistics {
    JFreeChart barChart;
    ChartPanel chartPanel;
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    MembershipTypeBUS membershipTypeBUS = new MembershipTypeBUS();
    CustomerBUS customerBUS = new CustomerBUS();

    public MembershipStatistics() {
        initComboboxvalues();
        initChart();
    }

    public void createDataset() {
        for (int i = 1; i <= 12; i++) {
            dataset.setValue(customerBUS.countMembershipByMonth(i), "Số khách hàng", i + "");
        }
    }

    public void initChart() {
        createDataset();

        barChart = ChartFactory.createBarChart(
                "",
                "Tháng",
                "Số khách hàng",
                dataset,
                PlotOrientation.VERTICAL,
                false, false, false
        );

        chartPanel = new ChartPanel(barChart);
        chart.add(chartPanel, BorderLayout.CENTER);
    }

    public void initComboboxvalues() {
        cbxMembershipType.addItem("Tất cả");
        for (MembershipType membershipType : membershipTypeBUS.getMembershipTypeList()) {
            cbxMembershipType.addItem(membershipType.getMembershipTypeName());
        }

        LocalDate now = LocalDate.now();
        int year = now.getYear();
        for (int i = 2023; i <= year; i++) {
            cbxYear.addItem(i);
        }
        cbxYear.setSelectedIndex(cbxYear.getItemCount() - 1);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CustomerStatistics");
        frame.setContentPane(new MembershipStatistics().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel mainPanel;
    private JPanel chart;
    private JComboBox cbxMembershipType;
    private JComboBox cbxYear;
}
