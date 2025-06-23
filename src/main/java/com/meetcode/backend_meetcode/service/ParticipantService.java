package com.meetcode.backend_meetcode.service;

import java.util.List;
import java.util.UUID;

import com.meetcode.backend_meetcode.dto.UserChallengeHistory;
import com.meetcode.backend_meetcode.entity.Participant;

public interface ParticipantService {
    Participant joinChallenge(UUID cid, String username);
    List<Participant> getParticipantsByChallengeId(UUID cid);
    List<Participant> getParticipantsByUsername(String username);
    boolean isParticipantInChallenge(UUID cid, String username);
    void updateParticipantScore(UUID cid, String username, Integer score, Integer rank);
    List<UserChallengeHistory> getUserChallengeHistory(String username);
} 