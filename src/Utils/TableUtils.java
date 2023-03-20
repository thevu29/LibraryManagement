package Utils;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.ArrayList;
public class TableUtils {

    public static void filter(JTable table) {
        if (!(table.getModel() instanceof AbstractTableModelWithFilters model)) {
            return;
        }
        TableRowSorter<AbstractTableModel> sorter = new TableRowSorter<>((AbstractTableModel) table.getModel());
        sorter.setRowFilter(model.createTableFilter());
        table.setRowSorter(sorter);
    }

//    public static


}
