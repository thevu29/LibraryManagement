package Borrow;

import Book.DTO.Book;
import Book.EBookStatus;

import java.util.ArrayList;

public class BorrowDetail {
    static int priceCounter = 0;
    private String id;
    private String maPhieuMuon;
    private String tenSach;
    private String tenLoi;
    private String soLuong;
    private String giaTien;

    public BorrowDetail(String id, String maPhieuMuon, String tenSach, String tenLoi, String soLuong, String giaTien) {
        this.id = id;
        this.maPhieuMuon = maPhieuMuon;
        this.tenSach = tenSach;
        this.tenLoi = tenLoi;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
    }

    public static int getPriceCounter() {
        return priceCounter;
    }

    public static void setPriceCounter(int priceCounter) {
        BorrowDetail.priceCounter = priceCounter;
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

    public String getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(String giaTien) {
        this.giaTien = giaTien;
    }

    public static BorrowDetail createTestBook() {

        return new BorrowDetail(String.valueOf(priceCounter),"Ma phieu "+priceCounter,"Tensach"+priceCounter,
                "tenloi"+priceCounter,"soluong","giatien"+priceCounter++);
    }
}
