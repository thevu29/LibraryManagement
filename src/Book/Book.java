package Book;

import java.util.ArrayList;

public class Book implements Cloneable {

    static int priceCounter = 0;
    private String id;
    private String name;
    private ArrayList<String> authors;
    private ArrayList<String> publisher;
    private ArrayList<String> genre;
    private String location;
    private long price;
    private EBookStatus bookStatus;
    private String language;
    private String description;


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

    public void setPrice(String price) {
        this.price = Long.parseLong(price);
    }

    public EBookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(EBookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        if (bookStatus.equals(String.valueOf(EBookStatus.SOLD))) {
            this.bookStatus = EBookStatus.SOLD;
        }
        else if (bookStatus.equals(String.valueOf(EBookStatus.MISSING))) {
            this.bookStatus = EBookStatus.MISSING;
        }
        else if (bookStatus.equals(String.valueOf(EBookStatus.IN_USE))) {
            this.bookStatus = EBookStatus.IN_USE;
        }
        else if (bookStatus.equals(String.valueOf(EBookStatus.BORROWED))) {
            this.bookStatus = EBookStatus.BORROWED;
        }
        else if (bookStatus.equals(String.valueOf(EBookStatus.AVAILABLE))) {
            this.bookStatus = EBookStatus.AVAILABLE;
        }
    }

    public static int getPriceCounter() {
        return priceCounter;
    }

    public static void setPriceCounter(int priceCounter) {
        Book.priceCounter = priceCounter;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Book(String id, String name, ArrayList<String> authors, ArrayList<String> publisher, ArrayList<String> genre, String location, long price, EBookStatus bookStatus, String language) {
        this.id = id;
        this.name = name;
        this.authors = authors;
        this.publisher = publisher;
        this.genre = genre;
        this.location = location;
        this.price = price;
        this.bookStatus = bookStatus;
        this.language = language;
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

        return new Book(String.valueOf(priceCounter), "HelloA"+priceCounter, authors, publisher, genre, "Nep", priceCounter++, EBookStatus.AVAILABLE, "English");
    }


    @Override
    public Book clone() {
        try {
            Book clone = (Book) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public void cloneFrom(Book book) {
        this.id = book.id;
        this.name = book.name;
        this.authors = book.authors;
        this.publisher = book.publisher;
        this.genre = book.genre;
        this.location = book.location;
        this.price = book.price;
        this.bookStatus = book.bookStatus;
        this.description = book.description;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", authors=" + authors +
                ", publisher=" + publisher +
                ", genre=" + genre +
                ", location='" + location + '\'' +
                ", price=" + price +
                ", bookStatus=" + bookStatus +
                '}';
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
