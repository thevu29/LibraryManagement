package DAO;

import Core.DefaultConnection;
import DTO.*;
import org.jfree.data.general.DefaultPieDataset;

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

                var importer = getImporter(stmt, id);

                books.add(new Book(id, name, authors, publisher, genre, location, price, bookStatus, language,
                        description, publishYear, totalPage, importer));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Không thể kết nối");
            return books;
        }
        return books;
    }

    private BookImporter getImporter(Statement stmt, String id) throws SQLException {
        ResultSet importerRS = stmt.executeQuery(
                "SELECT I.ID, I.NAME FROM BOOK B JOIN IMPORTED_FROM I on B.MA_NHA_NHAP = I.ID WHERE B.MA_SERIES='" + id + "'");
        if (importerRS.next()) {
            var importerID = importerRS.getString("ID");
            var name = importerRS.getString("NAME");

            return new BookImporter(importerID, name);
        }
        return new BookImporter("", "");
    }

    public boolean isIDExist(String id) {
        PreparedStatement stmt = null;
        try {
            stmt = getConnection().prepareStatement("SELECT MA_SERIES FROM BOOK WHERE MA_SERIES=? AND IS_DELETED = 0");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
    public boolean isIDExistWithDelete(String id) {
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

    public void update(Book book) {
        PreparedStatement stmt = null;
        try {
            if (isIDExistWithDelete(book.getId())) {
                stmt = getConnection()
                        .prepareStatement("UPDATE BOOK SET TEN_SACH=?, MO_TA=?, VI_TRI=?, GIA=?, NAM_XUAT_BAN=?, " +
                                "NGON_NGU=?, TRANG_THAI=?, TONG_SO_TRANG=?, MA_NHA_NHAP=?, TRANG_THAI=?, IS_DELETED=0 WHERE MA_SERIES=?");
                stmt.setString(1, book.getName());
                stmt.setString(2, book.getDescription());
                stmt.setString(3, book.getLocation());
                stmt.setLong(4, book.getPrice());
                stmt.setInt(5, book.getPublishYear());
                stmt.setString(6, book.getLanguage());
                stmt.setString(7, String.valueOf(book.getBookStatus()));
                stmt.setInt(8, book.getTotalPage());
                stmt.setString(9, book.getImporter().getId());
                stmt.setString(10, book.getBookStatus().toString());
                stmt.setString(11, book.getId());
                stmt.executeUpdate();

                stmt = getConnection().prepareStatement("DELETE FROM BOOK_AUTHOR WHERE MA_SERIES=?");
                stmt.setString(1, book.getId());
                stmt.executeUpdate();

                for (var author: book.getAuthors()) {
                    if (author.getId().strip().equals("")) {
                        continue;
                    }
                    stmt = getConnection().prepareStatement("INSERT INTO BOOK_AUTHOR(MA_SERIES, MA_TG, IS_DELETED) VALUES (?,?,0)");
                    stmt.setString(1, book.getId());
                    stmt.setString(2, author.getId());
                    stmt.executeUpdate();
                }

                stmt = getConnection().prepareStatement("DELETE FROM BOOK_GENRE WHERE MA_SERIES=?");
                stmt.setString(1, book.getId());
                stmt.executeUpdate();

                for (var genre: book.getGenre()) {
                    if (genre.getId().strip().equals("")) {
                        continue;
                    }
                    stmt = getConnection().prepareStatement("INSERT INTO BOOK_GENRE(MA_SERIES, MA_TL, IS_DELETED) VALUES (?,?,0)");
                    stmt.setString(1, book.getId());
                    stmt.setString(2, genre.getId());
                    stmt.executeUpdate();
                }

                stmt = getConnection().prepareStatement("DELETE FROM BOOK_PUBLISHER WHERE MA_SERIES=?");
                stmt.setString(1, book.getId());
                stmt.executeUpdate();

                var publisher = book.getPublisher();
                stmt = getConnection().prepareStatement("INSERT INTO BOOK_PUBLISHER(MA_SERIES, MA_NXB, IS_DELETED) VALUES (?,?,0)");
                stmt.setString(1, book.getId());
                stmt.setString(2, publisher.getId());
                stmt.executeUpdate();
            }
            else {
                stmt = getConnection()
                        .prepareStatement("INSERT INTO BOOK(MA_SERIES, MA_SACH, TEN_SACH, MO_TA, VI_TRI, GIA, NAM_XUAT_BAN, NGON_NGU, TONG_SO_TRANG, TRANG_THAI, MA_NHA_NHAP, IS_DELETED) VALUES " +
                                "(?,?,?,?,?,?,?,?,?,?,?,0)");
                stmt.setString(1, book.getId());
                stmt.setString(2, book.getId().split("_")[0]);
                stmt.setString(3, book.getName());
                stmt.setString(4, book.getDescription());
                stmt.setString(5, book.getLocation());
                stmt.setLong(6, book.getPrice());
                stmt.setInt(7, book.getPublishYear());
                stmt.setString(8, book.getLanguage());
                stmt.setInt(9, book.getTotalPage());
                stmt.setString(10, String.valueOf(book.getBookStatus()));
                stmt.setString(11, book.getImporter().getId());
                stmt.executeUpdate();


                stmt = getConnection().prepareStatement("DELETE FROM BOOK_AUTHOR WHERE MA_SERIES=?");
                stmt.setString(1, book.getId());
                stmt.executeUpdate();

                for (var author: book.getAuthors()) {
                    stmt = getConnection().prepareStatement("INSERT INTO BOOK_AUTHOR(MA_SERIES, MA_TG, IS_DELETED) VALUES (?,?,0)");
                    stmt.setString(1, book.getId());
                    stmt.setString(2, author.getId());
                    stmt.executeUpdate();
                }

                stmt = getConnection().prepareStatement("DELETE FROM BOOK_GENRE WHERE MA_SERIES=?");
                stmt.setString(1, book.getId());
                stmt.executeUpdate();

                for (var genre: book.getGenre()) {
                    stmt = getConnection().prepareStatement("INSERT INTO BOOK_GENRE(MA_SERIES, MA_TL, IS_DELETED) VALUES (?,?,0)");
                    stmt.setString(1, book.getId());
                    stmt.setString(2, genre.getId());
                    stmt.executeUpdate();
                }

                stmt = getConnection().prepareStatement("DELETE FROM BOOK_PUBLISHER WHERE MA_SERIES=?");
                stmt.setString(1, book.getId());
                stmt.executeUpdate();

                var publisher = book.getPublisher();
                stmt = getConnection().prepareStatement("INSERT INTO BOOK_PUBLISHER(MA_SERIES, MA_NXB, IS_DELETED) VALUES (?,?,0)");
                stmt.setString(1, book.getId());
                stmt.setString(2, publisher.getId());
                stmt.executeUpdate();

            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public String getLatestSubSeries(String series) {
        var book = series.split("_")[0];


        PreparedStatement stmt = null;



//        SELECT MAX(CAST(SUBSTR(MA_NXB, 4) AS UNSIGNED)) AS MA_NXB FROM PUBLISHER
        try {
            stmt = getConnection().prepareStatement("SELECT MAX(CAST(SUBSTR(MA_SERIES, ?) AS UNSIGNED)) AS ID FROM BOOK WHERE MA_SACH=?");
            stmt.setInt(1, book.length()+2);
            stmt.setString(2, book);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getString("ID");
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
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

    public DefaultPieDataset thongKeTrangThaiSach(){
        DefaultPieDataset dataset = new DefaultPieDataset();

        String sql ="SELECT COUNT(MA_SERIES) as slg, TRANG_THAI FROM `BOOK` GROUP BY TRANG_THAI";
        Statement stmt = null;
        try {
            stmt = getConnect().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                double slg = rs.getDouble("slg");
                String tt = rs.getString("TRANG_THAI");
                dataset.setValue(tt,slg);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return dataset;
    }

    public static void main(String[] args) {
        BookDAO b = new BookDAO();
        b.changeTrangThaiSach("5_4","AVAILABLE");
    }

}
