package api.controller;

import api.dto.NoteInsertDTO;
import api.model.Note;
import api.service.NoteService;
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
    public List<Note> getAll(){
        return this.noteService.getAll();
    }

    @GetMapping("/{id}")
    public Note getNoteById(@PathVariable("id") Long id){
        return this.noteService.getById(id);
    }

    @PostMapping
    public Note insert(@RequestBody @Valid NoteInsertDTO note){
        return this.noteService.insert(note);
    }

    @PutMapping("/{id}")
    public Note update(@PathVariable("id") Long id, @RequestBody @Valid NoteInsertDTO note){
        return this.noteService.update(note, id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        this.noteService.delete(id);
        return "Note "+id+" deleted with success!";
    }
}
