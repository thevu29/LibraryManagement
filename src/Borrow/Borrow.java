package Borrow;

import Book.Book;
import Book.EBookStatus;

import java.util.ArrayList;

public class Borrow {
    private String id;
    private String maNhanVien;
    private String maDocGia;
    private String maChiTiet;
    private Long tienTamTinh;
    private Long tienTong;
    private String ngayMuon;
    private String ngayTra;

    public Borrow(String id, String maNhanVien, String maDocGia, String maChiTiet, Long tienTamTinh, Long tienTong,
            String ngayMuon, String ngayTra) {
        this.id = id;
        this.maNhanVien = maNhanVien;
        this.maDocGia = maDocGia;
        this.maChiTiet = maChiTiet;
        this.tienTamTinh = tienTamTinh;
        this.tienTong = tienTong;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getMaDocGia() {
        return maDocGia;
    }

    public void setMaDocGia(String maDocGia) {
        this.maDocGia = maDocGia;
    }

    public String getMaChiTiet() {
        return maChiTiet;
    }

    public void setMaChiTiet(String maChiTiet) {
        this.maChiTiet = maChiTiet;
    }

    public Long getTienTamTinh() {
        return tienTamTinh;
    }

    public void setTienTamTinh(Long tienTamTinh) {
        this.tienTamTinh = tienTamTinh;
    }

    public Long getTienTong() {
        return tienTong;
    }

    public void setTienTong(Long tienTong) {
        this.tienTong = tienTong;
    }

    public String getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(String ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public String getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(String ngayTra) {
        this.ngayTra = ngayTra;
    }
}
