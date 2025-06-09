package com.meetcode.backend_meetcode.service;

import com.meetcode.backend_meetcode.entity.CollaborativeSession;

public interface CollaborativeSessionService {

    CollaborativeSession createSession(String sessionId);

    CollaborativeSession getSession(String sessionId);

    CollaborativeSession joinSession(String sessionId, String username);

    CollaborativeSession leaveSession(String sessionId, String username);

    CollaborativeSession updateDocument(String sessionId, String document, String username);
}
