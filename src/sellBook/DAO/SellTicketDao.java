package sellBook.DAO;

import Book.DTO.Author;
import Core.DefaultConnection;
import sellBook.DTO.HoaDon;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        String sql = "SELECT sell_ticket.*,customer.TEN as tenKH,employee.TEN as tenNV  " +
                "FROM SELL_TICKET INNER JOIN customer on sell_ticket.MA_KH = customer.MA_KH " +
                "INNER JOIN employee on sell_ticket.MA_NV = employee.MA_NV " +
                "where sell_ticket.IS_DELETED = 0";
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
        String sql = "SELECT SUM(CAST(book.GIA AS UNSIGNED) * sell_ticket_details.HE_SO) as tongTien " +
                "FROM `sell_ticket` INNER JOIN sell_ticket_details on sell_ticket.MA_PHIEU = sell_ticket_details.MA_PHIEU " +
                "INNER JOIN book on sell_ticket_details.MA_SERIES = book.MA_SERIES " +
                "WHERE sell_ticket.MA_PHIEU = "+maHD;
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
        String sql = "SELECT sell_ticket.*,customer.TEN as tenKH,employee.TEN as tenNV  " +
                "FROM SELL_TICKET INNER JOIN customer on sell_ticket.MA_KH = customer.MA_KH " +
                "INNER JOIN employee on sell_ticket.MA_NV = employee.MA_NV " +
                "where sell_ticket.IS_DELETED = 0  AND sell_ticket.MA_PHIEU = '"+ma+"'";
        System.out.println(ma);
//        var a = """
//                SELECT sell_ticket.*,customer.TEN as tenKH,employee.TEN as tenNV\s
//                FROM SELL_TICKET INNER JOIN customer on sell_ticket.MA_KH = customer.MA_KH\s
//                INNER JOIN employee on sell_ticket.MA_NV = employee.MA_NV\s
//                where sell_ticket.IS_DELETED = 0  AND sell_ticket.MA_PHIEU = '%s'
//                """.formatted(ma);
        return getDs(sql);
    }
    public List<HoaDon> locMaNV(String ma){
        String sql = "SELECT sell_ticket.*,customer.TEN as tenKH,employee.TEN as tenNV  " +
                "FROM SELL_TICKET INNER JOIN customer on sell_ticket.MA_KH = customer.MA_KH " +
                "INNER JOIN employee on sell_ticket.MA_NV = employee.MA_NV " +
                "where sell_ticket.IS_DELETED = 0  AND sell_ticket.MA_NV = "+ma;
        return getDs(sql);
    }
    public List<HoaDon> locMaKH(String ma){
        String sql = "SELECT sell_ticket.*,customer.TEN as tenKH,employee.TEN as tenNV  " +
                "FROM SELL_TICKET INNER JOIN customer on sell_ticket.MA_KH = customer.MA_KH " +
                "INNER JOIN employee on sell_ticket.MA_NV = employee.MA_NV " +
                "where sell_ticket.IS_DELETED = 0  AND sell_ticket.MA_KH = "+ma;
        return getDs(sql);
    }

    public String getNewMaHD(){
        String sql = "SELECT CONCAT('HD', LPAD(SUBSTRING(MAX(MA_PHIEU), 3) + 1, 3, '0')) AS new_id FROM sell_ticket WHERE MA_PHIEU LIKE 'HD%'";
        Statement stmt = null;
        String maHD = "";
        try {
            stmt = getConnect().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                maHD = rs.getString("new_id");
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return maHD;
    }

    public int insertHD(HoaDon hd){
        String sql ="INSERT INTO `SELL_TICKET`(`MA_PHIEU`, `MA_NV`, `MA_KH`,`IS_DELETED`) VALUES (?,?,?,?)";
        int smt=0;

        SellTicketDao temp = new SellTicketDao();
        String maHD = temp.getNewMaHD();
        PreparedStatement pst = null;

        try {
            pst = getConnect().prepareStatement(sql);
            pst.setString(1, maHD);
            pst.setString(2,hd.getMa_nv());
            pst.setString(3,hd.getMa_KH());
            pst.setBoolean(4,false);
            smt = pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return smt;
    }

    public int removeHD(String id){
        String sql = "UPDATE `sell_ticket` SET `IS_DELETED`=1 WHERE MA_PHIEU = "+id;
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
        String sql = "SELECT  `TEN` FROM `customer` WHERE MA_KH LIKE '%"+maKH+"%'";
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

        System.out.println(t.locMaHD("1").size());
    }
}
