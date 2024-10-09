package com.ssaffeine.ssaffeine.surveys.repository;

import com.ssaffeine.ssaffeine.surveys.domain.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {
}
