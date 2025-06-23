package com.meetcode.backend_meetcode.graphql;

import java.util.List;
import java.util.UUID;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.meetcode.backend_meetcode.dto.ParticipantScoreInput;
import com.meetcode.backend_meetcode.entity.Challenge;
import com.meetcode.backend_meetcode.entity.Participant;
import com.meetcode.backend_meetcode.service.ChallengeService;
import com.meetcode.backend_meetcode.service.ParticipantService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class ChallengeResolver {

    private final ChallengeService challengeService;
    private final ParticipantService participantService;

    @MutationMapping
    public Challenge createChallenge(@Argument String pid) {
        return challengeService.createChallenge(pid);
    }

    @MutationMapping
    public Challenge findOrCreateLobby(@Argument String username) {
        System.out.println("username: " + username);
        Challenge challenge = challengeService.findOrCreateChallenge(username);
        System.out.println("Challenge returned from service: " + challenge);
        
        // Explicitly fetch and set participants to avoid lazy loading issues
        List<Participant> participants = participantService.getParticipantsByChallengeId(challenge.getCid());
        challenge.setParticipants(participants);
        System.out.println("Challenge with participants loaded: " + participants.size() + " participants");
        
        return challenge;
    }

    @MutationMapping
    public Challenge startChallenge(@Argument String cid) {
        UUID challengeId = UUID.fromString(cid);
        return challengeService.startChallenge(challengeId);
    }

    @MutationMapping
    public Challenge endChallenge(@Argument String cid, @Argument("participantScores") List<ParticipantScoreInput> participantScores) {
        UUID challengeId = UUID.fromString(cid);
        Challenge challenge = challengeService.endChallenge(challengeId, participantScores);
        
        // Explicitly fetch and set participants to show updated scores
        List<Participant> participants = participantService.getParticipantsByChallengeId(challenge.getCid());
        challenge.setParticipants(participants);
        
        return challenge;
    }

    @QueryMapping
    public Challenge getChallengeById(@Argument String cid) {
        UUID challengeId = UUID.fromString(cid);
        return challengeService.getChallengeById(challengeId);
    }

    @QueryMapping
    public List<Challenge> getAllChallenges() {
        return challengeService.getAllChallenges();
    }

    @QueryMapping
    public List<Challenge> getOpenChallenges() {
        return challengeService.getOpenChallenges();
    }
} 