package tech.buid.ecommerce.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tech.buid.ecommerce.controller.dto.CreateOrderDto;
import tech.buid.ecommerce.controller.dto.OrderItemDto;
import tech.buid.ecommerce.controller.dto.OrderSummaryDto;
import tech.buid.ecommerce.entities.*;
import tech.buid.ecommerce.exception.CreateOrderException;
import tech.buid.ecommerce.repository.OrderRepository;
import tech.buid.ecommerce.repository.ProductRepository;
import tech.buid.ecommerce.repository.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public OrderEntity createOrder(CreateOrderDto dto) {

        var order = new OrderEntity();

        // 1. Validate user
        var user = validateUser(dto);

        // 2. Validate Order Item
        var orderItems = validateOrderItems(order, dto);

        // 3. Calculate order total
        var total = calculateOrderTotal(orderItems);

        // 4. Map to Entity
        order.setOrderDate(LocalDateTime.now());
        order.setUser(user);
        order.setItems(orderItems);
        order.setTotal(total);

        // 5. Repósitory save
        return orderRepository.save(order);
    }

    private BigDecimal calculateOrderTotal(List<OrderItemEntity> items) {
        return items.stream()
                .map(i -> i.getSalePrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private List<OrderItemEntity> validateOrderItems(OrderEntity order, CreateOrderDto dto) {

        if (dto.items().isEmpty()) {
            throw new CreateOrderException("order items is empty");
        }

        return dto.items().stream()
                .map(ordemItemDto -> getOrderItem(order, ordemItemDto))
                .toList();
    }

    private OrderItemEntity getOrderItem(OrderEntity order, OrderItemDto ordemItemDto) {
        return null;
    }

    private ProductEntity getProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new CreateOrderException("product not found"));
    }

    private UserEntity validateUser(CreateOrderDto dto) {
        return userRepository.findById(dto.userId())
                .orElseThrow(() -> new CreateOrderException("user not found"));
    }

    public Page<OrderSummaryDto> findAll(Integer page, Integer pageSize) {

        return orderRepository.findAll(PageRequest.of(page, pageSize))
                .map(entity -> {
                    return new OrderSummaryDto(
                            entity.getOrderId(),
                            entity.getOrderDate(),
                            entity.getUser().getUserId(),
                            entity.getTotal()
                    );
                });
    }
}
