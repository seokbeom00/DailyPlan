package com.springboot.week.dailyplan.domain.dailyPlan.entity;

import com.springboot.week.dailyplan.domain.dailyPlan.entity.DailyPlan;
import com.springboot.week.dailyplan.domain.dailyPlan.entity.Member;
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
    private int successCount;
    @ManyToOne
    @JoinColumn(name = "member_id)")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "dailyplan_id)")
    private DailyPlan dailyPlan;
}
