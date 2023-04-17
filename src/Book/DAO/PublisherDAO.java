package Book.DAO;

import Book.DTO.Genre;
import Book.DTO.Publisher;
import Core.DefaultConnection;

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
            ResultSet rs = stmt.executeQuery("SELECT * FROM PUBLISHER");
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

}
