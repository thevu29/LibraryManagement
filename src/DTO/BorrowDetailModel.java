package DTO;

import Utils.AbstractTableModelWithFilters;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BorrowDetailModel extends AbstractTableModelWithFilters<BorrowDetail> {
    private final String[] cols = {
            "Mã Phiếu Mượn",
            "Mã Sách",
            "Tên Sách",
            "Tiền Tạm Tính",
            "Tiền Tổng",
    };

    private boolean isEditable = true;

    public  boolean checkIdAdd(String maPhieu,String maSach){
        for (BorrowDetail item:
             rows) {
            if(item.getMaPhieu().equals(maPhieu)&&item.getMaSach().equals(maSach)){
                return true;
            }
        }
        return false;
    }

    public  boolean checkIdUpdate(String maSachTruoc,String maPhieu,String maSach){
        for (BorrowDetail item:
                rows) {
            if(!item.getMaSach().equals(maSachTruoc) && item.getMaPhieu().equals(maPhieu)&&item.getMaSach().equals(maSach)){
                return true;
            }
        }
        return false;
    }

    // Contructor
    public BorrowDetailModel(boolean isEditable) {
        this();
        this.isEditable = isEditable;

    }

    public BorrowDetailModel() {
        super();
    }

    public void initModelTable(ArrayList<BorrowDetail> dsMuonCT){
        rows.clear();
        for (BorrowDetail borrowDetail: dsMuonCT) {
            rows.add(borrowDetail);
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

    public Object translateValue(BorrowDetail borrowDetail, int columnIndex) {
        switch (columnIndex) {
            case 0 -> {
                return borrowDetail.getMaPhieu();
            }
            case 1 -> {
                return borrowDetail.getMaSach();
            }
            case 2 -> {
                return borrowDetail.getTenSach();
            }
            case 3 -> {
                return borrowDetail.getTienTamTinh();
            }
            case 4 -> {
                return borrowDetail.getTienTong();
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
        return rows.stream().map(book -> Objects.toString(translateValue(book,
                col))).toList();
    }
}
