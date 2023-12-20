package api.repository;

import api.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    public Tag findByTitle(String title);

    public Optional<Tag> findByColor(String color);
}
