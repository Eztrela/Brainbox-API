package api.repository;

import api.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoteRepository  extends JpaRepository<Note, Long> {
    public Optional<Note> findByContent(String content);
}
