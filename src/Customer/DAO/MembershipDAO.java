package Customer.DAO;

import Core.DefaultConnection;
import Customer.DTO.MembershipType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MembershipDAO {
    public ArrayList<MembershipType> createList() {
        ArrayList<MembershipType> list = new ArrayList<>();
        try {
            Connection conn = DefaultConnection.openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from membership_type");

            while (rs.next()) {
                String membershipName = rs.getString("DANG_THE");
                int discount = rs.getInt("GIA_GIAM");
                boolean isDeleted = rs.getBoolean("IS_DELETED");
                list.add(new MembershipType(membershipName, discount, isDeleted));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
