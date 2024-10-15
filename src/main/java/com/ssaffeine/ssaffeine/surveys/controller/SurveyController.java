package com.ssaffeine.ssaffeine.surveys.controller;

import com.ssaffeine.ssaffeine.surveys.dto.request.SurveyRequestDto;
import com.ssaffeine.ssaffeine.surveys.dto.response.SurveyResponseDto;
import com.ssaffeine.ssaffeine.surveys.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/survey")
@RestController
public class SurveyController implements SurveyControllerDocs{
    SurveyService surveyService;

    @Autowired
    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    public ResponseEntity<Void> canOrderToSurvey() {

        return null;
    }

    /* ------- CRUD 시작 -------- */
    // 이번주 달성률 조회 -> 날자만 있으면 댐
    // 만약에 없으면 자동으로 만들기

    // survey id요청하는거 마감?


    @PostMapping
    public ResponseEntity<SurveyResponseDto> createSurvey(@RequestBody SurveyRequestDto surveyRequestDto) {
        SurveyResponseDto createdSurvey = surveyService.createSurvey(surveyRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSurvey);
    }

    @GetMapping("/{surveyId}")
    public ResponseEntity<SurveyResponseDto> getSurveyById(@PathVariable Long surveyId) {
        SurveyResponseDto survey = surveyService.getSurveyById(surveyId);
        return ResponseEntity.ok(survey);
    }

    @PutMapping("/{surveyId}")
    public ResponseEntity<SurveyResponseDto> updateSurvey(@PathVariable Long surveyId, @RequestBody SurveyRequestDto surveyRequestDto) {
        SurveyResponseDto updatedSurvey = surveyService.updateSurvey(surveyId, surveyRequestDto);
        return ResponseEntity.ok(updatedSurvey);
    }

    @DeleteMapping("/{surveyId}")
    public ResponseEntity<Void> deleteSurvey(@PathVariable Long surveyId) {
        surveyService.deleteSurvey(surveyId);
        return ResponseEntity.noContent().build();
    }
    /* ------- CRUD 종료 -------- */

}
