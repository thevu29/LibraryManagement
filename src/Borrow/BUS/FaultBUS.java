package Borrow.BUS;

import Borrow.DAO.FaultDAO;
import Borrow.DTO.Fault;
import java.util.ArrayList;

public class FaultBUS {
    private FaultDAO faultDAO;

    public FaultBUS() {
        faultDAO = new FaultDAO();
    }
    public ArrayList<Fault> getDsLoi(){
        return faultDAO.getDsLoi();
    }
//    public List<String> getAllMaHD(){
//        return hd.getMaHD();
//    }
//    public List<String> getAllMaNV(){
//        return hd.getMaNV();
//    }
//    public List<String> getAllMaKH(){
//        return hd.getMaKH();
//    }
//
//    public List<HoaDon> filterMaHD(String maHD){
//        return hd.locMaHD(maHD);
//    }
//    public List<HoaDon> filterMaNV(String maNV){
//        return hd.locMaNV(maNV);
//    }
//    public List<HoaDon> filterMaKH(String maKH){
//        return hd.locMaKH(maKH);
//    }
//
    public int insert(Fault fault){
        return faultDAO.insert(fault);
    }
    public int remove(String id){
        return faultDAO.remove(id);
    }
    public int update(Fault fault){
        return faultDAO.update(fault);
    }
    public static void main(String[] args) {
        FaultBUS faultBUS = new FaultBUS();
        ArrayList<Fault> ds = faultBUS.getDsLoi();
        System.out.println("Du lieu");
        for (Fault fault: ds) {
            System.out.println(fault.getTenLoi());
        }
    }
}
