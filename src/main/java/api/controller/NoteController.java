package api.controller;

import api.model.Note;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping
    public List<NoteListingDTO> getAll(){
        return this.noteService.getAll();
    }

    @GetMapping("/{id}")
    public Note getNoteById(@PathVariable("id") Long id){
        return this.noteService.getNoteById(id);
    }

    @PostMapping
    public Note insert(@RequestBody @Valid NoteInsertDTO note){
        Note newNote = new Note(note.content);
        return this.noteService.insert(newNote);
    }

    @PutMapping("/{id}")
    public Note update(@PathVariable("id") Long id, @RequestBody Note note){
        return this.noteService.update(note, id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        this.noteService.delete(id);
        return "Note "+id+" deleted with success!";
    }
}
