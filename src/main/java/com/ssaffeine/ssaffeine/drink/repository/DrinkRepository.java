package com.ssaffeine.ssaffeine.drink.repository;

import com.ssaffeine.ssaffeine.drink.domain.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long> {
}
