package tech.buid.ecommerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.buid.ecommerce.controller.dto.ApiResponse;
import tech.buid.ecommerce.controller.dto.CreateOrderDto;
import tech.buid.ecommerce.controller.dto.OrderSummaryDto;
import tech.buid.ecommerce.controller.dto.PaginationResponse;
import tech.buid.ecommerce.entities.OrderEntity;
import tech.buid.ecommerce.service.OrderService;

import java.net.URI;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody CreateOrderDto dto) {

        var order = orderService.createOrder(dto);
        return ResponseEntity.created(URI.create("/orders/" + order.getOrderId())).build();

    }

    @GetMapping
    public ResponseEntity<ApiResponse<OrderSummaryDto>> listOrders(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {

        var resp = orderService.findAll(page, pageSize);
        return ResponseEntity.ok(new ApiResponse<>(
                resp.getContent(),
                new PaginationResponse(resp.getNumber(), resp.getSize(), resp.getTotalElements(), resp.getTotalPages())
                ));

    }
}
