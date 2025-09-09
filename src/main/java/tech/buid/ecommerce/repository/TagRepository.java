package tech.buid.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.buid.ecommerce.entities.TagEntity;

public interface TagRepository extends JpaRepository<TagEntity, Long> {
}
