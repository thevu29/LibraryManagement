package Book.DAO;

import Book.DTO.Genre;
import Core.DefaultConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GenreDAO extends DefaultConnection {
    public ArrayList<Genre> getAllFromDatabase() {
        ArrayList<Genre> genres = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM GENRE");
            while (rs.next()) {

                var id = rs.getString("MA_TL");
                var name = rs.getString("TEN_TL");
                var description = rs.getString("MO_TA");

                genres.add(new Genre(id, name, description));
            }
        } catch (SQLException | ClassNotFoundException e) {
            return genres;
        }
        return genres;
    }
}
