package com.ssaffeine.ssaffeine.surveys.domain;

import com.ssaffeine.ssaffeine.users.domain.User;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long survey_id;  // survey_id와 매핑

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;  // users 테이블과 연관 관계 (N:1)

	@Column(nullable = false)
	private String title;  // 게시글 제목

	@Column(nullable = false, columnDefinition = "TEXT")
	private String content;  // 게시글 내용

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private SurveyStatus survey_status;  // 설문 상태 (enum: 설문 진행, 입금 대기, 결제 중, 입금 마감, 설문 마감)

	@Column(nullable = false)
	private Integer goal;  // 모집 인원 달성 여부

	@Column(nullable = false, updatable = false)
	private LocalDateTime created_at;  // 작성일

	@PrePersist
	protected void onCreate() {
		this.created_at = LocalDateTime.now();
	}
}

// 설문 진행, 입금 대기, 결제 중, 입금 마감, 설문 마감)
enum SurveyStatus {
	SURVEY_IN_PROGRESS,  // 설문 진행 중
	PAYMENT_IN_PROGRESS, // 결제 중 -> 골 달성 시 넘어감
	SURVEY_CLOSED        // 마감
}
