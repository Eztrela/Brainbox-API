package api.controller;

import api.model.Memorybox;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/memoryboxes")
public class MemoryboxController {

    @Autowired
    private MemoryboxService memoryboxService;

    @GetMapping
    public List<MemoryboxListingDTO> getAll(){
        return this.memoryboxService.getAll();
    }

    @GetMapping("/{id}")
    public Memorybox getMemoryboxById(@PathVariable("id") Long id){
        return this.memoryboxService.getMemoryboxById(id);
    }

    @PostMapping
    public Memorybox insert(@RequestBody @Valid MemoryboxInsertDTO memorybox){
        Memorybox newMemorybox = new Memorybox(memorybox.title,memorybox.tasks,memorybox.notes,memorybox.tags,memorybox.banner,memorybox.datetimeCreated);
        return this.memoryboxService.insert(newMemorybox);
    }

    @PutMapping("/{id}")
    public Memorybox update(@RequestBody MemoryboxInsertDTO memorybox, @PathVariable("id") Long id){
        return this.memoryboxService.update(memorybox, id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        this.memoryboxService.delete(id);
        return "Memorybox "+id+" deleted with success!";
    }
}