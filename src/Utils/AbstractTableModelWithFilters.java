package Utils;


import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.JTextComponent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public abstract class AbstractTableModelWithFilters<T> extends AbstractTableModel {
    HashMap<Integer, JTextComponent> filterFields = new HashMap<>();
    protected ArrayList<T> rows = new ArrayList<>();
    private boolean isEditable = true;

    private String[] cols = {};


    protected AbstractTableModelWithFilters() {
    }




    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public String getColumnName(int column) { return cols[column]; }

    public void addRow(T item) {
        rows.add(item);
        fireTableRowsInserted(rows.size()-2, rows.size());
    }

    public void setRows(ArrayList<T> rows) {
        this.rows = rows;
        fireTableDataChanged();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    public String[] getCols() {
        return cols;
    }

    public void setCols(String[] cols) {
        this.cols = cols;
    }



    public ArrayList<T> getRows() {
        return rows;
    }

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

    public boolean isEditable() {
        return isEditable;
    }
    public void setEditable(boolean editable) {
        isEditable = editable;
    }


    public boolean isCellEditable(int row, int column) { return isEditable;}

    public RowFilter<Object, Object> createTableFilter() {
        ArrayList<RowFilter<Object, Object>> filters = new ArrayList<>();
        var keyList = filterFields.keySet().stream().toList();
        var valueList = filterFields.values().stream().toList();
        for (int i = 0; i < keyList.size(); i++) {
            filters.add(RowFilter.regexFilter(valueList.get(i).getText(), keyList.get(i)));
        }
        return RowFilter.andFilter(filters);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        var item = rows.get(rowIndex);
        return translateValue(item, columnIndex);
    }

    public abstract Object translateValue(T value, int columnIndex);

    public List<?> getColumnValue(int col) {
        return rows.stream().map((book) -> translateValue(book, col)).toList();
    };

    public List<String> getColumnValueToString(int col) {
        return rows.stream().map(book -> Objects.toString(translateValue(book, col))).toList();
    };
}
