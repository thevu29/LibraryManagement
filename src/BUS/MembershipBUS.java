package BUS;

import DAO.MembershipDAO;
import DTO.Membership;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class MembershipBUS {
    private MembershipDAO membershipDAO = new MembershipDAO();
    private ArrayList<Membership> membershipList;

    public MembershipBUS() {
        membershipList = membershipDAO.getAllMembership();
        for (Membership membership : membershipList) {
            membership.setRegisDate(reverseDate(membership.getRegisDate()));
            membership.setExpireDate(reverseDate(membership.getExpireDate()));
        }
    }

    public void renderToTable(DefaultTableModel tblModel) {
        tblModel.setRowCount(0);

        membershipList = membershipDAO.getAllMembership();
        for (Membership membership : membershipList) {
            membership.setRegisDate(reverseDate(membership.getRegisDate()));
            membership.setExpireDate(reverseDate(membership.getExpireDate()));
        }
        membershipList.sort((a, b) -> a.getMembershipId().compareTo(b.getMembershipId()));

        for (Membership membership : membershipList) {
            if (!membership.isDeleted()) {
                tblModel.addRow(new Object[]{membership.getMembershipId(), membership.getCustomerId(), membership.getMembershipTypeName(),
                        membership.getRegisDate(), membership.getExpireDate()});
            }
        }

        tblModel.fireTableDataChanged();
    }

    public String reverseDate(String date) {
        if (date.equals("")) {
            return "";
        }

        String newDate = "";
        String[] arr = date.split("-");

        if (arr.length < 2) {
            return date;
        }

        newDate = arr[2] + "-" + arr[1] + "-" + arr[0];
        return newDate;
    }

    public ArrayList<Membership> getMembershipList() {
        return membershipList;
    }
}