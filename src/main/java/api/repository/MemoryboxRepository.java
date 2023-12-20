package api.repository;

import api.model.Memorybox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemoryboxRepository extends JpaRepository<Memorybox, Long> {
    public Optional<Memorybox> findByTitle(String title);
}
