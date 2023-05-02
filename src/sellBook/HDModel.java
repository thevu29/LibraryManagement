package sellBook;

import java.util.ArrayList;
import java.util.List;

public class HDModel implements Cloneable{
    private int id;
    private long gia;
    private String tenKH;
    private String ngayMua;
    private String tinhTrangHD;
    private ArrayList<String> dsSach;
    public static int totalId = 0;

    public HDModel(int id, long gia, String tenKH, String ngayMua, String tinhTrangHD, ArrayList<String> dsSach) {
        this.id = id;
        this.gia = gia;
        this.tenKH = tenKH;
        this.ngayMua = ngayMua;
        this.tinhTrangHD = tinhTrangHD;
        this.dsSach = dsSach;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getGia() {
        return gia;
    }

    public void setGia(long gia) {
        this.gia = gia;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(String ngayMua) {
        this.ngayMua = ngayMua;
    }

    public String getTinhTrangHD() {
        return tinhTrangHD;
    }

    public void setTinhTrangHD(String tinhTrangHD) {
        this.tinhTrangHD = tinhTrangHD;
    }

    public ArrayList<String> getDsSach() {
        return dsSach;
    }

    public void setDsSach(ArrayList<String> dsSach) {
        this.dsSach = dsSach;
    }

    public static HDModel createTest(){
        ArrayList<String> dsSach = new ArrayList<>();
        dsSach.add("Hello");
        dsSach.add("Huy");
        dsSach.add("Haha");
        return new HDModel(totalId++,500000,"Huy Ne Hihi"+totalId,"9/12/2003","Dang Duyet",dsSach);
    }
    public HDModel clone(){
        try{
            HDModel clone = (HDModel)  super.clone();
            return clone;
        }
        catch (CloneNotSupportedException e){
            throw new AssertionError();
        }

    }
}
