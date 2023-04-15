package sellBook;

public class CTHDModel {
    private int idHD;
    private String tenSach;
    private int soLuong;
    private long gia;
    private String trangThai;
    public static int totalId = 0;

    public CTHDModel(int idHD, String tenSach, int soLuong, long gia, String trangThai) {
        this.idHD = idHD;
        this.tenSach = tenSach;
        this.soLuong = soLuong;
        this.gia = gia;
        this.trangThai = trangThai;
    }

    public int getIdHD() {
        return idHD;
    }

    public void setIdHD(int idHD) {
        this.idHD = idHD;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public long getGia() {
        return gia;
    }

    public void setGia(long gia) {
        this.gia = gia;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public static CTHDModel createTest(){
        return new CTHDModel(totalId++,"huy ne",3,10000,"moi");
    }

    public CTHDModel clone(){
        try{
            CTHDModel clone = (CTHDModel)  super.clone();
            return clone;
        }
        catch (CloneNotSupportedException e){
            throw new AssertionError();
        }
    }

}
