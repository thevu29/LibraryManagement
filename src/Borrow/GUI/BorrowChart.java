package Borrow.GUI;

import Borrow.BUS.BorrowBUS;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Year;

public class BorrowChart {
    private JLabel lblTotalMembership;
    private JPanel chart1;
    private JPanel mainPNL;
    private JTextField txtNam;
    private JTextField txtTongSlgPM;
    private JButton btnThongKe;
    private JComboBox cboThongKe;

    private BorrowBUS bus;
    private static DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    private static DefaultPieDataset pieDataset = new DefaultPieDataset();

    public BorrowChart(BorrowBUS bus) {
        this.bus = bus;
        int nam =  Year.now().getValue();

        dataset = bus.laySoLieuPhieuMuon(nam);
        cboThongKe.addItem("Thống kê số lượng phiếu mượn");
        cboThongKe.addItem("Thống kê số lượng sách");
        cboThongKe.addItem("Thống kê lỗi");


        JFreeChart chart = createBarChart(cboThongKe.getSelectedItem().toString());
        txtTongSlgPM.setText(String.valueOf(bus.tongSoPhieuMuon(nam)));
        txtNam.setText(String.valueOf(nam));
        // create chart panel and add it to the chart JPanel
        ChartPanel chartPanel = new ChartPanel(chart);
        chart1.setLayout(new java.awt.BorderLayout());
        chart1.add(chartPanel);
        btnThongKe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nam =Integer.parseInt(txtNam.getText());
                JFreeChart newChart = thongKeTheoOption();
                chartPanel.setChart(newChart);
            }
        });

        cboThongKe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFreeChart newChart = thongKeTheoOption();

                chartPanel.setChart(newChart);

            }
        });
    }

    public JFreeChart thongKeTheoOption(){
        String selectedOption = cboThongKe.getSelectedItem().toString();
        JFreeChart newChart = createBarChart(selectedOption);
        int nam = Year.now().getValue();
        if(cboThongKe.getSelectedItem()=="Thống kê số lượng phiếu mượn"){
            if(txtNam.getText().isEmpty()){
                dataset = bus.laySoLieuPhieuMuon(nam);
            }
            else{
                int year = Integer.parseInt(txtNam.getText());
                dataset = bus.laySoLieuPhieuMuon(year);
            }
            newChart = createBarChart(selectedOption);
        }
        else if(cboThongKe.getSelectedItem()=="Thống kê số lượng sách"){
            if(txtNam.getText().isEmpty()){
                dataset = bus.thongKeSachMuon(nam);
            }
            else{
                int year = Integer.parseInt(txtNam.getText());
                dataset = bus.thongKeSachMuon(year);
            }
            newChart = createBarChart(selectedOption);
        }
        else if(cboThongKe.getSelectedItem()=="Thống kê lỗi"){
            if(txtNam.getText().isEmpty()){
                pieDataset = bus.thongKeLoi(nam);
            }
            else{
                int year = Integer.parseInt(txtNam.getText());
                pieDataset = bus.thongKeLoi(year);
            }
            newChart = createPieChart(selectedOption);
        }

        // update the chart panel with the new chart
        return newChart;
    }

    public static JFreeChart createBarChart(String selectedOption){
        JFreeChart newChart = ChartFactory.createBarChart(
                "Hoa Don", // SellChart title
                selectedOption, // X-Axis Label
                "Number of Phieu Muon", // Y-Axis Label
                dataset, // New dataset
                PlotOrientation.VERTICAL, // Plot orientation
                false, // Show legend
                true, // Use tooltips
                false // Generate URLs
        );
        return newChart;
    }

    public JFreeChart createPieChart(String title) {
        JFreeChart chart = ChartFactory.createPieChart3D(
                title, // SellChart title
                pieDataset, // Dataset
                true, // Show legend
                true, // Use tooltips
                false // Generate URLs
        );

        // Set a custom color scheme
        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setSectionPaint("Section 1", new Color(255, 100, 100));
        plot.setSectionPaint("Section 2", new Color(100, 255, 100));
        plot.setSectionPaint("Section 3", new Color(100, 100, 255));

        // Add labels and tooltips
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: ({2})"));
        plot.setToolTipGenerator(new StandardPieToolTipGenerator());

        // Adjust the font size
        Font font = chart.getTitle().getFont().deriveFont(24f);
        chart.getTitle().setFont(font);
        plot.setLabelFont(font);
        chart.getLegend().setItemFont(font);

        // Add a legend
        chart.getLegend().setBorder(0, 0, 0, 0);

        return chart;
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("BorrowChart");
        frame.setContentPane(new BorrowChart(new BorrowBUS()).mainPNL);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public JPanel getMain() {
        return mainPNL;
    }
}
