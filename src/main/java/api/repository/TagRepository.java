package api.repository;

import api.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    public Tag findByTitle(String title);

    public List<Tag> findByColor(String color);
}
