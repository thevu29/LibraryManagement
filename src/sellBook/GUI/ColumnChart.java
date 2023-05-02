package sellBook.GUI;

import javax.swing.*;
import java.awt.*;

public class ColumnChart extends JFrame {
    private int[] values;

    public ColumnChart() {
        super("Column Chart");

        // Initialize the data for the chart
        values = new int[]{10, 5, 8, 12};

        // Set the size of the frame
        setSize(800, 400);

        // Set the frame to exit when it is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void paint(Graphics g) {
        super.paint(g);

        // Set the font for the chart labels
        g.setFont(new Font("SansSerif", Font.BOLD, 20));

        // Set the color for the chart bars
        g.setColor(Color.BLUE);

        // Set the width and height of the chart bars
        int barWidth = getWidth() / (2 * values.length);
        int maxValue = getMaxValue(values);
        int heightScale = getHeight() / maxValue;

        // Draw the chart bars
        for (int i = 0; i < values.length; i++) {
            int x1 = i * 2 * barWidth + 50;
            int y1 = getHeight() - values[i] * heightScale + 50;
            int x2 = (i + 1) * 2 * barWidth + 50 - 1;
            int y2 = getHeight() - 1 + 50;
            g.fillRect(x1, y1, x2 - x1, y2 - y1);

            // Draw the chart labels
            g.setColor(Color.BLACK);
            g.drawString("Product " + (i + 1), x1 + barWidth / 2, getHeight() + 40);
            g.drawString("" + values[i], x1 + barWidth / 2, y1 - 5);
        }

        // Draw the chart axes
        g.setColor(Color.BLACK);
        g.drawLine(50, getHeight() + 50, getWidth() - 50, getHeight() + 50);
        g.drawLine(50, 50, 50, getHeight() + 50);
    }

    private int getMaxValue(int[] values) {
        int maxValue = Integer.MIN_VALUE;
        for (int value : values) {
            if (value > maxValue) {
                maxValue = value;
            }
        }
        return maxValue;
    }

    public static void main(String[] args) {
        new ColumnChart().setVisible(true);
    }
}
