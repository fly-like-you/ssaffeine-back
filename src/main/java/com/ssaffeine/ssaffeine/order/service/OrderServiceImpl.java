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
import jakarta.persistence.Id;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        User user = userRepository.findByUserId(orderRequestDto.getUserId());
        order.setUser(user);
        order.setOrderStatus(OrderStatus.ORDER_PENDING);

        Drink drink = drinkRepository.findById(orderRequestDto.getDrinkId()).orElse(null);

        Order savedOrder = orderRepository.save(order);
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setOrderId(savedOrder.getOrderId());
        responseDto.setSurveyId(survey.getSurvey_id());
        responseDto.setSurveyTitle(survey.getTitle()); // 설문 제목
        responseDto.setUserId(user.getUserId());
        responseDto.setUserName(user.getUsername()); // 사용자 이름
        responseDto.setDrinkId(orderRequestDto.getDrinkId()); // 주문한 음료 ID
        responseDto.setDrinkName(drink.getName()); // 주문한 음료 이름
        responseDto.setQuantity(orderRequestDto.getQuantity()); // 주문 수량
        responseDto.setOrderStatus(savedOrder.getOrderStatus());
        responseDto.setCreatedAt(savedOrder.getCreatedAt()); // 생성일
        responseDto.setUpdatedAt(savedOrder.getUpdatedAt()); // 수정일

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
