package BUS;

import DAO.BorrowDetailDAO;
import DAO.FaultDetailDAO;
import DTO.FaultDetail;

import java.util.ArrayList;

public class FaultDetailBUS {
    public static FaultDetailDAO dao = new FaultDetailDAO();

    public String getNameBook(String maSach){
        return BorrowDetailDAO.getNameBook(maSach);
    }

    public String getTenLoi(String maLoi){
        return FaultDetailDAO.getTenLoi(maLoi);
    }

    public int addField(String maPhieu,String maSach,String maLoi,int soLuong){
        return dao.addField(maPhieu,maSach,maLoi,soLuong);
    }

    public int updateField(String maSachTruoc,String maLoiTruoc,String maPhieu,String maSach,String maLoi,int soLuong){
        return dao.updateField(maSachTruoc,maLoiTruoc,maPhieu,maSach,maLoi,soLuong);
    }

    public int deleteField(String maPhieu,String maSach,String maLoi){
        return dao.deleteField(maPhieu,maSach,maLoi);
    }
    public double getTongTien(String maLoi,String maSach,int soLuong){
        return dao.getTongTien(maLoi,maSach,soLuong);
    }
    public ArrayList<String> getDsMaSach(String maPhieu){
        return dao.getDsMaSach(maPhieu);
    }

    public ArrayList<String> getDsMaLoi(){
        return dao.getDsMaLoi();
    }
    public ArrayList<FaultDetail> getDsLoiCT(String maPhieuMuon){
        return dao.getDsLoiCT(maPhieuMuon);
    }
}
