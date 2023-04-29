package Borrow.BUS;

import Borrow.DAO.BorrowDAO;
import Borrow.DTO.Borrow;
import Borrow.DTO.Fault;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BorrowBUS {
    public static BorrowDAO dao = new BorrowDAO();

    public ArrayList<Borrow> getDsMuon(){
        return dao.getDsMuon();
    }
    public String getNewPM(){
        return dao.getNewMaPhieuMuon();
    }

    public String getNameMember(String maThe){
        return dao.getNameMember(maThe);
    }

    public ArrayList<String> getDsMaThe(){
        return dao.getDsMaThe();
    }

    public int add(String id,String maThe,String maNhanVien,int soNgayMuon){
        return dao.add(id,maThe,maNhanVien,soNgayMuon);
    }

    public int updateField(String id,String maThe,int soNgayMuon){
        return dao.updateField(id,maThe,soNgayMuon);
    }





    public int delete(String id){
        return dao.delete(id);
    }
}
