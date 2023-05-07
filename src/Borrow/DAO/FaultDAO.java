package Borrow.DAO;

import Borrow.DTO.Fault;
import Core.DefaultConnection;
import sellBook.DAO.SellTicketDao;
import sellBook.DTO.HoaDon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class FaultDAO extends DefaultConnection {
    public static ArrayList<Fault> getDanhSach(String sql) {
        ArrayList<Fault> dsLoi = new ArrayList<>();
        Statement stmt = null;
        try {
            Connection connect = getConnect();
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                var maLoi = rs.getString("MA_LOI");
                var tenLoi = rs.getString("TEN_LOI");
                var heSo = rs.getDouble("HE_SO");

                dsLoi.add(new Fault(maLoi, tenLoi, heSo));
            }
            rs.close();
            stmt.close();
            connect.close();
        } catch (SQLException | ClassNotFoundException e) {
            return dsLoi;
        }
        return dsLoi;
    }

    public ArrayList<Fault> getDsLoi() {
        String sql = "SELECT * FROM `BOOK_FAULT` WHERE IS_DELETED = 0";
        return getDanhSach(sql);
    }

    public int insert(Fault fault) {
        String sql = "INSERT INTO `BOOK_FAULT`(`MA_LOI`, `TEN_LOI`, `HE_SO` , `IS_DELETED`) VALUES (?,?,?,?)";
        int smt = 0;
        PreparedStatement pst = null;
        try {
            Connection connect = getConnect();
            pst = connect.prepareStatement(sql);
            pst.setString(1, fault.getId());
            pst.setString(2, fault.getTenLoi());
            pst.setDouble(3, fault.getHeSo());
            pst.setInt(4, 0);
            smt = pst.executeUpdate();
            pst.close();
            connect.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return smt;
    }

    //
    public int remove(String id) {
        String sql = "UPDATE `BOOK_FAULT` SET IS_DELETED=1 WHERE MA_LOI=?";
        int smt = 0;

        PreparedStatement pst = null;
        try {
            Connection connect = getConnect();
            pst = connect.prepareStatement(sql);
            pst.setString(1, id);
            smt = pst.executeUpdate();
            pst.close();
            connect.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return smt;
    }

    public int update(Fault fault) {
        String sql = "UPDATE `BOOK_FAULT` SET TEN_LOI=?,`HE_SO`=? WHERE MA_LOI=?";
        int smt = 0;

        PreparedStatement pst = null;
        try {
            Connection connect = getConnect();
            pst = connect.prepareStatement(sql);
            pst.setString(3, fault.getId());
            pst.setString(1, fault.getTenLoi());
            pst.setDouble(2, fault.getHeSo());
            smt = pst.executeUpdate();
            pst.close();
            connect.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return smt;
    }

    public String getML() {
        Statement stmt = null;
        String sql = "SELECT count(*) as soluong FROM `BOOK_FAULT`";

        try {
            Connection connect = getConnect();
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int maHD = 0;
            while (rs.next()) {
                maHD = rs.getInt("soluong");
            }
            var maHDMoi = "ML" + (maHD + 1);
            rs.close();
            stmt.close();
            connect.close();
            return maHDMoi;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

}
