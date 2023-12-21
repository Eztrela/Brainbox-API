package api.service;

import api.dto.TagInsertDTO;
import api.model.Tag;
import api.repository.TagRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public List<Tag> getAll(){
        return tagRepository.findAll().stream().toList();
    }

    public Tag getById(Long id){
        return tagRepository.findById(id).orElse(null);
    }

    @Transactional
    public Tag insert(TagInsertDTO tag){
        Optional<Tag> register = tagRepository.findByTitle(tag.title());
        if (register.isPresent()) throw new RuntimeException("Tag already exists");
        Tag newTag = new Tag(tag.title(), tag.color());
        return tagRepository.save(newTag);
    }

    @Transactional
    public Tag update(TagInsertDTO tag,Long id){
        return tagRepository.findById(id).map(
                register -> {
                    register.setTitle(tag.title());
                    register.setColor(tag.color());
                    return tagRepository.save(register);
                }
        ).orElseThrow(() -> new RuntimeException("Note not found"));
    }

    public void delete(Long id){
        Optional<Tag> register = tagRepository.findById(id);
        if (register.isEmpty()) throw new RuntimeException("Tag " + id + " not found");
        tagRepository.deleteById(id);
    }

}
