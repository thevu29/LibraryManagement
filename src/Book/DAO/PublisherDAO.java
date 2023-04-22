package Book.DAO;

import Book.DTO.Genre;
import Book.DTO.Publisher;
import Core.DefaultConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PublisherDAO extends DefaultConnection {
    public ArrayList<Publisher> getAllFromDatabase() {
        ArrayList<Publisher> publishers = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PUBLISHER WHERE IS_DELETED=0");
            while (rs.next()) {
                var id = rs.getString("MA_NXB");
                var name = rs.getString("TEN_NXB");
                var email = rs.getString("EMAIL");
                var address = rs.getString("DIA_CHI");
                var phone = rs.getString("PHONE");
                var description = rs.getString("DESCRIPTION");

                publishers.add(new Publisher(id, name, address, email, phone, description));
            }
        } catch (SQLException | ClassNotFoundException e) {
            return publishers;
        }
        return publishers;
    }

    public String getLatestID() {
        Statement stmt = null;
        try {
            stmt = getConnection().createStatement();
            var rs = stmt.executeQuery("SELECT MA_NXB FROM PUBLISHER WHERE MA_NXB=(SELECT max(MA_NXB) FROM PUBLISHER)");
            if (!rs.next()) {
                return "NXB0";
            }
            return rs.getString("MA_NXB");
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
    public boolean isIDDuplicate(String id) {
        PreparedStatement stmt = null;
        try {
            stmt = getConnection().prepareStatement("SELECT MA_NXB FROM PUBLISHER WHERE MA_NXB=?");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void update(Publisher publisher) {
        PreparedStatement stmt = null;
        try {
            if (isIDDuplicate(publisher.getId())) {
                stmt = getConnection()
                        .prepareStatement("UPDATE PUBLISHER SET TEN_NXB=?,EMAIL=?,DIA_CHI=?,PHONE=?,DESCRIPTION=? WHERE MA_NXB=?");
                stmt.setString(1, publisher.getName());
                stmt.setString(2, publisher.getEmail());
                stmt.setString(3, publisher.getAddress());
                stmt.setString(4, publisher.getPhone());
                stmt.setString(5, publisher.getDescription());
                stmt.setString(6, publisher.getId());
                stmt.executeUpdate();
            }
            else {
                stmt = getConnection()
                        .prepareStatement("INSERT INTO PUBLISHER(MA_NXB, TEN_NXB, EMAIL, DIA_CHI, PHONE, DESCRIPTION, IS_DELETED) " +
                                "VALUES (?,?,?,?,?,?,0)");
                stmt.setString(1, publisher.getId());
                stmt.setString(2, publisher.getName());
                stmt.setString(3, publisher.getEmail());
                stmt.setString(4, publisher.getAddress());
                stmt.setString(5, publisher.getPhone());
                stmt.setString(6, publisher.getDescription());
                stmt.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Publisher publisher) {
        PreparedStatement stmt = null;
        try {
            stmt = getConnection().prepareStatement("UPDATE PUBLISHER SET IS_DELETED=1 WHERE MA_NXB=?");
            stmt.setString(1, publisher.getId());
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

//    public String getLatestID() {
//    }
}
