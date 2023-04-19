package Borrow.DTO;

import java.util.ArrayList;

public class FaultDetail {
    static int priceCounter = 0;
    private String maChiTiet;
    private String maLoi;
    private String tenDocGia;
    private String tenSach;
    private String tenLoi;
    private String soLuong;
    private String tienDen;

    public FaultDetail(String maChiTiet, String maLoi, String tenDocGia, String tenSach, String tenLoi, String soLuong, String tienDen) {
        this.maChiTiet = maChiTiet;
        this.maLoi = maLoi;
        this.tenDocGia = tenDocGia;
        this.tenSach = tenSach;
        this.tenLoi = tenLoi;
        this.soLuong = soLuong;
        this.tienDen = tienDen;
    }

    public static int getPriceCounter() {
        return priceCounter;
    }

    public static void setPriceCounter(int priceCounter) {
        FaultDetail.priceCounter = priceCounter;
    }

    public String getMaChiTiet() {
        return maChiTiet;
    }

    public void setMaChiTiet(String maChiTiet) {
        this.maChiTiet = maChiTiet;
    }

    public String getMaLoi() {
        return maLoi;
    }

    public void setMaLoi(String maLoi) {
        this.maLoi = maLoi;
    }

    public String getTenDocGia() {
        return tenDocGia;
    }

    public void setTenDocGia(String tenDocGia) {
        this.tenDocGia = tenDocGia;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTenLoi() {
        return tenLoi;
    }

    public void setTenLoi(String tenLoi) {
        this.tenLoi = tenLoi;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getTienDen() {
        return tienDen;
    }

    public void setTienDen(String tienDen) {
        this.tienDen = tienDen;
    }

    public static FaultDetail createTestBook() {
        ArrayList<String> authors = new ArrayList<>();

        return new FaultDetail(String.valueOf(priceCounter), "maloi"+priceCounter,"docgia"+priceCounter,
                "tensach"+priceCounter,"tenLoi"+priceCounter,"soluong"+priceCounter,"tienden"+priceCounter++);
    }
}
