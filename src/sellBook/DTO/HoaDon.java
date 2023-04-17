package sellBook.DTO;

import java.util.Date;

public class HoaDon {
    private String ma_phieu;
    private String ma_nv;
    private String ma_KH;

    public HoaDon() {
    }

    public HoaDon(String ma_phieu, String ma_nv,  String ma_KH) {
        this.ma_phieu = ma_phieu;
        this.ma_nv = ma_nv;
        this.ma_KH = ma_KH;
    }

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
}
