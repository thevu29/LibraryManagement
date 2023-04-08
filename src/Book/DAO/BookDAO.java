package Book.DAO;

import Book.DTO.Book;
import Core.DefaultConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BookDAO extends DefaultConnection {
    public ArrayList<Book> getAllFromDatabase() {
        ArrayList<Book> books = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK WHERE TRANG_THAI='AVAILABLE'");
            while (rs.next()) {
                var id = rs.getString("MA_SACH");
                ResultSet authorRS;
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return books;
    }
}
