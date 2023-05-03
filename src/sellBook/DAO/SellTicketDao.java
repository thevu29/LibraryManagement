package sellBook.DAO;

import Book.DTO.Author;
import Core.DefaultConnection;
import org.jfree.data.category.DefaultCategoryDataset;
import sellBook.DTO.HoaDon;

import java.sql.*;
import java.time.Instant;
import java.util.*;

public class SellTicketDao extends DefaultConnection {


    public static ArrayList<HoaDon> getDs(String sql){
        SellTicketDao temp = new SellTicketDao();
        ArrayList<HoaDon> dshd = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = getConnect().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                var maPhieu = rs.getString("MA_PHIEU");
                var maNv = rs.getString("MA_NV");
                var maKh = rs.getString("MA_KH");
                var tenKH = rs.getString("tenKH");
                var tenNV = rs.getString("tenNV");
                var total = temp.tinhTongHoaDon(maPhieu);
                dshd.add(new HoaDon(maPhieu, maNv,  maKh,tenNV,tenKH,total));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return dshd;
    }


    public  ArrayList<HoaDon> getDsHoaDon() {
        String sql = "SELECT SELL_TICKET.*,CUSTOMER.TEN as tenKH,EMPLOYEE.TEN as tenNV  " +
                "FROM SELL_TICKET INNER JOIN CUSTOMER on SELL_TICKET.MA_KH = CUSTOMER.MA_KH " +
                "INNER JOIN EMPLOYEE on SELL_TICKET.MA_NV = EMPLOYEE.MA_NV " +
                "where SELL_TICKET.IS_DELETED = 0";
        return getDs(sql);
    }

    public ArrayList<String> getMaHD(){
        Set<String> maHDSet = new HashSet<>();
        ArrayList<HoaDon> dsHoaDon = getDsHoaDon();
        for (HoaDon hd : dsHoaDon) {
            maHDSet.add(hd.getMa_phieu());
        }
        ArrayList<String> sortedMaHDList = new ArrayList<>(maHDSet);
        Collections.sort(sortedMaHDList);
        return sortedMaHDList;
    }

    public ArrayList<String> getMaNV(){
        Set<String> maNVSet = new HashSet<>();
        ArrayList<HoaDon> dsHoaDon = getDsHoaDon();
        for (HoaDon hd : dsHoaDon) {
            maNVSet.add(hd.getMa_nv());
        }
        return new ArrayList<>(maNVSet);
    }

    public ArrayList<String> getMaKH(){
        Set<String> maKHSet = new HashSet<>();
        ArrayList<HoaDon> dsHoaDon = getDsHoaDon();
        for (HoaDon hd : dsHoaDon) {
            maKHSet.add(hd.getMa_KH());
        }
        return new ArrayList<>(maKHSet);
    }

