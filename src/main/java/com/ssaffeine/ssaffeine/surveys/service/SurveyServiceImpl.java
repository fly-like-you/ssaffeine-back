package com.ssaffeine.ssaffeine.surveys.service;

import com.ssaffeine.ssaffeine.surveys.dto.SurveyRequestDto;
import com.ssaffeine.ssaffeine.surveys.dto.SurveyResponseDto;
import org.springframework.stereotype.Service;

@Service
public class SurveyServiceImpl implements SurveyService{
    @Override
    public SurveyResponseDto createSurvey(SurveyRequestDto surveyRequestDto) {
        return null;
    }

    @Override
    public SurveyResponseDto getSurveyById(Long surveyId) {
        return null;
    }

    @Override
    public SurveyResponseDto updateSurvey(Long surveyId, SurveyRequestDto surveyRequestDto) {
        return null;
    }

    @Override
    public void deleteSurvey(Long surveyId) {

    }
}
