package com.ssaffeine.ssaffeine.surveys.dto;

import com.ssaffeine.ssaffeine.surveys.domain.SurveyStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// SurveyResponseDto
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SurveyResponseDto {
    private Long surveyId;  // 설문 ID
    private Long userId;  // 설문을 작성한 사용자 ID
    private String userName;  // 작성자 이름
    private String title;  // 게시글 제목
    private String content;  // 게시글 내용
    private SurveyStatus surveyStatus;  // 설문 상태
    private Integer goal;  // 목표 인원
    private LocalDateTime createdAt;  // 작성일
}