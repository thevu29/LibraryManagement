package Customer.model;

public class Membership {
    private String membershipId;
    private String membershipName;
    private float discount;

    public Membership() {
    }

    public Membership(String membershipId, String membershipName, float discount) {
        this.membershipId = membershipId;
        this.membershipName = membershipName;
        this.discount = discount;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String membershipId) {
        this.membershipId = membershipId;
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
}