package com.ssaffeine.ssaffeine.user.domain;

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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long user_id;  // user_id와 매핑

	@Column(nullable = false, length = 50)
	private String user_name;  // user_name과 매핑

	@Column(nullable = false, length = 50)
	private String login_id;  // login_id 매핑, 최대 50자 제한

	@Column(nullable = false, length = 50)
	private String password;  // password와 매핑, 최대 50자 제한

	@Column(nullable = false)
	private Integer role;  // 0: 사용자, 1: 관리자

	@Column(nullable = false, updatable = false)
	private LocalDateTime created_at;  // created_at과 매핑

	@Column(nullable = false)
	private LocalDateTime updated_at;  // updated_at과 매핑

	@PrePersist
	protected void onCreate() {
		this.created_at = LocalDateTime.now();
		this.updated_at = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updated_at = LocalDateTime.now();
	}
}
