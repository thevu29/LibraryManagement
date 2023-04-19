package Borrow.DAO;

import Borrow.DTO.Fault;
import Core.DefaultConnection;
import sellBook.DAO.SellTicketDao;
import sellBook.DTO.HoaDon;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class FaultDAO extends DefaultConnection {
    public static ArrayList<Fault> getDanhSach(String sql){
        ArrayList<Fault> dsLoi = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = getConnect().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                var maLoi = rs.getString("MA_LOI");
                var tenLoi = rs.getString("TEN_LOI");
                var heSo = rs.getDouble("HE_SO");

                dsLoi.add(new Fault(maLoi, tenLoi, heSo));
            }
        } catch (SQLException | ClassNotFoundException e) {
            return dsLoi;
        }
        return dsLoi;
    }


    public  ArrayList<Fault> getDsLoi() {
        String sql = "SELECT * FROM book_fault WHERE IS_DELETED = 1";
        return getDanhSach(sql);
    }
//
//    public ArrayList<String> getMaHD(){
//        Set<String> maHDSet = new HashSet<>();
//        ArrayList<HoaDon> dsHoaDon = getDsHoaDon();
//        for (HoaDon hd : dsHoaDon) {
//            maHDSet.add(hd.getMa_phieu());
//        }
//        ArrayList<String> sortedMaHDList = new ArrayList<>(maHDSet);
//        Collections.sort(sortedMaHDList);
//        return sortedMaHDList;
//    }
//
//    public ArrayList<String> getMaNV(){
//        Set<String> maNVSet = new HashSet<>();
//        ArrayList<HoaDon> dsHoaDon = getDsHoaDon();
//        for (HoaDon hd : dsHoaDon) {
//            maNVSet.add(hd.getMa_nv());
//        }
//        return new ArrayList<>(maNVSet);
//    }
//
//    public ArrayList<String> getMaKH(){
//        Set<String> maKHSet = new HashSet<>();
//        ArrayList<HoaDon> dsHoaDon = getDsHoaDon();
//        for (HoaDon hd : dsHoaDon) {
//            maKHSet.add(hd.getMa_nv());
//        }
//        return new ArrayList<>(maKHSet);
//    }
//
//    public List<HoaDon> locMaHD(String ma){
//        String sql = "SELECT * FROM sell_ticket where MA_PHIEU = "+ma;
//        return getDs(sql);
//    }
//    public List<HoaDon> locMaNV(String ma){
//        String sql = "SELECT * FROM sell_ticket where MA_NV = "+ma;
//        return getDs(sql);
//    }
//    public List<HoaDon> locMaKH(String ma){
//        String sql = "SELECT * FROM sell_ticket where MA_KH = "+ma;
//        return getDs(sql);
//    }
//
    public int insert(Fault fault){
        String sql ="INSERT INTO `book_fault`(`MA_LOI`, `TEN_LOI`, `HE_SO` , `IS_DELETED`) VALUES (?,?,?,?)";
        int smt=0;
        PreparedStatement pst = null;
        try {
            pst = getConnect().prepareStatement(sql);
            pst.setString(1, fault.getId());
            pst.setString(2, fault.getTenLoi());
            pst.setDouble(3, fault.getHeSo());
            pst.setInt(4, 1);
            smt = pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return smt;
    }
//
    public int remove(String id){
        String sql = "UPDATE `book_fault` SET IS_DELETED=0 WHERE MA_LOI=?";
        int smt = 0;

        PreparedStatement pst = null;
        try {
            pst = getConnect().prepareStatement(sql);
            pst.setString(1, id);
            smt = pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return smt;
    }

    public int update(Fault fault){
        String sql = "UPDATE `book_fault` SET TEN_LOI=?,`HE_SO`=? WHERE MA_LOI=?";
        int smt = 0;

        PreparedStatement pst = null;
        try {
            pst = getConnect().prepareStatement(sql);
            pst.setString(3, fault.getId());
            pst.setString(1, fault.getTenLoi());
            pst.setDouble(2, fault.getHeSo());
            smt = pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return smt;
    }

}
