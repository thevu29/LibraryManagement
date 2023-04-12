package NhanVien.DAO;

import Book.DTO.Book;
import Core.DefaultConnection;
import NhanVien.DTO.nhanVien;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class NhanVienDAO extends DefaultConnection {

    public ArrayList<nhanVien> getAllFromDatabase(){
        ArrayList<nhanVien> NV = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employee");
            while (rs.next()) {

                var id = rs.getString("MA_NV");
                var name = rs.getString("TEN");
                var SDT = rs.getString("PHONE");
                var PASSWORD = rs.getString("PASSWORD");
                var CV = rs.getInt("CHUC_VU");
                var Luong = rs.getInt("LUONG");
                var NLV = rs.getInt("NOI_LAM_VIEC");
                var Ca = rs.getInt("CA");
                var GT = rs.getInt("GIOI_TINH");
                var NL = rs.getInt("SO_NGAY_LAM_VIEC");
                var NS = rs.getString("NGAY_SINH");
                var email = rs.getString("Email");
                var dc = rs.getString("DIA_CHI");


                NV.add(new nhanVien(id,name,SDT,NS,dc,email,PASSWORD,Ca,CV,GT,NLV,NL,Luong));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Không thể kết nối");
//            return books;
        }
        return NV;
    }

}
