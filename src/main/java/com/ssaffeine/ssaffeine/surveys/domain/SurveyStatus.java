package com.ssaffeine.ssaffeine.surveys.domain;

// 설문 진행, 입금 대기, 결제 중, 입금 마감, 설문 마감)
public enum SurveyStatus {
	SURVEY_IN_PROGRESS,  // 설문 진행 중
	PAYMENT_IN_PROGRESS, // 결제 중 -> 골 달성 시 넘어감
	SURVEY_CLOSED        // 마감
}
