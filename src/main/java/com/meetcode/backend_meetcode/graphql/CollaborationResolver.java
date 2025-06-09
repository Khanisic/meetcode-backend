package com.meetcode.backend_meetcode.graphql;

import org.reactivestreams.Publisher;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;

import com.meetcode.backend_meetcode.entity.CollaborativeSession;
import com.meetcode.backend_meetcode.service.CollaborativeSessionService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Sinks;

@RequiredArgsConstructor
@Controller
public class CollaborationResolver {
    private final CollaborativeSessionService sessionService;
    private final Sinks.Many<CollaborativeSession> sink = Sinks.many().multicast().onBackpressureBuffer();

    @QueryMapping
    public CollaborativeSession getSession(@Argument String sessionId) {
        return sessionService.getSession(sessionId);
    }

    @MutationMapping
    public CollaborativeSession joinSession(@Argument String sessionId, @Argument String username) {
        CollaborativeSession session = sessionService.joinSession(sessionId, username);
        sink.tryEmitNext(session);
        return session;
    }

    @MutationMapping
    public CollaborativeSession leaveSession(@Argument String sessionId, @Argument String username) {
        CollaborativeSession session = sessionService.leaveSession(sessionId, username);
        if (session != null) {
            sink.tryEmitNext(session);
        }
        return session;
    }

    @MutationMapping
    public CollaborativeSession updateDocument(@Argument String sessionId, @Argument String document, @Argument String username) {
        CollaborativeSession session = sessionService.updateDocument(sessionId, document, username);
        sink.tryEmitNext(session);
        return session;
    }

    @SubscriptionMapping
    public Publisher<CollaborativeSession> sessionUpdates(@Argument String sessionId) {
        return sink.asFlux().filter(s -> s.getSessionId().equals(sessionId));
    }
} 
