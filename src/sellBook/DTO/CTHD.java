package sellBook.DTO;

public class CTHD {
    private String ma_phieu;
    private double he_so;
    private String ma_series;
    private double tienSach;
    private String tenSach;

    public CTHD() {
    }

    public CTHD(String ma_phieu, double he_so, String ma_series) {
        this.ma_phieu = ma_phieu;
        this.he_so = he_so;
        this.ma_series = ma_series;
    }

//    public CTHD(String ma_phieu, double he_so, String ma_series,long tienSach) {
//        this.ma_phieu = ma_phieu;
//        this.he_so = he_so;
//        this.ma_series = ma_series;
//        this.tienSach = tienSach;
//    }

    public CTHD(String ma_phieu, double he_so, String ma_series, double tienSach, String tenSach) {
        this.ma_phieu = ma_phieu;
        this.he_so = he_so;
        this.ma_series = ma_series;
        this.tienSach = tienSach;
        this.tenSach = tenSach;
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

    public double getTienSach() {
        return tienSach;
    }

    public void setTienSach(double tienSach) {
        this.tienSach = tienSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }
}
