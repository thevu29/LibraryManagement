package sellBook;

import Utils.AbstractTableModelWithFilters;

public class CTHDTableModel extends AbstractTableModelWithFilters<CTHDModel> {

    public CTHDTableModel() {
        String[] cols = {"Mã HD","Sách","So Luong", "Giá", "Trang Thai"};
        setCols(cols);
    }

    public void add(){
        super.addRow(CTHDModel.createTest());
    }

    @Override
    public Object translateValue(CTHDModel model, int column) {
        switch (column){
            case 0->{
                return model.getIdHD();
            }
            case 1->{
                return  model.getTenSach();
            }
            case 2->{
                return model.getSoLuong();
            }
            case 3->{
                return  model.getGia();
            }
            case 4->{
                return model.getTrangThai();
            }
        }
        return null;
    }
}
