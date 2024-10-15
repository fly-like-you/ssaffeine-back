package com.ssaffeine.ssaffeine.order.dto;

import com.ssaffeine.ssaffeine.order.domain.Order;
import com.ssaffeine.ssaffeine.order.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    private Long orderId;  // 주문 ID
    private Long surveyId;  // 설문 ID
    private String surveyTitle;  // 설문 제목
    private UUID userId;  // 사용자 ID
    private String username;  // 사용자 이름
    private Long drinkId;  // 음료 ID
    private String drinkName;  // 음료 이름
    private Integer weekday;  // 주문 요일
    private Integer quantity;  // 주문 수량
    private Order order;
    private OrderStatus orderStatus;  // 주문 상태
    private LocalDateTime createdAt;  // 생성일
    private LocalDateTime updatedAt;  // 수정일
}