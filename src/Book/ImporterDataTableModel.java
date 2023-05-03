package Book;

import Book.DTO.Importer;
import Utils.AbstractTableModelWithFilters;

public class ImporterDataTableModel extends AbstractTableModelWithFilters<Importer> {

    public ImporterDataTableModel() {
        String[] cols = {"ID", "Tên", "SĐT", "Email","Địa chỉ", "Mô tả"};
        setCols(cols);

    }

    @Override
    public Object translateValue(Importer value, int columnIndex) {
        switch (columnIndex) {
            case 0 -> {
                return value.getId();
            }
            case 1 -> {
                return value.getName();
            }
            case 2 -> {
                return value.getPhone();
            }
            case 3 -> {
                return value.getEmail();
            }
            case 4 -> {
                return value.getAddress();
            }
            case 5 -> {
                return value.getDescription();
            }
            case 10 -> {
                return value.getId() + ", " + value.getName();
            }
        }
        return null;
    }
}
