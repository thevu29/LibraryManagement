package customer.model;
public class MembershipType {
    private String typeName;
    private double discount;

    public MembershipType() {
    }

    public MembershipType(String typeName, double discount) {
        this.typeName = typeName;
        this.discount = discount;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
