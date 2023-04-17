package Book.DAO;

import Book.DTO.Author;
import Book.DTO.Book;
import Core.DefaultConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AuthorDAO extends DefaultConnection {
    public ArrayList<Author> getAllFromDatabase() {
        ArrayList<Author> authors = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM AUTHOR");
            while (rs.next()) {

                var id = rs.getString("MA_TG");
                var name = rs.getString("TEN_TG");
                var email = rs.getString("EMAIL");
                var description = rs.getString("GIOI_THIEU");
                var sex = rs.getString("GIOI_TINH");

                authors.add(new Author(id, name, email, sex, description));
            }
        } catch (SQLException | ClassNotFoundException e) {
            return authors;
        }
        return authors;
    }

}
