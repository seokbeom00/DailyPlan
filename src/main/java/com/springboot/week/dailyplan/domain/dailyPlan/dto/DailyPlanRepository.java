package com.springboot.week.dailyplan.domain.dailyPlan.dto;

import com.springboot.week.dailyplan.domain.dailyPlan.entity.DailyPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DailyPlanRepository extends JpaRepository<DailyPlan, Long> {
    Optional<DailyPlan> findByYearmonthAndDate(String yearmonth, String date);
}
