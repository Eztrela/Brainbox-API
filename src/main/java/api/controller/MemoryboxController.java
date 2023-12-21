package api.controller;

import api.dto.MemoryboxInsertDTO;
import api.dto.MemoryboxListingDTO;
import api.dto.MemoryboxUpdateDTO;
import api.dto.UserIdDTO;
import api.model.Memorybox;
import api.service.MemoryboxService;
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
        return this.memoryboxService.getById(id);
    }

    @PostMapping
    public Memorybox insert(@RequestBody @Valid MemoryboxInsertDTO memorybox){
        return this.memoryboxService.insert(memorybox);
    }

    @PostMapping("/user")
    public List<MemoryboxListingDTO> getAllByUserId(@RequestBody @Valid UserIdDTO userId){
        Long id = Long.parseLong(userId.id());
        return this.memoryboxService.getAllByUserId(id);
    }

    @PutMapping("/{id}")
    public Memorybox update(@RequestBody MemoryboxUpdateDTO memorybox, @PathVariable("id") Long id){
        return this.memoryboxService.update(memorybox, id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        this.memoryboxService.delete(id);
        return "Memorybox "+id+" deleted with success!";
    }

}