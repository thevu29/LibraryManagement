package Borrow.DAO;

import Borrow.DTO.BorrowDetail;
import Borrow.DTO.Fault;
import Borrow.GUI.BorrowDetailInfo;
import Core.DefaultConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BorrowDetailDAO extends DefaultConnection {
    public static ArrayList<BorrowDetail> getDanhSach(String sql){
        ArrayList<BorrowDetail> dsMuonCT = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = getConnect().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                var maPhieu = rs.getString("MA_PHIEU");
                var maSeri = rs.getString("MA_SERIES");
                var tenSach = getTenSach(maSeri);
                var tienTamTinh = Double.parseDouble(rs.getString("TIEN_TAM_TINH")==null? "0" :rs.getString("TIEN_TAM_TINH"));
                var tongTien = Double.parseDouble(rs.getString("TIEN_TONG")==null? "0" :rs.getString("TIEN_TONG"));

                dsMuonCT.add(new BorrowDetail(maPhieu,maSeri,tenSach,tienTamTinh,tongTien));
            }
        } catch (SQLException | ClassNotFoundException e) {
            return dsMuonCT;
        }
        return dsMuonCT;
    }


    public  ArrayList<BorrowDetail> getDsMuonCT(String maPhieuMuon) {
        String sql = "SELECT * FROM borrow_ticket_details WHERE MA_PHIEU = '"+maPhieuMuon+"' ";
        return getDanhSach(sql);
    }


    public static String getTenSach(String maSeri){
        String sql = "SELECT TEN_SACH from book WHERE  MA_SERIES = '"+maSeri+"'";


        Statement stmt = null;

        try {
            stmt = getConnect().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String tenSach = "" ;
            while(rs.next()){
                tenSach = rs.getString("TEN_SACH");
            }
            if(tenSach == null){
                return "";
            }
            return tenSach;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public ArrayList<String>  getDsMaSach(){
        ArrayList<String> dsTen = new ArrayList<String>();
        String sql = "select MA_SERIES from book where is_deleted = 0s";
        Statement st = null;

        try {
            st = getConnect().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){
                dsTen.add(rs.getString("MA_SERIES"));
            }

        }catch (SQLException | ClassNotFoundException ex){
            return dsTen;
        }

        return dsTen;
    }

    public static String getNameBook(String maSach){
        String sql = "SELECT TEN_SACH from book WHERE  MA_SERIES = '"+maSach+"'";


        Statement stmt = null;

        try {
            stmt = getConnect().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String name = "" ;
            while(rs.next()){
                name = rs.getString("TEN_SACH");
            }
            if(name == null){
                return "";
            }
            return name;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Long getPriceBook(String maSach){
        String sql = "SELECT GIA from book WHERE  MA_SERIES = '"+maSach+"'";

        Statement stmt = null;

        try {
            stmt = getConnect().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            String price = "" ;
            while(rs.next()){
                price = rs.getString("GIA");
            }
            if(price == null){
                return 0l;
            }
            return Long.parseLong(price);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public double getTienTamTinh(Long giaSach, int soNgayMuon){
        return giaSach * soNgayMuon * 0.05;
    }

    public int getGiaGiam(String maThe){
        String sql = "SELECT GIA_GIAM from membership,membership_type WHERE  membership.DANG_THE = membership_type.DANG_THE" +
                " and MA_THE = '"+maThe+"'";

        Statement stmt = null;

        try {
            stmt = getConnect().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int heSo = 0;
            while(rs.next()){
                heSo = rs.getInt("GIA_GIAM");
            }

            return heSo;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public int insert(BorrowDetail borrowDetail){
        String sql ="INSERT INTO `borrow_ticket_details`(`MA_PHIEU`, `MA_SERIES`, `TIEN_TAM_TINH`,`TIEN_TONG` ) VALUES (?,?,?,?)";
        int smt=0;
        PreparedStatement pst = null;
        try {
            pst = getConnect().prepareStatement(sql);
            pst.setString(1, borrowDetail.getMaPhieu());
            pst.setString(2, borrowDetail.getMaSach());
            pst.setString(3, borrowDetail.getTienTamTinh()+"");
            pst.setString(4, borrowDetail.getTienTong()+"");
            smt = pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return smt;
    }

    public int update(BorrowDetail borrowDetail,String maSachTruoc){
        String sql = "UPDATE `borrow_ticket_details` SET MA_SERIES=?,TIEN_TAM_TINH=?,TIEN_TONG=? WHERE MA_PHIEU=? and MA_SERIES = ?";
        int smt = 0;

        PreparedStatement pst = null;
        try {
            pst = getConnect().prepareStatement(sql);
            pst.setString(1, borrowDetail.getMaSach());
            pst.setString(2, borrowDetail.getTienTamTinh()+"");
            pst.setString(3, borrowDetail.getTienTong()+"");
            pst.setString(4, borrowDetail.getMaPhieu());
            pst.setString(5, maSachTruoc);
            smt = pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return smt;
    }

    public int delete(String maPhieu,String maSach){
        String sql = "delete from `borrow_ticket_details` WHERE MA_PHIEU=? and MA_SERIES = ?";
        int smt = 0;

        PreparedStatement pst = null;
        try {
            pst = getConnect().prepareStatement(sql);
            pst.setString(1,maPhieu);
            pst.setString(2,maSach);
            smt = pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return smt;
    }


    public static void main(String[] args) {
        BorrowDetailDAO dao = new BorrowDetailDAO();
        System.out.println(BorrowDetailDAO.getPriceBook("1_1"));
    }
}
