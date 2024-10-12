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

    // orders 는 사용자 자신의 주문만 조회 수정 삭제할 수 있다.
    // 사용자는 자신의 주문을 각 survey에 한번만 생성가능하다 (악용 방지)
    // 사용자는 order_status에 접근 불가능하다.
    // 관리자는 주문의 status 만 변경 시킬 수 있다.
    // 관리자는 order id 로 조회하거나, 특정 사용자의 id의 모든 order 를 조회할 수 있다.
    //

    /**
     * 게시글에 주문할 수 있는가?
     * 중요도: 상
     * 권한: 사용자, 관리자
     * @return: 현재 게시글에 주문할 수 있으면 True
     */
    @GetMapping("/canOrder/{surveyId}")
    public ResponseEntity<Boolean> canOrder(@PathVariable Long surveyId) {
        // TODO: 현재 게시글의 상태를 확인하여 주문이 가능한지 판단, 사용자 인증 필요
        boolean canOrder = orderService.canOrder(surveyId);

        return ResponseEntity.ok(canOrder);
    }

    /* ------- CRUD 시작 -------- */
    /**
     * 주문 생성하기
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
        // TODO: 관리자 인증 필요
        OrderResponseDto order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }

    // TODO: 사용자 본인의 주문을 확인할 수 있는 메서드, 권한은 사용자

    /**
     * 권한: 관리자만 가능
     * @return
     */
    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        // TODO: 관리자 인증 필요
        List<OrderResponseDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    /**
     * 권한: 관리자
     *
     * @param orderId
     * @param orderRequestDto
     * @return
     */
    @PutMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> updateOrder(
            @PathVariable Long orderId,
            @RequestBody OrderRequestDto orderRequestDto
    ) {
        // TODO: 관리자 인증 필요
        OrderResponseDto updatedOrder = orderService.updateOrder(orderId, orderRequestDto);
        return ResponseEntity.ok(updatedOrder);
    }

    // TODO: 사용자 본인의 주문을 수정할 수 있는 메서드, 권한은 사용자, 상태에 따라서 삭제 불가능할 수도

    /**
     * 권한: 관리자
     * @param orderId
     * @return
     */
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        // TODO: 관리자 인증 필요
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    // TODO: 사용자 본인의 주문을 삭제할 수 있는 메서드, 권한은 사용자

    /* ------- CRUD 종료 -------- */


}
