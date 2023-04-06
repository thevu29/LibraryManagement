package Customer.model;
public class Membership {
    private String membershipId;
    private String membershipName;
    private String registrationDate;
    private String expirationDate;
    private float discount;

    public Membership() {
    }

    public Membership(String membershipId, String membershipName, String registrationDate, String expirationDate, float discount) {
        this.membershipId = membershipId;
        this.membershipName = membershipName;
        this.registrationDate = registrationDate;
        this.expirationDate = expirationDate;
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

    public String registrationDate() {
        return registrationDate;
    }

    public void registrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}
