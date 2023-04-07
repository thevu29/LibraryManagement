package BookFault;

import Utils.AbstractTableModelWithFilters;

import java.util.List;

public class BorrowBookFaultModel extends AbstractTableModelWithFilters<BorrowBookFault> {
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
    public BorrowBookFaultModel(boolean isEditable) {
        this();
        this.isEditable = isEditable;

    }

    public BorrowBookFaultModel() {
        super();
    }

    // add data test
    public void addBlank() {
        // rows.add(Book.createTestBook());
        // fireTableRowsInserted(rows.size() - 1, rows.size() - 1);
    }

    public void addTestData() {
        // rows.add(Book.createTestBook());
        // fireTableRowsInserted(rows.size() - 1, rows.size() - 1);
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

    public Object translateValue(BorrowBookFault borrowBookFault, int columnIndex) {
        // switch (columnIndex) {
        // case 0 -> {
        // return bookFault.getId();
        // }
        // case 1 -> {
        // return bookFault.getTenLoi();
        // }
        // case 2 -> {
        // return bookFault.getHeSo();
        // }
        // }
        return null;
    }

    @Override
    public List<String> getColumnValueToString(int col) {
        // switch (col) {
        // case 2, 3, 4 -> {
        // var item = new ArrayList<String>();
        // rows.stream().map(book -> Objects.toString(translateValue(book, col)))
        // .forEach((elem) -> item.addAll(List.of(elem.split(","))));
        // return item;
        // }
        // }
        // return rows.stream().map(book -> Objects.toString(translateValue(book,
        // col))).toList();
        return null;
    }
}
