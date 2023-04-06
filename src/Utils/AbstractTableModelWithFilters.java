package Utils;


import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.JTextComponent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractTableModelWithFilters<T> extends AbstractTableModel {
    HashMap<Integer, JTextComponent> filterFields = new HashMap<>();
    protected final ArrayList<T> rows = new ArrayList<>();

    public void setFilterField(int column, JTextComponent filterField) {
        filterFields.put(column, filterField);
    }

    public void removeFilterField(int column) {
        filterFields.remove(column);
    }

    public T get(int row) {
        return rows.get(row);
    }

    public void resetFilter() {
        filterFields.clear();
    }

    public RowFilter<Object, Object> createTableFilter() {
        ArrayList<RowFilter<Object, Object>> filters = new ArrayList<>();
        var keyList = filterFields.keySet().stream().toList();
        var valueList = filterFields.values().stream().toList();
        for (int i = 0; i < keyList.size(); i++) {
            filters.add(RowFilter.regexFilter(valueList.get(i).getText(), keyList.get(i)));
        }
        return RowFilter.andFilter(filters);
    }

    public abstract List<?> getColumnValue(int col);

    public abstract List<String> getColumnValueToString(int col);
}
