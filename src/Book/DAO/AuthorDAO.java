package Book.DAO;

import Book.DTO.Author;
import Book.DTO.Book;
import Core.DefaultConnection;

import java.sql.PreparedStatement;
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM AUTHOR WHERE IS_DELETED=0");
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

    public boolean isIDDuplicate(String id) {
        PreparedStatement stmt = null;
        try {
            stmt = getConnection().prepareStatement("SELECT MA_TG FROM AUTHOR WHERE MA_TG=?");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void update(Author author) {
        PreparedStatement stmt = null;
        try {
            if (isIDDuplicate(author.getId())) {
                stmt = getConnection()
                        .prepareStatement("UPDATE AUTHOR SET TEN_TG=?, EMAIL=?,GIOI_THIEU=?,GIOI_TINH=? WHERE MA_TG=?");
                stmt.setString(1, author.getName());
                stmt.setString(2, author.getEmail());
                stmt.setString(3, author.getDescription());
                stmt.setString(4, String.valueOf(author.getGender()));
                stmt.setString(5, author.getId());
                stmt.executeUpdate();
            }
            else {
                stmt = getConnection()
                        .prepareStatement("INSERT INTO AUTHOR(MA_TG, TEN_TG, EMAIL, GIOI_THIEU, GIOI_TINH, IS_DELETED) VALUE " +
                                "(?,?,?,?,?,0)");
                stmt.setString(1, author.getId());
                stmt.setString(2, author.getName());
                stmt.setString(3, author.getEmail());
                stmt.setString(4, author.getDescription());
                stmt.setString(5, String.valueOf(author.getGender()));
                stmt.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public String getLatestId() {
        Statement stmt = null;
        try {
            stmt = getConnection().createStatement();
            var rs = stmt.executeQuery("SELECT MA_TG FROM AUTHOR WHERE MA_TG=(SELECT max(MA_TG) FROM AUTHOR)");
            if (!rs.next()) {
                return "TG0";
            }
            return rs.getString("MA_TG");
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void delete(Author author) {
        PreparedStatement stmt = null;
        try {
            stmt = getConnection().prepareStatement("UPDATE AUTHOR SET IS_DELETED=1 WHERE MA_TG=?");
            stmt.setString(1, author.getId());
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
