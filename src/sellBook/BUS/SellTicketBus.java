package sellBook.BUS;

import sellBook.DAO.SellTicketDao;
import sellBook.DTO.HoaDon;

import java.util.List;

public class SellTicketBus {
    private SellTicketDao hd;

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
        return  hd.removeHD(id);
    }
    public int update(HoaDon bill){
        return hd.updateHD(bill);
    }

    public String goiYNameKH(String maKH){
        return hd.goiYTenKH(maKH);
    }
    public static void main(String[] args) {

    }
}
