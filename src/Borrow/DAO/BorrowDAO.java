package Borrow.DAO;

import Borrow.DTO.Borrow;
import Borrow.DTO.Fault;
import Core.DefaultConnection;
import sellBook.DTO.HoaDon;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BorrowDAO extends DefaultConnection {

    public static ArrayList<Borrow> getDanhSach(String sql) {
        ArrayList<Borrow> dsLoi = new ArrayList<>();
        Statement stmt = null;
        try {
            Connection connect = getConnect();
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                var maPhieu = rs.getString("MA_PHIEU");
                var maNvMuon = rs.getString("MA_NV_MUON");
                var tenNvMuon = getNameNv(maNvMuon);
                var maNvTra = rs.getString("MA_NV_TRA");
                var tenNvTra = getNameNv(maNvTra);
                var maThe = rs.getString("MA_THE");
                var tenDocGia = getNameMember(maThe);
                var ngayMuon = rs.getString("DATE_BORROW");
                var ngayHenTra = rs.getString("DATE_TO_GIVE_BACK");
                var ngayTra = rs.getString("DATE_GIVE_BACK");
                var tongTienPhat = getTongTienPhat(maPhieu);
                var tongTienMuon = Double.parseDouble(getTongTien(maPhieu));

                if (ngayTra != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Long diff = 0l;
                    try {
                        Date firstDate = sdf.parse(ngayHenTra);
                        Date secondDate = sdf.parse(ngayTra);
                        long diffInMillies = secondDate.getTime() - firstDate.getTime();
                        if(diffInMillies>=0){
                            diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                        }
                    } catch (ParseException ex) {
                        System.out.println("Co loi");
                    }
                    ArrayList<String> dsMa = getDsMaSach(maPhieu);

                    if (dsMa.size() != 0) {
                        for (String item : dsMa) {
                            tongTienPhat += getPriceBook(item) * diff * 0.2;
                        }
                    }

                }

                dsLoi.add(new Borrow(maPhieu, maNvMuon, tenNvMuon, maNvTra, tenNvTra, maThe, tenDocGia,
                        ngayMuon, ngayHenTra, ngayTra, tongTienPhat, tongTienMuon));
            }
            rs.close();
            stmt.close();
            connect.close();

        } catch (SQLException | ClassNotFoundException e) {
            return dsLoi;
        }
        return dsLoi;
    }

    public static double getTongTienPhat(String maPhieu) {
        String sql = "SELECT * FROM borrow_book_ticket_fault \n" +
                "WHERE ma_phieu = '" + maPhieu + "'";
        double tongTien = 0;
        Statement stmt = null;
        try {
            Connection connect = getConnect();
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String maSach = rs.getString("MA_SERIES");
                String maLoi = rs.getString("MA_LOI");
                int soLuong = rs.getInt("SO_LUONG");

                tongTien += getTongTien(maLoi, maSach, soLuong);
            }
            rs.close();
            stmt.close();
            connect.close();
        } catch (SQLException | ClassNotFoundException e) {
            return tongTien;
        }
        return tongTien;
    }

    public static double getTongTien(String maLoi, String maSach, int soLuong) {
        String sql = "SELECT book.GIA, book_fault.HE_SO FROM book,book_fault\n" +
                "WHERE book.MA_SERIES = '" + maSach + "' AND book_fault.MA_LOI = '" + maLoi + "'";
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

            }
            rs.close();
            stmt.close();
            connect.close();
            return tongTien;
        } catch (SQLException | ClassNotFoundException e) {
            return tongTien;
        }
    }

    public ArrayList<Borrow> getDsMuon() {
        String sql = "SELECT * FROM borrow_ticket WHERE IS_DELETED = 0";
        return getDanhSach(sql);
    }

    //
    public int delete(String id) {
        String sql = "UPDATE `borrow_ticket` SET IS_DELETED=1 WHERE MA_PHIEU=?";
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

    public String getNewMaPhieuMuon() {
        Statement stmt = null;
        String sql = "SELECT COUNT(*) AS soluong FROM borrow_ticket";

        try {
            Connection connect = getConnect();
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int maHD = 0;
            while (rs.next()) {
                maHD = rs.getInt("soluong");
            }
            rs.close();
            rs.close();
            rs.close();
            stmt.close();
            connect.close();
            return "PM" + (maHD + 1);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String getNameMember(String maThe) {
        String sql = "SELECT customer.TEN  FROM membership,customer WHERE membership.MA_KH = customer.MA_KH AND membership.ma_the = '"
                + maThe + "'";
        Statement stmt = null;

        try {
            Connection connect = getConnect();
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String nameMember = "";
            while (rs.next()) {
                nameMember = rs.getString("ten");
            }
            rs.close();
            rs.close();
            rs.close();
            stmt.close();
            connect.close();
            if (nameMember == null) {
                return "";
            }
            return nameMember;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String getNameNv(String maNV) {
        String sql = "SELECT TEN from employee WHERE  MA_NV = '" + maNV + "'";

        Statement stmt = null;

        try {
            Connection connect = getConnect();
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String nameEmployee = "";
            while (rs.next()) {
                nameEmployee = rs.getString("TEN");
            }
            rs.close();
            rs.close();
            rs.close();
            stmt.close();
            connect.close();
            if (nameEmployee == null) {
                return "";
            }
            return nameEmployee;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String getTongTien(String maPhieuMuon) {
        String sql = "SELECT tien_tong AS tongtien FROM borrow_ticket_details\n" +
                "WHERE ma_phieu = '" + maPhieuMuon + "'\n" +
                "GROUP BY ma_phieu";

        Statement stmt = null;

        try {
            Connection connect = getConnect();
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String tongTien = "0";
            while (rs.next()) {
                tongTien = rs.getString("tongtien");
            }
            rs.close();
            stmt.close();
            connect.close();
            if (tongTien == null) {
                return "0";
            }
            return tongTien;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public ArrayList<String> getDsMaThe() {
        String sql = "select * from membership";
        Statement stmt = null;
        ArrayList<String> dsThe = new ArrayList<String>();
        try {
            Connection connect = getConnect();
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String maThe = rs.getString("MA_THE");
                dsThe.add(maThe);
            }
            rs.close();
            stmt.close();
            connect.close();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        return dsThe;
    }

    public int add(String id, String maThe, String maNhanVien, int soNgayMuon) {
        String sql = "INSERT INTO `borrow_ticket`(`MA_PHIEU`, `MA_NV_MUON`,`MA_NV_TRA`, `MA_THE` , `DATE_BORROW`,`DATE_TO_GIVE_BACK`,`IS_DELETED`) "
                +
                "VALUES (?,?,?,?,?,?,?)";
        int smt = 0;
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        String ngayMuon = sdf1.format(calendar.getTime());
        calendar.add(Calendar.DATE, soNgayMuon);
        String ngayHenTra = sdf1.format(calendar.getTime());
        PreparedStatement pst = null;
        try {
            Connection connect = getConnect();
            pst = connect.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, maNhanVien);
            pst.setString(3, "");
            pst.setString(4, maThe);
            pst.setString(5, ngayMuon);
            pst.setString(6, ngayHenTra);
            pst.setInt(7, 0);
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

    public static ArrayList<String> getDsMaSach(String maPhieuMuon) {
        ArrayList<String> dsTen = new ArrayList<String>();
        String sql = "select MA_SERIES from borrow_ticket_details where MA_PHIEU = '" + maPhieuMuon + "'";
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

    public static double getPriceBook(String maSach) {
        String sql = "SELECT GIA from book WHERE  MA_SERIES = '" + maSach + "'";

        Statement stmt = null;

        try {
            Connection connect = getConnect();
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String price = "";
            while (rs.next()) {
                price = rs.getString("GIA");
            }
            rs.close();
            stmt.close();
            connect.close();
            if (price == null) {
                return 0l;
            }
            return Double.parseDouble(price);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public int updateField(String id, String maThe, int soNgayMuon) {
        String sql = "UPDATE `BORROW_TICKET` SET `MA_THE`=?,`DATE_TO_GIVE_BACK`=? WHERE `MA_PHIEU`=?";
        // String sql ="update `borrow_ticket` set `MA_THE` = ? ,`DATE_TO_GIVE_BACK` = ?
        // where `MA_PHIEU` = ? ";
        ArrayList<Borrow> ds = getDsMuon();

        String ngayMuon = "";
        for (Borrow borrow : ds) {
            if (borrow.getId().equals(id)) {
                ngayMuon = borrow.getNgayMuon();
            }
        }

        String ngayHenTra = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // The date format to be used
        try {
            Date date = dateFormat.parse(ngayMuon); // Parse the date string into a Date object
            Calendar calendar = Calendar.getInstance(); // Create a Calendar object
            calendar.setTime(date); // Set the Calendar object to the parsed date
            calendar.add(Calendar.DATE, soNgayMuon);
            ngayHenTra = dateFormat.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int smt = 0;
        PreparedStatement pst = null;
        try {
            Connection connect = getConnect();
            pst = connect.prepareStatement(sql);
            pst.setString(1, maThe);
            pst.setString(2, ngayHenTra);
            pst.setString(3, id);

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

    public int updateStatusBookBorrow(String maSach, String trangThai) {
        String sql = "UPDATE `book` SET TRANG_THAI = ?   WHERE MA_SERIES = ?";

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

    public void submitBookBorrow(String id){
        ArrayList<String> dsSach = getDsMaSach(id);
        if(dsSach.size()!=0){
            for (String item:
                 dsSach) {
                System.out.println(item);
                updateStatusBookBorrow(item,"AVAILABLE");
            }
        }
    }

    public int submitBorrow(String id) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        String ngayTra = sdf1.format(calendar.getTime());
        String maNv = "NV1";
        String sql = "UPDATE `BORROW_TICKET` SET MA_NV_TRA = ?, DATE_GIVE_BACK = ? WHERE `MA_PHIEU`=?";

        int smt = 0;
        PreparedStatement pst = null;
        try {
            Connection connect = getConnect();
            pst = connect.prepareStatement(sql);
            pst.setString(1, maNv);
            pst.setString(2, ngayTra);
            pst.setString(3, id);

            smt = pst.executeUpdate();
            submitBookBorrow(id);

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
        BorrowDAO t = new BorrowDAO();
        // System.out.println(Long.parseLong(t.getTongTien("PM1")));

        System.out.println(t.getNewMaPhieuMuon());

        // SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        // Calendar calendar = Calendar.getInstance();
        // Calendar calendar1 = Calendar.getInstance();
        // calendar.add(Calendar.DATE, -15);
        //// calendar1.add(Calendar.DATE);
        // System.out.println(sdf1.format(calendar.getTime()));

    }

}
