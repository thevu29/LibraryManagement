package Borrow.DAO;

import Borrow.DTO.BorrowDetail;
import Borrow.DTO.FaultDetail;
import Core.DefaultConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class FaultDetailDAO extends DefaultConnection {
    public static ArrayList<FaultDetail> getDanhSach(String sql){
        ArrayList<FaultDetail> dsLoiCT = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = getConnect().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String maPhieu = rs.getString("MA_PHIEU");
                String maSach = rs.getString("MA_SERIES");
                String tenSach = rs.getString("ten_sach");
                double gia = rs.getDouble("gia");
                String maLoi = rs.getString("MA_LOI");
                String tenLoi = rs.getString("ten_loi");
                double heSo = rs.getDouble("he_so");
                int soLuong = rs.getInt("SO_LUONG");
                double tongTien = soLuong * gia * heSo / 100;

                dsLoiCT.add(new FaultDetail(maPhieu,maSach,tenSach,maLoi,tenLoi,soLuong,tongTien));
            }
        } catch (SQLException | ClassNotFoundException e) {
            return dsLoiCT;
        }
        return dsLoiCT;
    }


    public  ArrayList<FaultDetail> getDsLoiCT(String maPhieuMuon) {
        String sql = "SELECT ma_phieu,book_fault.*,book.ma_series,book.ten_sach,\n" +
                "book.GIA,borrow_book_ticket_fault.SO_LUONG FROM borrow_book_ticket_fault,book,\n" +
                "book_fault WHERE borrow_book_ticket_fault.MA_SERIES = book.MA_SERIES \n" +
                "AND borrow_book_ticket_fault.MA_LOI = book_fault.MA_LOI and ma_phieu='"+maPhieuMuon+"'";
        return getDanhSach(sql);
    }


    public static String getTenSach(String maSeri){
        String sql = "SELECT TEN_SACH from book WHERE  MA_SERIES = '"+maSeri+"'";

        Statement stmt = null;

        try {
            stmt = getConnect().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String tenSach = "" ;
            while(rs.next()){
                tenSach = rs.getString("TEN_SACH");
            }
            if(tenSach == null){
                return "";
            }
            return tenSach;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String getTenLoi(String maLoi){
        String sql = "SELECT TEN_LOI from book_fault WHERE  MA_LOI = '"+maLoi+"'";

        Statement stmt = null;

        try {
            stmt = getConnect().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String tenSach = "" ;
            while(rs.next()){
                tenSach = rs.getString("TEN_LOI");
            }
            if(tenSach == null){
                return "";
            }
            return tenSach;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public ArrayList<String> getDsMaSach(String maPhieu){
        ArrayList<String> dsTen = new ArrayList<String>();
        String sql = "select MA_SERIES from borrow_ticket_details where ma_phieu = '"+maPhieu+"'";
        Statement st = null;

        try {
            st = getConnect().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){
                dsTen.add(rs.getString("MA_SERIES"));
            }

        }catch (SQLException | ClassNotFoundException ex){
            return dsTen;
        }

        return dsTen;
    }

    public ArrayList<String> getDsMaLoi(){
        ArrayList<String> dsTen = new ArrayList<String>();
        String sql = "select ma_loi from book_fault where is_deleted = 0";
        Statement st = null;

        try {
            st = getConnect().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){
                dsTen.add(rs.getString("ma_loi"));
            }

        }catch (SQLException | ClassNotFoundException ex){
            return dsTen;
        }

        return dsTen;
    }
//
    public static String getNameBook(String maSach){
        String sql = "SELECT TEN_SACH from book WHERE  MA_SERIES = '"+maSach+"'";


        Statement stmt = null;

        try {
            stmt = getConnect().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String name = "" ;
            while(rs.next()){
                name = rs.getString("TEN_SACH");
            }
            if(name == null){
                return "";
            }
            return name;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }


    public double getTongTien(String maLoi,String maSach,int soLuong){
        String sql = "SELECT book.GIA, book_fault.HE_SO FROM book,book_fault\n" +
                "WHERE book.MA_SERIES = '"+maSach+"' AND book_fault.MA_LOI = '"+maLoi+"'";
        double tongTien = 0;
        Statement stmt = null;
        try {
            stmt = getConnect().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                double gia = rs.getDouble("gia");
                double heSo = rs.getDouble("he_so");
                tongTien = soLuong * gia * heSo / 100;

                return tongTien;
            }
        } catch (SQLException | ClassNotFoundException e) {
            return tongTien;
        }
        return tongTien;
    }

    public int addField(String maPhieu,String maSach,String maLoi,int soLuong){
        String sql ="INSERT INTO `borrow_book_ticket_fault`(`MA_PHIEU`, `MA_SERIES`,`MA_LOI`,`SO_LUONG`) " +
                "VALUES (?,?,?,?)";
        int smt=0;
        PreparedStatement pst = null;
        try {
            pst = getConnect().prepareStatement(sql);
            pst.setString(1,maPhieu);
            pst.setString(2,maSach);
            pst.setString(3,maLoi);
            pst.setInt(4,soLuong);
            smt = pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return smt;
    }

    public int updateField(String maSachTruoc,String maLoiTruoc,String maPhieu,String maSach,String maLoi,int soLuong){
        String sql ="update `borrow_book_ticket_fault` set  `MA_SERIES` = ?,`MA_LOI`=?,`SO_LUONG`=? " +
                "where `MA_PHIEU` = ? and `MA_SERIES` = ? and `MA_LOI`=?";
        int smt=0;
        PreparedStatement pst = null;
        try {
            pst = getConnect().prepareStatement(sql);
            pst.setString(1,maSach);
            pst.setString(2,maLoi);
            pst.setInt(3,soLuong);
            pst.setString(4,maPhieu);
            pst.setString(5,maSachTruoc);
            pst.setString(6,maLoiTruoc);
            smt = pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return smt;
    }

    public int deleteField(String maPhieu,String maSach,String maLoi){
        String sql ="delete from `borrow_book_ticket_fault` "+
                "where `MA_PHIEU` = ? and `MA_SERIES` = ? and `MA_LOI`=?";
        int smt=0;
        PreparedStatement pst = null;
        try {
            pst = getConnect().prepareStatement(sql);
            pst.setString(1,maPhieu);
            pst.setString(2,maSach);
            pst.setString(3,maLoi);
            smt = pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return smt;
    }

    public static void main(String[] args) {

        System.out.println(FaultDetailDAO.getTenLoi("ML001"));
    }
}
