package DAO;

import Core.DefaultConnection;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import DTO.CTHD;
import DTO.HDPDF;

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
        String sql= "SELECT DISTINCT HE_SO FROM SELL_TICKET_DETAILS  WHERE  MA_PHIEU = '"+ma+"'";

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
        String sql = "select SELL_TICKET_DETAILS.*,BOOK.TEN_SACH from SELL_TICKET_DETAILS  INNER JOIN BOOK on BOOK.MA_SERIES = SELL_TICKET_DETAILS.MA_SERIES   WHERE  MA_PHIEU = '"+id+"' and SELL_TICKET_DETAILS.MA_SERIES = '"+ma+"'";
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
        String sql = "UPDATE `SELL_TICKET_DETAILS` SET `IS_DELETED`=1 WHERE MA_PHIEU = ?";
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

    public double tinhTienSach(String maHD,String maSeri){
        String sql = "SELECT  ROUND(CAST(BOOK.GIA AS UNSIGNED) * (1.0 - SELL_TICKET_DETAILS.HE_SO), 2)  as total FROM SELL_TICKET_DETAILS " +
                "INNER JOIN BOOK on SELL_TICKET_DETAILS.MA_SERIES = BOOK.MA_SERIES " +
                "WHERE MA_PHIEU = '"+maHD+"' and BOOK.MA_SERIES = '"+maSeri+"'";
        Statement stmt = null;
        double total = 0;
        try {
            stmt = getConnect().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                total = rs.getDouble("total");
            }
        } catch (SQLException | ClassNotFoundException e) {
            return 0;
        }
        return total;
    }

    public double layGiaSach(String maSeri){
        String sql ="SELECT GIA FROM `BOOK` WHERE MA_SERIES = '"+maSeri+"'";
        Statement stmt = null;
        double total = 0;
        try {
            stmt = getConnect().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                total = rs.getDouble("GIA");
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
        String sql = "SELECT  COUNT(`BOOK`.MA_SERIES) as slgSach,YEAR(CREATED_AT) as nam, MONTH(CREATED_AT) as thang \n" +
                "FROM `SELL_TICKET_DETAILS`\n" +
                "INNER JOIN `BOOK` on `BOOK`.MA_SERIES = SELL_TICKET_DETAILS.MA_SERIES\n" +
                "INNER JOIN SELL_TICKET on SELL_TICKET.MA_PHIEU=SELL_TICKET_DETAILS.MA_PHIEU\n" +
                "WHERE SELL_TICKET_DETAILS.IS_DELETED =0\n" +
                "GROUP BY SELL_TICKET_DETAILS.MA_PHIEU,YEAR(SELL_TICKET.CREATED_AT), MONTH(SELL_TICKET.CREATED_AT)\n" +
                "ORDER BY YEAR(SELL_TICKET.CREATED_AT) ASC, MONTH(SELL_TICKET.CREATED_AT) ASC";
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
        String sql ="SELECT COUNT(SELL_TICKET_DETAILS.MA_SERIES) as slgSach,Month(SELL_TICKET.CREATED_AT) as thang FROM `SELL_TICKET_DETAILS` \n" +
                "INNER JOIN SELL_TICKET on SELL_TICKET_DETAILS.MA_PHIEU=SELL_TICKET.MA_PHIEU \n" +
                "INNER JOIN `BOOK` on `BOOK`.MA_SERIES = SELL_TICKET_DETAILS.MA_SERIES \n" +
                "WHERE Year(SELL_TICKET.CREATED_AT) = "+nam+" and SELL_TICKET_DETAILS.IS_DELETED = 0 \n" +
                "GROUP BY Year(SELL_TICKET.CREATED_AT),thang";
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
        String sql ="SELECT  COUNT(`BOOK_GENRE`.MA_SERIES) as slgSach,`GENRE`.TEN_TL as tl " +
                "FROM `SELL_TICKET_DETAILS` " +
                "INNER JOIN `BOOK` on `BOOK`.MA_SERIES = SELL_TICKET_DETAILS.MA_SERIES " +
                "INNER JOIN SELL_TICKET on SELL_TICKET.MA_PHIEU=SELL_TICKET_DETAILS.MA_PHIEU " +
                "INNER JOIN `BOOK_GENRE` on `BOOK`.MA_SERIES = `BOOK_GENRE`.MA_SERIES\n" +
                "INNER JOIN `GENRE` on `BOOK_GENRE`.MA_TL = `GENRE`.MA_TL " +
                "WHERE SELL_TICKET_DETAILS.IS_DELETED =0 " +
                "GROUP BY `BOOK_GENRE`.MA_TL";
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



    public List<HDPDF> xuatCTHDPDF(String maHD){
        String sql = "SELECT ROUND(CAST(`BOOK`.GIA AS UNSIGNED) *(1.0 - SELL_TICKET_DETAILS.HE_SO),2) as total," +
                "`BOOK`.TEN_SACH,`BOOK`.MA_SERIES,`SELL_TICKET_DETAILS`.HE_SO " +
                "FROM `SELL_TICKET_DETAILS` " +
                "INNER JOIN `BOOK` on `BOOK`.MA_SERIES = `SELL_TICKET_DETAILS`.MA_SERIES " +
                "WHERE `SELL_TICKET_DETAILS`.IS_DELETED = 0 and `SELL_TICKET_DETAILS`.MA_PHIEU = '"+maHD+"'";
        List<HDPDF> dsCTHD = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = getConnect().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                var heSo = Double.valueOf(rs.getString("HE_SO"));
                var maSeri = rs.getString("MA_SERIES");
                var tienSach = rs.getDouble("total");
                var tenSach = rs.getString("TEN_SACH");
                dsCTHD.add(new HDPDF(maHD,maSeri,tenSach, heSo,tienSach));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return dsCTHD;
    }

    public double layHeSo(String maSach){
        String sql = "SELECT SUM(`BOOK_FAULT`.HE_SO*`BORROW_BOOK_TICKET_FAULT`.SO_LUONG) as tongHS \n" +
                "FROM `BORROW_BOOK_TICKET_FAULT` \n" +
                "INNER JOIN `BOOK_FAULT` on `BORROW_BOOK_TICKET_FAULT`.MA_LOI = `BOOK_FAULT`.MA_LOI  \n" +
                "WHERE MA_SERIES = '"+maSach+"' \n" +
                "GROUP BY `BORROW_BOOK_TICKET_FAULT`.MA_SERIES";
        Statement stmt = null;
        double hs = 0 ;
        try {
            stmt = getConnect().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                hs = rs.getDouble("tongHS");
                break;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        if(hs>0.5){
            return 0.5;
        }
        return hs;
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

//        DefaultCategoryDataset dataset = t.thongKeSachBanTheoNam(1900);
//        System.out.println("Hoa Don \t Thang \t So Luong");
//        for (int i = 0; i < dataset.getRowCount(); i++) {
//            String rowKey = (String) dataset.getRowKey(i);
//            for (int j = 0; j < dataset.getColumnCount(); j++) {
//                String columnKey = (String) dataset.getColumnKey(j);
//                Number value = dataset.getValue(i, j);
//                System.out.println(rowKey + " \t " + columnKey + " \t " + value);
//            }
//        }


        List<HDPDF> ds = t.xuatCTHDPDF("HD2");
        for(HDPDF hd:ds){
            System.out.println(hd.getGiaSach());
        }

    }

}
