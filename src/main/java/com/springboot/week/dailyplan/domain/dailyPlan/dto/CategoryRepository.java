package com.springboot.week.dailyplan.domain.dailyPlan.dto;

import com.springboot.week.dailyplan.domain.dailyPlan.entity.Category;
import com.springboot.week.dailyplan.domain.dailyPlan.entity.CategoryCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryCode(CategoryCode categoryCode);
}
