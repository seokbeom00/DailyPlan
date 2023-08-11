package com.springboot.week.dailyplan.domain.dailyPlan.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class DailyPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String yearmonth;
    private String date;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @OneToMany(mappedBy = "dailyPlan")
    private List<ToDoList> toDoLists = new ArrayList<>();
    public void addTodo(ToDoList toDoList){
        toDoLists.add(toDoList);
    }
}
