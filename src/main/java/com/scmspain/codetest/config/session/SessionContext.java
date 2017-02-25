package com.scmspain.codetest.config.session;

/**
 * Created by josep.carne on 25/02/2017.
 */
public class SessionContext {
    private static final ThreadLocal<SessionInfo> localContextSession = new ThreadLocal<>();

    public static void setSession(SessionInfo sessionInfo) {
        localContextSession.set(sessionInfo);
    }

    public static SessionInfo getSession() {
        return localContextSession.get();
    }
}
