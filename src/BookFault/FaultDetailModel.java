package BookFault;

import Utils.AbstractTableModelWithFilters;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FaultDetailModel extends AbstractTableModelWithFilters<FaultDetail> {
    private final String[] cols = {
            "Mã Lỗi Chi Tiết",
            "Mã Mượn Chi Tiết",
            "Tên Độc Giả",
            "Tên Sách",
            "Tên Lỗi",
            "Số Lượng",
            "Tiền Đền",
    };

    private boolean isEditable = true;

    // Contructor
    public FaultDetailModel(boolean isEditable) {
        this();
        this.isEditable = isEditable;

    }

    public FaultDetailModel() {
        super();
    }

    // add data test
    public void addBlank() {
         rows.add(FaultDetail.createTestBook());
         fireTableRowsInserted(rows.size() - 1, rows.size() - 1);
    }

    public void addTestData() {
         rows.add(FaultDetail.createTestBook());
         fireTableRowsInserted(rows.size() - 1, rows.size() - 1);
    }

    public void deleteTestData(String id){
        int index = 0;
        for(FaultDetail row : rows) {
            if(row.getMaChiTiet().equals(id)){
                rows.remove(index);
                return;
            }
            index++;
        }
    }

    public void renderTable(){
        fireTableRowsInserted(rows.size() - 1, rows.size() - 1);
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
        var borrowBookFault = rows.get(rowIndex);
        return translateValue(borrowBookFault, columnIndex);

    }

    @Override
    public List<?> getColumnValue(int columnIndex) {
        return rows.stream().map((borrowBookFault) -> translateValue(borrowBookFault,
                columnIndex)).toList();
    }

    public Object translateValue(FaultDetail borrowBookFault, int columnIndex) {
         switch (columnIndex) {
         case 0 -> {
         return borrowBookFault.getMaChiTiet();
         }
         case 1 -> {
         return borrowBookFault.getMaLoi();
         }
         case 2 -> {
         return borrowBookFault.getTenDocGia();
         }
             case 3 -> {
                 return borrowBookFault.getTenSach();
             }
             case 4 -> {
                 return borrowBookFault.getTenLoi();
             }
             case 5 -> {
                 return borrowBookFault.getSoLuong();
             }
             case 6 -> {
                 return borrowBookFault.getTienDen();
             }
         }
        return null;
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
