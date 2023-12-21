package api.model;

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

    @ManyToOne
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

    public Memorybox(String title, String banner) {
        this.title = title;
        this.datetimeCreated = new Date();
        this.tasks = new ArrayList<Task>();
        this.notes = new ArrayList<Note>();
        this.tags = new ArrayList<Tag>();
        this.banner = banner;
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

    public void addTag(Tag tag){
        this.tags.add(tag);
    }
    public void addTask(Task task){
        this.tasks.add(task);
    }
    public void addNote(Note note){
        this.notes.add(note);
    }

    public void removerTag(Tag tag){
        this.tags.remove(tag);
    }

    public void removerTask(Task task){
        this.tasks.remove(task);
    }
    public void removerNote(Note note){
        this.notes.remove(note);
    }

    public Note getNoteByID(Long id){
        for(Note note: this.getNotes()){
            if(note.getId().equals(id)){
                return note;
            }
        }
        return null;
    }

    public Task getTaskByID(Long id){
        for(Task task: this.getTasks()){
            if(task.getId().equals(id)){
                return task;
            }
        }
        return null;
    }

    public Tag getTagByID(Long id){
        for(Tag tag: this.getTags()){
            if(tag.getId().equals(id)){
                return tag;
            }
        }
        return null;
    }
}
