package com.springboot.week.dailyplan.domain.dailyPlan.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int successToDoCount = 0;
    private int countByToDo = 0;
    @ManyToOne
    @JoinColumn(name = "member_id)")
    private Member member;
    private CategoryCode categoryCode;
    @OneToMany(mappedBy = "category")
    private List<ToDoList> toDoLists = new ArrayList<>();

    public void addTodo(ToDoList toDoList){
        toDoLists.add(toDoList);
    }
}
