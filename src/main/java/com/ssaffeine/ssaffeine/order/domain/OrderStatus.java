package com.ssaffeine.ssaffeine.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
	ORDER_PENDING,    // 주문 대기 -> 골 달성 대기
	ORDER_COMPLETE,   // 주문 완료 -> 골 달성 후 입금 완료
	ORDER_CANCELLED   // 주문 취소 -> 사용자 취소, 미결제 취소, 골 미달성
}
