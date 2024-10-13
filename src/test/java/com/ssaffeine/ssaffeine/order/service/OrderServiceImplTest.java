package com.ssaffeine.ssaffeine.order.service;

import com.ssaffeine.ssaffeine.drink.domain.Drink;
import com.ssaffeine.ssaffeine.order.domain.Order;
import com.ssaffeine.ssaffeine.order.domain.OrderStatus;
import com.ssaffeine.ssaffeine.order.dto.OrderRequestDto;
import com.ssaffeine.ssaffeine.order.dto.OrderResponseDto;
import com.ssaffeine.ssaffeine.order.repository.OrderRepository;
import com.ssaffeine.ssaffeine.drink.repository.DrinkRepository;
import com.ssaffeine.ssaffeine.surveys.domain.Survey;
import com.ssaffeine.ssaffeine.surveys.repository.SurveyRepository;
import com.ssaffeine.ssaffeine.user.domain.User;
import com.ssaffeine.ssaffeine.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderServiceImplTest {
	@Mock
	private UserRepository userRepository;

	@Mock
	private OrderRepository orderRepository;

	@Mock
	private DrinkRepository drinkRepository;

	@Mock
	private SurveyRepository surveyRepository;

	@InjectMocks
	private OrderServiceImpl orderService;

	private Survey survey;
	private User user;
	private Drink drink;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		// Sample data for testing
		survey = new Survey();
		survey.setSurveyId(1L);
		survey.setTitle("Sample Survey");

		user = new User();
		user.setUserId(UUID.randomUUID()); // UUID로 설정
		user.setUsername("User Name");

		drink = new Drink();
		drink.setDrinkId(1L);
		drink.setName("Sample Drink");
	}

	@Test
	public void testCreateOrder() {
		// Given
		OrderRequestDto orderRequestDto = new OrderRequestDto();
		orderRequestDto.setSurveyId(1L);
		orderRequestDto.setUserId(user.getUserId()); // UUID를 문자열로 설정
		orderRequestDto.setDrinkId(1L);
		orderRequestDto.setQuantity(2);

		when(surveyRepository.findById(1L)).thenReturn(Optional.of(survey));
		when(userRepository.findByUserId(user.getUserId())).thenReturn(user);
		when(drinkRepository.findById(1L)).thenReturn(Optional.of(drink));

		Order savedOrder = new Order();
		savedOrder.setOrderId(1L);
		savedOrder.setSurvey(survey);
		savedOrder.setUser(user);
		savedOrder.setOrderStatus(OrderStatus.ORDER_PENDING);
		when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

		// When
		OrderResponseDto responseDto = orderService.createOrder(orderRequestDto);

		// Then
		assertEquals(savedOrder.getOrderId(), responseDto.getOrderId());
		assertEquals(survey.getSurveyId(), responseDto.getSurveyId());
		assertEquals(survey.getTitle(), responseDto.getSurveyTitle());
		assertEquals(user.getUserId(), responseDto.getUserId()); // UUID를 문자열로 변환하여 비교
		assertEquals(user.getUsername(), responseDto.getUsername());
		assertEquals(drink.getDrinkId(), responseDto.getDrinkId());
		assertEquals(drink.getName(), responseDto.getDrinkName());
		assertEquals(orderRequestDto.getQuantity(), responseDto.getQuantity());
		assertEquals(OrderStatus.ORDER_PENDING, responseDto.getOrderStatus());
	}
}
