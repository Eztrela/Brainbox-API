package api.service;

import api.model.Memorybox;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemoryboxService {
    @Autowired
    private MemoryboxRepository memoryboxRepository;

    public List<Memorybox> getAll(){
        return memoryboxRepository.findAll().stream().toList();
    }

    public Memorybox getById(Long id){
        return memoryboxRepository.findById(id).orElse(null);
    }

    @Transactional
    public Memorybox insert(Memorybox memorybox){
        Optional<Memorybox> register = memoryboxRepository.findByTitle(memorybox.getTitle());
        if(register.isPresent()) throw new RuntimeException("Memorybox already exists");
        return memoryboxRepository.save(memorybox);
    }

    @Transactional
    public Memorybox update(Memorybox memorybox){
        return memoryboxRepository.findById(memorybox.getId()).map(
                register -> {
                    register.setTitle(memorybox.getTitle());
                    register.setBanner(memorybox.getBanner());
                    register.setTasks(memorybox.getTasks());
                    register.setNotes(memorybox.getNotes());
                    register.setTags(memorybox.getTags());
                    return memoryboxRepository.save(register);
                }
        ).orElseThrow(() -> new RuntimeException("Memorybox not found"));
    }

    public void delete(Long id){
        Optional<Memorybox> register = memoryboxRepository.findById(id);
        if(register.isEmpty()) throw new RuntimeException("Memorybox " + id + " not found");
        memoryboxRepository.deleteById(id);
    }

    public Memorybox findByTitle(String title){
        return memoryboxRepository.findByTitle(title).orElse(null);
    }


}
