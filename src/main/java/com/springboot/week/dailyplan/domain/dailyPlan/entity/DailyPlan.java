package com.springboot.week.dailyplan.domain.dailyPlan.entity;

import jakarta.persistence.*;

@Entity
public class DailyPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long member_id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(length = 200, nullable = false)
    private String title;
}
