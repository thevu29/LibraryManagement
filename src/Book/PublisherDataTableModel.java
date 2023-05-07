package Book;

import Book.DTO.Publisher;
import Utils.AbstractTableModelWithFilters;

public class PublisherDataTableModel extends AbstractTableModelWithFilters<Publisher> {

    public PublisherDataTableModel() {
        String[] cols = {"Mã NXB", "Tên", "Email", "SĐT", "Địa chỉ", "Mô tả" };
        setCols(cols);
    }

    @Override
    public Object translateValue(Publisher value, int columnIndex) {
        switch (columnIndex) {
            case 0 -> {
                return value.getId();
            }
            case 1 -> {
                return value.getName();
            }
            case 2 -> {
                return value.getEmail();
            }
            case 3 -> {
                return value.getPhone();
            }
            case 4 -> {
                return value.getAddress();
            }
            case 5 -> {
                return value.getDescription();
            }
            case 110 -> {
                return value.getId() +", " + value.getName();
            }
        }
        return null;
    }
}
