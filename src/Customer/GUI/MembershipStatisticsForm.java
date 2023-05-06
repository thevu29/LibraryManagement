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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;

public class MembershipStatisticsForm {
    private JFreeChart barChart;
    private ChartPanel chartPanel;
    private DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    private MembershipTypeBUS membershipTypeBUS = new MembershipTypeBUS();
    private CustomerBUS customerBUS = new CustomerBUS();

    public MembershipStatisticsForm() {
        initComboboxvalues();
        initChart();

        cbxYear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int year = Integer.parseInt(cbxYear.getSelectedItem().toString());
                lblTotalMembership.setText("Tổng thành viên trong năm " + year + ": " + customerBUS.countMembershipInYear(year));
            }
        });

        ItemListener itemListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                initChart();
            }
        };
        cbxMembershipType.addItemListener(itemListener);
        cbxYear.addItemListener(itemListener);
    }

    public void createDataset() {
        String type = cbxMembershipType.getSelectedItem().toString();
        int year = Integer.parseInt(cbxYear.getSelectedItem().toString());

        for (int i = 1; i <= 12; i++) {
            dataset.setValue(customerBUS.countMembershipByMonth(i, year, type), "Số khách hàng thành viên", i + "");
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

        int year = LocalDate.now().getYear();
        for (int i = 2022; i <= year; i++) {
            cbxYear.addItem(i);
        }
        cbxYear.setSelectedIndex(cbxYear.getItemCount() - 1);
        lblTotalMembership.setText("Tổng thành viên trong năm " + year + ": " + customerBUS.countMembershipInYear(year));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CustomerStatistics");
        frame.setContentPane(new MembershipStatisticsForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel mainPanel;
    private JPanel chart;
    private JComboBox cbxMembershipType;
    private JComboBox cbxYear;
    private JLabel lblTotalMembership;
}
