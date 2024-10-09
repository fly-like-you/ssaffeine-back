package com.ssaffeine.ssaffeine.users.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

//	// 객체 생성 메서드 Builder pattern
//	private static void toto() {
//		new User.UserBuilder().
//				password("111").
//				userName("111").
//				role(1).
//				build();
//	}
//
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;  // user_id와 매핑

	@Column(nullable = false, length = 50)
	private String userName;  // user_name과 매핑

	@Column(nullable = false, length = 50)
	private String password;  // password와 매핑, 최대 50자 제한

	@Column(nullable = false)
	private Integer role;  // 0: 사용자, 1: 관리자

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