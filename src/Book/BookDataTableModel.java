package Book;

import Utils.AbstractTableModelWithFilters;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookDataTableModel extends AbstractTableModelWithFilters<Book> {
    public BookDataTableModel(boolean isEditable) {
        this();
        setEditable(isEditable);
    }

    public BookDataTableModel() {
        String[] cols = {"Mã Sách", "Tên Sách", "Tác Giả", "Nhà Phát Hành", "Thể Loại", "Vị Trí", "Giá", "Tình Trạng", "Ngôn ngữ"};
        setCols(cols);
    }

    public void addBlank() {
        rows.add(Book.createTestBook());
        fireTableRowsInserted(rows.size()-1, rows.size()-1);
    }

    public void addTestData() {
        rows.add(Book.createTestBook());
        fireTableRowsInserted(rows.size()-1, rows.size()-1);
    }


    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 6 || columnIndex == 0) {
            return Integer.class;
        }
        return super.getColumnClass(columnIndex);
    }


    public void setValueAt(Object value, int row, int col) {
//        rows.get(row).set(col, (String) value);
        fireTableCellUpdated(row, col);
    }


    public Object translateValue(Book value, int columnIndex) {
        switch (columnIndex) {
            case 0 -> {
                return value.getId();
            }
            case 1 -> {
                return value.getName();
            }
            case 2 -> {
                return String.join(", ", value.getAuthors());
            }
            case 3 -> {
                return String.join(", ", value.getPublisher());
            }
            case 4 -> {
                return String.join(", ", value.getGenre());
            }
            case 5 -> {
                return value.getLocation();
            }
            case 6 -> {
                return value.getPrice();
            }
            case 7 -> {
                return value.getBookStatus().toString();
            }
            case 8 -> {
                return value.getLanguage();
            }
        }
        return null;
    }



    @Override
    public List<String> getColumnValueToString(int col) {
        switch (col) {
            case 2, 3, 4 -> {
                var item = new ArrayList<String>();
                rows.stream().map(book -> Objects.toString(translateValue(book, col))).forEach((elem) ->
                        item.addAll(List.of(elem.split(","))));
                return item;
            }
        }
        return rows.stream().map(book -> Objects.toString(translateValue(book, col))).toList();
    }

}
