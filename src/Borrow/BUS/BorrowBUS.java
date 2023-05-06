package Borrow.BUS;

import Borrow.DAO.BorrowDAO;
import Borrow.DTO.Borrow;
import Borrow.DTO.Fault;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

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

    public int submitBorrow(String id){
        return dao.submitBorrow(id);
    }


    public DefaultCategoryDataset laySoLieuPhieuMuon(int nam){
        return dao.laySoLuongPhieuMuon(nam);
    }

    public int tongSoPhieuMuon(int nam){
        DefaultCategoryDataset dataset = dao.laySoLuongPhieuMuon(nam);
        int tong  = 0;
        for (int i = 0; i < dataset.getRowCount(); i++) {
            for (int j = 0; j < dataset.getColumnCount(); j++) {
                String columnKey = (String) dataset.getColumnKey(j);
                Number value = dataset.getValue(i, j);
                System.out.println(" \t " + columnKey + " \t " + value);
                tong+=value.intValue();
            }
        }
        return tong;
    }

    public DefaultPieDataset thongKeLoi(int nam){
        return dao.thongKeLoiSach(nam);
    }
    public DefaultCategoryDataset thongKeSachMuon(int nam){
        return dao.laySoLuongSachMuon(nam);
    }

    public int delete(String id){
        return dao.delete(id);
    }

    public static void main(String[] args) {
        BorrowBUS bus = new BorrowBUS();
        bus.tongSoPhieuMuon(2023);
    }
}
