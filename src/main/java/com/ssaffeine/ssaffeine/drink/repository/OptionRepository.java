package com.ssaffeine.ssaffeine.drink.repository;

import com.ssaffeine.ssaffeine.drink.domain.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {
}
