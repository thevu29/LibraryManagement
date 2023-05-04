package Borrow;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import Borrow.DAO.FaultDAO;
import Borrow.DTO.Fault;
import Utils.AbstractTableModelWithFilters;

public class FaultModel extends AbstractTableModelWithFilters<Fault> {
    private final String[] cols = {
            "Mã Lỗi",
            "Tên Lỗi",
            "Hệ Số",
    };

    private FaultDAO faultDAO = new FaultDAO();
    private boolean isEditable = true;

    // Contructor
    public FaultModel(boolean isEditable) {
        this();
        this.isEditable = isEditable;

    }

    public FaultModel() {
        super();
    }

    public void initModelTable(ArrayList<Fault> dsLoi){
        rows.clear();
        for (Fault fault: dsLoi) {
            rows.add(fault);
            fireTableRowsInserted(rows.size() - 1, rows.size() - 1);
        }
        fireTableDataChanged();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return super.getColumnClass(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return cols[column];
    }

    public boolean isCellEditable(int row, int column) {
        return isEditable;
    }

    public void setValueAt(Object value, int row, int col) {
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
        var borrow = rows.get(rowIndex);
        return translateValue(borrow, columnIndex);

    }

    public Object translateValue(Fault fault, int columnIndex) {
        switch (columnIndex) {
            case 0 -> {
                return fault.getId();
            }
            case 1 -> {
                return fault.getTenLoi();
            }
            case 2 -> {
                return fault.getHeSo();
            }
        }
        return null;
    }

    @Override
    public List<?> getColumnValue(int columnIndex) {
        return rows.stream().map((fault) -> translateValue(fault,
                columnIndex)).toList();
    }

    @Override
    public List<String> getColumnValueToString(int col) {
        return rows.stream().map(book -> Objects.toString(translateValue(book, col))).toList();
    }
}
