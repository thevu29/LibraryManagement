package Book.DAO;

import Book.DTO.Importer;
import Core.DefaultConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ImporterDAO extends DefaultConnection {
    public ArrayList<Importer> getAllFromDatabase() {
        ArrayList<Importer> importers = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM IMPORTED_FROM WHERE IS_DELETED=0");
            while (rs.next()) {

                var id = rs.getString("ID");
                var name = rs.getString("NAME");
                var phone = rs.getString("PHONE");
                var address = rs.getString("ADDRESS");
                var email = rs.getString("EMAIL");
                var description = rs.getString("DESCRIPTION");

                importers.add(new Importer(id, name, phone,address, description, email));
            }
        } catch (SQLException | ClassNotFoundException e) {
            return importers;
        }
        return importers;
    }



    public boolean isIDDuplicate(String id) {
        PreparedStatement stmt = null;
        try {
            stmt = getConnection().prepareStatement("SELECT ID FROM IMPORTED_FROM WHERE ID=?");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void update(Importer publisher) {
        PreparedStatement stmt = null;
        try {
            if (isIDDuplicate(publisher.getId())) {
                stmt = getConnection()
                        .prepareStatement("UPDATE IMPORTED_FROM SET NAME=?,PHONE=?,ADDRESS=?,EMAIL=?,DESCRIPTION=? WHERE ID=?");
                stmt.setString(1, publisher.getName());
                stmt.setString(2, publisher.getPhone());
                stmt.setString(3, publisher.getAddress());
                stmt.setString(4, publisher.getEmail());
                stmt.setString(5, publisher.getDescription());
                stmt.setString(6, publisher.getId());
            }
            else {
                stmt = getConnection()
                        .prepareStatement("INSERT INTO IMPORTED_FROM VALUE " +
                                "(?,?,?,?,?,?,0)");
                stmt.setString(1, publisher.getId());
                stmt.setString(2, publisher.getName());
                stmt.setString(3, publisher.getPhone());
                stmt.setString(4, publisher.getAddress());
                stmt.setString(5, publisher.getEmail());
                stmt.setString(6, publisher.getDescription());
            }
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String getLatestID() {
        Statement stmt = null;
        try {
            stmt = getConnection().createStatement();
            var rs = stmt.executeQuery("SELECT ID FROM IMPORTED_FROM WHERE ID=(SELECT max(ID) FROM IMPORTED_FROM)");
            if (!rs.next()) {
                return "IM0";
            }
            return rs.getString("ID");
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void delete(Importer importer) {
        PreparedStatement stmt = null;
        try {
            stmt = getConnection().prepareStatement("UPDATE IMPORTED_FROM SET IS_DELETED=1 WHERE ID=?");
            stmt.setString(1, importer.getId());
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
