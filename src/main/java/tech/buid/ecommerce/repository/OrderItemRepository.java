package tech.buid.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.buid.ecommerce.entities.OrderItemEntity;
import tech.buid.ecommerce.entities.OrderItemId;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, OrderItemId> {
}
