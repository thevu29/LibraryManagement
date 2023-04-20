package NhanVien.arraylistNV;


import Utils.AbstractTableModelWithFilters;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NVDataTableModel extends AbstractTableModelWithFilters<nhanVien> {
    private final String[] cols = {"Mã nhân viên", "Tên nhân viên", "SDT", "Mật khẩu", "Chức Vụ", "Lương", "Nơi làm việc", "Ca làm việc"};

    private boolean isEditable = true;


    public NVDataTableModel(boolean isEditable) {
        this();
        this.isEditable = isEditable;

    }

    public NVDataTableModel() {
        super();
    }



    public boolean isEditable() {
        return isEditable;
    }

    public void setEditable(boolean editable) {
        isEditable = editable;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 6) {
            return Integer.class;
        }
        return super.getColumnClass(columnIndex);
    }

    @Override
    public String getColumnName(int column) { return cols[column]; }
    public boolean isCellEditable(int row, int column) { return isEditable;}
    public void setValueAt(Object value, int row, int col) {
//        rows.get(row).set(col, (String) value);
        fireTableCellUpdated(row, col);
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        var NhanVien = rows.get(rowIndex);
        return translateValue(NhanVien, columnIndex);

    }

    public Object translateValue(nhanVien NV,int columnIndex) {
        switch (columnIndex) {
            case 0 -> {
                return NV.getID();
            }
            case 1 -> {
                return NV.getName();
            }
            case 2 -> {
                return NV.getPhone();
            }
            case 3 -> {
                return NV.getPassword();
            }
            case 4 -> {
                return NV.getPosition();
            }
            case 5 -> {
                return NV.getSalary();
            }
            case 6 -> {
                return NV.getWork();
            }
            case 7 -> {
                return NV.getShift();
            }
        }
        return null;
    }

    @Override
    public List<?> getColumnValue(int columnIndex) {
        return rows.stream().map((NV) -> translateValue(NV, columnIndex)).toList();
    }

    @Override
    public List<String> getColumnValueToString(int col) {
        switch (col) {
            case 2, 3, 4 -> {
                var item = new ArrayList<String>();
                rows.stream().map(NV -> Objects.toString(translateValue(NV, col))).forEach((elem) ->
                        item.addAll(List.of(elem.split(","))));
                return item;
            }
        }
        return rows.stream().map(book -> Objects.toString(translateValue(book, col))).toList();
    }

}
