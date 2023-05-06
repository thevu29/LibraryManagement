package sellBook.GUI;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import sellBook.BUS.CTHDBus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.Year;
import java.util.ArrayList;

public class ChartCTHD {
    private JPanel chart1;
    private JComboBox cboOption;
    private JPanel main;
    private JTextField txtYear;

    CTHDBus bus = new CTHDBus();
    DefaultPieDataset pieDataset = new DefaultPieDataset<>();
    DefaultCategoryDataset categoryDataset  = new DefaultCategoryDataset();

    public ChartCTHD(){
        int nam = Year.now().getValue();
        this.categoryDataset = bus.thongKeSachBanTheoNam(nam);
        createBarChart("THỐNG KÊ SỐ SÁCH ĐƯỢC BÁN THEO NĂM");
        JFreeChart newChart = createBarChart("THỐNG KÊ SỐ SÁCH ĐƯỢC BÁN THEO NĂM");
        // update the chart panel with the new chart
        ChartPanel chartPanel = new ChartPanel(newChart);
        chart1.setLayout(new java.awt.BorderLayout());
        chart1.add(chartPanel);
        cboOption.addItem("THỐNG KÊ SỐ SÁCH BÁN THEO NĂM");
        cboOption.addItem("THỐNG KÊ SỐ LOẠI ĐƯỢC BÁN");

        cboOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = cboOption.getSelectedItem().toString();

                if (selectedOption.equals("THỐNG KÊ SỐ SÁCH BÁN THEO NĂM")) {
                    int nam = Year.now().getValue();
                    if(!txtYear.getText().isEmpty()){
                        nam = Integer.parseInt(txtYear.getText());
                    }
                    categoryDataset = bus.thongKeSachBanTheoNam(nam);
                    JFreeChart newChart = createBarChart("THỐNG KÊ SỐ SÁCH ĐƯỢC BÁN THEO NĂM");
                    chartPanel.setChart(newChart);
                }
                else if (selectedOption.equals("THỐNG KÊ SỐ LOẠI ĐƯỢC BÁN")) {
                    pieDataset = bus.thongKeSoLoaiDcBan();
                    txtYear.setEditable(false);
                    JFreeChart newChart = createPieChart("THỐNG KÊ SỐ LOẠI ĐƯỢC BÁN");
                    chartPanel.setChart(newChart);
                }

            }
        });


        txtYear.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    int nam = Year.now().getValue();
                    if(!txtYear.getText().isEmpty()){
                        nam = Integer.parseInt(txtYear.getText());
                    }
                    categoryDataset = bus.thongKeSachBanTheoNam(nam);
                    JFreeChart newChart = createBarChart("THỐNG KÊ SỐ SÁCH ĐƯỢC BÁN THEO NĂM");
                    chartPanel.setChart(newChart);
                }
            }
        });
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

    public JFreeChart createBarChart(String selectedOption) {
        JFreeChart newChart = ChartFactory.createBarChart(
                "Hoa Don", // SellChart title
                selectedOption, // X-Axis Label
                "Number of Hoa Don", // Y-Axis Label
                categoryDataset, // New dataset
                PlotOrientation.VERTICAL, // Plot orientation
                false, // Show legend
                true, // Use tooltips
                false // Generate URLs
        );

        // Set a custom color palette
        BarRenderer renderer = new BarRenderer();
        renderer.setBarPainter(new StandardBarPainter());
        renderer.setSeriesPaint(0, new Color(79, 129, 189));
        renderer.setSeriesPaint(1, new Color(155, 187, 89));
        renderer.setSeriesPaint(2, new Color(192, 80, 77));
        CategoryPlot plot = newChart.getCategoryPlot();
        plot.setRenderer(renderer);

        // Add grid lines
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.GRAY);

        // Set custom font for title and axis labels
        Font titleFont = new Font("Arial", Font.BOLD, 18);
        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        newChart.getTitle().setFont(titleFont);
        plot.getDomainAxis().setLabelFont(labelFont);
        plot.getRangeAxis().setLabelFont(labelFont);

        return newChart;
    }




    public JPanel getMain() {
        return main;
    }

    public void setMain(JPanel main) {
        this.main = main;
    }
}
