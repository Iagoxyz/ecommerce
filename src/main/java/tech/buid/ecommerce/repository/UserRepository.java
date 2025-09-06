package tech.buid.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.buid.ecommerce.entities.UserEntity;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
}
