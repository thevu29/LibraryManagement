package customer.model;
public class Membership {
    private String membershipId;
    private String registrationDate;
    private String expirationDate;
    private MembershipType type;

    public Membership() {
    }

    public Membership(String membershipId, String registrationDate, String expirationDate, MembershipType type) {
        this.membershipId = membershipId;
        this.registrationDate = registrationDate;
        this.expirationDate = expirationDate;
        this.type = type;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String membershipId) {
        this.membershipId = membershipId;
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

    public MembershipType getType() {
        return type;
    }

    public void setType(MembershipType type) {
        this.type = type;
    }
}
