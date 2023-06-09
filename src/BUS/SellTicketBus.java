package BUS;

import org.jfree.data.category.DefaultCategoryDataset;
import DAO.SellTicketDao;
import DTO.HoaDon;

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
        int smt = 0 ;
        smt = hd.removeHD(id);
        cthdBus.xoaHD(id);
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


    public DefaultCategoryDataset laySoLieuTheoNam(){
        return hd.laySoLieuTheoNam();
    }
    public DefaultCategoryDataset thongKeSoTienTheoThang(int nam){
        return hd.thongKeThuNhapTheoThang(nam);
    }

    public DefaultCategoryDataset thongKeTheoNam(int nam){
        double[] slgHDs = hd.thongKeTheoNam(nam);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String rowKey = "Hoa Don";
        for (int i = 0; i < 12; i++) {
            int thang = i + 1;
            double slgHD = slgHDs[i];
            System.out.println("Tháng " + thang + "/" + nam + ": " + slgHD + " hóa đơn");
            String thangNam = thang+"/"+nam;
            dataset.setValue(slgHD,rowKey,thangNam);

        }
        return dataset;

    }



    public static void main(String[] args) {

    }
}
