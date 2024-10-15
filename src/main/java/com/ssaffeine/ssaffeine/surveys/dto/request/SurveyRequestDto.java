package com.ssaffeine.ssaffeine.surveys.dto.request;

import com.ssaffeine.ssaffeine.surveys.domain.SurveyStatus;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SurveyRequestDto {
    private UUID userId;  // 설문을 작성하는 사용자 ID
    private String title;  // 게시글 제목
    private String content;  // 게시글 내용
    private SurveyStatus surveyStatus;  // 설문 상태
    private Integer goal;  // 목표 인원
}