package com.scmspain.codetest.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

/**
 * Created by josep.carne on 11/02/2017.
 */
public class SessionHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        //final Session sessionInfo = getSessionInfo(httpExchange);

        //ContextSession.setSession(sessionInfo);
    }
}
