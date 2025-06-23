package com.meetcode.backend_meetcode.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.meetcode.backend_meetcode.entity.Participant;
import com.meetcode.backend_meetcode.entity.ParticipantId;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, ParticipantId> {
    List<Participant> findByCid(UUID cid);
    List<Participant> findByUsername(String username);
    boolean existsByCidAndUsername(UUID cid, String username);
    
    @Query("SELECT p FROM Participant p JOIN p.challenge c WHERE p.username = :username ORDER BY c.endDate DESC, p.time DESC")
    List<Participant> findUserChallengeHistoryOrderByRecent(@Param("username") String username);
} 