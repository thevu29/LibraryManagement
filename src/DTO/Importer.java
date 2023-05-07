package DTO;

public class Importer implements Cloneable{
    private String id;
    private String name;
    private String phone;
    private String address;
    private String description;
    private String email;

    public Importer(String id, String name, String phone, String address, String description, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.description = description;
        this.email = email;
    }

    public static Importer createBlank() {
        return new Importer("","","","","","");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Importer clone() {
        try {
            Importer clone = (Importer) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public void cloneFrom(Importer importer) {
        this.id = importer.id;
        this.name = importer.name;
        this.phone = importer.phone;
        this.address = importer.address;
        this.description = importer.description;
        this.email = importer.email;
    }

}
