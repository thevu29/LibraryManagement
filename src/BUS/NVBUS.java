package BUS;

import DAO.NhanVienDAO;
import DTO.nhanVien;
import GUI.NhanVienadmin;
import DTO.NVDataTableModel;

import java.util.ArrayList;

public class NVBUS {
    private NVDataTableModel NVs;
    private NhanVienDAO NVDAO = new NhanVienDAO();

    public NVBUS() {
        NVs = new NVDataTableModel();
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
}