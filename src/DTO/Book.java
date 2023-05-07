package DTO;

import java.util.ArrayList;
import java.util.HashSet;

public class Book implements Cloneable {

    static int priceCounter = 0;
    private String id;
    private String name;
    private ArrayList<BookAuthor> authors;
    private BookPublisher publisher;
    private ArrayList<BookGenre> genre;
    private String location;
    private long price;
    private EBookStatus bookStatus;
    private String language;
    private String description;

    private int publishYear;
    private int totalPage;
    private BookImporter importer;

    public Book(String id, String name, ArrayList<BookAuthor> authors, BookPublisher publisher,
                ArrayList<BookGenre> genre, String location, long price, String bookStatus, String language,
                String description, int publishYear, int totalPage, BookImporter importer) {
        this.id = id;
        this.name = name;
        this.authors = authors;
        this.publisher = publisher;
        this.genre = genre;
        this.location = location;
        this.price = price;
        setBookStatus(bookStatus);
        this.language = language;
        this.description = description;
        this.publishYear = publishYear;
        this.totalPage = totalPage;
        this.importer = importer;
    }

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

    public ArrayList<BookAuthor> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<BookAuthor> authors) {
        this.authors = authors;
    }

    public void setAuthor(int index, String id,String name) {
        authors.get(index).setId(id.strip());
        authors.get(index).setName(name.strip());
    }

    public BookPublisher getPublisher() {
        return publisher;
    }

    public void setPublisher(BookPublisher publisher) {
        this.publisher = publisher;
    }

    public ArrayList<BookGenre> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<BookGenre> genre) {
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
        try {
            this.price = Long.parseLong(price);
        }
        catch (NumberFormatException ex)  {
            setPrice(0);
        }
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
        } else if (bookStatus.equals(String.valueOf(EBookStatus.MISSING))) {
            this.bookStatus = EBookStatus.MISSING;
        } else if (bookStatus.equals(String.valueOf(EBookStatus.IN_USE))) {
            this.bookStatus = EBookStatus.IN_USE;
        } else if (bookStatus.equals(String.valueOf(EBookStatus.BORROWED))) {
            this.bookStatus = EBookStatus.BORROWED;
        } else if (bookStatus.equals(String.valueOf(EBookStatus.AVAILABLE))) {
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

    public static Book createTestBook() {
        ArrayList<BookAuthor> authors = new ArrayList<>();
        authors.add(new BookAuthor("1", "My"));
        authors.add(new BookAuthor("2", "Real"));

        var publisher = new BookPublisher("3", "Nep");

        ArrayList<BookGenre> genre = new ArrayList<>();
        genre.add(new BookGenre("5", "GG"));
        genre.add(new BookGenre("6", "WP"));

        return new Book(String.valueOf(priceCounter),
                "HelloA" + priceCounter,
                authors, publisher, genre, "Nep",
                priceCounter++, String.valueOf(EBookStatus.AVAILABLE), "English", "Ok", 2003, 10, new BookImporter("", ""));
    }

    public static Book createBlankBook() {
        ArrayList<BookAuthor> authors = new ArrayList<>();
        authors.add(new BookAuthor("", ""));
        ArrayList<BookGenre> genre = new ArrayList<>();
        genre.add(new BookGenre("", ""));
        return new Book("", "", authors, new BookPublisher("", ""), genre, "", -1,
                String.valueOf(EBookStatus.AVAILABLE), "", "", -1, -1, new BookImporter("", ""));
    }

    @Override
    public Book clone() {
        try {
            Book clone = (Book) super.clone();
            clone.authors = (ArrayList<BookAuthor>) this.authors.clone();
            clone.genre = (ArrayList<BookGenre>) this.genre.clone();
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
        this.location = book.location;
        this.price = book.price;
        this.bookStatus = book.bookStatus;
        this.language = book.language;
        this.description = book.description;
        this.publishYear = book.publishYear;
        this.totalPage = book.totalPage;
        this.importer = book.importer;

        cloneGenre(book.genre);
        cloneAuthor(book.authors);
    }

    public void cloneGenre(ArrayList<BookGenre> genres) {
        HashSet<String> seen = new HashSet<>();
        genres.removeIf(bookGenre -> !seen.add(bookGenre.getId()));
        this.genre = genres;
    }

    public void cloneAuthor(ArrayList<BookAuthor> authors) {
        HashSet<String> seen = new HashSet<>();
        authors.removeIf(author -> !seen.add(author.getId()));
        this.authors = authors;
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

    public int getPublishYear() {
        return publishYear;
    }


    public void setPublishYear(String publishYear) {
        try {
            setPublishYear(Integer.parseInt(publishYear));
        } catch (NumberFormatException ignored) {
            setPublishYear(-1);
        }
    }
    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public int getTotalPage() {
        return totalPage;
    }


    public void setTotalPage(String totalPage) {
        try {
            this.totalPage = Integer.parseInt(totalPage);
        }
        catch (Exception ex) {
            this.totalPage = 0;
        }
    }
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public BookImporter getImporter() {
        return importer;
    }

    public void setImporter(BookImporter importer) {
        this.importer = importer;
    }

    public void setImporter(String id, String name) {
        this.importer.setId(id);
        this.importer.setName(name);
    }

    public void setPublisher(String id, String name) {
        publisher.setId(id);
        publisher.setName(id);
    }
}
