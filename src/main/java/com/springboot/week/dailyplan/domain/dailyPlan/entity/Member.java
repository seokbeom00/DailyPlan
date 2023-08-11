package com.springboot.week.dailyplan.domain.dailyPlan.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String profileUrl;
    private String name;
    private int planSuccessCount;
    private int challengeSuccessCount;

    @OneToMany(mappedBy = "member")
    private List<DailyPlan> dailyPlanList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Category> categoryList = new ArrayList<>();
    public void addDailyPlan(DailyPlan dailyPlan){
        dailyPlanList.add(dailyPlan);
    }
    public void addCategory(Category category){
        categoryList.add(category);
    }
}
