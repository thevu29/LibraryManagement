package DAO;

import DTO.Genre;
import Core.DefaultConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

public class GenreDAO extends DefaultConnection {
    public ArrayList<Genre> getAllFromDatabase() {
        ArrayList<Genre> genres = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM GENRE WHERE IS_DELETED=0");
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



    public boolean isIDExist(String id) {
        PreparedStatement stmt = null;
        try {
            stmt = getConnection().prepareStatement("SELECT MA_TL FROM GENRE WHERE MA_TL=?");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean isNameDuplicate(String name) {
        PreparedStatement stmt = null;
        try {
            stmt = getConnection().prepareStatement("SELECT TEN_TL FROM GENRE WHERE TEN_TL=? AND IS_DELETED=0");
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void update(Genre genre) {
        PreparedStatement stmt = null;
        try {
            if (isIDExist(genre.getId())) {
                stmt = getConnection()
                        .prepareStatement("UPDATE GENRE SET TEN_TL=?,MO_TA=? WHERE MA_TL=?");
                stmt.setString(1, genre.getName());
                stmt.setString(2, genre.getDescription());
                stmt.setString(3, genre.getId());
                stmt.executeUpdate();
            }
            else {
                stmt = getConnection()
                        .prepareStatement("INSERT INTO GENRE VALUE " +
                                "(?,?,?,0)");
                stmt.setString(1, genre.getId());
                stmt.setString(2, genre.getName());
                stmt.setString(3, genre.getDescription());
                stmt.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String getLatestID() {
        Statement stmt = null;
        try {
            stmt = getConnection().createStatement();
            var rs = stmt.executeQuery("SELECT MAX(CAST(SUBSTR(MA_TL, 3) AS UNSIGNED)) AS MA_TL FROM GENRE");
            if (!rs.next()) {
                return "0";
            }

            var maTL = rs.getString("MA_TL");
            if (Objects.isNull(maTL)) {
                return "0";
            }
            return maTL;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void delete(Genre genre) {
        PreparedStatement stmt = null;
        try {
            stmt = getConnection().prepareStatement("UPDATE GENRE SET IS_DELETED=1 WHERE MA_TL=?");
            stmt.setString(1, genre.getId());
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
