package Book.DTO;

public class Genre implements Cloneable {
    private String id;
    private String name;
    private String description;

    public Genre(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static Genre createBlank() {
        return new Genre("", "", "");

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Genre clone() {
        try {
            Genre clone = (Genre) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public void cloneFrom(Genre clonedGenre) {
        this.id = clonedGenre.id;
        this.name = clonedGenre.name;
        this.description = clonedGenre.description;
    }
}
