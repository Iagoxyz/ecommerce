package tech.buid.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.buid.ecommerce.entities.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
