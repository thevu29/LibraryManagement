package DTO;

import Utils.AbstractTableModelWithFilters;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BorrowModel extends AbstractTableModelWithFilters<Borrow> {
    private final String[] cols = {
            "Mã PM",
            "Mã NVXN Mượn",
            "Tên NVXN Mượn",
            "Mã NVXN Trả",
            "Tên NVXN Trả",
            "Thẻ DG",
            "Tên DG",
            "Ngày Mượn",
            "Ngày hẹn trả",
            "Ngày Trả",
            "TT Phạt",
            "TT Mượn",
    };

    private boolean isEditable = true;


    // Contructor
    public BorrowModel(boolean isEditable) {
        this();
        this.isEditable = isEditable;
    }

    public BorrowModel() {
        super();
    }

    public boolean checkBorrow(String id){
        for (Borrow item:
             rows) {
            if(item.getId().equals(id)){
                if(item.getNgayTra()!=null){
                    return true;
                }
            }
        }
        return false;
    }


    public void initModelTable(ArrayList<Borrow> dsMuon){
        rows.clear();
        for (Borrow borrow: dsMuon) {
            rows.add(borrow);
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

    public Object translateValue(Borrow borrow, int columnIndex) {
        switch (columnIndex) {
            case 0 -> {
                return borrow.getId();
            }
            case 1 -> {
                return borrow.getMa_nv_muon();
            }
            case 2 -> {
                return borrow.getTenNhanVienMuon();
            }
            case 3 -> {
                return borrow.getMa_nv_tra();
            }
            case 4 -> {
                return borrow.getTenNhanVienTra();
            }
            case 5 -> {
                return borrow.getMa_the();
            }
            case 6 -> {
                return borrow.getTenDocGia();
            }
            case 7 -> {
                return borrow.getNgayMuon();
            }
            case 8 -> {
                return borrow.getNgayHenTra();
            }
            case 9 -> {
                return borrow.getNgayTra();
            }
            case 10 -> {
                return borrow.getTongTienPhat()+"";
            }
            case 11 -> {
                return borrow.getTongTienMuon()+"";
            }

        }
        return null;
    }

    @Override
    public List<?> getColumnValue(int columnIndex) {
        return rows.stream().map((borrow) -> translateValue(borrow,
                columnIndex)).toList();
    }

    @Override
    public List<String> getColumnValueToString(int col) {
         return rows.stream().map(borrow -> Objects.toString(translateValue(borrow,
         col))).toList();
    }
}
