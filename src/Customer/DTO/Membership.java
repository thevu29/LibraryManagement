package Customer.DTO;

public class Membership {
    private String membershipId;
    private String customerId;
    private String membershipTypeName;
    private String regisDate;
    private String expireDate;
    private boolean isDeleted;

    public Membership() {
    }

    public Membership(String membershipId, String customerId, String membershipTypeName, String regisDate, String expireDate, boolean isDeleted) {
        this.membershipId = membershipId;
        this.customerId = customerId;
        this.membershipTypeName = membershipTypeName;
        this.regisDate = regisDate;
        this.expireDate = expireDate;
        this.isDeleted = isDeleted;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String membershipId) {
        this.membershipId = membershipId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getMembershipTypeName() {
        return membershipTypeName;
    }

    public void setMembershipTypeName(String membershipTypeName) {
        this.membershipTypeName = membershipTypeName;
    }

    public String getRegisDate() {
        return regisDate;
    }

    public void setRegisDate(String regisDate) {
        this.regisDate = regisDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
