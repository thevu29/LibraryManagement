package Book;

import java.util.ArrayList;
import java.util.List;

public class Book {

    static int priceCounter = 0;
    private String id;
    private String name;
    private ArrayList<String> authors;
    private ArrayList<String> publisher;
    private ArrayList<String> genre;
    private String location;
    private long price;
    private EBookStatus bookStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }

    public ArrayList<String> getPublisher() {
        return publisher;
    }

    public void setPublisher(ArrayList<String> publisher) {
        this.publisher = publisher;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public EBookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(EBookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public Book(String id, String name, ArrayList<String> authors, ArrayList<String> publisher, ArrayList<String> genre, String location, long price, EBookStatus bookStatus) {
        this.id = id;
        this.name = name;
        this.authors = authors;
        this.publisher = publisher;
        this.genre = genre;
        this.location = location;
        this.price = price;
        this.bookStatus = bookStatus;
    }

    public static Book createTestBook() {
        ArrayList<String> authors = new ArrayList<>();
        authors.add("My");
        authors.add("Real");

        ArrayList<String> publisher = new ArrayList<>();
        publisher.add("Nep");
        publisher.add("Tune");

        ArrayList<String> genre = new ArrayList<>();
        genre.add("Xin chàp");
        genre.add("Wp");

        return new Book(String.valueOf(priceCounter), "HelloA"+priceCounter, authors, publisher, genre, "Nep", priceCounter++, EBookStatus.AVAILABLE);
    }
}
