package com.springboot.week.dailyplan.domain.dailyPlan.dto;

import com.springboot.week.dailyplan.domain.dailyPlan.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
}
