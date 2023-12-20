package api.service;

import api.model.Note;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public List<Note> getAll(){
        return noteRepository.findAll().stream().toList();
    }

    public Note getById(Long id){
        return noteRepository.findById(id).orElse(null);
    }

    @Transactional
    public Note insert(NoteInsertDTO note){
        Optional<Note> register = noteRepository.findByContent(note.getContent());
        if (register.isPresent()) throw new RuntimeException("Note already exists");
        return noteRepository.save(note);
    }

    @Transactional
    public Note update(NoteInsertDTO note, Long id){
        return noteRepository.findById(id).map(
                register -> {
                    register.setContent(note.getContent());
                    return noteRepository.save(register);
                }
        ).orElseThrow(() -> new RuntimeException("Note "+id+" not found"));
    }

    public void delete(Long id){
        Optional<Note> register = noteRepository.findById(id);
        if (register.isEmpty()) throw new RuntimeException("Note " + id + " not found");
        noteRepository.deleteById(id);
    }

    public Note findByContent(String title){
        return noteRepository.findByContent(title).orelse(null);
    }

}
