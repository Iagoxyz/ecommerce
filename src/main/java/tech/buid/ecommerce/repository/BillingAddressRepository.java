package tech.buid.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.buid.ecommerce.entities.BillingAddressEntity;

public interface BillingAddressRepository extends JpaRepository<BillingAddressEntity, Long> {
}
