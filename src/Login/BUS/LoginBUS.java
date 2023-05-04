package Login.BUS;

import Login.GUI.LoginForm;
import NhanVien.DAO.NhanVienDAO;
import NhanVien.DTO.nhanVien;
import javax.swing.*;
import java.util.ArrayList;

public class LoginBUS {
    private NhanVienDAO nhanVienDAO = new NhanVienDAO();
    private ArrayList<nhanVien> nvList;

    public LoginBUS() {
        nvList = nhanVienDAO.getAllFromDatabase();
    }

    public boolean validateEmpty(String username, String password) {
        StringBuilder sb = new StringBuilder();

        if (username.equals("")) {
            sb.append("Chưa nhập tài khoản \n");
        }
        if (password.equals("")) {
            sb.append("Chưa nhập tên mật khẩu \n");
        }

        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(null, sb.toString(), "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean login(String username, String password) {
        if (!validateEmpty(username, password)) {
            return false;
        }

        nhanVien nv = findNhanVienById(username);

        if (nv == null) {
            JOptionPane.showMessageDialog(null, "Tài khoản không tồn tại", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!nv.getPassword().equals(password)) {
            JOptionPane.showMessageDialog(null, "Mật khẩu không đúng", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public void logout(JFrame frame) {
        frame.dispose();
    }

    public nhanVien findNhanVienById(String id) {
        for (nhanVien nv : nvList) {
            if (nv.getID().equals(id)) {
                return nv;
            }
        }
        return null;
    }

    public String findNhanVienChucVu(String id) {
        for (nhanVien nv : nvList) {
            if (nv.getID().equals(id)) {
                return nv.getPosition() == 0 ? "Thủ thư" : "Quản lý";
            }
        }
        return "";
    }
}