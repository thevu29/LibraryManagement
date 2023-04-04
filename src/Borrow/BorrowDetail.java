package Borrow;

import Book.Book;
import Book.EBookStatus;

import java.util.ArrayList;

public class BorrowDetail {
    private String id;
    private String maPhieuMuon;
    private String maNhanVien;
    private String maSach;
    private String maNvxn;

    public BorrowDetail(String id, String maPhieuMuon, String maNhanVien, String maSach, String maNvxn) {
        this.id = id;
        this.maPhieuMuon = maPhieuMuon;
        this.maNhanVien = maNhanVien;
        this.maSach = maSach;
        this.maNvxn = maNvxn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public void setMaPhieuMuon(String maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getMaNvxn() {
        return maNvxn;
    }

    public void setMaNvxn(String maNvxn) {
        this.maNvxn = maNvxn;
    }
}
