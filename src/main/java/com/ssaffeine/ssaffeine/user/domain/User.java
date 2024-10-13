package com.ssaffeine.ssaffeine.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)", name = "user_id", updatable = false, nullable = false, unique = true)
	private UUID userId;

	@Column(name = "login_id", nullable = false, length = 50)
	private String loginId;

	@Column(name = "username", nullable = false, length = 50)
	private String username;

	@Column(name = "student_number", unique = true, length = 10)
	private String studentNumber;

	@Column(name = "region")
	@Enumerated(value = EnumType.STRING)
	private Region region;

	@Column(name = "user_group")
	private Integer group;

	@Column(name = "password", nullable = false, length = 60)
	private String password;

	@Column(name = "user_role", nullable = false)
	private UserRole role;

	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	// 생성자 등 추가로 필요시 Lombok이 자동으로 생성해줍니다

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
