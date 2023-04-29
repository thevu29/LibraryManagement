package Borrow.BUS;

import Borrow.DAO.BorrowDetailDAO;
import Borrow.DTO.BorrowDetail;

import java.util.ArrayList;

public class BorrowDetailBUS {
    public static BorrowDetailDAO dao = new BorrowDetailDAO();

    public String getNameBook(String maSach){
        return BorrowDetailDAO.getNameBook(maSach);
    }

    public Long getPriceBook(String maSach){
        return BorrowDetailDAO.getPriceBook(maSach);
    }

    public double getTienTamTinh(Long giaSach, int soNgayMuon){
        return dao.getTienTamTinh(giaSach,soNgayMuon);
    }

    public int insert(BorrowDetail borrowDetail){
        return dao.insert(borrowDetail);
    }

    public int update(BorrowDetail borrowDetail,String maSachTruoc){
        return dao.update(borrowDetail,maSachTruoc);
    }

    public int getGiaGiam(String maThe){
        return dao.getGiaGiam(maThe);
    }

    public int delete(String maPhieu,String maSach){
        return dao.delete(maPhieu,maSach);
    }

    public ArrayList<String> getDsMaSach(){
        return dao.getDsMaSach();
    }
    public ArrayList<BorrowDetail> getDsMuonCT(String maPhieuMuon){
        return dao.getDsMuonCT(maPhieuMuon);
    }
}
