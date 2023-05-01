package NhanVien.arraylistNV;


import NhanVien.DTO.nhanVien;
import Utils.AbstractTableModelWithFilters;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NVDataTableModel extends AbstractTableModelWithFilters<nhanVien> {
    private final String[] cols = {"ID", "Tên Nhân viên","Giới tính", "SDT", "Mật khẩu", "Chức Vụ", "Lương", "Nơi làm việc", "Ca làm việc","Địa chỉ","email","Ngày sinh","Ngày làm việc"};

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
                return NV.getGender()== 0 ? "Nam" : "Nữ" ;
            }
            case 3 -> {
                return NV.getPhone();
            }
            case 4 -> {
                return NV.getPassword();
            }
            case 5 -> {
                return NV.getPosition() == 0 ? "Librarian"  : "Manager";
            }
            case 6 -> {
                return NV.getSalary()*NV.getDaywork()*1000;
            }
            case 7 -> {
                return NV.getWork() == 0 ? "CS1"  : NV.getWork() == 1 ? "CS2" : "CS3"  ;
            }
            case 8 -> {
                return NV.getShift() == 0 ? "C1"  : NV.getShift() == 1 ? "C2" : "C3"    ;
            }
            case 9 -> {
                return NV.getAddress();
            }
            case 10 -> {
                return NV.getEmail();
            }
            case 11 -> {
                return NV.getBirth();
            }
            case 12 -> {
                return NV.getDaywork();
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
