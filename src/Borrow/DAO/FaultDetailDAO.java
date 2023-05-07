package Borrow.DAO;

import Borrow.DTO.BorrowDetail;
import Borrow.DTO.FaultDetail;
import Borrow.GUI.FaultDetailUI;
import Core.DefaultConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class FaultDetailDAO extends DefaultConnection {
    public static ArrayList<FaultDetail> getDanhSach(String sql) {
        ArrayList<FaultDetail> dsLoiCT = new ArrayList<>();
        Statement stmt = null;
        try {
            Connection connect = getConnect();
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String maPhieu = rs.getString("MA_PHIEU");
                String maSach = rs.getString("MA_SERIES");
                String tenSach = rs.getString("TEN_SACH");
                double gia = rs.getDouble("gia");
                String maLoi = rs.getString("MA_LOI");
                String tenLoi = rs.getString("ten_loi");
                double heSo = rs.getDouble("he_so");
                int soLuong = rs.getInt("SO_LUONG");
                double tongTien = soLuong * gia * heSo / 100;

                dsLoiCT.add(new FaultDetail(maPhieu, maSach, tenSach, maLoi, tenLoi, soLuong, tongTien));
            }
            connect.close();
        } catch (SQLException | ClassNotFoundException e) {
            return dsLoiCT;
        }
        return dsLoiCT;
    }

    public ArrayList<FaultDetail> getDsLoiCT(String maPhieuMuon) {
        String sql = "SELECT ma_phieu,book_fault.MA_LOI,book_fault.TEN_LOI,book_fault.HE_SO ,book.MA_SERIES ,book.TEN_SACH,book.GIA,\n" +
                "borrow_book_ticket_fault.SO_LUONG FROM borrow_book_ticket_fault,book,book_fault\n" +
                "WHERE borrow_book_ticket_fault.MA_SERIES = book.MA_SERIES \n" +
                "AND borrow_book_ticket_fault.MA_LOI = book_fault.MA_LOI AND ma_phieu = '"+maPhieuMuon+"'";
        return getDanhSach(sql);
    }

    public static String getTenSach(String maSeri) {
        String sql = "SELECT TEN_SACH from `BOOK` WHERE  MA_SERIES = '" + maSeri + "'";

        Statement stmt = null;

        try {
            Connection connect = getConnect();
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String tenSach = "";
            while (rs.next()) {
                tenSach = rs.getString("TEN_SACH");
            }
            rs.close();
            stmt.close();
            connect.close();
            if (tenSach == null) {
                return "";
            }
            return tenSach;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String getTenLoi(String maLoi) {
        String sql = "SELECT TEN_LOI from `BOOK_FAULT` WHERE  MA_LOI = '" + maLoi + "'";

        Statement stmt = null;

        try {
            Connection connect = getConnect();
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String tenSach = "";
            while (rs.next()) {
                tenSach = rs.getString("TEN_LOI");
            }
            rs.close();
            stmt.close();
            connect.close();
            if (tenSach == null) {
                return "";
            }
            return tenSach;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public ArrayList<String> getDsMaSach(String maPhieu) {
        ArrayList<String> dsTen = new ArrayList<String>();
        String sql = "select MA_SERIES from `BORROW_TICKET_DETAILS` where MA_PHIEU = '" + maPhieu + "'";
        Statement stmt = null;

        try {
            Connection connect = getConnect();
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                dsTen.add(rs.getString("MA_SERIES"));
            }

            rs.close();
            stmt.close();
            connect.close();

        } catch (SQLException | ClassNotFoundException ex) {
            return dsTen;
        }

        return dsTen;
    }

    public ArrayList<String> getDsMaLoi() {
        ArrayList<String> dsTen = new ArrayList<String>();
        String sql = "select ma_loi from `BOOK_FAULT` where is_deleted = 0";
        Statement stmt = null;

        try {
            Connection connect = getConnect();
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                dsTen.add(rs.getString("ma_loi"));
            }

            rs.close();
            stmt.close();
            connect.close();

        } catch (SQLException | ClassNotFoundException ex) {
            return dsTen;
        }

        return dsTen;
    }

    //
    public static String getNameBook(String maSach) {
        String sql = "SELECT TEN_SACH from `BOOK` WHERE  MA_SERIES = '" + maSach + "'";

        Statement stmt = null;

        try {
            Connection connect = getConnect();
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String name = "";
            while (rs.next()) {
                name = rs.getString("TEN_SACH");
            }
            rs.close();
            stmt.close();
            connect.close();
            if (name == null) {
                return "";
            }
            return name;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public double getTongTien(String maLoi, String maSach, int soLuong) {
        String sql = "SELECT `BOOK`.GIA, `BOOK_FAULT`.HE_SO FROM `BOOK`,`BOOK_FAULT`\n" +
                "WHERE `BOOK`.MA_SERIES = '" + maSach + "' AND `BOOK_FAULT`.MA_LOI = '" + maLoi + "'";
        double tongTien = 0;
        Statement stmt = null;
        try {
            Connection connect = getConnect();
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                double gia = rs.getDouble("gia");
                double heSo = rs.getDouble("he_so");
                tongTien = soLuong * gia * heSo / 100;

                return tongTien;
            }
            rs.close();
            stmt.close();
            connect.close();
        } catch (SQLException | ClassNotFoundException e) {
            return tongTien;
        }
        return tongTien;
    }

    public int addField(String maPhieu, String maSach, String maLoi, int soLuong) {
        String sql = "INSERT INTO `BORROW_BOOK_TICKET_FAULT`(`MA_PHIEU`, `MA_SERIES`,`MA_LOI`,`SO_LUONG`) " +
                "VALUES (?,?,?,?)";
        int smt = 0;
        PreparedStatement pst = null;
        try {
            Connection connect = getConnect();
            pst = connect.prepareStatement(sql);
            pst.setString(1, maPhieu);
            pst.setString(2, maSach);
            pst.setString(3, maLoi);
            pst.setInt(4, soLuong);
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

    public int updateField(String maSachTruoc, String maLoiTruoc, String maPhieu, String maSach, String maLoi,
            int soLuong) {
        String sql = "update `BORROW_BOOK_TICKET_FAULT` set  `MA_SERIES` = ?,`MA_LOI`=?,`SO_LUONG`=? " +
                "where `MA_PHIEU` = ? and `MA_SERIES` = ? and `MA_LOI`=?";
        int smt = 0;
        PreparedStatement pst = null;
        try {
            Connection connect = getConnect();
            pst = connect.prepareStatement(sql);
            pst.setString(1, maSach);
            pst.setString(2, maLoi);
            pst.setInt(3, soLuong);
            pst.setString(4, maPhieu);
            pst.setString(5, maSachTruoc);
            pst.setString(6, maLoiTruoc);
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

    public int deleteField(String maPhieu, String maSach, String maLoi) {
        String sql = "delete from `BORROW_BOOK_TICKET_FAULT` " +
                "where `MA_PHIEU` = ? and `MA_SERIES` = ? and `MA_LOI`=?";
        int smt = 0;
        PreparedStatement pst = null;
        try {
            Connection connect = getConnect();
            pst = connect.prepareStatement(sql);
            pst.setString(1, maPhieu);
            pst.setString(2, maSach);
            pst.setString(3, maLoi);
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

    public static void main(String[] args) {
        FaultDetailDAO dao = new FaultDetailDAO();
        ArrayList<FaultDetail> ds = dao.getDsLoiCT("PM5");
        for (FaultDetail fault:
             ds) {
            System.out.println(fault.getTenLoi());
        }
    }
}
