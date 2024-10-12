package com.ssaffeine.ssaffeine.cafe.repository;

import com.ssaffeine.ssaffeine.cafe.domain.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CafeRepository extends JpaRepository<Cafe, Long> {

}
