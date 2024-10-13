package com.ssaffeine.ssaffeine.surveys.domain;

import com.ssaffeine.ssaffeine.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "surveys")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Survey {

	@Id
	@Column(name="survey_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long surveyId;  // survey_id와 매핑

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;  // users 테이블과 연관 관계 (N:1)

	@Column(name="title", nullable = false)
	private String title;  // 게시글 제목

	@Column(name="content", nullable = false, columnDefinition = "TEXT")
	private String content;  // 게시글 내용

	@Enumerated(EnumType.STRING)
	@Column(name="survey_status", nullable = false)
	private SurveyStatus surveyStatus;  // 설문 상태 (enum: 설문 진행, 입금 대기, 결제 중, 입금 마감, 설문 마감)

	@Column(name="goal", nullable = false)
	private Integer goal;  // 모집 인원 달성 여부

	@Column(name="created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;  // 작성일

	@Column(name="updated_at", nullable = false, updatable = false)
	private LocalDateTime updatedAt;  // 수정일

	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	public void preUpdate() {
		this.updatedAt = LocalDateTime.now();
	}
}

