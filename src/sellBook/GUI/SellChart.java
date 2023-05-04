package sellBook.GUI;

import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import sellBook.BUS.SellTicketBus;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.Year;
import java.util.ArrayList;

public class SellChart {

    SellTicketBus bus = new SellTicketBus();

    private JPanel main;
    private JPanel chart1;
    private JComboBox cboOption;
    private JTextField txtNam;

    DefaultCategoryDataset newDataset = null;

    public SellChart(DefaultCategoryDataset dataset, String tkTheo, ArrayList<String> dsOpt) {
        this.newDataset = dataset;
        for(int i=0;i<dsOpt.size();i++){
            cboOption.addItem(dsOpt.get(i));
        }
        // create chart
        JFreeChart chart = ChartFactory.createBarChart(
                "Phiếu bán", // SellChart title
                tkTheo, // X-Axis Label
                "Số lượng phiếu bán", // Y-Axis Label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Plot orientation
                false, // Show legend
                true, // Use tooltips
                false // Generate URLs
        );

        // create chart panel and add it to the chart JPanel
        ChartPanel chartPanel = new ChartPanel(chart);
        chart1.setLayout(new java.awt.BorderLayout());
        chart1.add(chartPanel);

        cboOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = cboOption.getSelectedItem().toString();
                 if (selectedOption.equals("THỐNG KÊ SỐ LƯỢNG PHIẾU BÁN THEO NĂM")) {

                    txtNam.setEditable(true);
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
//                chart1.add(chartPanel);
            }
        });
        txtNam.addKeyListener(new KeyAdapter() {
            DefaultCategoryDataset newDataset = null;
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    txtNam.setEditable(true);
                    int nam = Year.now().getValue();

                    System.out.println(txtNam.getText());
                    if(txtNam.getText().isEmpty()){
                        newDataset = bus.thongKeTheoNam(nam);
                    }
                    else{
                        newDataset = bus.thongKeTheoNam(Integer.parseInt(txtNam.getText()));
                    }
                    JFreeChart newChart = createBarChart("Thống kê theo năm");
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
    //    public static void main(String[] args) {
//        JFrame frame = new JFrame("SellChart");
//        frame.setContentPane(new SellChart().main);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
//    }
}
