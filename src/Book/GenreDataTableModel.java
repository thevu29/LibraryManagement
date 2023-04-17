package Book;

import Book.DTO.Genre;
import Utils.AbstractTableModelWithFilters;

public class GenreDataTableModel extends AbstractTableModelWithFilters<Genre> {
    public GenreDataTableModel() {
        String[] cols = {"ID", "Tên", "Mô tả"};
        setCols(cols);
    }

    @Override
    public Object translateValue(Genre value, int columnIndex) {
        switch (columnIndex) {
            case 0 -> {
                return value.getId();
            }
            case 1 -> {
                return value.getName();
            }
            case 2 -> {
                return value.getDescription();
            }
        }
        return null;
    }
}
