package api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_tag")
public class Tag {
    /* Model class for Tag */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String color;

    public Tag(String title, String color) {
        this.title = title;
        this.color = color;
    }

    public Tag() {}

    @java.lang.Override
    public java.lang.String toString() {
        return "Tag{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getId() {
        return id;
    }
}

