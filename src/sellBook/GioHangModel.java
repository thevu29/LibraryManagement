package sellBook;

import java.util.ArrayList;

public class GioHangModel implements Cloneable{
    private int id;
    private String tenSach;
    private int solm;
    private long gia;
    private ArrayList<String> nxb;
    private ArrayList<Integer> idTacGia;
    private ArrayList<Integer> idTheLoai;
    private ArrayList<String> tacGia;
    private ArrayList<String> theLoai;



    public GioHangModel(int id, String tenSach, int solm, long gia, ArrayList<String> nxb, ArrayList<String> tacGia, ArrayList<String> theLoai) {
        this.id = id;
        this.tenSach = tenSach;
        this.solm = solm;
        this.gia = gia;
        this.nxb = nxb;
        this.tacGia = tacGia;
        this.theLoai = theLoai;
    }

    public static int totalId = 0;

    public static GioHangModel createTest(){
        ArrayList<String> authors = new ArrayList<>();
        authors.add("My");
        authors.add("Real");

        ArrayList<String> publisher = new ArrayList<>();
        publisher.add("Nep");
        publisher.add("Tune");

        ArrayList<String> genre = new ArrayList<>();
        genre.add("Xin chàp");
        genre.add("Wp");
        return new GioHangModel(totalId++,"Sach "+totalId,1,3,publisher,authors,genre);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getSolm() {
        return solm;
    }

    public void setSolm(int solm) {
        this.solm = solm;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(long gia) {
        this.gia = gia;
    }

    public static int getTotalId() {
        return totalId;
    }

    public ArrayList<String> getNxb() {
        return nxb;
    }

    public void setNxb(ArrayList<String> nxb) {
        this.nxb = nxb;
    }

    public ArrayList<Integer> getIdTacGia() {
        return idTacGia;
    }

    public void setIdTacGia(ArrayList<Integer> idTacGia) {
        this.idTacGia = idTacGia;
    }

    public ArrayList<Integer> getIdTheLoai() {
        return idTheLoai;
    }

    public void setIdTheLoai(ArrayList<Integer> idTheLoai) {
        this.idTheLoai = idTheLoai;
    }

    public ArrayList<String> getTacGia() {
        return tacGia;
    }

    public void setTacGia(ArrayList<String> tacGia) {
        this.tacGia = tacGia;
    }

    public ArrayList<String> getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(ArrayList<String> theLoai) {
        this.theLoai = theLoai;
    }

    public static void setTotalId(int totalId) {
        GioHangModel.totalId = totalId;
    }

    public GioHangModel clone(){
        try{
            GioHangModel clone = (GioHangModel) super.clone();
            return clone;
        }
        catch (CloneNotSupportedException e){
            throw new AssertionError();
        }

    }
}
