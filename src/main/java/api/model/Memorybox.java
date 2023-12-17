package api.model;

import jakarta.persistence.*;

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
    private String banner;

    public Memorybox(String title, List<Task> tasks, List<Note> notes, List<Tag> tags, String banner) {
        this.title = title;
        this.datetimeCreated = new Date();
        this.tasks = tasks;
        this.notes = notes;
        this.tags = tags;
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
