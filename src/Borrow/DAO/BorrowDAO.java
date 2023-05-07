package Borrow.DAO;

import Borrow.DTO.Borrow;
import Borrow.DTO.Fault;
import Core.DefaultConnection;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
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
        String sql = "SELECT * FROM `BORROW_BOOK_TICKET_FAULT` \n" +
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
        String sql = "SELECT * FROM `BORROW_TICKET` WHERE IS_DELETED = 0";
        return getDanhSach(sql);
    }

    //
    public int delete(String id) {
        String sql = "UPDATE `BORROW_TICKET` SET IS_DELETED=1 WHERE MA_PHIEU=?";
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
        String sql = "SELECT COUNT(*) AS soluong FROM `BORROW_TICKET`";

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
        String sql = "SELECT `CUSTOMER`.TEN  FROM `MEMBERSHIP`,`CUSTOMER` WHERE `MEMBERSHIP`.MA_KH = `CUSTOMER`.MA_KH AND `MEMBERSHIP`.ma_the = '"
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
        String sql = "SELECT TEN from `EMPLOYEE` WHERE  MA_NV = '" + maNV + "'";

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
        String sql = "SELECT TIEN_TONG AS tongtien FROM `BORROW_TICKET_DETAILS`" +
                "WHERE ma_phieu = '" + maPhieuMuon  + "' GROUP BY ma_phieu, TIEN_TONG";

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
        String sql = "select * from `MEMBERSHIP`";
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
        String sql = "INSERT INTO `BORROW_TICKET`(`MA_PHIEU`, `MA_NV_MUON`,`MA_NV_TRA`, `MA_THE` , `DATE_BORROW`,`DATE_TO_GIVE_BACK`,`IS_DELETED`) "
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
        String sql = "select MA_SERIES from `BORROW_TICKET_DETAILS` where MA_PHIEU = '" + maPhieuMuon + "'";
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
        String sql = "SELECT GIA from `BOOK` WHERE  MA_SERIES = '" + maSach + "'";

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
        // String sql ="update `BORROW_TICKET` set `MA_THE` = ? ,`DATE_TO_GIVE_BACK` = ?
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

    public int submitBorrow(String id,String login_id) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        String ngayTra = sdf1.format(calendar.getTime());
        String maNv = login_id;
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

//    public  ArrayList<Borrow>

    public DefaultCategoryDataset laySoLuongPhieuMuon(int nam){
        String sql = "SELECT count(`BORROW_TICKET`.MA_PHIEU) as slg,month(`BORROW_TICKET`.DATE_BORROW) as thang " +
                "FROM `BORROW_TICKET` " +
                "where year(`BORROW_TICKET`.DATE_BORROW) = "+nam+" and `BORROW_TICKET`.IS_DELETED =0 " +
                "group by month(`BORROW_TICKET`.DATE_BORROW)";
        String rowKey = "";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        ArrayList<Borrow> dsLoi = new ArrayList<>();
        Statement stmt = null;
        int[] slgPM = new int[12];
        try {
            Connection connect = getConnect();
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int slg = rs.getInt("slg");
                int thang= rs.getInt("thang");
                slgPM[thang-1] = slg;
            }
            for (int i = 0; i < 12; i++) {
                int thang = i + 1;
                int slg = slgPM[i];
                String thangNam = thang+"/"+nam;
                dataset.setValue(slg,rowKey,thangNam);

            }
            rs.close();
            stmt.close();
            connect.close();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return dataset;

    }


    public DefaultCategoryDataset laySoLuongSachMuon(int nam){
        String sql = "SELECT COUNT(`BORROW_TICKET_DETAILS`.MA_SERIES) as slgSach,month(`BORROW_TICKET`.DATE_BORROW) as thang \n" +
                "FROM `BORROW_TICKET` \n" +
                "INNER JOIN `BORROW_TICKET_DETAILS` on `BORROW_TICKET_DETAILS`.MA_PHIEU = `BORROW_TICKET`.MA_PHIEU \n" +
                "WHERE year(`BORROW_TICKET`.DATE_BORROW) = "+nam+" and `BORROW_TICKET`.IS_DELETED =0 \n" +
                "GROUP BY month(DATE_BORROW)";
        String rowKey = "So Luong Sach";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        ArrayList<Borrow> dsLoi = new ArrayList<>();
        Statement stmt = null;
        int[] slgPM = new int[12];
        try {
            Connection connect = getConnect();
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int slg = rs.getInt("slgSach");
                int thang= rs.getInt("thang");
                slgPM[thang-1] = slg;
            }
            for (int i = 0; i < 12; i++) {
                int thang = i + 1;
                int slg = slgPM[i];
                String thangNam = thang+"/"+nam;
                dataset.setValue(slg,rowKey,thangNam);
            }
            rs.close();
            stmt.close();
            connect.close();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return dataset;

    }



    public DefaultPieDataset thongKeLoiSach(int nam){
        DefaultPieDataset dataset = new DefaultPieDataset();
        String sql ="SELECT COUNT(MA_SERIES) as soLoi,`BOOK_FAULT`.TEN_LOI as loi \n" +
                "FROM `BORROW_BOOK_TICKET_FAULT` \n" +
                "INNER JOIN `BOOK_FAULT` on `BOOK_FAULT`.MA_LOI=`BORROW_BOOK_TICKET_FAULT`.MA_LOI \n" +
                "INNER JOIN `BORROW_TICKET` on `BORROW_TICKET`.MA_PHIEU = `BORROW_BOOK_TICKET_FAULT`.MA_PHIEU \n" +
                "where Year(`BORROW_TICKET`.DATE_BORROW) = "+nam+" and `BORROW_TICKET`.IS_DELETED =0 \n" +
                "GROUP BY `BORROW_BOOK_TICKET_FAULT`.MA_LOI";
        Statement stmt = null;
        try {
            stmt = getConnect().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                double slg = rs.getDouble("soLoi");
                String tl = rs.getString("loi");
                dataset.setValue(tl,slg);
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return dataset;
    }

    public static void main(String[] args) {
        BorrowDAO t = new BorrowDAO();
        // System.out.println(Long.parseLong(t.getTongTien("PM1")));

//        System.out.println(t.getNewMaPhieuMuon());

        // SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        // Calendar calendar = Calendar.getInstance();
        // Calendar calendar1 = Calendar.getInstance();
        // calendar.add(Calendar.DATE, -15);
        //// calendar1.add(Calendar.DATE);
        // System.out.println(sdf1.format(calendar.getTime()));

//        DefaultCategoryDataset dataset = t.laySoLuongPhieuMuon(2023);
//        for (int i = 0; i < dataset.getRowCount(); i++) {
//            String rowKey = (String) dataset.getRowKey(i);
//            for (int j = 0; j < dataset.getColumnCount(); j++) {
//                String columnKey = (String) dataset.getColumnKey(j);
//                Number value = dataset.getValue(i, j);
//                System.out.println(rowKey + " \t " + columnKey + " \t " + value);
//            }
//        }



    }

}
