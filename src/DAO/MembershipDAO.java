package DAO;

import Core.DefaultConnection;
import DTO.Customer;
import DTO.Membership;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public boolean addMembership(Customer customer) {
        boolean res = false;
        try {
            Connection conn = DefaultConnection.getConnect();

            String query = "insert into `MEMBERSHIP` values (?, ?, ?, ?, ?, ?)";
            PreparedStatement ptmt = conn.prepareStatement(query);

            int length = getMembershipLength() + 1;
            String id = String.format("%03d", length);
            String memId = "MEM" + id;

            ptmt.setString(1, memId);
            ptmt.setString(2, customer.getCustomerId());
            ptmt.setString(3, customer.getMembership());
            ptmt.setString(4, customer.getRegistrationDate());
            ptmt.setString(5, customer.getExpirationDate());
            ptmt.setInt(6, 0);

            if (ptmt.executeUpdate() >= 1) {
                res = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public boolean deleteMembership(String customerId) {
        boolean res = false;
        try {
            Connection conn = DefaultConnection.getConnect();

            String query = "update `MEMBERSHIP` set IS_DELETED = ? " + "where MA_KH = ?";
            PreparedStatement ptmt = conn.prepareStatement(query);

            ptmt.setInt(1, 1);
            ptmt.setString(2, customerId);

            if (ptmt.executeUpdate() >= 1) {
                res = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public boolean deleteDirectlyMembership(String customerId) {
        boolean res = false;
        try {
            Connection conn = DefaultConnection.getConnect();

            String query = "delete from `MEMBERSHIP` where MA_KH = ?";
            PreparedStatement ptmt = conn.prepareStatement(query);

            ptmt.setString(1, customerId);

            if (ptmt.executeUpdate() >= 1) {
                res = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public int getMembershipLength() {
        int cnt = 0;
        try {
            Connection conn = DefaultConnection.getConnect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select count(*) as Length from `MEMBERSHIP`");

            if (rs.next()) {
                cnt = rs.getInt("Length");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cnt;
    }
}