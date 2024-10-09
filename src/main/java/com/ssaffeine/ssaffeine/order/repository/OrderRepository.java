package com.ssaffeine.ssaffeine.order.repository;

import com.ssaffeine.ssaffeine.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
