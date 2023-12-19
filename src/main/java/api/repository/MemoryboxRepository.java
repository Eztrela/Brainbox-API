package api.repository;

import api.model.Memorybox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoryboxRepository extends JpaRepository<Memorybox, Long> {
    public Memorybox findByTitle(String title);
}
