package DTO;

public class Publisher implements Cloneable {
    private String id;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String description;

    public Publisher(String id, String name, String address, String email, String phone, String description) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.description = description;
    }

    public static Publisher createBlank() {
        return new Publisher("", "", "", "", "", "");
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Publisher clone() {
        try {
            Publisher clone = (Publisher) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public void cloneFrom(Publisher clonedPublisher) {
        this.id = clonedPublisher.id;
        this.name = clonedPublisher.name;
        this.address = clonedPublisher.address;
        this.email = clonedPublisher.email;
        this.phone = clonedPublisher.phone;
        this.description = clonedPublisher.description;
    }
}
