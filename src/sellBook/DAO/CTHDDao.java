package sellBook.DAO;

import Core.DefaultConnection;
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


    public int removeCTHD(String maCTHD){
        String sql = "DELETE FROM SELL_TICKET_DETAILS WHERE MA_PHIEU = "+maCTHD;
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

    public int insertCTHD(CTHD cthd){
        String sql = "INSERT INTO SELL_TICKET_DETAILS(`MA_PHIEU`, `HE_SO`, `MA_SERIES`) VALUES (?,?,?)";
        int smt=0;
        PreparedStatement pst = null;
        try {
            pst = getConnect().prepareStatement(sql);
            pst.setString(1,cthd.getMa_phieu());
            pst.setDouble(2, cthd.getHe_so());
            pst.setString(3,cthd.getMa_series());
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
        System.out.println(t.layGiaSach("2"));
    }

}
