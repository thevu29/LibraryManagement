package GUI;

import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import BUS.SellTicketBus;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.Year;

public class SellChart {

    SellTicketBus bus = new SellTicketBus();

    private JPanel main;
    private JPanel chart1;
    private JComboBox cboOption;
    private JTextField txtNam;

    DefaultCategoryDataset newDataset = null;

    public SellChart() {
        int nam = Year.now().getValue();
        this.newDataset = bus.thongKeTheoNam(nam);

        cboOption.addItem("THỐNG KÊ SỐ LƯỢNG PHIẾU BÁN THEO NĂM");
        cboOption.addItem("THỐNG KÊ THU NHẬP THEO NĂM");
        // create chart
        JFreeChart chart = createBarChart(String.valueOf(cboOption.getSelectedItem()) );

        // create chart panel and add it to the chart JPanel
        ChartPanel chartPanel = new ChartPanel(chart);
        chart1.setLayout(new java.awt.BorderLayout());
        chart1.add(chartPanel);

        cboOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = cboOption.getSelectedItem().toString();
                 if (selectedOption.equals("THỐNG KÊ SỐ LƯỢNG PHIẾU BÁN THEO NĂM")) {
                    int nam = Year.now().getValue();
                    if(txtNam.getText().isEmpty()){
                        newDataset = bus.thongKeTheoNam(nam);
                    }
                    else{
                        newDataset = bus.thongKeTheoNam(Integer.parseInt(txtNam.getText()));
                    }
                }
                else if (selectedOption.equals("THỐNG KÊ THU NHẬP THEO NĂM")) {
                    int nam = Year.now().getValue();
                    if(!txtNam.getText().isEmpty()){
                        nam = Integer.parseInt(txtNam.getText());
                    }
                    newDataset = bus.thongKeSoTienTheoThang(nam);

                }
                JFreeChart newChart = createBarChart(selectedOption);
                // update the chart panel with the new chart
                chartPanel.setChart(newChart);
            }
        });
        txtNam.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                String selectedOpt = String.valueOf(cboOption.getSelectedItem());
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    int nam = Year.now().getValue();

                    System.out.println(txtNam.getText());
                    if(txtNam.getText().isEmpty()){
                        newDataset = bus.thongKeTheoNam(nam);
                    }
                    else{
                        newDataset = bus.thongKeTheoNam(Integer.parseInt(txtNam.getText()));
                    }
                    JFreeChart newChart = createBarChart(selectedOpt);
                    // update the chart panel with the new chart
                    chartPanel.setChart(newChart);
                }

            }
        });
    }

    public JFreeChart createBarChart(String selectedOption){
        JFreeChart newChart = ChartFactory.createBarChart(
                "Phiếu bán", // SellChart title
                selectedOption, // X-Axis Label
                "Số lượng phiếu bán", // Y-Axis Label
                newDataset, // New dataset
                PlotOrientation.VERTICAL, // Plot orientation
                false, // Show legend
                true, // Use tooltips
                false // Generate URLs
        );
        return newChart;
    }

    public JFreeChart createPieChart(DefaultPieDataset dataset,String title){
        JFreeChart chart = ChartFactory.createPieChart(
                title, // SellChart title
                dataset, // Dataset
                true, // Show legend
                true, // Use tooltips
                false // Generate URLs
        );
        chart.setBackgroundPaint(Color.WHITE);
        chart.getPlot().setBackgroundPaint(Color.WHITE);
        return chart;
    }


    public JPanel getMain() {
        return main;
    }

    public void setMain(JPanel main) {
        this.main = main;
    }
        public static void main(String[] args) {
        JFrame frame = new JFrame("Chart");
        frame.setContentPane(new SellChart().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
