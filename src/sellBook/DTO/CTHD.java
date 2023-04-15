package sellBook.DTO;

public class CTHD {
    private String ma_chiTiet;
    private String ma_phieu;
    private double he_so;
    private String ma_series;

    public CTHD() {
    }

    public CTHD(String ma_chiTiet, String ma_phieu, double he_so, String ma_series) {
        this.ma_chiTiet = ma_chiTiet;
        this.ma_phieu = ma_phieu;
        this.he_so = he_so;
        this.ma_series = ma_series;
    }

    public String getMa_chiTiet() {
        return ma_chiTiet;
    }

    public void setMa_chiTiet(String ma_chiTiet) {
        this.ma_chiTiet = ma_chiTiet;
    }

    public String getMa_phieu() {
        return ma_phieu;
    }

    public void setMa_phieu(String ma_phieu) {
        this.ma_phieu = ma_phieu;
    }

    public double getHe_so() {
        return he_so;
    }

    public void setHe_so(double he_so) {
        this.he_so = he_so;
    }

    public String getMa_series() {
        return ma_series;
    }

    public void setMa_series(String ma_series) {
        this.ma_series = ma_series;
    }
}
