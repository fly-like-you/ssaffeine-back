package com.ssaffeine.ssaffeine.surveys.service;

import com.ssaffeine.ssaffeine.surveys.dto.SurveyRequestDto;
import com.ssaffeine.ssaffeine.surveys.dto.SurveyResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface SurveyService {
    SurveyResponseDto createSurvey(SurveyRequestDto surveyRequestDto);

    SurveyResponseDto getSurveyById(Long surveyId);

    SurveyResponseDto updateSurvey(Long surveyId, SurveyRequestDto surveyRequestDto);

    void deleteSurvey(Long surveyId);
}
