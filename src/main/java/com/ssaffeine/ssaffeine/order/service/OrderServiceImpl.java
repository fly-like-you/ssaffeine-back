package com.ssaffeine.ssaffeine.order.service;

import com.ssaffeine.ssaffeine.order.dto.OrderRequestDto;
import com.ssaffeine.ssaffeine.order.dto.OrderResponseDto;
import com.ssaffeine.ssaffeine.surveys.repository.SurveyRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    SurveyRepository surveyRepository;

    @Autowired
    public OrderServiceImpl(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @Override
    public boolean canOrder(Long surveyId) {
        return false;
    }

    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {

        // 초기 status는 PENDING 상태 -> DTO에는 안담아도 괜찮음
        return null;
    }

    @Override
    public OrderResponseDto getOrderById(Long orderId) {
        return null;
    }

    @Override
    public List<OrderResponseDto> getAllOrders() {
        return null;
    }

    @Override
    public OrderResponseDto updateOrder(Long orderId, OrderRequestDto orderRequestDto) {
        return null;
    }

    @Override
    public void deleteOrder(Long orderId) {

    }


}
