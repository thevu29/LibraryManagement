package sellBook.GUI;

import Utils.AbstractTableModelWithFilters;
import sellBook.HDModel;

public class HDTableModel extends AbstractTableModelWithFilters<HDModel> {


    public HDTableModel() {
        String[] cols = {"Mã HD","Tên KH","Sách", "Giá", "Tình trạng HD","Thời gian"};
        setCols(cols);
    }

    public void add(){
        super.addRow(HDModel.createTest());
    }

    @Override
    public Object translateValue(HDModel model, int column) {
        switch (column) {
            case 0 -> {
                return model.getId();
            }
            case 1 -> {
                return model.getTenKH();
            }
            case 2 -> {
                return String.join(",", model.getDsSach());
            }
            case 3 -> {
                return model.getGia();
            }
            case 4 ->{
                return String.join(",", model.getTinhTrangHD());
            }
            case 5 ->{
                return String.join(",", model.getNgayMua());
            }

        }
        return null;
    }
}
