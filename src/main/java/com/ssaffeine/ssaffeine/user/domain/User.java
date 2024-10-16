package com.ssaffeine.ssaffeine.user.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

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

	@Column(name = "region")
	@Enumerated(value = EnumType.STRING)
	private Region region;

	@Column(name = "user_group")
	private Integer group;

	@Column(name = "semester")
	private Integer semester;

	@Column(name = "password", nullable = false, length = 60)
	private String password;

	@Column(name = "user_role", nullable = false)
	private UserRole role;

	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

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
