package Book.DAO;

import Book.DTO.Book;
import Book.DTO.BookAuthor;
import Book.DTO.BookGenre;
import Book.DTO.BookPublisher;
import Core.DefaultConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BookDAO extends DefaultConnection {
    public ArrayList<Book> getAllFromDatabase() {
        ArrayList<Book> books = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK");
            while (rs.next()) {

                var id = rs.getString("MA_SERIES");
                var name = rs.getString("TEN_SACH");
                var location = rs.getString("VI_TRI");
                var price = rs.getLong("GIA");
                var bookStatus = rs.getString("TRANG_THAI");
                var totalPage = rs.getInt("TONG_SO_TRANG");
                var publishYear = rs.getInt("NAM_XUAT_BAN");
                var language = rs.getString("NGON_NGU");
                var description = rs.getString("MO_TA");
                stmt = getConnection().createStatement();
                var authors = getAuthors(stmt, id);
                stmt = getConnection().createStatement();
                var publisher = getPublishers(stmt, id);
                stmt = getConnection().createStatement();
                var genre = getGenre(stmt, id);

                books.add(new Book(id, name, authors, publisher, genre, location, price, bookStatus, language,
                        description, publishYear, totalPage));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    private static ArrayList<BookGenre> getGenre(Statement stmt, String id) throws SQLException {
        ResultSet genreRS = stmt.executeQuery(
                "SELECT * FROM GENRE GE JOIN BOOK_GENRE BG on GE.MA_TL = BG.MA_TL WHERE BG.MA_SERIES='" + id + "'");
        ArrayList<BookGenre> genres = new ArrayList<>();
        while (genreRS.next()) {
            var genreID = genreRS.getString("MTG");
            var genreName = genreRS.getString("TEN_TG");

            genres.add(new BookGenre(genreID, genreName));
        }
        return genres;
    }

    private static ArrayList<BookPublisher> getPublishers(Statement stmt, String id) throws SQLException {
        ResultSet publisherRS = stmt.executeQuery(
                "SELECT * FROM PUBLISHER PU JOIN BOOK_PUBLISHER BP on PU.MA_NXB = BP.MA_NXB WHERE MA_SERIES='" + id + "'");
        ArrayList<BookPublisher> publishers = new ArrayList<>();
        while (publisherRS.next()) {
            var publisherID = publisherRS.getString("MTG");
            var publisherName = publisherRS.getString("TEN_TG");

            publishers.add(new BookPublisher(publisherID, publisherName));
        }
        return publishers;
    }

    private static ArrayList<BookAuthor> getAuthors(Statement stmt, String id) throws SQLException {
        ResultSet authorRS = stmt.executeQuery(
                "SELECT AUTHOR.MA_TG as MTG, TEN_TG FROM AUTHOR JOIN BOOK_AUTHOR BA on AUTHOR.MA_TG = BA.MA_TG WHERE MA_SERIES='" + id + "'");
        ArrayList<BookAuthor> authors = new ArrayList<>();
        while (authorRS.next()) {
            var authorID = authorRS.getString("MTG");
            var authorName = authorRS.getString("TEN_TG");

            authors.add(new BookAuthor(authorID, authorName));
        }
        return authors;
    }
}
