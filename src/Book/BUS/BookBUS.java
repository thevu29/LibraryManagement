package Book.BUS;

import Book.*;
import Book.DAO.AuthorDAO;
import Book.DAO.BookDAO;
import Book.DAO.GenreDAO;
import Book.DAO.PublisherDAO;
import Book.DTO.Book;
import Book.GUI.BookEditorDialog;
import Book.GUI.BookGUI;

import javax.swing.*;
import java.util.Date;

public class BookBUS {
    private final BookDataTableModel bookDataTableModel;
    private final AuthorDataTableModel authorDataTableModel;
    private final BookGUI bookGUI;
    private final GenreDataTableModel genreDataTableModel;
    private final PublisherDataTableModel publisherDataTableModel;

    public BookBUS() {
        this.bookDataTableModel = new BookDataTableModel();
        this.authorDataTableModel = new AuthorDataTableModel();
        this.publisherDataTableModel = new PublisherDataTableModel();
        this.genreDataTableModel = new GenreDataTableModel();
        bookGUI = new BookGUI(this);
        var bookDAO = new BookDAO();
        var authorDAO = new AuthorDAO();
        var genreDAO = new GenreDAO();
        var publisherDAO = new PublisherDAO();
        bookDataTableModel.setRows(bookDAO.getAllFromDatabase());
        authorDataTableModel.setRows(authorDAO.getAllFromDatabase());
        publisherDataTableModel.setRows(publisherDAO.getAllFromDatabase());
        genreDataTableModel.setRows(genreDAO.getAllFromDatabase());
    }

//    public BookBUS(BookDataTableModel bookDataTableModel, AuthorDataTableModel authorDataTableModel) {
//        this.bookDataTableModel = bookDataTableModel;
//        this.authorDataTableModel = authorDataTableModel;
//        bookGUI = new BookGUI(this);
//        var bookDAO = new BookDAO();
//        var authorDAO = new AuthorDAO();
//        bookDataTableModel.setRows(bookDAO.getAllFromDatabase());
//        authorDataTableModel.setRows(authorDAO.getAllFromDatabase());
//    }


    public PublisherDataTableModel getPublisherDataTableModel() {
        return publisherDataTableModel;
    }

    public BookDataTableModel getBookDataTableModel() {
        return bookDataTableModel;
    }

    public AuthorDataTableModel getAuthorDataTableModel() {
        return authorDataTableModel;
    }

    public GenreDataTableModel getGenreDataTableModel() {
        return genreDataTableModel;
    }

    public void openNewBookDialog() {
        openBookEditDialog(Book.createBlankBook(), "Thêm sách");
    }
    public void openNewBookDialog(int coords) {
        var book = bookDataTableModel.get(coords);
        openNewBookDialog(book);
    }

    private void openNewBookDialog(Book book) {
        openBookEditDialog(book, "Chỉnh sửa sách");
    }

    private void openBookEditDialog(Book book, String title) {
        var dialog = new BookEditorDialog(book, bookDataTableModel, title);
        dialog.pack();
        dialog.setVisible(true);
    }

    public JPanel getPanel() {
        return bookGUI.getPanel1();
    }

    public void startGUI() {
        JFrame frame = new JFrame("Book");
        frame.setContentPane(bookGUI.getPanel1());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        var authorModel = new AuthorDataTableModel();
        var bookModel = new BookDataTableModel();
        var bus = new BookBUS();
        bus.startGUI();
    }
}
