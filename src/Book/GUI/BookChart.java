package Book.GUI;

import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
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

    }

    public JPanel getMain() {
        return main;
    }

    public void setMain(JPanel main) {
        this.main = main;
    }
}
