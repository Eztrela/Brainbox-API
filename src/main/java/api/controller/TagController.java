package api.controller;

import api.model.Tag;
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
    public List<TagListingDTO> getAll(){
        return this.tagService.getAll();
    }

    @GetMapping("/{id}")
    public Tag getTagById(@PathVariable("id") Long id){
        return this.tagService.getTagById(id);
    }

    @PostMapping
    public Tag insert(@RequestBody @Valid TagInsetDTO tag){
        Tag newTag = new Tag(tag.title, tag.color);
        return this.tagService.insert(newTag);
    }

    @PutMapping("/{id}")
    public Tag update(@PathVariable("id") Long id, @RequestBody Tag tag){
        return this.tagService.update(tag, id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        this.tagService.delete(id);
        return "Tag "+id+" deleted with success!";
    }
}
