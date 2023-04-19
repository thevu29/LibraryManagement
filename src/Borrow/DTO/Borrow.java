package Borrow.DTO;

import java.util.ArrayList;

public class Borrow {
    static int priceCounter = 0;
    private String id;
    private String tenNhanVien;
    private String tenDocGia;
    private String ngayMuon;
    private String ngayHenTra;
    private String ngayTra;
    private String tongTien;

    public Borrow(String id, String tenNhanVien, String tenDocGia, String ngayMuon, String ngayHenTra, String ngayTra, String tongTien) {
        this.id = id;
        this.tenNhanVien = tenNhanVien;
        this.tenDocGia = tenDocGia;
        this.ngayMuon = ngayMuon;
        this.ngayHenTra = ngayHenTra;
        this.ngayTra = ngayTra;
        this.tongTien = tongTien;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getTenDocGia() {
        return tenDocGia;
    }

    public void setTenDocGia(String tenDocGia) {
        this.tenDocGia = tenDocGia;
    }

    public String getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(String ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public String getNgayHenTra() {
        return ngayHenTra;
    }

    public void setNgayHenTra(String ngayHenTra) {
        this.ngayHenTra = ngayHenTra;
    }

    public String getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(String ngayTra) {
        this.ngayTra = ngayTra;
    }

    public String getTongTien() {
        return tongTien;
    }

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }

    public static int getPriceCounter() {
        return priceCounter;
    }

    public static void setPriceCounter(int priceCounter) {
        Borrow.priceCounter = priceCounter;
    }



    public static Borrow createTestBook() {
        ArrayList<String> authors = new ArrayList<>();

        return new Borrow(String.valueOf(priceCounter),"NhanVien "+priceCounter,"Doc gia "+priceCounter,"Ngaymuon"+priceCounter,"Ngay hen tra"+priceCounter,"ngayTra"+priceCounter,
                "Tong tien "+priceCounter++);
    }
}
