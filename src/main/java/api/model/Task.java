package api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;


import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private String status;
    private Date datetimeCreated;
    private Date datetimeDue;
    private Long priority;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Tag tag;

    public Task(String title, String description, String status, Date datetimeDue, Long priority, Tag tag) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.datetimeCreated = new Date();
        this.datetimeDue = datetimeDue;
        this.priority = priority;
        this.tag = tag;
    }

    public Task() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDatetimeDue() {
        return datetimeDue;
    }

    public void setDatetimeDue(Date datetimeDue) {
        this.datetimeDue = datetimeDue;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Long getId() {
        return id;
    }

    public Date getDatetimeCreated() {
        return datetimeCreated;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", datetimeCreated=" + datetimeCreated +
                ", datetimeDue=" + datetimeDue +
                ", priority=" + priority +
                ", tag=" + tag.getTitle() +
                '}';
    }
}
