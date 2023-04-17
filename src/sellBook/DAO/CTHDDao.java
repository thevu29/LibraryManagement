package sellBook.DAO;

import Core.DefaultConnection;
import sellBook.DTO.CTHD;
import sellBook.DTO.HoaDon;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CTHDDao extends DefaultConnection {

    public static ArrayList<CTHD> getDs(String sql){
        ArrayList<CTHD> dsct = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = getConnect().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                var maPhieu = rs.getString("MA_PHIEU");
                var heSo = Double.valueOf(rs.getString("HE_SO"))  ;
                var maSeri = rs.getString("MA_SERIES");

                dsct.add(new CTHD( maPhieu,  heSo, maSeri));
            }
        } catch (SQLException | ClassNotFoundException e) {
            return dsct;
        }
        return dsct;
    }
    public ArrayList<CTHD> getDsCTHD(String ma) {
        String sql = "SELECT * FROM SELL_TICKET_DETAILS WHERE MA_PHIEU = '"+ma+"'";
        return CTHDDao.getDs(sql);
    }

    public List<String> getMaCTHD(String ma){
        List<CTHD> dsct = getDsCTHD(ma);
        List<String> dsid = new ArrayList<>();
        for(CTHD ct:dsct){
            dsid.add(ct.getMa_chiTiet());
        }
        return dsid;
    }

    public List<String> getMaHD(String ma){
        List<CTHD> dsct = getDsCTHD(ma);
        List<String> dsid = new ArrayList<>();
        for(CTHD ct:dsct){
            dsid.add(ct.getMa_phieu());
        }
        return dsid;
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


    public List<CTHD> locCTHD(String id,String ma){
        String sql = "select * from SELL_TICKET_DETAILS  WHERE  MA_PHIEU = "+id+" and MA_CHITIET = "+ma;
        return CTHDDao.getDs(sql);
    }
    public List<CTHD> locHeSo(String id,double hs){
        String sql = "select * from SELL_TICKET_DETAILS  WHERE  MA_PHIEU = "+id+" and HE_SO = "+hs;
        return CTHDDao.getDs(sql);
    }
    public List<CTHD> locMaSeri(String id,String ma){
        String sql = "select * from SELL_TICKET_DETAILS  WHERE  MA_PHIEU = "+id+" and MA_SERIES = "+ma;
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

    public static void main(String[] args) {
        CTHDDao t = new CTHDDao();
//        List<CTHD> dsct = t.locHeSo("1",3);
//        for(CTHD ct:dsct){
//            System.out.println(ct.getHe_so());
//        }
//        List<Double> ds = t.getHeSo("1");
//        for(Double h:ds){
//            System.out.println(h);
//        }

        CTHD cthd = new CTHD();
        cthd.setMa_chiTiet("20");
        cthd.setHe_so(2);
        cthd.setMa_series("5");
        cthd.setMa_phieu("5");
        int smt = t.removeCTHD("20");
        if(smt>0){
            System.out.println("Hello");
        }
        else{
            System.out.println("hmm");
        }
    }
}
