package DTO;

import java.util.ArrayList;

public class Borrow {
    private String id;
    private String ma_nv_muon;

    private String tenNhanVienMuon;
    private String ma_nv_tra;

    private String tenNhanVienTra;
    private String ma_the;

    private String tenDocGia;
    private String ngayMuon;
    private String ngayHenTra;
    private String ngayTra;

    private double tongTienPhat;
    private double tongTienMuon;

    public Borrow(String id, String ma_nv_muon, String tenNhanVienMuon, String ma_nv_tra, String tenNhanVienTra, String ma_the, String tenDocGia, String ngayMuon, String ngayHenTra, String ngayTra, double tongTienPhat, double tongTienMuon) {
        this.id = id;
        this.ma_nv_muon = ma_nv_muon;
        this.tenNhanVienMuon = tenNhanVienMuon;
        this.ma_nv_tra = ma_nv_tra;
        this.tenNhanVienTra = tenNhanVienTra;
        this.ma_the = ma_the;
        this.tenDocGia = tenDocGia;
        this.ngayMuon = ngayMuon;
        this.ngayHenTra = ngayHenTra;
        this.ngayTra = ngayTra;
        this.tongTienPhat = tongTienPhat;
        this.tongTienMuon = tongTienMuon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMa_nv_muon() {
        return ma_nv_muon;
    }

    public void setMa_nv_muon(String ma_nv_muon) {
        this.ma_nv_muon = ma_nv_muon;
    }

    public String getTenNhanVienMuon() {
        return tenNhanVienMuon;
    }

    public void setTenNhanVienMuon(String tenNhanVienMuon) {
        this.tenNhanVienMuon = tenNhanVienMuon;
    }

    public String getMa_nv_tra() {
        return ma_nv_tra;
    }

    public void setMa_nv_tra(String ma_nv_tra) {
        this.ma_nv_tra = ma_nv_tra;
    }

    public String getTenNhanVienTra() {
        return tenNhanVienTra;
    }

    public void setTenNhanVienTra(String tenNhanVienTra) {
        this.tenNhanVienTra = tenNhanVienTra;
    }

    public String getMa_the() {
        return ma_the;
    }

    public void setMa_the(String ma_the) {
        this.ma_the = ma_the;
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

    public double getTongTienPhat() {
        return tongTienPhat;
    }

    public void setTongTienPhat(double tongTienPhat) {
        this.tongTienPhat = tongTienPhat;
    }

    public double getTongTienMuon() {
        return tongTienMuon;
    }

    public void setTongTienMuon(double tongTienMuon) {
        this.tongTienMuon = tongTienMuon;
    }
}
