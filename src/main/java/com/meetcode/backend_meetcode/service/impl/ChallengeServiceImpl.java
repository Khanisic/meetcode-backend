package com.meetcode.backend_meetcode.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meetcode.backend_meetcode.dto.ParticipantScoreInput;
import com.meetcode.backend_meetcode.entity.Challenge;
import com.meetcode.backend_meetcode.entity.Participant;
import com.meetcode.backend_meetcode.entity.Question;
import com.meetcode.backend_meetcode.repository.ChallengeRepository;
import com.meetcode.backend_meetcode.service.ChallengeService;
import com.meetcode.backend_meetcode.service.ParticipantService;
import com.meetcode.backend_meetcode.service.QuestionService;
import com.meetcode.backend_meetcode.service.UserService;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ChallengeServiceImpl implements ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final ParticipantService participantService;
    private final QuestionService questionService;
    private final UserService userService;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Challenge createChallenge(String pid) {
        Challenge challenge = new Challenge();
        challenge.setPid(pid);
        challenge.setStatus("OPEN");
        challenge.setStartDate(null);
        challenge.setEndDate(null);
        // Don't initialize participants list - let JPA handle it
        
        System.out.println("About to save challenge with pid: " + pid);
        Challenge savedChallenge = challengeRepository.save(challenge);
        challengeRepository.flush(); // Force immediate save to DB
        System.out.println("Saved and flushed challenge: " + savedChallenge.getCid());
        
        // Verify it was actually saved
        Challenge verifyChallenge = challengeRepository.findById(savedChallenge.getCid()).orElse(null);
        System.out.println("Verification - Challenge exists in DB: " + (verifyChallenge != null));
        
        return savedChallenge;
    }

    @Override
    @Transactional
    public Challenge findOrCreateChallenge(String username) {
        // Check if user is already in an open challenge
        System.out.println(username);
        List<Participant> userParticipations = participantService.getParticipantsByUsername(username);
        for (Participant participation : userParticipations) {
            Challenge existingChallenge = challengeRepository.findById(participation.getCid()).orElse(null);
            if (existingChallenge != null && "OPEN".equals(existingChallenge.getStatus())) {
                return existingChallenge;
            }
        }
        
        // Look for any Challenge with status = "OPEN" and participants.length < 4
        List<Challenge> openChallenges = challengeRepository.findOpenChallenges();
        System.out.println("openChallenges: " + openChallenges);
        for (Challenge challenge : openChallenges) {
            // Check participant count and if user is not already in this challenge
            List<Participant> participants = participantService.getParticipantsByChallengeId(challenge.getCid());
            if (participants.size() < 4 && !participantService.isParticipantInChallenge(challenge.getCid(), username)) {
                // Add username to that challenge's participants
                System.out.println("joining challenge: " + challenge.getCid());
                participantService.joinChallenge(challenge.getCid(), username);
                return challenge;
            }
        }
        System.out.println("no open challenges found");
        
        // Create new Challenge in separate transaction to ensure it's saved first
        String randomPid = getRandomProblemId();
        System.out.println("randomPid: " + randomPid);
        Challenge newChallenge = createChallenge(randomPid);
        System.out.println("newChallenge created: " + newChallenge.getCid());
        
        // Now add participant in separate operation
        try {
            participantService.joinChallenge(newChallenge.getCid(), username);
            System.out.println("Participant added successfully");
        } catch (Exception e) {
            System.out.println("Error adding participant: " + e.getMessage());
            throw e;
        }
        System.out.println("newChallenge returned: " + newChallenge);
        return newChallenge;
    }

    @Override
    public Challenge startChallenge(UUID cid) {
        Challenge challenge = challengeRepository.findById(cid)
                .orElseThrow(() -> new RuntimeException("Challenge not found with id: " + cid));
        
        if (!"OPEN".equals(challenge.getStatus())) {
            throw new RuntimeException("Challenge is not in OPEN status");
        }
        
        challenge.setStatus("IN_PROGRESS");
        challenge.setStartDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        
        return challengeRepository.save(challenge);
    }

    @Override
    public Challenge endChallenge(UUID cid, List<ParticipantScoreInput> participantScores) {
        Challenge challenge = challengeRepository.findById(cid)
                .orElseThrow(() -> new RuntimeException("Challenge not found with id: " + cid));
        
        if (!"IN_PROGRESS".equals(challenge.getStatus())) {
            throw new RuntimeException("Challenge is not in IN_PROGRESS status");
        }
        
        // Update challenge status and end date
        challenge.setStatus("CLOSED");
        challenge.setEndDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        
        System.out.println("Ending challenge " + cid + " at " + challenge.getEndDate());
        
        // Update participant scores and rankings
        for (ParticipantScoreInput scoreInput : participantScores) {
            participantService.updateParticipantScore(cid, scoreInput.getUsername(), scoreInput.getScore(), scoreInput.getRank());
            
            // Update user's total score
            userService.updateUserScore(scoreInput.getUsername(), scoreInput.getScore());
        }
        
        Challenge savedChallenge = challengeRepository.save(challenge);
        System.out.println("Challenge " + cid + " ended successfully");
        
        return savedChallenge;
    }

    @Override
    @Transactional(readOnly = true)
    public Challenge getChallengeById(UUID cid) {
        return challengeRepository.findById(cid)
                .orElseThrow(() -> new RuntimeException("Challenge not found with id: " + cid));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Challenge> getAllChallenges() {
        return challengeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Challenge> getOpenChallenges() {
        return challengeRepository.findByStatus("OPEN");
    }

    private String getRandomProblemId() {
        List<Question> allQuestions = questionService.getAllQuestions();
        if (allQuestions.isEmpty()) {
            throw new RuntimeException("No questions available in the database");
        }
        
        Random random = new Random();
        Question randomQuestion = allQuestions.get(random.nextInt(allQuestions.size()));
        
        // Use titleSlug as the problem identifier, or fallback to qid if titleSlug is null
        return randomQuestion.getTitleSlug() != null ? 
               randomQuestion.getTitleSlug() : 
               randomQuestion.getQid().toString();
    }
} 