package DTO;

import Utils.AbstractTableModelWithFilters;

public class GenreDataTableModel extends AbstractTableModelWithFilters<Genre> {
    public GenreDataTableModel() {
        String[] cols = {"Mã loại", "Tên", "Mô tả"};
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
            case 10 -> {
                return value.getId() +", " + value.getName();
            }
        }
        return null;
    }
}
