package Book.BUS;

import Book.*;
import Book.DAO.*;
import Book.DTO.*;
import Book.GUI.*;
import Utils.ValidationContract.Validation;

import javax.swing.*;
import java.util.Arrays;

public class BookBUS {
    //region FIELD
    private final BookDataTableModel bookDataTableModel;
    private final AuthorDataTableModel authorDataTableModel;
    private final BookGUI bookGUI;
    private final GenreDataTableModel genreDataTableModel;
    private final PublisherDataTableModel publisherDataTableModel;
    private final BookDAO bookDAO;
    private final AuthorDAO authorDAO;
    private final GenreDAO genreDAO;
    private final PublisherDAO publisherDAO;
    private final ImporterDataTableModel importerTableModel;
    private final ImporterDAO importerDAO;
    //endregion

    //region CONSTRUCTOR
    public BookBUS() {
        this.bookDataTableModel = new BookDataTableModel();
        this.authorDataTableModel = new AuthorDataTableModel();
        authorDataTableModel.setEditable(false);

        this.publisherDataTableModel = new PublisherDataTableModel();
        publisherDataTableModel.setEditable(false);

        this.genreDataTableModel = new GenreDataTableModel();
        genreDataTableModel.setEditable(false);

        this.importerTableModel = new ImporterDataTableModel();
        importerTableModel.setEditable(false);

        bookGUI = new BookGUI(this);
        this.bookDAO = new BookDAO();
        this.authorDAO = new AuthorDAO();
        this.genreDAO = new GenreDAO();
        this.publisherDAO = new PublisherDAO();
        this.importerDAO = new ImporterDAO();
        bookDataTableModel.setRows(bookDAO.getAllFromDatabase());
        authorDataTableModel.setRows(authorDAO.getAllFromDatabase());
        publisherDataTableModel.setRows(publisherDAO.getAllFromDatabase());
        genreDataTableModel.setRows(genreDAO.getAllFromDatabase());
        importerTableModel.setRows(importerDAO.getAllFromDatabase());
    }
    //endregion

    //region MODEL
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
    //endregion

    //region BOOK


    private void openNewBookDialog(Book book) {
        openBookEditDialog(book, "Chỉnh sửa sách");
    }

    private void openBookEditDialog(Book book, String title) {
        var dialog = new BookEditorDialog(book, this, title);
        dialog.pack();
        dialog.setVisible(true);
    }

    public void openNewBookDialog() {
        openBookEditDialog(Book.createBlankBook(), "Thêm sách");
    }
    public void openNewBookDialog(int coords) {
        var book = bookDataTableModel.get(coords);
        openNewBookDialog(book);
    }

    public void openBookDeleteDialog(int coords) {
        var book = bookDataTableModel.get(coords);
        var inp = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa sách "+book.getName(), "Xóa sách " + book.getName(), JOptionPane.OK_CANCEL_OPTION);
        if (inp == 0) {
            bookDAO.delete(book);
        }
    }

    public void updateBookDataModel() {
        bookDataTableModel.setRows(bookDAO.getAllFromDatabase());
    }
    //endregion

    //region AUTHOR
    public String getNewAuthorID() {
        var latestID = authorDAO.getLatestId();
        var id = Integer.parseInt(latestID.split("TG")[1]);

        return "TG"+ (id + 1);
    }
    public void openNewAuthorDialog() {
        var author = Author.createBlankAuthor();
        author.setId(getNewAuthorID());
        openAuthorEditDialog(author, "Tạo tác giả");
    }
    public void openNewAuthorDialog(int coords) {
        var author = authorDataTableModel.get(coords);
        openAuthorEditDialog(author, "Chỉnh sửa tác giả");
    }

    private void openAuthorEditDialog(Author author, String title) {
        var dialog = new AuthorDialog(author, authorDataTableModel, this, title);
        dialog.pack();
        dialog.setVisible(true);
    }

    public boolean validateAuthor(Author author, int mode) {
        var message = "";
        if (mode == 0 && authorDAO.isIDDuplicate(author.getId())) {
            message += "ID trùng\n";
        }

        if (!Validation.isValidEmail(author.getEmail())) {
            message += "Email không hợp lệ";
        }

        if (!message.equals("")) {
            JOptionPane.showConfirmDialog(null, message, "Giá trị nhập không hợp lệ", JOptionPane.OK_CANCEL_OPTION);
            return false;
        }

        return true;
    }
    public void commitAuthor(Author author) {
        authorDAO.update(author);
        authorDataTableModel.setRows(authorDAO.getAllFromDatabase());
    }

    public void deleteAuthor(int coords) {
        var author = authorDataTableModel.get(coords);
        var inp = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa tác giả "+author.getName(), "Xóa tác giả " + author.getName(), JOptionPane.OK_CANCEL_OPTION);
        if (inp == 0) {
            authorDAO.delete(author);
        }
    }

    //endregion

    //region GENRE
    public boolean validateGenre(Genre genre, int mode) {
        var message = "";
        if (genre.getName().trim().equals("")) {
            message += "Tên không được để trống\n";
        }

        if (mode==0 && genreDAO.isNameDuplicate(genre.getName())) {
            message += "Tên không được trùng\n";
        }

        if (!message.equals("")) {
            JOptionPane.showConfirmDialog(null, message, "Giá trị nhập không hợp lệ", JOptionPane.OK_CANCEL_OPTION);
            return false;
        }
        return true;
    }

