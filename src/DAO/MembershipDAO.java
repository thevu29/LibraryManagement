package DAO;

import Core.DefaultConnection;
import DTO.Membership;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MembershipDAO {
    public ArrayList<Membership> getAllMembership() {
        ArrayList<Membership> list = new ArrayList<>();
        try {
            Connection conn = DefaultConnection.getConnect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from `MEMBERSHIP`");

            while (rs.next()) {
                String membershipId = rs.getString("MA_THE");
                String customerId = rs.getString("MA_KH");
                String membershipTypeName = rs.getString("DANG_THE");
                String regisDate = rs.getDate("NGAY_DK").toString();
                String expireDate = rs.getDate("NGAY_HH").toString();
                boolean isDeleted = rs.getBoolean("IS_DELETED");

                list.add(new Membership(membershipId, customerId, membershipTypeName, regisDate, expireDate, isDeleted));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}