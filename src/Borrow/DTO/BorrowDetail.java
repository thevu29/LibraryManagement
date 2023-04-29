package Borrow.DTO;

import Borrow.BUS.BorrowBUS;

public class BorrowDetail {
    private String maPhieu;
    private String maSach;
    private String tenSach;
    private double tienTamTinh;
    private double tienTong;

    public BorrowDetail(String maPhieu, String maSach, String tenSach, double tienTamTinh, double tienTong) {
        this.maPhieu = maPhieu;
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tienTamTinh = tienTamTinh;
        this.tienTong = tienTong;
    }

    public String getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
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

    public double getTienTamTinh() {
        return tienTamTinh;
    }

    public void setTienTamTinh(double tienTamTinh) {
        this.tienTamTinh = tienTamTinh;
    }

    public double getTienTong() {
        return tienTong;
    }

    public void setTienTong(double tienTong) {
        this.tienTong = tienTong;
    }
}
