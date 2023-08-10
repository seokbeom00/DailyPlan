package com.springboot.week.dailyplan.domain.dailyPlan.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponseDto {
    private Long id;
    private String email;
    private String profileUrl;
    private String name;
    private int planSuccessCount;
    private int challengeSuccessCount;

    @Builder
    public MemberResponseDto(Long id, String email, String profileUrl,
                             String name, int planSuccessCount, int challengeSuccessCount){
        this.id = id;
        this.email = email;
        this.profileUrl = profileUrl;
        this.name = name;
        this.planSuccessCount = planSuccessCount;
        this.challengeSuccessCount = challengeSuccessCount;
    }
}