    private String getNewGenreID() {
        var id = Integer.parseInt(genreDAO.getLatestID().split("TL")[1]);
        return "TL"+(id+1);
    }

    public void openNewGenreDialog() {
        var genre = Genre.createBlank();
        genre.setId(getNewGenreID());
        openGenreEditDialog(genre, "Tạo thể loại");
    }
    public void openNewGenreDialog(int coords) {
        var genre = genreDataTableModel.get(coords);
        openGenreEditDialog(genre, "Chỉnh sửa thể loại");
    }

    private void openGenreEditDialog(Genre genre, String title) {
        var dialog = new GenreDialog(genre, genreDataTableModel, this, title);
        dialog.pack();
        dialog.setVisible(true);
    }


    public void commitGenre(Genre genre) {
        genreDAO.update(genre);
        genreDataTableModel.setRows(genreDAO.getAllFromDatabase());
    }

    public void deleteGenre(int coords) {
        var genre = genreDataTableModel.get(coords);
        var inp = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa thể loại "+genre.getName() + "?", "Xóa tác giả " + genre.getName(), JOptionPane.OK_CANCEL_OPTION);
        if (inp == 0) {
            genreDAO.delete(genre);
        }
    }
    //endregion

    //region PUBLISHER
    public boolean validatePublisher(Publisher publisher) {
        var message = "";

        if (!Validation.isValidEmail(publisher.getEmail())) {
            message += "Email không hợp lệ";
        }

        if (!Validation.isValidPhoneNumber(publisher.getPhone())) {
            message += "SĐT không hợp lệ";
        }

        if (!message.equals("")) {
            JOptionPane.showConfirmDialog(null, message, "Giá trị nhập không hợp lệ", JOptionPane.OK_CANCEL_OPTION);
            return false;
        }
        return true;
    }

    private String getNewPublisherID() {
        var id = Integer.parseInt(publisherDAO.getLatestID().split("NXB")[1]);
        return "NXB"+(id+1);
    }

    public void openNewPublisherDialog() {
        var publisher = Publisher.createBlank();
        publisher.setId(getNewPublisherID());
        openPublisherEditDialog(publisher, "Tạo thể loại");
    }
    public void openNewPublisherDialog(int coords) {
        var publisher = publisherDataTableModel.get(coords);
        openPublisherEditDialog(publisher, "Chỉnh sửa thể loại");
    }

    private void openPublisherEditDialog(Publisher publisher, String title) {
        var dialog = new PublisherDialog(publisher, publisherDataTableModel, this, title);
        dialog.pack();
        dialog.setVisible(true);
    }


    public void commitPublisher(Publisher publisher) {
        publisherDAO.update(publisher);
        publisherDataTableModel.setRows(publisherDAO.getAllFromDatabase());
    }
    public void deletePublisher(int coords) {
        var publisher = publisherDataTableModel.get(coords);
        var inp = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa NXB "+publisher.getName() + "?", "Xóa NXB " + publisher.getName(), JOptionPane.OK_CANCEL_OPTION);
        if (inp == 0) {
            publisherDAO.delete(publisher);
        }
    }
    //endregion

    //region IMPORTER
    public boolean validateImporter(Importer importer) {
        var message = "";

        if (!Validation.isValidEmail(importer.getEmail())) {
            message += "Email không hợp lệ";
        }

        if (!Validation.isValidPhoneNumber(importer.getPhone())) {
            message += "SĐT không hợp lệ";
        }

        if (!message.equals("")) {
            JOptionPane.showConfirmDialog(null, message, "Giá trị nhập không hợp lệ", JOptionPane.OK_CANCEL_OPTION);
            return false;
        }
        return true;
    }

    private String getNewImporterID() {
        var id = Integer.parseInt(importerDAO.getLatestID().split("IM")[1]);
        return "IM"+(id+1);
    }

    public void openNewImporterDialog() {
        var importer = Importer.createBlank();
        importer.setId(getNewImporterID());
        openImporterEditDialog(importer, "Tạo mới nhà nhập");
    }
    public void openNewImporterDialog(int coords) {
        var importer = importerTableModel.get(coords);
        openImporterEditDialog(importer, "Chỉnh sửa nhà nhậpi");
    }

    private void openImporterEditDialog(Importer importer, String title) {
        var dialog = new ImporterDialog(importer, importerTableModel, this, title);
        dialog.pack();
        dialog.setVisible(true);
    }


    public void commitImporter(Importer importer) {
        importerDAO.update(importer);
        importerTableModel.setRows(importerDAO.getAllFromDatabase());
    }

    public void deleteImporter(int coords) {
        var importer = importerTableModel.get(coords);
        var inp = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa nguồn cung "+importer.getName() + "?", "Xóa NXB " + importer.getName(), JOptionPane.OK_CANCEL_OPTION);
        if (inp == 0) {
            importerDAO.delete(importer);
        }
    }

    //endregion


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

    public ImporterDataTableModel getImporterDataTableModel() {
        return importerTableModel;
    }



}
