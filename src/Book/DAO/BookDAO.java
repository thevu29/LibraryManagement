package Book.DAO;

import Book.DTO.Book;
import Book.DTO.BookAuthor;
import Book.DTO.BookGenre;
import Book.DTO.BookPublisher;
import Core.DefaultConnection;

import java.sql.PreparedStatement;
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM BOOK WHERE IS_DELETED=0");
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
            System.out.println("Không thể kết nối");
            return books;
        }
        return books;
    }

    public boolean isIDDuplicate(String id) {
        PreparedStatement stmt = null;
        try {
            stmt = getConnection().prepareStatement("SELECT MA_SERIES FROM BOOK WHERE MA_SERIES=?");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static ArrayList<BookGenre> getGenre(Statement stmt, String id) throws SQLException {
        ResultSet genreRS = stmt.executeQuery(
                "SELECT GE.MA_TL AS MTL, TEN_TL FROM GENRE GE JOIN BOOK_GENRE BG on GE.MA_TL = BG.MA_TL WHERE BG.MA_SERIES='" + id + "'");
        ArrayList<BookGenre> genres = new ArrayList<>();
        while (genreRS.next()) {
            var genreID = genreRS.getString("MTL");
            var genreName = genreRS.getString("TEN_TL");

            genres.add(new BookGenre(genreID, genreName));
        }
        return genres;
    }

    private static BookPublisher getPublishers(Statement stmt, String id) throws SQLException {
        ResultSet publisherRS = stmt.executeQuery(
                "SELECT PU.MA_NXB AS NXB, TEN_NXB FROM PUBLISHER PU JOIN BOOK_PUBLISHER BP on PU.MA_NXB = BP.MA_NXB WHERE MA_SERIES='" + id + "'");
        ArrayList<BookPublisher> publishers = new ArrayList<>();
        if (publisherRS.next()) {
            var publisherID = publisherRS.getString("NXB");
            var publisherName = publisherRS.getString("TEN_NXB");

            return new BookPublisher(publisherID, publisherName);
        }
        return null;
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

    public void delete(Book book) {
        PreparedStatement stmt = null;
        try {
            stmt = getConnection().prepareStatement("UPDATE BOOK SET IS_DELETED=1 WHERE MA_SERIES=?");
            stmt.setString(1, book.getId());
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public int changeTrangThaiSach(String maSeri,String tt){
        String sql = "UPDATE `BOOK` SET `TRANG_THAI`=? WHERE MA_SERIES = ? ";
        int smt = 0;
        PreparedStatement pst = null;
        try {
            pst = getConnect().prepareStatement(sql);
            pst.setString(1,tt);
            pst.setString(2, maSeri);
            smt = pst.executeUpdate();
            System.out.println("Hello 1");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return smt;
    }

    public static void main(String[] args) {
        BookDAO b = new BookDAO();
        b.changeTrangThaiSach("5_4","AVAILABLE");
    }

}
