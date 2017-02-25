package com.scmspain.codetest.handler;

import com.scmspain.codetest.config.session.SessionContext;
import com.scmspain.codetest.config.session.SessionInfo;
import com.scmspain.codetest.config.session.Sessions;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by josep.carne on 11/02/2017.
 */
public class SessionHandler implements HttpHandler {
    private static final String COOKIE_HEADER = "Cookie";

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        final SessionInfo sessionInfo = getSessionInfo(httpExchange);

        SessionContext.setSession(sessionInfo);
    }

    protected SessionInfo getSessionInfo(HttpExchange httpExchange) {
        final Headers headers = httpExchange.getRequestHeaders();
        final String cookieValue = headers.getFirst(COOKIE_HEADER);

        SessionInfo sessionInfo = null;

        if (cookieValue != null) {
            final UUID uuid = UUID.fromString(cookieValue);
            sessionInfo = Sessions.getInstance().getSession(uuid);
        }

        return sessionInfo;
    }
}
