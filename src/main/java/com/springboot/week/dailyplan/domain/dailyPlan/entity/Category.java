package com.springboot.week.dailyplan.domain.dailyPlan.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int successDailyPlanCount;
    private int countByDailyPlan;
    @ManyToOne
    @JoinColumn(name = "member_id)")
    private Member member;
    private CategoryCode categoryCode;
}
