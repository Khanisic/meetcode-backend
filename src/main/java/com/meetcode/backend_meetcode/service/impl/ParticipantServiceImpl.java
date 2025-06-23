package com.meetcode.backend_meetcode.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meetcode.backend_meetcode.dto.UserChallengeHistory;
import com.meetcode.backend_meetcode.entity.Challenge;
import com.meetcode.backend_meetcode.entity.Participant;
import com.meetcode.backend_meetcode.repository.ParticipantRepository;
import com.meetcode.backend_meetcode.service.ParticipantService;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepository;

    @Override
    public Participant joinChallenge(UUID cid, String username) {
        // Ensure not already joined
        if (participantRepository.existsByCidAndUsername(cid, username)) {
            throw new RuntimeException("User " + username + " is already participating in challenge " + cid);
        }
        System.out.println(cid + " cid");
        System.out.println(username + " username");
        Participant participant = new Participant();
        participant.setCid(cid);
        System.out.println( " done with setCid  " + cid);
        participant.setUsername(username);
        System.out.println( " done with setUsername" + username);
        participant.setRank(null);
        System.out.println( " done with setRank");
        participant.setTime(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        System.out.println( " done with setTime");
        participant.setScore(0);
        System.out.println( " done with setScore");
        System.out.println( " done with setParticipant");

        return participantRepository.save(participant);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Participant> getParticipantsByChallengeId(UUID cid) {
        return participantRepository.findByCid(cid);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Participant> getParticipantsByUsername(String username) {
        return participantRepository.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isParticipantInChallenge(UUID cid, String username) {
        return participantRepository.existsByCidAndUsername(cid, username);
    }

    @Override
    public void updateParticipantScore(UUID cid, String username, Integer score, Integer rank) {
        Participant participant = participantRepository.findById(new com.meetcode.backend_meetcode.entity.ParticipantId(cid, username))
                .orElseThrow(() -> new RuntimeException("Participant not found: " + username + " in challenge " + cid));
        
        participant.setScore(score);
        participant.setRank(rank);
        participantRepository.save(participant);
        
        System.out.println("Updated participant " + username + " in challenge " + cid + ": score=" + score + ", rank=" + rank);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserChallengeHistory> getUserChallengeHistory(String username) {
        List<Participant> participantHistory = participantRepository.findUserChallengeHistoryOrderByRecent(username);
        
        return participantHistory.stream()
                .limit(5) // Get last 5 challenges
                .map(participant -> {
                    Challenge challenge = participant.getChallenge();
                    return new UserChallengeHistory(
                            challenge.getCid(),
                            challenge.getPid(),
                            challenge.getStatus(),
                            challenge.getStartDate(),
                            challenge.getEndDate(),
                            participant.getScore(),
                            participant.getRank(),
                            participant.getTime()
                    );
                })
                .collect(Collectors.toList());
    }
} 