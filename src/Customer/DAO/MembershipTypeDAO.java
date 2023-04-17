package Customer.DAO;

import Core.DefaultConnection;
import Customer.DTO.MembershipType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MembershipTypeDAO {
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

    public boolean addMembershipType(MembershipType membershipType) {
        boolean res = false;
        try {
            Connection conn = DefaultConnection.openConnection();

            String query = "insert into membership_type values (?, ?, ?)";
            PreparedStatement ptmt = conn.prepareStatement(query);

            ptmt.setString(1, membershipType.getMembershipTypeName());
            ptmt.setInt(2, membershipType.getDiscount());
            ptmt.setInt(3, 0);

            if (ptmt.executeUpdate() >= 1) {
                res = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public boolean deleteMembershipType(String name) {
        boolean res = false;
        try {
            Connection conn = DefaultConnection.openConnection();

            String query = "update membership_type set IS_DELETED = ? " + "where DANG_THE = ?";
            PreparedStatement ptmt = conn.prepareStatement(query);

            ptmt.setInt(1, 1);
            ptmt.setString(2, name);

            if (ptmt.executeUpdate() >= 1) {
                res = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public boolean updateMembership(MembershipType membershipType) {
        boolean res = false;
        try {
            Connection conn = DefaultConnection.openConnection();

            String query = "update membership_type set GIA_GIAM = ? " + "where DANG_THE = ?";
            PreparedStatement ptmt = conn.prepareStatement(query);

            ptmt.setInt(1, membershipType.getDiscount());
            ptmt.setString(2, membershipType.getMembershipTypeName());

            if (ptmt.executeUpdate() >= 1) {
                res = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
