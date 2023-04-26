package sellBook.DAO;

import Core.DefaultConnection;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import sellBook.DTO.CTHD;
import sellBook.DTO.HoaDon;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CTHDDao extends DefaultConnection {

    public static ArrayList<CTHD> getDs(String sql){
        ArrayList<CTHD> dsct = new ArrayList<>();
        Statement stmt = null;
        CTHDDao temp = new CTHDDao();
        try {
            stmt = getConnect().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                var maPhieu = rs.getString("MA_PHIEU");
                var heSo = Double.valueOf(rs.getString("HE_SO"))  ;
                var maSeri = rs.getString("MA_SERIES");
                var tienSach = temp.tinhTienSach(maPhieu,maSeri);
                var tenSach = rs.getString("TEN_SACH");
                dsct.add(new CTHD( maPhieu,  heSo, maSeri,tienSach,tenSach));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return dsct;
    }
    public ArrayList<CTHD> getDsCTHD(String ma) {
        String sql = "SELECT SELL_TICKET_DETAILS.*,BOOK.TEN_SACH FROM `SELL_TICKET_DETAILS` INNER JOIN BOOK on BOOK.MA_SERIES = SELL_TICKET_DETAILS.MA_SERIES WHERE MA_PHIEU = '"+ma+"'";
        return CTHDDao.getDs(sql);
    }

    public List<String> getMaHD(String ma){
        List<CTHD> dsct = getDsCTHD(ma);
        Set<String> maHDSet = new HashSet<>();
        List<String> dsid = new ArrayList<>();
        for(CTHD ct:dsct){
            dsid.add(ct.getMa_phieu());
            maHDSet.add(ct.getMa_phieu());
        }
        return  new ArrayList<>(maHDSet);
    }
    public List<String> getMaSeries(String ma){
        List<CTHD> dsct = getDsCTHD(ma);
        List<String> dsid = new ArrayList<>();
        for(CTHD ct:dsct){
            dsid.add(ct.getMa_series());
        }
        return dsid;
    }

    public List<Double> getHeSo(String ma){
        String sql= "SELECT DISTINCT HE_SO FROM SELL_TICKET_DETAILS  WHERE  MA_PHIEU = "+ma;

        List<Double> dshs = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = getConnect().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                var heSo = Double.valueOf(rs.getString("HE_SO"))  ;

                dshs.add(heSo);
            }
        } catch (SQLException | ClassNotFoundException e) {
            return dshs;
        }
        return dshs;
    }


    public List<CTHD> locCTHD(String id,String maSeri){
        String sql = "select SELL_TICKET_DETAILS.*,BOOK.TEN_SACH from SELL_TICKET_DETAILS  INNER JOIN BOOK on BOOK.MA_SERIES = SELL_TICKET_DETAILS.MA_SERIES   WHERE  MA_PHIEU = '"+id +"' and SELL_TICKET_DETAILS.MA_SERIES = '"+maSeri+"'";
        return CTHDDao.getDs(sql);
    }

    public List<CTHD> locHeSo(String id,double hs){
        String sql = "select SELL_TICKET_DETAILS.*,BOOK.TEN_SACH from SELL_TICKET_DETAILS  INNER JOIN BOOK on BOOK.MA_SERIES = SELL_TICKET_DETAILS.MA_SERIES   WHERE  MA_PHIEU = '"+id+"' and HE_SO = "+hs;
        return CTHDDao.getDs(sql);
    }
    public List<CTHD> locMaSeri(String id,String ma){
        String sql = "select SELL_TICKET_DETAILS.*,BOOK.TEN_SACH from SELL_TICKET_DETAILS  INNER JOIN BOOK on BOOK.MA_SERIES = SELL_TICKET_DETAILS.MA_SERIES   WHERE  MA_PHIEU = "+id+" and SELL_TICKET_DETAILS.MA_SERIES = '"+ma+"'";
        return CTHDDao.getDs(sql);
    }


    public int removeCTHD(String maHD,String maSeri){
        String sql = "DELETE FROM SELL_TICKET_DETAILS WHERE MA_PHIEU = ? and MA_SERIES  = ?";
        int smt = 0;
        PreparedStatement pst = null;
        try {
            pst = getConnect().prepareStatement(sql);
            pst.setString(1,maHD);
            pst.setString(2,maSeri);
            smt = pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return smt;
    }

    public int insertCTHD(CTHD cthd){
        String sql = "INSERT INTO SELL_TICKET_DETAILS(`MA_PHIEU`, `HE_SO`, `MA_SERIES`,`IS_DELETED`) VALUES (?,?,?,?)";
        int smt=0;
        PreparedStatement pst = null;
        try {
            pst = getConnect().prepareStatement(sql);
            pst.setString(1,cthd.getMa_phieu());
            pst.setDouble(2, cthd.getHe_so());
            pst.setString(3,cthd.getMa_series());
            pst.setInt(4,0);
            smt = pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return smt;
    }

    public int updateCTHD(CTHD cthd){
        String sql = "UPDATE SELL_TICKET_DETAILS SET `MA_PHIEU`=?,`HE_SO`=?,`MA_SERIES`=? WHERE MA_PHIEU=? AND MA_SERIES=?";
        int smt = 0;
        PreparedStatement pst = null;
        try {
            pst = getConnect().prepareStatement(sql);
            pst.setString(1, cthd.getMa_phieu());
            pst.setDouble(2,cthd.getHe_so());
            pst.setString(3,cthd.getMa_series());
            pst.setString(4,cthd.getMa_phieu());
            pst.setString(5,cthd.getMa_series());
            smt = pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return smt;
    }

    public int changeTrangThaiSach(String maSeri,String tt){
        String sql = "UPDATE `BOOK` SET `TRANG_THAI`=? WHERE MA_SERIES = ? ";
        int smt = 0;
        PreparedStatement pst = null;
        try {
            pst = getConnect().prepareStatement(sql);
            pst.setString(1,tt);
            pst.setString(2, maSeri);
            smt = pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return smt;
    }

    public int hiddenCTHD(String maHD){
        String sql = "UPDATE `sell_ticket_details` SET `IS_DELETED`=1 WHERE MA_PHIEU = ?";
        int smt = 0;
        PreparedStatement pst = null;
        try {
            pst = getConnect().prepareStatement(sql);
            pst.setString(1,maHD);
            smt = pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return smt;
    }

    public long tinhTienSach(String maHD,String maSeri){
        String sql = "SELECT CAST(BOOK.GIA AS UNSIGNED) * SELL_TICKET_DETAILS.HE_SO as total FROM SELL_TICKET_DETAILS " +
                "INNER JOIN BOOK on SELL_TICKET_DETAILS.MA_SERIES = BOOK.MA_SERIES " +
                "WHERE MA_PHIEU = '"+maHD+"' and BOOK.MA_SERIES = '"+maSeri+"'";
        Statement stmt = null;
        long total = 0;
        try {
            stmt = getConnect().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                total = rs.getLong("total");
            }
        } catch (SQLException | ClassNotFoundException e) {
            return 0;
        }
        return total;
    }

    public long layGiaSach(String maSeri){
        String sql ="SELECT GIA FROM `BOOK` WHERE MA_SERIES = '"+maSeri+"'";
        Statement stmt = null;
        long total = 0;
        try {
            stmt = getConnect().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                total = rs.getLong("GIA");
            }
        } catch (SQLException | ClassNotFoundException e) {
            return 0;
        }
        return total;

    }

    public String goiYTenSach(String maSeri){
        String sql ="SELECT TEN_SACH FROM BOOK WHERE BOOK.TRANG_THAI = 'AVAILABLE' and BOOK.MA_SERIES = '"+maSeri+"'";
        Statement stmt = null;
        String tenSach = "";
        try {
            stmt = getConnect().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                tenSach = rs.getString("TEN_SACH");
                break;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return tenSach;

    }

    public DefaultCategoryDataset thongKeSLGSachBanTheoThang(){
        String sql = "SELECT  COUNT(book.MA_SERIES) as slgSach,YEAR(CREATED_AT) as nam, MONTH(CREATED_AT) as thang \n" +
                "FROM `sell_ticket_details`\n" +
                "INNER JOIN book on book.MA_SERIES = sell_ticket_details.MA_SERIES\n" +
                "INNER JOIN sell_ticket on sell_ticket.MA_PHIEU=sell_ticket_details.MA_PHIEU\n" +
                "WHERE sell_ticket_details.IS_DELETED =0\n" +
                "GROUP BY sell_ticket_details.MA_PHIEU,YEAR(sell_ticket.CREATED_AT), MONTH(sell_ticket.CREATED_AT)\n" +
                "ORDER BY YEAR(sell_ticket.CREATED_AT) ASC, MONTH(sell_ticket.CREATED_AT) ASC";
        Statement stmt = null;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try {
            stmt = getConnect().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                double slg = rs.getDouble("slgSach");
                String thang = rs.getString("thang");
                String nam = rs.getString("nam");
                String thangNam=thang+"/"+nam;
                dataset.setValue(slg,"Slg Sach",thangNam);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return dataset;
    }

    public DefaultCategoryDataset thongKeSachBanTheoNam(int nam){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String sql ="SELECT COUNT(sell_ticket_details.MA_SERIES) as slgSach,Month(sell_ticket.CREATED_AT) as thang FROM `sell_ticket_details` \n" +
                "INNER JOIN sell_ticket on sell_ticket_details.MA_PHIEU=sell_ticket.MA_PHIEU \n" +
                "INNER JOIN book on book.MA_SERIES = sell_ticket_details.MA_SERIES \n" +
                "WHERE Year(sell_ticket.CREATED_AT) = "+nam+" and sell_ticket_details.IS_DELETED = 0 \n" +
                "GROUP BY Year(sell_ticket.CREATED_AT),thang";
        Statement stmt = null;
        String rowKey = "slgSach";
        double[] slgSach = new double[12];
        try {
            stmt = getConnect().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                double slg = rs.getDouble("slgSach");
                int thang = rs.getInt("thang");
                slgSach[thang-1] = slg;
            }
            for(int i=0;i<12;i++){
                int thang = i + 1;
                double slg = slgSach[i];
                System.out.println("Tháng " + thang + "/" + nam + ": " + slg + " hóa đơn");
                String thangNam = thang+"/"+nam;
                dataset.setValue(slg,rowKey,thangNam);
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }

        return dataset;
    }

    public DefaultPieDataset thongKeSoLoaiSach(){
        DefaultPieDataset dataset = new DefaultPieDataset();
        String sql ="SELECT  COUNT(book_genre.MA_SERIES) as slgSach,genre.TEN_TL as tl " +
                "FROM `sell_ticket_details` " +
                "INNER JOIN book on book.MA_SERIES = sell_ticket_details.MA_SERIES " +
                "INNER JOIN sell_ticket on sell_ticket.MA_PHIEU=sell_ticket_details.MA_PHIEU " +
                "INNER JOIN book_genre on book.MA_SERIES = book_genre.MA_SERIES\n" +
                "INNER JOIN genre on book_genre.MA_TL = genre.MA_TL " +
                "WHERE sell_ticket_details.IS_DELETED =0 " +
                "GROUP BY book_genre.MA_TL";
        Statement stmt = null;
        try {
            stmt = getConnect().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                double slg = rs.getDouble("slgSach");
                String tl = rs.getString("tl");
                dataset.setValue(tl,slg);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return dataset;

    }



    public static void main(String[] args) {
        CTHDDao t = new CTHDDao();
//        List<CTHD> dsct = t.locCTHD("1","1_1");
//        for(CTHD ct:dsct){
//            System.out.println(ct.getHe_so());
//        }
//        List<Double> ds = t.getHeSo("1");
//        for(Double h:ds){
//            System.out.println(h);
//        }

//        CTHD cthd = new CTHD();
//        cthd.setMa_chiTiet("20");
//        cthd.setHe_so(2);
//        cthd.setMa_series("5");
//        cthd.setMa_phieu("5");
//        int smt = t.removeCTHD("20");
//        if(smt>0){
//            System.out.println("Hello");
//        }
//        else{
//            System.out.println("hmm");
//        }

//        System.out.println(t.tinhTienSach("1","2"));
//        System.out.println(t.goiYTenSach("1"));
//        System.out.println(t.layGiaSach("2"));

        DefaultCategoryDataset dataset = t.thongKeSachBanTheoNam(1900);
        System.out.println("Hoa Don \t Thang \t So Luong");
        for (int i = 0; i < dataset.getRowCount(); i++) {
            String rowKey = (String) dataset.getRowKey(i);
            for (int j = 0; j < dataset.getColumnCount(); j++) {
                String columnKey = (String) dataset.getColumnKey(j);
                Number value = dataset.getValue(i, j);
                System.out.println(rowKey + " \t " + columnKey + " \t " + value);
            }
        }


    }

}
