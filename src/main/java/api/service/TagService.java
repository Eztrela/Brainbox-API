package api.service;

import api.model.Tag;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    @Autowired
    private NoteRepository tagRepository;

    public List<Tag> getAll(){
        return tagRepository.findAll().stream().toList();
    }

    public Tag getById(Long id){
        return tagRepository.findById(id).orElse(null);
    }

    @Transactional
    public Tag insert(Tag tag){
        Optional<Tag> register = tagRepository.findByTitle(tag.getTitle());
        if (register.isPresent()) throw new RuntimeException("Tag already exists");
        return tagRepository.save(tag);
    }

    @Transactional
    public Tag update(TagInsertDTO tag,Long id){
        return tagRepository.findById(id).map(
                register -> {
                    register.setContent(tag.getTitle());
                    register.setColor(tag.getColor());
                    return tagRepository.save(register);
                }
        ).orElseThrow(() -> new RuntimeException("Note not found"));
    }

    public void delete(Long id){
        Optional<Tag> register = tagRepository.findById(id);
        if (register.isEmpty()) throw new RuntimeException("Tag " + id + " not found");
        tagRepository.deleteById(id);
    }

    public Tag findByContent(String title){
        return tagRepository.findByContent(title).orelse(null);
    }
}
