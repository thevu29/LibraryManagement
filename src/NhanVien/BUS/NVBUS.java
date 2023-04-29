package NhanVien.BUS;

import Book.DTO.Book;
import Book.GUI.BookEditorDialog;
import NhanVien.DAO.NhanVienDAO;
import NhanVien.DTO.nhanVien;
import NhanVien.GUI.NhanVienC;
import NhanVien.GUI.NhanVienadmin;
import NhanVien.arraylistNV.NVDataTableModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class NVBUS {

    private NVDataTableModel NVs;

    private NhanVienadmin NVGUI;

    private NhanVienDAO NVDAO = new NhanVienDAO();

    public NVBUS() {
        NVs = new NVDataTableModel();
//        NVGUI = new NhanVienadmin();
//        var NCDAO = new NhanVienDAO();
        NVs.setRows(NVDAO.getAllFromDatabase());
    }

    public void AddNV(nhanVien nv){
        getNVDAO().AddNV(nv);
    }

    public ArrayList<nhanVien> getAllFromDatabase(){
        return getNVDAO().getAllFromDatabase();
    }
    public void EditNV(nhanVien nv){
        getNVDAO().EditNV(nv);
    }

    public void removeNV(String id){
        getNVDAO().removeNV(id);
    }

    public int contNV() throws SQLException {
        return getNVDAO().contNV();
    }


    public NhanVienDAO getNVDAO() {
        return NVDAO;
    }

    public NVDataTableModel getNVs() {
        return NVs;
    }



    public NhanVienadmin getNVGUI() {
        return NVGUI;
    }

//    public void newNV(){
////        opennewNV();
//    }
//
//    public void opennewNV(int coords){
//        var NV = NVs.get(coords);
//        newNV();
//    }
//
//    public void editNV(nhanVien nv){
////        opennewNV();

//    }

//    private void openNhanVienC(nhanVien nv, String title) {
//        var dialog = new NhanVienC(nv, NVs, title);
//        dialog.pack();
//        dialog.setVisible(true);
//    }

}
