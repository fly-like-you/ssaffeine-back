package com.ssaffeine.ssaffeine.order.domain;

import com.ssaffeine.ssaffeine.surveys.domain.Survey;
import com.ssaffeine.ssaffeine.users.domain.User;
import com.ssaffeine.ssaffeine.users.domain.Drink;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;  // order_id와 매핑

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "survey_id", nullable = false)
	private Survey survey;  // surveys 테이블과 연관 관계 (N:1)

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;  // users 테이블과 연관 관계 (N:1)

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "drink_id", nullable = false)
	private Drink drink;  // drinks 테이블과 연관 관계 (N:1)

	@Column(nullable = false)
	private Integer weekday;  // 주문 요일

	@Column(nullable = false)
	private Integer quantity;  // 주문 수량

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private OrderStatus orderStatus;  // 주문 상태 (enum: 주문 대기, 주문 완료, 주문 취소)

	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;  // created_at과 매핑

	@Column(nullable = false)
	private LocalDateTime updatedAt;  // updated_at과 매핑

	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
}

@PreUpdate
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}
}

enum OrderStatus {
	ORDER_PENDING,    // 주문 대기 -> 골 달성 대기
	ORDER_COMPLETE,   // 주문 완료 -> 골 달성 후 입금 완료
	ORDER_CANCELLED   // 주문 취소 -> 사용자 취소, 미결제 취소, 골 미달성
}