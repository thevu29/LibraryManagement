package BookFault;

import Book.DTO.Book;

import java.util.ArrayList;

public class Fault {
    static int priceCounter = 0;
    private String id;
    private String tenLoi;
    private double heSo;

    public Fault(){

    }

    public Fault(String id, String tenLoi, double heSo) {
        this.id = id;
        this.tenLoi = tenLoi;
        this.heSo = heSo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenLoi() {
        return tenLoi;
    }

    public void setTenLoi(String tenLoi) {
        this.tenLoi = tenLoi;
    }

    public double getHeSo() {
        return heSo;
    }

    public void setHeSo(double heSo) {
        this.heSo = heSo;
    }

    public static Fault createTestBook() {
        ArrayList<String> authors = new ArrayList<>();

        return new Fault(String.valueOf(priceCounter), "Loi "+priceCounter,priceCounter++);
    }
}
