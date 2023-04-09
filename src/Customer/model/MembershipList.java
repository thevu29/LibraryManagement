package Customer.model;

import Customer.model.Membership;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class MembershipList {
    private ArrayList<Membership> membershipList = new ArrayList<>();

    public MembershipList() {
        membershipList.add(new Membership("MBS001", "Bình thường", 0));
        membershipList.add(new Membership("MBS002", "Bạc", 10));
        membershipList.add(new Membership("MBS003", "Vàng", 20));
    }

    public MembershipList(ArrayList<Membership> membershipList) {
        this.membershipList = membershipList;
    }

    public void addMembership(Membership membership) {
        membershipList.add(membership);
    }

    public boolean deleteMembership(String id) {
        for (Membership membership : membershipList) {
            if (membership.getMembershipId().equals(id)) {
                membershipList.remove(membership);
                return true;
            }
        }
        return false;
    }

    public boolean editMembership(Membership mem) {
        for (Membership membership : membershipList) {
            if (membership.getMembershipId().equals(mem.getMembershipId())) {
                membership.setMembershipName(mem.getMembershipName());
                membership.setDiscount(mem.getDiscount());
                return true;
            }
        }
        return false;
    }

    public void renderToTable(DefaultTableModel tblModel) {
        tblModel.setRowCount(0);

        for (Membership membership : membershipList) {
            tblModel.addRow(new Object[]{membership.getMembershipId(), membership.getMembershipName(), membership.getDiscount()});
        }

        tblModel.fireTableDataChanged();
    }

    public ArrayList<Membership> getMembershipList() {
        return membershipList;
    }

    public void setMembershipList(ArrayList<Membership> membershipList) {
        this.membershipList = membershipList;
    }
}