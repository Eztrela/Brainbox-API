package api.service;

import api.dto.MemoryboxInsertDTO;
import api.dto.MemoryboxListingDTO;
import api.dto.MemoryboxUpdateDTO;
import api.dto.UserIdDTO;
import api.model.Memorybox;
import api.model.User;
import api.repository.MemoryboxRepository;
import api.repository.UserRepository;
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
