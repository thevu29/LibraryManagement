package Borrow.DTO;

import java.util.ArrayList;

public class FaultDetail {
    private String maPhieuMuon;
    private String maSach;
    private String tenSach;
    private String maLoi;
    private String tenLoi;
    private int soLuong;
    private double tongTien;

    public FaultDetail(String maPhieuMuon, String maSach, String tenSach, String maLoi, String tenLoi, int soLuong, double tongTien) {
        this.maPhieuMuon = maPhieuMuon;
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.maLoi = maLoi;
        this.tenLoi = tenLoi;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
    }

    public String getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public void setMaPhieuMuon(String maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getMaLoi() {
        return maLoi;
    }

    public void setMaLoi(String maLoi) {
        this.maLoi = maLoi;
    }

    public String getTenLoi() {
        return tenLoi;
    }

    public void setTenLoi(String tenLoi) {
        this.tenLoi = tenLoi;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }
}
