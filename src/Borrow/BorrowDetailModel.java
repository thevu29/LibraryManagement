package Borrow;

import Borrow.DTO.BorrowDetail;
import Utils.AbstractTableModelWithFilters;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BorrowDetailModel extends AbstractTableModelWithFilters<BorrowDetail> {
    private final String[] cols = {
            "Mã Chi Tiết",
            "Mã Phiếu Mượn",
            "Tên Sách",
            "Tên Lỗi",
            "Số Lượng",
            "Giá Tiền",
    };

    private boolean isEditable = true;

    // Contructor
    public BorrowDetailModel(boolean isEditable) {
        this();
        this.isEditable = isEditable;

    }

    public BorrowDetailModel() {
        super();
    }

    // add data testExcel
    public void addBlank() {
//         rows.add(BorrowDetail.createTestBook());
        rows.add(BorrowDetail.createTestBook());
         fireTableRowsInserted(rows.size() - 1, rows.size() - 1);
    }

    public void addTestData() {
         rows.add(BorrowDetail.createTestBook());
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

    public Object translateValue(BorrowDetail borrowDetail, int columnIndex) {
        switch (columnIndex) {
            case 0 -> {
                return borrowDetail.getId();
            }
            case 1 -> {
                return borrowDetail.getMaPhieuMuon();
            }
            case 2 -> {
                return borrowDetail.getTenSach();
            }
            case 3 -> {
                return borrowDetail.getTenLoi();
            }
            case 4 -> {
                return borrowDetail.getSoLuong();
            }
            case 5 -> {
                return borrowDetail.getGiaTien();
            }
        }
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        var borrowDetail = rows.get(rowIndex);
        return translateValue(borrowDetail, columnIndex);

    }

    @Override
    public List<?> getColumnValue(int columnIndex) {
        return rows.stream().map((borrowDetail) -> translateValue(borrowDetail,
                columnIndex)).toList();
    }

    private Object translateValue(BorrowDetailModel borrowDetail, int columnIndex) {
        return null;
    }

    @Override
    public List<String> getColumnValueToString(int col) {
        switch (col) {
            case 2, 3, 4 -> {
                var item = new ArrayList<String>();
                rows.stream().map(book -> Objects.toString(translateValue(book, col)))
                        .forEach((elem) -> item.addAll(List.of(elem.split(","))));
                return item;
            }
        }
        return rows.stream().map(book -> Objects.toString(translateValue(book,
                col))).toList();
    }
}
