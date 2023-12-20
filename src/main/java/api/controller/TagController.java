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
        return tagService.getAll();
    }

    @GetMapping("/{id}")
    public Tag getTagById(@PathVariable("id") Long id){
        return tagService.getTagById(id);
    }

    @PostMapping
    public Tag insert(@RequestBody @Valid TagInsetDTO tag){
        Tag newTag = new Tag(tag.title, tag.color);
        return tagService.insert(newTag);
    }

    @PutMapping("/{id}")
    public Tag update(@PathVariable("id") Long id, @RequestBody Tag tag){
        return tagService.update(id, tag);
    }

}
