package DAO;

import Core.DefaultConnection;
import DTO.nhanVien;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class NhanVienDAO extends DefaultConnection {
    public ArrayList<nhanVien> getAllFromDatabase() {
        ArrayList<nhanVien> NV = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `EMPLOYEE` WHERE `IS_DELETED` = 0 ");
            while (rs.next()) {

                var id = rs.getString("MA_NV");
                var name = rs.getString("TEN");
                var SDT = rs.getString("PHONE");
                var PASSWORD = rs.getString("PASSWORD");
                var CV = rs.getInt("CHUC_VU");
                var Luong = rs.getInt("LUONG");
                var NLV = rs.getInt("NOI_LAM_VIEC");
                var Ca = rs.getInt("CA");
                var GT = rs.getInt("GIOI_TINH");
                var NL = rs.getInt("SO_NGAY_LAM_VIEC");
                var NS = rs.getString("NGAY_SINH");
                var email = rs.getString("Email");
                var dc = rs.getString("DIA_CHI");
                var CCCD = rs.getString("CCCD");


                NV.add(new nhanVien(id, name, SDT, NS, dc, email, PASSWORD, Ca, CV, GT, NLV, NL, Luong, CCCD));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Không thể kết nối");
        }
        return NV;
    }

    public int AddNV(nhanVien nv) {
        String sql = "INSERT INTO `EMPLOYEE` (`MA_NV`,`TEN`,`CA`,`CHUC_VU`,`SO_NGAY_LAM_VIEC`,`NOI_LAM_VIEC`,`PASSWORD`,`NGAY_SINH`,`DIA_CHI`,`PHONE`,`GIOI_TINH`,`LUONG`,`EMAIL`, `CCCD`, `IS_DELETED`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,0,0)";
        int smt = 0;
        PreparedStatement pst = null;
        try {
            pst = getConnection().prepareStatement(sql);
            pst.setString(1, nv.getID());
            pst.setString(2, nv.getName());
            pst.setInt(3, nv.getShift());
            pst.setInt(4, nv.getPosition());
            pst.setInt(5, nv.getDaywork());
            pst.setInt(6, nv.getWork());
            pst.setString(7, nv.getPassword());
            pst.setString(8, nv.getBirth());
            pst.setString(9, nv.getAddress());
            pst.setString(10, nv.getPhone());
            pst.setInt(11, nv.getGender());
            pst.setInt(12, nv.getSalary());
            pst.setString(13, nv.getEmail());
            pst.setString(14, nv.getCCCD());

            smt = pst.executeUpdate();
        } catch (SQLException e) {
            System.out.print("nhập chưa hợp lý nó ko chạy");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return smt;
    }

    public int removeNV(String id) {
        String sql = "UPDATE `EMPLOYEE` SET `IS_DELETED`=? WHERE    `MA_NV`=?";
        ;

        int smt = 0;
        PreparedStatement pst = null;
        try {
            pst = getConnection().prepareStatement(sql);
            pst.setInt(1, 1);
            pst.setString(2, id);
            smt = pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return smt;
    }

    public int contNV() {
        String sql = "select count(*) con from EMPLOYEE";
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = getConnection().createStatement();
//            pst = getConnection().prepareStatement(sql);
            rs = stmt.executeQuery(sql);
//            smt = pst.executeUpdate();
            if (rs.next())
                return rs.getInt(1) + 1;

            return 1;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public int EditNV(nhanVien nv) {
        String sql = "UPDATE `EMPLOYEE` SET `TEN`=?,`CA`=?,`CHUC_VU`=?,`SO_NGAY_LAM_VIEC`=?,`NOI_LAM_VIEC`=?,`PASSWORD`=?,`NGAY_SINH`=?,`DIA_CHI`=?,`PHONE`=?,`GIOI_TINH`=?,`LUONG`=?,`EMAIL`=?, `CCCD`=? WHERE `MA_NV`=?";

        int smt = 0;
        PreparedStatement pst = null;
        try {
            pst = getConnection().prepareStatement(sql);
            pst.setString(14, nv.getID());
            pst.setString(1, nv.getName());
            pst.setInt(2, nv.getShift());
            pst.setInt(3, nv.getPosition());
            pst.setInt(4, nv.getDaywork());
            pst.setInt(5, nv.getWork());
            pst.setString(6, nv.getPassword());
            pst.setString(7, nv.getBirth());
            pst.setString(8, nv.getAddress());
            pst.setString(9, nv.getPhone());
            pst.setInt(10, nv.getGender());
            pst.setInt(11, nv.getSalary());
            pst.setString(12, nv.getEmail());
            pst.setString(13, nv.getCCCD());
            smt = pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return smt;
    }
}