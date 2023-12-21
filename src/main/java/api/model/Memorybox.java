package api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_memorybox")
public class Memorybox {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String title;
    private Date datetimeCreated;
    @OneToMany
    private List<Task> tasks;
    @OneToMany
    private List<Note> notes;
    @OneToMany
    private List<Tag> tags;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;
    private String banner;

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Memorybox(String title, String banner, User user) {
        this.title = title;
        this.datetimeCreated = new Date();
        this.tasks = new ArrayList<Task>();
        this.notes = new ArrayList<Note>();
        this.tags = new ArrayList<Tag>();
        this.banner = banner;
        this.user = user;
    }

    public Memorybox() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public Long getId() {
        return id;
    }

    public Date getDatetimeCreated() {
        return datetimeCreated;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public List<Tag> getTags() {
        return tags;
    }
    
}
