package Customer.DTO;

public class MembershipType {
    private String membershipTypeName;
    private int discount;
    private boolean isDeleted;

    public MembershipType() {
    }

    public MembershipType(String membershipTypeName, int discount, boolean isDeleted) {
        this.membershipTypeName = membershipTypeName;
        this.discount = discount;
        this.isDeleted = isDeleted;
    }

    public String getMembershipTypeName() {
        return membershipTypeName;
    }

    public void setMembershipTypeNameName(String membershipTypeName) {
        this.membershipTypeName = membershipTypeName;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}