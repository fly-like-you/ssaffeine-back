package com.ssaffeine.ssaffeine.order.service;

import com.ssaffeine.ssaffeine.order.dto.OrderRequestDto;
import com.ssaffeine.ssaffeine.order.dto.OrderResponseDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    boolean canOrder(Long surveyId);

    OrderResponseDto createOrder(OrderRequestDto orderRequestDto);
    OrderResponseDto getOrderById(Long orderId);

    List<OrderResponseDto> getAllOrders();

    OrderResponseDto updateOrder(Long orderId, OrderRequestDto orderRequestDto);

    void deleteOrder(Long orderId);


}
