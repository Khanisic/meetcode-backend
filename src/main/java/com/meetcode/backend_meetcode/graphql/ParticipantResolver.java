package com.meetcode.backend_meetcode.graphql;

import java.util.List;
import java.util.UUID;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.meetcode.backend_meetcode.dto.UserChallengeHistory;
import com.meetcode.backend_meetcode.entity.Participant;
import com.meetcode.backend_meetcode.service.ParticipantService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class ParticipantResolver {

    private final ParticipantService participantService;

    @MutationMapping
    public Participant joinChallenge(@Argument String cid, @Argument String username) {
        UUID challengeId = UUID.fromString(cid);
        return participantService.joinChallenge(challengeId, username);
    }

    @QueryMapping
    public List<Participant> getParticipantsByChallengeId(@Argument String cid) {
        UUID challengeId = UUID.fromString(cid);
        return participantService.getParticipantsByChallengeId(challengeId);
    }

    @QueryMapping
    public List<Participant> getParticipantsByUsername(@Argument String username) {
        return participantService.getParticipantsByUsername(username);
    }

    @QueryMapping
    public Boolean isParticipantInChallenge(@Argument String cid, @Argument String username) {
        UUID challengeId = UUID.fromString(cid);
        return participantService.isParticipantInChallenge(challengeId, username);
    }

    @QueryMapping
    public List<UserChallengeHistory> getUserChallengeHistory(@Argument String username) {
        return participantService.getUserChallengeHistory(username);
    }
} 