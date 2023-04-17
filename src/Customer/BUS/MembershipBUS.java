package Customer.BUS;

import Customer.DAO.MembershipDAO;
import Customer.DTO.MembershipType;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class MembershipBUS {
    private ArrayList<MembershipType> membershipList = new ArrayList<>();
    private MembershipDAO memDAO = new MembershipDAO();

    public MembershipBUS() {
        membershipList = memDAO.createList();
    }

    public MembershipBUS(ArrayList<MembershipType> membershipList) {
        this.membershipList = membershipList;
    }

    public void addMembership(MembershipType membership) {
        membershipList.add(membership);
    }

    public boolean deleteMembership(String name) {
        for (MembershipType membership : membershipList) {
            if (membership.getMembershipName().equals(name)) {
                membershipList.remove(membership);
                return true;
            }
        }
        return false;
    }

    public boolean editMembership(MembershipType mem) {
        for (MembershipType membership : membershipList) {
            if (membership.getMembershipName().equals(mem.getMembershipName())) {
                membership.setMembershipName(mem.getMembershipName());
                membership.setDiscount(mem.getDiscount());
                return true;
            }
        }
        return false;
    }

    public void renderToTable(DefaultTableModel tblModel) {
        tblModel.setRowCount(0);

        for (MembershipType membership : membershipList) {
            tblModel.addRow(new Object[]{membership.getMembershipName(), membership.getDiscount()});
        }

        tblModel.fireTableDataChanged();
    }

    public ArrayList<MembershipType> getMembershipList() {
        return membershipList;
    }

    public void setMembershipList(ArrayList<MembershipType> membershipList) {
        this.membershipList = membershipList;
    }

    public int getMembershipListLength() {
        return  membershipList.size();
    }
}