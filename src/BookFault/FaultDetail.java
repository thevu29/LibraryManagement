package BookFault;

import java.util.ArrayList;

public class FaultDetail {
    static int priceCounter = 0;
    private String maLoiChiTiet;
    private String maLoi;
    private String tenDocGia;
    private String tenSach;
    private String tenLoi;
    private String soLuong;
    private String tienDen;

    public FaultDetail(String maLoiChiTiet, String maLoi, String tenDocGia, String tenSach, String tenLoi, String soLuong, String tienDen) {
        this.maLoiChiTiet = maLoiChiTiet;
        this.maLoi = maLoi;
        this.tenDocGia = tenDocGia;
        this.tenSach = tenSach;
        this.tenLoi = tenLoi;
        this.soLuong = soLuong;
        this.tienDen = tienDen;
    }

    public String getMaLoiChiTiet() {
        return maLoiChiTiet;
    }

    public void setMaLoiChiTiet(String maLoiChiTiet) {
        this.maLoiChiTiet = maLoiChiTiet;
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
