package BUS;

import DAO.MembershipTypeDAO;
import DTO.MembershipType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class MembershipTypeBUS {
    private ArrayList<MembershipType> membershipTypeList;
    private MembershipTypeDAO memDAO = new MembershipTypeDAO();

    public MembershipTypeBUS() {
        membershipTypeList = memDAO.getAll();
        membershipTypeList.sort((a, b) -> a.getDiscount() - b.getDiscount());
    }

    public boolean validateAdd(String name, String discount) {
        if (!validateEmpty(name, discount)) {
            return false;
        }

        if (validateDiscount(discount) == 0) {
            return false;
        }

        MembershipType membershipType = memDAO.getDeletedMembershipTypeByName(name);
        if (findMembershipTypeByName(name) && !membershipType.isDeleted()) {
            JOptionPane.showMessageDialog(null, "Tên loại thành viên đã tồn tại", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (findMembershipTypeByName(name) && membershipType.isDeleted()){
            if (memDAO.recoverMembershipType(membershipType)) {
                JOptionPane.showMessageDialog(null, "Thêm loại thành viên thành công");
                return true;
            }
        }

        membershipType = new MembershipType(name, Integer.parseInt(discount), false);
        if (memDAO.addMembershipType(membershipType)) {
            JOptionPane.showMessageDialog(null, "Thêm loại thành viên thành công");
            return true;
        }

        JOptionPane.showMessageDialog(null, "Thêm loại thành viên thất bại", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    public boolean validateDelete(String name) {
        if (memDAO.deleteMembershipType(name)) {
            JOptionPane.showMessageDialog(null, "Xóa loại thành viên thành công");
            return true;
        }

        JOptionPane.showMessageDialog(null, "Xóa loại thành viên thất bại", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    public boolean validateUpdate(String name, String discount) {
        if (!validateEmpty(name, discount)) {
            return false;
        }

        if (validateDiscount(discount) == 0) {
            return false;
        }

        MembershipType membershipType = new MembershipType(name, Integer.parseInt(discount), false);
        if (memDAO.updateMembership(membershipType)) {
            JOptionPane.showMessageDialog(null, "Sửa thông tin loại thành viên thành công");
            return true;
        }

        JOptionPane.showMessageDialog(null, "Sửa thông tin loại thành viên thất bại", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    public boolean findMembershipTypeByName(String name) {
        for (MembershipType membershipType : membershipTypeList) {
            if (membershipType.getMembershipTypeName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public int validateDiscount(String discount) {
        int res = 0;
        try {
            res = Integer.parseInt(discount);

            if (res < 0) {
                JOptionPane.showMessageDialog(null, "Giảm giá phải là số lớn hơn 0", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Giảm giá phải là chữ số", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return res;
    }

    public boolean validateEmpty(String name, String discount) {
        StringBuilder sb = new StringBuilder();

        if (name.equals("")) {
            sb.append("Tên loại thành viên không được để trống \n");
        }
        if (discount.equals("")) {
            sb.append("Giảm giá không được để trống \n");
        }

        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(null, sb.toString(), "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public void renderToTable(DefaultTableModel tblModel) {
        tblModel.setRowCount(0);

        membershipTypeList = memDAO.getAll();
        membershipTypeList.sort((a, b) -> a.getDiscount() - b.getDiscount());
        for (MembershipType membership : membershipTypeList) {
            if (!membership.isDeleted()) {
                tblModel.addRow(new Object[]{membership.getMembershipTypeName(), membership.getDiscount()});
            }
        }

        tblModel.fireTableDataChanged();
    }

    public ArrayList<MembershipType> getMembershipTypeList() {
        return membershipTypeList;
    }

    public void setMembershipTypeList(ArrayList<MembershipType> membershipTypeList) {
        this.membershipTypeList = membershipTypeList;
    }

    public int getMembershipTypeListLength() {
        return  membershipTypeList.size();
    }
}