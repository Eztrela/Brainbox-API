package api.service;

import api.dto.MemoryboxInsertDTO;
import api.dto.MemoryboxListingDTO;
import api.dto.MemoryboxUpdateDTO;
import api.dto.UserIdDTO;
import api.model.*;
import api.repository.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemoryboxService {
    @Autowired
    private MemoryboxRepository memoryboxRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private NoteRepository noteRepository;

    public List<MemoryboxListingDTO> getAll(){
        return this.convertToDTOList(memoryboxRepository.findAll());
    }

    public Memorybox getById(Long id){
        return memoryboxRepository.findById(id).orElse(null);
    }

    @Transactional
    public Memorybox insert(MemoryboxInsertDTO memorybox){
        Optional<Memorybox> register = memoryboxRepository.findByTitle(memorybox.title());
        if(register.isPresent()) throw new RuntimeException("Memorybox already exists");
        Optional<User> userRegister = userRepository.findById(memorybox.userId());
        if(userRegister.isEmpty()) throw new RuntimeException("Memorybox User does not exists");
        Memorybox newMemorybox = new Memorybox(memorybox.title(), memorybox.banner(), userRegister.get());
        return memoryboxRepository.save(newMemorybox);
    }

    @Transactional
    public Memorybox update(MemoryboxUpdateDTO memorybox, Long id){
        return memoryboxRepository.findById(id).map(
                register -> {
                    memorybox.tasks().forEach(task -> {
                        if(task.getId() != null) {
                            Optional<Task> taskRegister = this.taskRepository.findById(task.getId());
                            if (taskRegister.isEmpty()) throw new RuntimeException("Task does not exists");
                        }
                    });
                    memorybox.notes().forEach(note -> {
                        if(note.getId() != null) {
                            Optional<Note> noteRegister = this.noteRepository.findById(note.getId());
                            if (noteRegister.isEmpty()) throw new RuntimeException("Note does not exists");
                        }
                    });
                    memorybox.tags().forEach(tag -> {
                        if(tag.getId() != null) {
                            Optional<Tag> tagRegister = this.tagRepository.findById(tag.getId());
                            if (tagRegister.isEmpty()) throw new RuntimeException("Tag does not exists");
                        }
                    });
                    register.setTitle(memorybox.title());
                    register.setBanner(memorybox.banner());
                    register.setTasks(memorybox.tasks());
                    register.setNotes(memorybox.notes());
                    register.setTags(memorybox.tags());
                    return memoryboxRepository.save(register);
                }
        ).orElseThrow(() -> new RuntimeException("Memorybox not found"));
    }

    public List<MemoryboxListingDTO> getAllByUserId(Long id){
        return this.convertToDTOList(this.memoryboxRepository.getAllByUserId(id));
    }

    public void delete(Long id){
        Optional<Memorybox> register = memoryboxRepository.findById(id);
        if(register.isEmpty()) throw new RuntimeException("Memorybox " + id + " not found");
        memoryboxRepository.deleteById(id);
    }

    public Memorybox findByTitle(String title){
        return memoryboxRepository.findByTitle(title).orElse(null);
    }


    public List<MemoryboxListingDTO> convertToDTOList(List<Memorybox> memoryboxList) {
        return memoryboxList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public MemoryboxListingDTO convertToDTO(Memorybox memorybox) {
        return new MemoryboxListingDTO(
                memorybox.getId(),
                memorybox.getTitle(),
                memorybox.getDatetimeCreated(),
                memorybox.getTasks(),
                memorybox.getNotes(),
                memorybox.getTags(),
                memorybox.getBanner()
        );
    }

}
