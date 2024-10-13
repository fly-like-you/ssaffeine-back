package com.ssaffeine.ssaffeine.order.dto;

import com.ssaffeine.ssaffeine.order.domain.OrderStatus;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {
    private Long surveyId;  // 설문 ID
    private UUID userId;  // 사용자 ID
    private Long drinkId;  // 음료 ID
    private Integer weekday;  // 주문 요일
    private Integer quantity;  // 주문 수량
    private OrderStatus orderStatus;  // 주문 상태
}