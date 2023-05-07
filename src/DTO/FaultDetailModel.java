package DTO;

import Utils.AbstractTableModelWithFilters;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FaultDetailModel extends AbstractTableModelWithFilters<FaultDetail> {
    private final String[] cols = {
            "Mã Phiếu Mượn",
            "Mã Sách",
            "Tên Sách",
            "Mã Lỗi",
            "Tên Lỗi",
            "Số Lượng",
            "Tiền Phạt",
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

    public void initModelTable(ArrayList<FaultDetail> dsLoiCT){
        rows.clear();
        for (FaultDetail item: dsLoiCT) {
            rows.add(item);
            fireTableRowsInserted(rows.size() - 1, rows.size() - 1);
        }
        fireTableDataChanged();
    }

    public boolean checkTrung(String maSach,String maLoi){
        for (FaultDetail item:
             rows) {
            if(item.getMaSach().equals(maSach)&&item.getMaLoi().equals(maLoi)){
                return true;
            }
        }
        return false;
    }

    public boolean checkTrungCN(String maSachTruoc,String maLoiTruoc,String maSach,String maLoi){
        System.out.println(maSachTruoc+maLoiTruoc+maSach+maLoi);
        for (FaultDetail item:
                rows) {
            boolean check = !item.getMaSach().equals(maSachTruoc)||!item.getMaLoi().equals(maLoiTruoc);
            if(check&&item.getMaSach().equals(maSach)&&item.getMaLoi().equals(maLoi)){
                return true;
            }
        }
        return false;
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

    public Object translateValue(FaultDetail faultDetail, int columnIndex) {
         switch (columnIndex) {
             case 0 -> {
                return faultDetail.getMaPhieuMuon();
             }
             case 1 -> {
                return faultDetail.getMaSach();
             }
             case 2 -> {
                return faultDetail.getTenSach();
             }
             case 3 -> {
                 return faultDetail.getMaLoi();
             }
             case 4 -> {
                 return faultDetail.getTenLoi();
             }
             case 5 -> {
                 return faultDetail.getSoLuong();
             }
             case 6 -> {
                 return faultDetail.getTongTien();
             }
         }
        return null;
    }

    @Override
    public List<String> getColumnValueToString(int col) {
        return rows.stream().map(book -> Objects.toString(translateValue(book, col))).toList();
    }


}
