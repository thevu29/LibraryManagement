package sellBook;

import Utils.AbstractTableModelWithFilters;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GioHangTableModel extends AbstractTableModelWithFilters<GioHangModel> {

    public GioHangTableModel() {
        String[] cols = {"Id Sách","Tên sách", "Số lượng mua", "Giá","Tác giả","Thể loại","Nhà xuất bản","Tình Trạng","Ngôn Ngữ"};
        setCols(cols);
    }

    public void add() {
        super.addRow(GioHangModel.createTest());
    }



    public Object translateValue(GioHangModel model, int column) {
        // Cot nao hien thi ntn
        switch (column) {
            case 0 -> {
                return model.getId();
            }
            case 1 -> {
                return model.getTenSach();
            }
            case 2 -> {
                return model.getGia();
            }
            case 3 -> {
                return model.getSolm();
            }
            case 4 ->{
                return String.join(",", model.getTacGia());
            }
            case 5 ->{
                return String.join(",", model.getTheLoai());
            }
            case 6 ->{
                return String.join(",", model.getNxb());
            }
        }
        return null;
    }

}
