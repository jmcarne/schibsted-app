package com.scmspain.codetest.config.session;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by josep.carne on 25/02/2017.
 */
public class SessionInfo {
    private final UUID uuid;
    private final String username;
    private final LocalDateTime lastSessionTime;

    public UUID getUuid() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getLastSessionTime() {
        return lastSessionTime;
    }

    public SessionInfo(UUID uuid, String username, LocalDateTime lastSessionTime) {
        this.uuid = uuid;
        this.username = username;
        this.lastSessionTime = lastSessionTime;
    }
}
