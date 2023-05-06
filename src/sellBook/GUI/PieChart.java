package sellBook.GUI;

import java.awt.Color;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class PieChart extends JFrame {

    public PieChart() {
        setTitle("Pie SellChart Example");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create dataset
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Apple", 20);
        dataset.setValue("Banana", 30);
        dataset.setValue("Orange", 50);

        // Create chart
        JFreeChart chart = ChartFactory.createPieChart(
                "Fruit Distribution", // SellChart title
                dataset, // Dataset
                true, // Show legend
                true, // Use tooltips
                false // Generate URLs
        );

        // Set chart colors
        chart.setBackgroundPaint(Color.WHITE);
        chart.getPlot().setBackgroundPaint(Color.WHITE);

        // Create chart panel
        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel);
    }

    public static void main(String[] args) {
        PieChart example = new PieChart();
        example.setVisible(true);
    }
}
