package api.repository;

import api.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository  extends JpaRepository<Note, Long> {
    public Note findByContent(String content);
}
