package Customer.DTO;

public class MembershipType {
    private String membershipName;
    private float discount;
    private boolean isDeleted;

    public MembershipType() {
    }

    public MembershipType(String membershipName, float discount, boolean isDeleted) {
        this.membershipName = membershipName;
        this.discount = discount;
        this.isDeleted = isDeleted;
    }

    public String getMembershipName() {
        return membershipName;
    }

    public void setMembershipName(String membershipName) {
        this.membershipName = membershipName;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}