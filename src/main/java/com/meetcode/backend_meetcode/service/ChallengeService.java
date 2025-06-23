package com.meetcode.backend_meetcode.service;

import java.util.List;
import java.util.UUID;

import com.meetcode.backend_meetcode.dto.ParticipantScoreInput;
import com.meetcode.backend_meetcode.entity.Challenge;

public interface ChallengeService {
    Challenge createChallenge(String pid);
    Challenge findOrCreateChallenge(String username);
    Challenge startChallenge(UUID cid);
    Challenge endChallenge(UUID cid, List<ParticipantScoreInput> participantScores);
    Challenge getChallengeById(UUID cid);
    List<Challenge> getAllChallenges();
    List<Challenge> getOpenChallenges();
} 