package com.meetcode.backend_meetcode.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserChallengeHistory {
    private UUID cid;
    private String pid;
    private String status;
    private String startDate;
    private String endDate;
    private Integer userScore;
    private Integer userRank;
    private String participationTime;
} 