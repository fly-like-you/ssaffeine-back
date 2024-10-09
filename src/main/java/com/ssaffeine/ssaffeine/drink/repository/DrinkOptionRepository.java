package com.ssaffeine.ssaffeine.drink.repository;

import com.ssaffeine.ssaffeine.drink.domain.DrinkOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkOptionRepository extends JpaRepository<DrinkOption, Long> {
}
