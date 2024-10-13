package com.ssaffeine.ssaffeine.surveys.controller;

import com.ssaffeine.ssaffeine.surveys.dto.SurveyRequestDto;
import com.ssaffeine.ssaffeine.surveys.dto.SurveyResponseDto;
import com.ssaffeine.ssaffeine.surveys.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/survey")
@RestController
public class SurveyController {
    @Autowired
    SurveyService surveyService;

    /**
     * 게시글에 주문할 수 있는가?
     *
     * 권한: 사용자, 관리자
     * @return: 현재 게시글에 주문할 수 있으면 True
     */
    public ResponseEntity<Void> canOrderToSurvey() {

        return null;
    }

    /* ------- CRUD 시작 -------- */

    /**
     * 권한: 관리자만 가능
     * @param surveyRequestDto
     * @return
     */
    @PostMapping
    public ResponseEntity<SurveyResponseDto> createSurvey(@RequestBody SurveyRequestDto surveyRequestDto) {
        SurveyResponseDto createdSurvey = surveyService.createSurvey(surveyRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSurvey);
    }

    /**
     * 권한: 관리자, 사용자 모두 사용가능
     * @param surveyId
     * @return
     */
    @GetMapping("/{surveyId}")
    public ResponseEntity<SurveyResponseDto> getSurveyById(@PathVariable Long surveyId) {
        SurveyResponseDto survey = surveyService.getSurveyById(surveyId);
        return ResponseEntity.ok(survey);
    }

    /**
     *
     * @param surveyId
     * @param surveyRequestDto
     * @return
     */
    @PutMapping("/{surveyId}")
    public ResponseEntity<SurveyResponseDto> updateSurvey(@PathVariable Long surveyId, @RequestBody SurveyRequestDto surveyRequestDto) {
        SurveyResponseDto updatedSurvey = surveyService.updateSurvey(surveyId, surveyRequestDto);
        return ResponseEntity.ok(updatedSurvey);
    }

    /**
     *
     * @param surveyId
     * @return
     */
    @DeleteMapping("/{surveyId}")
    public ResponseEntity<Void> deleteSurvey(@PathVariable Long surveyId) {
        surveyService.deleteSurvey(surveyId);
        return ResponseEntity.noContent().build();
    }
    /* ------- CRUD 종료 -------- */

}
