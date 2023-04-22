package Book.DTO;

import NhanVien.arraylistNV.Gender;
import Utils.EGender;

public class Author implements Cloneable {
    private String id;
    private String name;
    private String email;
    private EGender gender;
    private String description;

    public static int totalID = 1;

    public Author(String id, String name, String email, String gender, String description) {
        this.id = id;
        this.name = name;
        this.email = email;
        setGender(gender);
        this.description = description;
    }


    public static Author createBlankAuthor() {
        return new Author("", "", "", "NAM", "");
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

    public void setGender(EGender gender) {
        this.gender = gender;
    }

    public void setGender(String gender) {
        if (gender.equals(String.valueOf(EGender.NAM))) {
            this.gender = EGender.NAM;
        } else if (gender.equals(String.valueOf(EGender.NU))) {
            this.gender = EGender.NU;
        }
    }

    public EGender getGender() {
        return gender;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Author clone() {
        try {
            Author clone = (Author) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }


    public void cloneFrom(Author cloner) {
        this.id = cloner.getId();
        this.name = cloner.getName();
        this.email = cloner.getEmail();
        setGender(cloner.gender);
        this.description = cloner.getDescription();
    }
}
