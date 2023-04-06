package Book;

import Utils.AbstractTableModelWithFilters;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookDataTableModel extends AbstractTableModelWithFilters<Book> {


    private final String[] cols = {"Mã Sách", "Tên Sách", "Tác Giả", "Nhà Phát Hành", "Thể Loại", "Vị Trí", "Giá", "Tình Trạng", "Ngôn ngữ"};

    private boolean isEditable = true;


    public BookDataTableModel(boolean isEditable) {
        this();
        this.isEditable = isEditable;

    }

    public BookDataTableModel() {
        super();
    }

    public void addBlank() {
        rows.add(Book.createTestBook());
        fireTableRowsInserted(rows.size()-1, rows.size()-1);
    }

    public void addTestData() {
        rows.add(Book.createTestBook());
        fireTableRowsInserted(rows.size()-1, rows.size()-1);
    }


    public boolean isEditable() {
        return isEditable;
    }

    public void setEditable(boolean editable) {
        isEditable = editable;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 6) {
            return Integer.class;
        }
        return super.getColumnClass(columnIndex);
    }

    @Override
    public String getColumnName(int column) { return cols[column]; }
    public boolean isCellEditable(int row, int column) { return false;}
    public void setValueAt(Object value, int row, int col) {
//        rows.get(row).set(col, (String) value);
        fireTableCellUpdated(row, col);
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
        var book = rows.get(rowIndex);
        return translateValue(book, columnIndex);

    }

    public Object translateValue(Book book,int columnIndex) {
        switch (columnIndex) {
            case 0 -> {
                return book.getId();
            }
            case 1 -> {
                return book.getName();
            }
            case 2 -> {
                return String.join(", ", book.getAuthors());
            }
            case 3 -> {
                return String.join(", ", book.getPublisher());
            }
            case 4 -> {
                return String.join(", ", book.getGenre());
            }
            case 5 -> {
                return book.getLocation();
            }
            case 6 -> {
                return book.getPrice();
            }
            case 7 -> {
                return book.getBookStatus().toString();
            }
            case 8 -> {
                return book.getLanguage();
            }
        }
        return null;
    }

    @Override
    public List<?> getColumnValue(int columnIndex) {
        return rows.stream().map((book) -> translateValue(book, columnIndex)).toList();
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
