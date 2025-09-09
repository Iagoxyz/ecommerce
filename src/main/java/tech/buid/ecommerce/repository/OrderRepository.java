package tech.buid.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.buid.ecommerce.entities.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
