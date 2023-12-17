package api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_note")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;

    public Note(String content) {
        this.content = content;
    }

    public Note() {}

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
