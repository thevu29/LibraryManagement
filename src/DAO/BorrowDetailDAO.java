package DAO;

import DTO.BorrowDetail;
import Core.DefaultConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class BorrowDetailDAO extends DefaultConnection {
    public static ArrayList<BorrowDetail> getDanhSach(String sql) {
        ArrayList<BorrowDetail> dsMuonCT = new ArrayList<>();
        Statement stmt = null;
        try {
            Connection connect = getConnect();
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                var maPhieu = rs.getString("MA_PHIEU");
                var maSeri = rs.getString("MA_SERIES");
                var tenSach = getTenSach(maSeri);
                var tienTamTinh = Double
                        .parseDouble(rs.getString("TIEN_TAM_TINH") == null ? "0" : rs.getString("TIEN_TAM_TINH"));
                var tongTien = Double.parseDouble(rs.getString("TIEN_TONG") == null ? "0" : rs.getString("TIEN_TONG"));

                DecimalFormat decimalFormat = new DecimalFormat("#.0");
                dsMuonCT.add(new BorrowDetail(maPhieu, maSeri, tenSach, Double.parseDouble(decimalFormat.format(tienTamTinh)), Double.parseDouble(decimalFormat.format(tongTien))));
            }
            rs.close();
            stmt.close();
            connect.close();
        } catch (SQLException | ClassNotFoundException e) {
            return dsMuonCT;
        }
        return dsMuonCT;
    }

    public ArrayList<BorrowDetail> getDsMuonCT(String maPhieuMuon) {
        String sql = "SELECT * FROM BORROW_TICKET_DETAILS WHERE MA_PHIEU = '" + maPhieuMuon + "' ";
        return getDanhSach(sql);
    }

    public static String getTenSach(String maSeri) {
        String sql = "SELECT TEN_SACH from BOOK WHERE  MA_SERIES = '" + maSeri + "'";

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

    public ArrayList<String> getDsMaSach() {
        ArrayList<String> dsTen = new ArrayList<String>();
        String sql = "select MA_SERIES from BOOK where TRANG_THAI = 'AVAILABLE' and is_deleted = 0";
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

    public static String getNameBook(String maSach) {
        String sql = "SELECT TEN_SACH from BOOK WHERE  MA_SERIES = '" + maSach + "'";

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

    public static double getPriceBook(String maSach) {
        String sql = "SELECT GIA from BOOK WHERE  MA_SERIES = '" + maSach + "'";

        Statement stmt = null;

        try {
            Connection connect = getConnect();
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String price = "0";
            while (rs.next()) {
                price = rs.getString("GIA");
            }
            rs.close();
            stmt.close();
            connect.close();
            if (price == null) {
                return 0;
            }
            return Double.parseDouble(price);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }


    public int getGiaGiam(String maThe) {
        String sql = "SELECT GIA_GIAM from MEMBERSHIP,MEMBERSHIP_TYPE WHERE  MEMBERSHIP.DANG_THE = MEMBERSHIP_TYPE.DANG_THE"
                +
                " and MA_THE = '" + maThe + "'";

        Statement stmt = null;

        try {
            Connection connect = getConnect();
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int heSo = 0;

            while (rs.next()) {
                heSo = rs.getInt("GIA_GIAM");
            }

            rs.close();
            stmt.close();
            connect.close();

            return heSo;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public int insert(BorrowDetail borrowDetail) {
        String sql = "INSERT INTO `BORROW_TICKET_DETAILS`(`MA_PHIEU`, `MA_SERIES`, `TIEN_TAM_TINH`,`TIEN_TONG` ) VALUES (?,?,?,?)";
        int smt = 0;
        PreparedStatement pst = null;
        try {
            Connection connect = getConnect();
            pst = connect.prepareStatement(sql);
            pst.setString(1, borrowDetail.getMaPhieu());
            pst.setString(2, borrowDetail.getMaSach());
            pst.setString(3, borrowDetail.getTienTamTinh() + "");
            pst.setString(4, borrowDetail.getTienTong() + "");
            smt = pst.executeUpdate();
            if (smt != 0) {
                updateStatusBookBorrow(borrowDetail.getMaSach(), "BORROWED");
            }
            pst.close();
            connect.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return smt;
    }

    public int update(BorrowDetail borrowDetail, String maSachTruoc) {
        String sql = "UPDATE `BORROW_TICKET_DETAILS` SET MA_SERIES=?,TIEN_TAM_TINH=?,TIEN_TONG=? WHERE MA_PHIEU=? and MA_SERIES = ?";
        int smt = 0;

        PreparedStatement pst = null;
        try {
            Connection connect = getConnect();
            pst = connect.prepareStatement(sql);
            pst.setString(1, borrowDetail.getMaSach());
            pst.setString(2, borrowDetail.getTienTamTinh() + "");
            pst.setString(3, borrowDetail.getTienTong() + "");
            pst.setString(4, borrowDetail.getMaPhieu());
            pst.setString(5, maSachTruoc);
            smt = pst.executeUpdate();
            if (smt != 0) {
                updateStatusBookBorrow(borrowDetail.getMaSach(), "BORROWED");
                updateStatusBookBorrow(maSachTruoc, "AVAILABLE");
            }
            pst.close();
            connect.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return smt;
    }

    public int updateStatusBookBorrow(String maSach, String trangThai) {
        String sql = "UPDATE `BOOK` SET TRANG_THAI = ?   WHERE MA_SERIES = ?";

        int smt = 0;

        PreparedStatement pst = null;
        try {
            Connection connect = getConnect();
            pst = connect.prepareStatement(sql);
            pst.setString(1, trangThai);
            pst.setString(2, maSach);
            smt = pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return smt;
    }

    public int delete(String maPhieu, String maSach) {
        String sql = "delete from `BORROW_TICKET_DETAILS` WHERE MA_PHIEU=? and MA_SERIES = ?";
        int smt = 0;

        PreparedStatement pst = null;
        try {
            Connection connect = getConnect();
            pst = connect.prepareStatement(sql);
            pst.setString(1, maPhieu);
            pst.setString(2, maSach);
            smt = pst.executeUpdate();
            if (smt != 0) {
                updateStatusBookBorrow(maSach, "AVAILABLE");
            }
            pst.close();
            connect.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return smt;
    }

    public boolean checkContainBookFault(String id, String maSach) {
        String sql = "select * from BORROW_BOOK_TICKET_FAULT where MA_PHIEU = '" + id + "' and MA_SERIES='" + maSach
                + "'";
        Statement stmt = null;
        try {
            Connection connect = getConnect();
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            boolean check = false;
            while (rs.next()) {
                check = true;
            }
            rs.close();
            stmt.close();
            connect.close();
            return check;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        BorrowDetailDAO dao = new BorrowDetailDAO();
        for (String s : dao.getDsMaSach()) {
            System.out.println(s);
        }
    }
}
