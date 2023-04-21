package sellBook.GUI;

import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Chart {
    private JPanel main;
    private JPanel chart1;
    private JButton button1;

    public Chart() {
        // create dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(120, "Books", "2019");
        dataset.setValue(160, "Books", "2020");
        dataset.setValue(200, "Books", "2021");

        // create chart
        JFreeChart chart = ChartFactory.createBarChart(
                "Books Sold", // Chart title
                "Year", // X-Axis Label
                "Number of Books", // Y-Axis Label
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
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Chart");
        frame.setContentPane(new Chart().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
