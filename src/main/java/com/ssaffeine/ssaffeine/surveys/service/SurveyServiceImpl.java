package com.ssaffeine.ssaffeine.surveys.service;

import com.ssaffeine.ssaffeine.exception.ResourceNotFoundException;
import com.ssaffeine.ssaffeine.order.domain.Order;
import com.ssaffeine.ssaffeine.order.dto.OrderListDto;
import com.ssaffeine.ssaffeine.order.dto.OrderResponseDto;
import com.ssaffeine.ssaffeine.order.service.OrderService;
import com.ssaffeine.ssaffeine.surveys.domain.Survey;
import com.ssaffeine.ssaffeine.surveys.dto.SurveyRequestDto;
import com.ssaffeine.ssaffeine.surveys.dto.SurveyResponseDto;
import com.ssaffeine.ssaffeine.surveys.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SurveyServiceImpl implements SurveyService{
    private final SurveyRepository surveyRepository;
    private final OrderService orderService;

    @Autowired
    public SurveyServiceImpl(SurveyRepository surveyRepository, OrderService orderService) {
        this.surveyRepository = surveyRepository;
        this.orderService = orderService;
    }

    @Override
    public SurveyResponseDto createSurvey(SurveyRequestDto surveyRequestDto) {
        return null;
    }

    @Override
    public SurveyResponseDto getSurveyById(Long surveyId) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new ResourceNotFoundException("Survey not found: " + surveyId));

        SurveyResponseDto surveyResponseDto =  SurveyResponseDto.builder()
                .surveyId(surveyId)
                .
        return null;
    }

    @Override
    public SurveyResponseDto updateSurvey(Long surveyId, SurveyRequestDto surveyRequestDto) {
        return null;
    }

    @Override
    public void deleteSurvey(Long surveyId) {

    }

    public SurveyResponseDto toDto(Survey survey) {
        return null;
//        return SurveyResponseDto.builder()
//                .surveyId(survey.getSurveyId())
//                .title(survey.getTitle())
//                .content(survey.getContent())
//                .username(survey.getUser().getUsername())
//                .goal(survey.getGoal())
//                .surveyStatus(survey.getSurveyStatus())
//                .orderList(survey.getOrders())
//                .build();

    }

    public OrderListDto toDto(List<Order> orderList) {
        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();
        for (Order order : orderList) {
            new OrderResponseDto()
            orderResponseDtoList.add(Order)
        }
        return OrderListDto.builder()
                .orders(orderList)
                .build();
    }
}