    public long tinhTongHoaDon(String maHD){
        String sql = """
                    SELECT SUM(CAST(BOOK.GIA AS UNSIGNED) * SELL_TICKET_DETAILS.HE_SO) as tongTien
                    FROM `SELL_TICKET` INNER JOIN SELL_TICKET_DETAILS on SELL_TICKET.MA_PHIEU = SELL_TICKET_DETAILS.MA_PHIEU 
                    INNER JOIN BOOK on SELL_TICKET_DETAILS.MA_SERIES = BOOK.MA_SERIES
                    WHERE SELL_TICKET.MA_PHIEU = '%s'""".formatted(maHD);
        Statement stmt = null;
        long total = 0;
        try {
            stmt = getConnect().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                total = rs.getLong("tongTien");
            }
        } catch (SQLException | ClassNotFoundException e) {
            return 0;
        }
        return total;
    }



    public List<HoaDon> locMaHD(String ma){
        String sql = "SELECT SELL_TICKET.*,CUSTOMER.TEN as tenKH,EMPLOYEE.TEN as tenNV  " +
                "FROM SELL_TICKET INNER JOIN CUSTOMER on SELL_TICKET.MA_KH = CUSTOMER.MA_KH " +
                "INNER JOIN EMPLOYEE on SELL_TICKET.MA_NV = EMPLOYEE.MA_NV " +
                "where SELL_TICKET.IS_DELETED = 0  AND SELL_TICKET.MA_PHIEU = '"+ma+"'";
        System.out.println(ma);
//        var a = """
//                SELECT SELL_TICKET.*,CUSTOMER.TEN as tenKH,EMPLOYEE.TEN as tenNV\s
//                FROM SELL_TICKET INNER JOIN CUSTOMER on SELL_TICKET.MA_KH = CUSTOMER.MA_KH\s
//                INNER JOIN EMPLOYEE on SELL_TICKET.MA_NV = EMPLOYEE.MA_NV\s
//                where SELL_TICKET.IS_DELETED = 0  AND SELL_TICKET.MA_PHIEU = '%s'
//                """.formatted(ma);
        return getDs(sql);
    }
    public List<HoaDon> locMaNV(String ma){
        String sql = "SELECT SELL_TICKET.*,CUSTOMER.TEN as tenKH,EMPLOYEE.TEN as tenNV  " +
                "FROM SELL_TICKET INNER JOIN CUSTOMER on SELL_TICKET.MA_KH = CUSTOMER.MA_KH " +
                "INNER JOIN EMPLOYEE on SELL_TICKET.MA_NV = EMPLOYEE.MA_NV " +
                "where SELL_TICKET.IS_DELETED = 0  AND SELL_TICKET.MA_NV = "+ma;
        return getDs(sql);
    }
    public List<HoaDon> locMaKH(String ma){
        String sql = "SELECT SELL_TICKET.*,CUSTOMER.TEN as tenKH,EMPLOYEE.TEN as tenNV  " +
                "FROM SELL_TICKET INNER JOIN CUSTOMER on SELL_TICKET.MA_KH = CUSTOMER.MA_KH " +
                "INNER JOIN EMPLOYEE on SELL_TICKET.MA_NV = EMPLOYEE.MA_NV " +
                "where SELL_TICKET.IS_DELETED = 0  AND SELL_TICKET.MA_KH = "+ma;
        return getDs(sql);
    }

    public String getNewMaHD(){
        Statement stmt = null;
        try {
            stmt = getConnection().createStatement();
            var rs = stmt.executeQuery("SELECT MAX(CAST(SUBSTR(MA_PHIEU, 3) AS UNSIGNED)) AS max_num FROM `SELL_TICKET` ");
            rs.next();
            var maHD = rs.getString("max_num");
            if (Objects.isNull(maHD)) {
                return "HD1";
            }
            int ma = Integer.parseInt(maHD)+1;
            String maHDMoi = "HD".concat(String.valueOf(ma));

            return maHDMoi;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public int insertHD(HoaDon hd){
        String sql ="INSERT INTO `SELL_TICKET`(`MA_PHIEU`, `MA_NV`, `MA_KH`,`IS_DELETED`,`CREATED_AT`) VALUES (?,?,?,0,?)";
        int smt=0;

        SellTicketDao temp = new SellTicketDao();
        String maHD = temp.getNewMaHD();
        PreparedStatement pst = null;

        try {
            pst = getConnect().prepareStatement(sql);
            pst.setString(1, maHD);
            pst.setString(2,hd.getMa_nv());
            pst.setString(3,hd.getMa_KH());
            pst.setTimestamp(4, Timestamp.from(Instant.now()));
            smt = pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return smt;
    }

    public int removeHD(String id){
        String sql = "UPDATE `SELL_TICKET` SET `IS_DELETED`=1 WHERE MA_PHIEU = '"+id+"'";
        int smt = 0;
        PreparedStatement pst = null;
        try {
            pst = getConnect().prepareStatement(sql);
            smt = pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return smt;
    }



    public int updateHD(HoaDon hd){
        String sql = "UPDATE `SELL_TICKET` SET MA_NV=?,`MA_KH`=? WHERE MA_PHIEU=?";
        int smt = 0;

        PreparedStatement pst = null;
        try {
            pst = getConnect().prepareStatement(sql);
            pst.setString(3, hd.getMa_phieu());
            pst.setString(1,hd.getMa_nv());
            pst.setString(2,hd.getMa_KH());
            smt = pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return smt;
    }

    public String goiYTenKH(String maKH){
        String sql = "SELECT  `TEN` FROM `CUSTOMER` WHERE MA_KH LIKE '%"+maKH+"%'";
        Statement stmt = null;
        String tenKH = "";
        try {
            stmt = getConnect().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                tenKH = rs.getString("TEN");
                break;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return tenKH;
    }



    public DefaultCategoryDataset laySoLieuTheoThang(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String rowKey = "Hoa Don";
        String sql = "SELECT COUNT(MA_PHIEU) as slgHD,Month(CREATED_AT) as thang FROM `SELL_TICKET` WHERE IS_DELETED = 0  GROUP BY month(CREATED_AT)  ORDER BY thang ASC";
        Statement stmt = null;
        try {
            stmt = getConnect().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Double soLieu = rs.getDouble("slgHD");
                String thang = rs.getString("thang");
                dataset.setValue(soLieu,rowKey,thang);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return dataset;
    }


    public DefaultCategoryDataset laySoLieuTheoNam(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String rowKey = "Hoa Don";
        String sql = "SELECT COUNT(MA_PHIEU) as slgHD,Year(CREATED_AT) as nam FROM `SELL_TICKET` WHERE IS_DELETED = 0 GROUP BY YEAR(CREATED_AT) ORDER BY nam ASC";
        Statement stmt = null;
        try {
            stmt = getConnect().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Double soLieu = rs.getDouble("slgHD");
                String thang = rs.getString("nam");
                dataset.setValue(soLieu,rowKey,thang);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return dataset;
    }

    public DefaultCategoryDataset thongKeTheoNam(int nam){
        String sql = "SELECT COUNT(MA_PHIEU) as slgHD, Year(CREATED_AT) as nam, MONTH(CREATED_AT) as thang \n" +
                "FROM `SELL_TICKET` \n" +
                "WHERE YEAR(CREATED_AT) = "+nam+" AND IS_DELETED = 0 \n" +
                "GROUP BY Year(CREATED_AT), MONTH(CREATED_AT)\n";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String rowKey = "Hoa Don";
        Statement stmt = null;
        double[] slgHDs = new double[12];
        try {
            stmt = getConnect().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Double soLieu = rs.getDouble("slgHD");
                int thang = rs.getInt("thang");
//                String thangNam = thang+"/"+nam;
//                dataset.setValue(soLieu,rowKey,thang);
                slgHDs[thang -1] = soLieu;
            }
            for (int i = 0; i < 12; i++) {
                int thang = i + 1;
                double slgHD = slgHDs[i];
                System.out.println("Tháng " + thang + "/" + nam + ": " + slgHD + " hóa đơn");
                String thangNam = thang+"/"+nam;
                dataset.setValue(slgHD,rowKey,thangNam);

            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }

        return dataset;
    }

    public DefaultCategoryDataset thongKeThuNhapTheoThang(int nam){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String rowKey = "Hoa Don";
        String sql = "SELECT\n" +
                "YEAR(CREATED_AT) as nam,\n" +
                "MONTH(CREATED_AT) as thang,\n" +
                "SUM(CAST(book.GIA AS UNSIGNED) * SELL_TICKET_DETAILS.HE_SO) as tongTien\n" +
                "FROM SELL_TICKET\n" +
                "INNER JOIN SELL_TICKET_DETAILS ON SELL_TICKET_DETAILS.MA_PHIEU = SELL_TICKET.MA_PHIEU\n" +
                "INNER JOIN BOOK ON book.MA_SERIES = SELL_TICKET_DETAILS.MA_SERIES\n" +
                "WHERE Year(SELL_TICKET.CREATED_AT) = "+nam+" and SELL_TICKET.IS_DELETED = 0 AND SELL_TICKET_DETAILS.IS_DELETED = 0\n" +
                "GROUP BY YEAR(CREATED_AT), MONTH(CREATED_AT)\n" +
                "ORDER BY YEAR(CREATED_AT) ASC, MONTH(CREATED_AT) ASC;";
        Statement stmt = null;
        double[] thuNhap = new double[12];
        try {
            stmt = getConnect().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Double soLieu = rs.getDouble("tongTien");
                int thang = rs.getInt("thang");
                String thangNam = thang+"/"+nam;
                thuNhap[thang-1] = soLieu;
            }
            for (int i = 0; i < 12; i++) {
                int thang = i + 1;
                double tien = thuNhap[i];
                System.out.println("Tháng " + thang + "/" + nam + ": " + tien + " hóa đơn");
                String thangNam = thang+"/"+nam;
                dataset.setValue(tien,rowKey,thangNam);

            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return dataset;
    }


    public static void main(String[] args) {
        SellTicketDao t = new SellTicketDao();
//        List<HoaDon> dshd  = t.getDsHoaDon();
//        for (HoaDon hd : dshd) {
//             System.out.println(hd.getTongHD());
//
//        }

//        List<String> ds = t.getMaKH();
//        for(String nv:ds){
//            System.out.println(nv);
//        }

//        HoaDon hd = new HoaDon("21","2","5");
//        int smt = t.updateHD(hd);
//        if(smt>0){
//            System.out.println("Hello Thanh Cong");
//        }
//        else{
//            System.out.println("Khong thanh");
//        }

//        List<String> dsMaKH = t.getMaKH();
//        for(String te:dsMaKH){
//            System.out.println(te);
//        }
//        System.out.println(t.tinhTongHoaDon("1"));
//        System.out.println(t.goiYTenKH("4"));

//        System.out.println(t.locMaHD("1").size());

//        DefaultCategoryDataset dataset = t.thongKeThuNhapTheoThang();
//        System.out.println("Hoa Don \t Thang \t So Luong");
//        for (int i = 0; i < dataset.getRowCount(); i++) {
//            String rowKey = (String) dataset.getRowKey(i);
//            for (int j = 0; j < dataset.getColumnCount(); j++) {
//                String columnKey = (String) dataset.getColumnKey(j);
//                Number value = dataset.getValue(i, j);
//                System.out.println(rowKey + " \t " + columnKey + " \t " + value);
//            }
//        }
//        t.thongKeTheoNam(1900);
    }
}
