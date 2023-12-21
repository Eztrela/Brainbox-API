package api.repository;

import api.dto.MemoryboxListingDTO;
import api.model.Memorybox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemoryboxRepository extends JpaRepository<Memorybox, Long> {
    public Optional<Memorybox> findByTitle(String title);

    public List<Memorybox> getAllByUserId(Long id);
}
