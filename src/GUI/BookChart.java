package GUI;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BookChart {
    private JPanel main;
    private JComboBox cboOption;
    private JPanel chart;
    private DefaultPieDataset dataset = new DefaultPieDataset();

    public BookChart(DefaultPieDataset dataset, ArrayList<String> dsOpt) {
        this.dataset = dataset;
        for(String t:dsOpt){
            cboOption.addItem(t);
        }
        JFreeChart newChart = createPieChart(dsOpt.get(0));
        ChartPanel chartPanel = new ChartPanel(newChart);
        chart.setLayout(new java.awt.BorderLayout());
        chart.add(chartPanel);

    }

    public JFreeChart createPieChart(String title) {
        JFreeChart chart = ChartFactory.createPieChart3D(
                title, // SellChart title
                dataset, // Dataset
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

    public JPanel getMain() {
        return main;
    }

    public void setMain(JPanel main) {
        this.main = main;
    }
}
