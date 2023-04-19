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
    }

    // add data test
    public void addBlank() {
         rows.add(Fault.createTestBook());
         fireTableRowsInserted(rows.size() - 1, rows.size() - 1);
    }

    public void addTestData() {
         rows.add(Fault.createTestBook());
         fireTableRowsInserted(rows.size() - 1, rows.size() - 1);
    }

    public void addData(String id,String tenLoi,double heSo){
        rows.add(new Fault(id,tenLoi,heSo));
        fireTableRowsInserted(rows.size() - 1, rows.size() - 1);
    }

    public void updateData(String id,String tenLoi,double heSo){
        for(Fault row : rows) {
            if(row.getId().equals(id)){
                row.setTenLoi(tenLoi);
                row.setHeSo(heSo);
                fireTableCellUpdated(rows.size() - 1, rows.size() - 1);
                return;
            }
        }
    }

    public void deleteTestData(String id){
        int index = 0;
        for(Fault row : rows) {
            if(row.getId().equals(id)){
                rows.remove(index);
                return;
            }
            index++;
        }
    }

    public void renderTable(){
        fireTableRowsInserted(rows.size() - 1, rows.size() - 1);
    }

    public boolean checkID(String id){
        for(Fault row : rows) {
            if(row.getId().equals(id)){
                return true;
            }
        }
        return false;
    }



    public boolean isEditable() {
        return isEditable;
    }

    public void setEditable(boolean editable) {
        isEditable = editable;
    }

    // chưa hiểu
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 6) {
            return Integer.class;
        }
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
        // rows.get(row).set(col, (String) value);
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
        switch (col) {
            case 2, 3, 4 -> {
                var item = new ArrayList<String>();
                rows.stream().map(book -> Objects.toString(translateValue(book, col))).forEach((elem) ->
                        item.addAll(List.of(elem.split(","))));
                return item;
            }
        }
        return rows.stream().map(book -> Objects.toString(translateValue(book, col))).toList();
    }
}
