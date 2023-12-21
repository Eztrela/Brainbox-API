package api.controller;

import api.dto.TagInsertDTO;
import api.model.Tag;
import api.service.TagService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping
    public List<Tag> getAll(){
        return this.tagService.getAll();
    }

    @GetMapping("/{id}")
    public Tag getTagById(@PathVariable("id") Long id){
        return this.tagService.getById(id);
    }

    @PostMapping
    public Tag insert(@RequestBody @Valid TagInsertDTO tag){
        return this.tagService.insert(tag);
    }

    @PutMapping("/{id}")
    public Tag update(@PathVariable("id") Long id, @RequestBody @Valid TagInsertDTO tag){
        return this.tagService.update(tag, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        this.tagService.delete(id);
    }
}
