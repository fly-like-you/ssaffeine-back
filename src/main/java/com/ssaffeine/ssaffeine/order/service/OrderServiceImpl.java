package com.ssaffeine.ssaffeine.order.service;

import com.ssaffeine.ssaffeine.order.dto.OrderRequestDto;
import com.ssaffeine.ssaffeine.order.dto.OrderResponseDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
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
