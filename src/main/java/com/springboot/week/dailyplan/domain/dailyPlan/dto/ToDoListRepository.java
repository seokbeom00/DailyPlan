package com.springboot.week.dailyplan.domain.dailyPlan.dto;

import com.springboot.week.dailyplan.domain.dailyPlan.entity.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoListRepository extends JpaRepository<ToDoList, Long> {
}
