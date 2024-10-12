package com.ssaffeine.ssaffeine.order.controller;

import com.ssaffeine.ssaffeine.order.domain.Order;
import com.ssaffeine.ssaffeine.order.dto.OrderRequestDto;
import com.ssaffeine.ssaffeine.order.dto.OrderResponseDto;
import com.ssaffeine.ssaffeine.order.service.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 유저의 주문을 CRUD하는 컨트롤러
 * 어떤 커피를 살지, 어떤 요일에 커피를 주문할 지
 * 또한, 주문은 현재 설문의 기한에 따라 상태를 가진다.
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    /* ------- CRUD 시작 -------- */
    /**
     * 권한: 모든 사용자 가능
     * @param orderRequestDto
     * @return
     */
    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        OrderResponseDto createdOrder = orderService.createOrder(orderRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    /**
     * 권한: 관리자만 가능
     * @param orderId
     * @return
     */
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long orderId) {
        OrderResponseDto order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }

    /**
     * 권한: 관리자만 가능
     * @return
     */
    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        List<OrderResponseDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    /**
     * 권한: 모든 사용자 가능
     * @param orderId
     * @param orderRequestDto
     * @return
     */
    @PutMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> updateOrder(@PathVariable Long orderId, @RequestBody OrderRequestDto orderRequestDto) {
        OrderResponseDto updatedOrder = orderService.updateOrder(orderId, orderRequestDto);
        return ResponseEntity.ok(updatedOrder);
    }

    /**
     * 권한: 모든 사용자 가능
     * @param orderId
     * @return
     */
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    /* ------- CRUD 종료 -------- */


}
