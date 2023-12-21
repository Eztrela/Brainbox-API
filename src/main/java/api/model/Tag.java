package api.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_tag")
public class Tag {
    /* Model class for Tag */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String color;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.REMOVE)
    private List<Task> tasks;

    public Tag(String title, String color) {
        this.title = title;
        this.color = color;
        this.tasks = new ArrayList<Task>();
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}

