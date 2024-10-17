package com.ssaffeine.ssaffeine.surveys.service;

import com.ssaffeine.ssaffeine.exception.ResourceNotFoundException;
import com.ssaffeine.ssaffeine.order.domain.Order;
import com.ssaffeine.ssaffeine.order.dto.OrderListResponseDto;
import com.ssaffeine.ssaffeine.order.dto.OrderResponseDto;
import com.ssaffeine.ssaffeine.order.service.OrderService;
import com.ssaffeine.ssaffeine.surveys.domain.Survey;
import com.ssaffeine.ssaffeine.surveys.dto.SurveyRequestDto;
import com.ssaffeine.ssaffeine.surveys.dto.SurveyResponseDto;
import com.ssaffeine.ssaffeine.surveys.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        // Order 엔티티 리스트를 OrderResponseDto 리스트로 변환
        return toDto(survey);
    }


    @Override
    public SurveyResponseDto updateSurvey(Long surveyId, SurveyRequestDto surveyRequestDto) {
        return null;
    }

    @Override
    public void deleteSurvey(Long surveyId) {

    }

    public SurveyResponseDto toDto(Survey survey) {
        OrderListResponseDto orderList = toDto(survey.getOrders());

        return SurveyResponseDto.builder()
                .surveyId(survey.getSurveyId())
                .title(survey.getTitle())
                .content(survey.getContent())
                .username(survey.getUser().getUsername())
                .goal(survey.getGoal())
                .surveyStatus(survey.getSurveyStatus())
                .orderList(orderList)
                .build();
    }

    public OrderListResponseDto toDto(List<Order> orderList) {
        return new OrderListResponseDto(
                orderList.stream()
                        .map(order -> OrderResponseDto.builder()
                                .orderId(order.getOrderId())
                                .userId(order.getUser().getUuid())
                                .orderStatus(order.getOrderStatus())
                                .createdAt(order.getCreatedAt())
                                .updatedAt(order.getUpdatedAt())
                                .build()
                        ).toList()
        );
    }
}
