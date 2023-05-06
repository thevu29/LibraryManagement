package sellBook.DTO;

public class HDPDF {
    private String maHD;
    private String maSach;
    private String  tenSach;
    private double heSo;
    private double giaSach;

    public HDPDF() {
    }

    public HDPDF(String maHD, String maSach, String tenSach, double heSo, double giaSach) {
        this.maHD = maHD;
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.heSo = heSo;
        this.giaSach = giaSach;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
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

    public double getHeSo() {
        return heSo;
    }

    public void setHeSo(double heSo) {
        this.heSo = heSo;
    }

    public double getGiaSach() {
        return giaSach;
    }

    public void setGiaSach(double giaSach) {
        this.giaSach = giaSach;
    }
}
