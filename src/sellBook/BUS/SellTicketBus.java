package sellBook.BUS;

import sellBook.DAO.SellTicketDao;
import sellBook.DTO.HoaDon;

import java.util.List;

public class SellTicketBus {
    private SellTicketDao hd;
    private CTHDBus cthdBus = new CTHDBus();

    public SellTicketBus() {
        hd =new SellTicketDao();
    }
    public List<HoaDon> getAllSellTicket(){
        return hd.getDsHoaDon();
    }
    public List<String> getAllMaHD(){
        return hd.getMaHD();
    }
    public List<String> getAllMaNV(){
        return hd.getMaNV();
    }
    public List<String> getAllMaKH(){
        return hd.getMaKH();
    }

    public List<HoaDon> filterMaHD(String maHD){
        return hd.locMaHD(maHD);
    }
    public List<HoaDon> filterMaNV(String maNV){
        return hd.locMaNV(maNV);
    }
    public List<HoaDon> filterMaKH(String maKH){
        return hd.locMaKH(maKH);
    }

    public int insert(HoaDon bill){
        return hd.insertHD(bill);
    }
    public int remove(String id){
        int smt = 1;
//        smt = hd.removeHD(id);
        if(smt>0){
            cthdBus.xoaHD(id);
        }
        else{
          System.out.println("Co Loi Khi Xoa Hoa Don");
        }
        return smt ;
    }
    public int update(HoaDon bill){
        return hd.updateHD(bill);
    }

    public String goiYNameKH(String maKH){
        return hd.goiYTenKH(maKH);
    }
    public String getNewMaHD(){
        return hd.getNewMaHD();
    }
    public static void main(String[] args) {

    }
}
