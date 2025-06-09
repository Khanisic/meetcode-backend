package com.meetcode.backend_meetcode.service.impl;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.meetcode.backend_meetcode.entity.CollaborativeSession;
import com.meetcode.backend_meetcode.entity.User;
import com.meetcode.backend_meetcode.repository.CollaborativeSessionRepository;
import com.meetcode.backend_meetcode.repository.UserRepository;
import com.meetcode.backend_meetcode.service.CollaborativeSessionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CollaborativeSessionServiceImpl implements CollaborativeSessionService {
    private final CollaborativeSessionRepository sessionRepository;
    private final UserRepository userRepository;

    @Override
    public CollaborativeSession createSession(String sessionId) {
        return sessionRepository.findById(sessionId)
            .orElseGet(() -> {
                CollaborativeSession session = new CollaborativeSession();
                session.setSessionId(sessionId);
                session.setDocument("");
                session.setActiveUsers(ConcurrentHashMap.newKeySet());
                return sessionRepository.save(session);
            });
    }

    @Override
    public CollaborativeSession getSession(String sessionId) {
        return sessionRepository.findById(sessionId).orElse(null);
    }

    @Override
    public CollaborativeSession joinSession(String sessionId, String username) {
        // Validate if the user exists
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User with username '" + username + "' does not exist");
        }
        
        CollaborativeSession session = createSession(sessionId);
        session.getActiveUsers().add(username);
        return sessionRepository.save(session);
    }

    @Override
    public CollaborativeSession leaveSession(String sessionId, String username) {
        CollaborativeSession session = sessionRepository.findById(sessionId).orElse(null);
        if (session != null) {
            session.getActiveUsers().remove(username);
            return sessionRepository.save(session);
        }
        return session;
    }

    @Override
    public CollaborativeSession updateDocument(String sessionId, String document, String username) {
        // Validate if the user exists
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User with username '" + username + "' does not exist");
        }
        
        CollaborativeSession session = createSession(sessionId);
        session.setDocument(document);
        session.getActiveUsers().add(username);
        return sessionRepository.save(session);
    }
}