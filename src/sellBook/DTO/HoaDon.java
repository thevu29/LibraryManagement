package sellBook.DTO;

import java.util.Date;

public class HoaDon {
    private String ma_phieu;
    private String ma_nv;
    private String ma_KH;
    private String tenNV;
    private String tenKH;
    private double tongHD;
    private Date createdAt;

    public HoaDon() {
    }

    public HoaDon(String ma_phieu, String ma_nv, String ma_KH, String tenNV, String tenKH,double tongHD) {
        this.ma_phieu = ma_phieu;
        this.ma_nv = ma_nv;
        this.ma_KH = ma_KH;
        this.tenNV = tenNV;
        this.tenKH = tenKH;
        this.tongHD = tongHD;
    }

    public HoaDon(String ma_phieu, String ma_nv, String ma_KH, String tenNV, String tenKH, double tongHD, Date createdAt) {
        this.ma_phieu = ma_phieu;
        this.ma_nv = ma_nv;
        this.ma_KH = ma_KH;
        this.tenNV = tenNV;
        this.tenKH = tenKH;
        this.tongHD = tongHD;
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    //    public HoaDon(String ma_phieu, String ma_nv, String ma_KH, Long tongHD) {
//        this.ma_phieu = ma_phieu;
//        this.ma_nv = ma_nv;
//        this.ma_KH = ma_KH;
//        this.tongHD = tongHD;
//    }

    public String getMa_phieu() {
        return ma_phieu;
    }

    public void setMa_phieu(String ma_phieu) {
        this.ma_phieu = ma_phieu;
    }

    public String getMa_nv() {
        return ma_nv;
    }

    public void setMa_nv(String ma_nv) {
        this.ma_nv = ma_nv;
    }


    public String getMa_KH() {
        return ma_KH;
    }

    public void setMa_KH(String ma_KH) {
        this.ma_KH = ma_KH;
    }

    public double getTongHD() {
        return tongHD;
    }

    public void setTongHD(double tongHD) {
        this.tongHD = tongHD;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }
}
