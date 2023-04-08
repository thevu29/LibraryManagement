package Book;

import NhanVien.arraylistNV.Gender;

public class Author {
    private String id;
    private String name;
    private String email;
    private Gender gender;
    private String description;

    public Author(String id, String name, String email, Gender gender, String description) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.description = description;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
