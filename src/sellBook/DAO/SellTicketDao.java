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
        ArrayList<HoaDon> dshd = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = getConnect().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                var maPhieu = rs.getString("MA_PHIEU");
                var maNv = rs.getString("MA_NV");
                var maKh = rs.getString("MA_KH");

                dshd.add(new HoaDon(maPhieu, maNv,  maKh));
            }
        } catch (SQLException | ClassNotFoundException e) {
            return dshd;
        }
        return dshd;
    }


    public  ArrayList<HoaDon> getDsHoaDon() {
        String sql = "SELECT * FROM SELL_TICKET";
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
            maKHSet.add(hd.getMa_nv());
        }
        return new ArrayList<>(maKHSet);
    }

    public List<HoaDon> locMaHD(String ma){
        String sql = "SELECT * FROM sell_ticket where MA_PHIEU = "+ma;
        return getDs(sql);
    }
    public List<HoaDon> locMaNV(String ma){
        String sql = "SELECT * FROM sell_ticket where MA_NV = "+ma;
        return getDs(sql);
    }
    public List<HoaDon> locMaKH(String ma){
        String sql = "SELECT * FROM sell_ticket where MA_KH = "+ma;
        return getDs(sql);
    }

    public int insertHD(HoaDon hd){
        String sql ="INSERT INTO `SELL_TICKET`(`MA_PHIEU`, `MA_NV`, `MA_KH`) VALUES (?,?,?)";
        int smt=0;
        PreparedStatement pst = null;
        try {
            pst = getConnect().prepareStatement(sql);
            pst.setString(1, hd.getMa_phieu());
            pst.setString(2,hd.getMa_nv());
            pst.setString(3,hd.getMa_KH());
            smt = pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return smt;
    }

    public int removeHD(String id){
        String sql = "DELETE FROM `SELL_TICKET` WHERE MA_PHIEU = "+id;
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

    public static void main(String[] args) {
        SellTicketDao t = new SellTicketDao();
//        List<HoaDon> dshd  = t.getDsHoaDon();
//        for (HoaDon hd : dshd) {
//             System.out.println(hd.getMa_nv());
//
//        }

//        List<String> ds = t.getMaKH();
//        for(String nv:ds){
//            System.out.println(nv);
//        }

        HoaDon hd = new HoaDon("21","2","5");
        int smt = t.updateHD(hd);
        if(smt>0){
            System.out.println("Hello Thanh Cong");
        }
        else{
            System.out.println("Khong thanh");
        }

    }
}
