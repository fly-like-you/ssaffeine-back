package com.ssaffeine.ssaffeine.order.service;

import com.ssaffeine.ssaffeine.drink.domain.Drink;
import com.ssaffeine.ssaffeine.drink.repository.DrinkRepository;
import com.ssaffeine.ssaffeine.order.domain.Order;
import com.ssaffeine.ssaffeine.order.domain.OrderStatus;
import com.ssaffeine.ssaffeine.order.dto.OrderRequestDto;
import com.ssaffeine.ssaffeine.order.dto.OrderResponseDto;
import com.ssaffeine.ssaffeine.order.repository.OrderRepository;
import com.ssaffeine.ssaffeine.surveys.domain.Survey;
import com.ssaffeine.ssaffeine.surveys.repository.SurveyRepository;
import com.ssaffeine.ssaffeine.user.domain.User;
import com.ssaffeine.ssaffeine.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    SurveyRepository surveyRepository;
    UserRepository userRepository;
    OrderRepository orderRepository;
    DrinkRepository drinkRepository;

    @Autowired
    public OrderServiceImpl(SurveyRepository surveyRepository,
                            UserRepository userRepository,
                            OrderRepository orderRepository,
                            DrinkRepository drinkRepository) {
        this.surveyRepository = surveyRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.drinkRepository = drinkRepository;
    }

    @Override
    public boolean canOrder(Long surveyId) {
        return false;
    }

    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        // 초기 status는 PENDING 상태 -> DTO에는 안담아도 괜찮음
        Order order = new Order();

        Survey survey = surveyRepository.findById(orderRequestDto.getSurveyId()).orElse(null);
        order.setSurvey(survey);

        User user = userRepository.findByUuid(orderRequestDto.getUserId());
        order.setUser(user);
        order.setOrderStatus(OrderStatus.ORDER_PENDING);

        Drink drink = drinkRepository.findById(orderRequestDto.getDrinkId()).orElse(null);

        Order savedOrder = orderRepository.save(order);


        OrderResponseDto responseDto = OrderResponseDto.builder()
                .orderId(savedOrder.getOrderId())
                .surveyId(survey.getSurveyId())
                .surveyTitle(survey.getTitle())
                .userId(user.getUuid())
                .username(user.getUsername())
                .drinkId(drink.getDrinkId())
                .drinkName(drink.getName())
                .quantity(orderRequestDto.getQuantity())
                .orderStatus(savedOrder.getOrderStatus())
                .createdAt(savedOrder.getCreatedAt())
                .updatedAt(savedOrder.getUpdatedAt())
                .build();

        return responseDto;
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
