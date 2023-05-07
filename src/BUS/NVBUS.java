package BUS;

import DAO.NhanVienDAO;
import DTO.nhanVien;
import GUI.NhanVienadmin;
import DTO.NVDataTableModel;

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

    public void AddNV(nhanVien nv) {
        getNVDAO().AddNV(nv);
    }

    public ArrayList<nhanVien> getAllFromDatabase() {
        return getNVDAO().getAllFromDatabase();
    }

    public void EditNV(nhanVien nv) {
        getNVDAO().EditNV(nv);
    }

    public void removeNV(String id) {
        getNVDAO().removeNV(id);
    }

    public int contNV() {
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
