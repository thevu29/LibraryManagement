package Book;

import Book.DTO.Author;
import Utils.AbstractTableModelWithFilters;

public class AuthorDataTableModel extends AbstractTableModelWithFilters<Author> {

    public AuthorDataTableModel() {
        String[] cols = {"Mã tác giả", "Tên", "Giới Tính", "Email", "Mô Tả"};
        setCols(cols);
    }
    @Override
    public Object translateValue(Author value, int columnIndex) {
        switch (columnIndex) {
            case 0 -> {
                return value.getId();
            }
            case 1 -> {
                return value.getName();
            }
            case 2 -> {
                return value.getGender().toString();
            }
            case 3 -> {
                return value.getEmail();
            }
            case 4 -> {
                return value.getDescription();
            }
            case 99 -> {
                return value.getId() + ", "+ value.getName();
            }
        }
        return null;
    }
}
