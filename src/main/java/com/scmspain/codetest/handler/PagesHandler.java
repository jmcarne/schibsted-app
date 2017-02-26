package com.scmspain.codetest.handler;

import com.scmspain.codetest.config.session.SessionContext;
import com.scmspain.codetest.config.session.SessionInfo;
import com.scmspain.codetest.config.session.Sessions;
import com.scmspain.codetest.controller.PagesController;
import com.scmspain.codetest.view.AuthorizationServicesImpl;
import com.scmspain.codetest.view.UnAuthorizedImpl;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

/**
 * Created by Josep Maria on 26/02/2017.
 */
public class PagesHandler implements HttpHandler {
    public static final String CONTEXT = "/app/pages/";

    private static final Logger LOGGER = LoggerFactory.getLogger(PagesHandler.class);
    private static final String SERVER_ADDRESS = "http://localhost:8080";

    private final PagesController pagesController = new PagesController();
    private final AuthorizationServicesImpl authorizationService = new AuthorizationServicesImpl();
    private final HttpHandler sessionHandler;

    public PagesHandler(HttpHandler sessionHandler) {
        this.sessionHandler = sessionHandler;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        try {
            this.handleThrowable(httpExchange);
        } catch (Exception exception) {
            LOGGER.error("PagesHandler error: ", exception);

            httpExchange.sendResponseHeaders(500, 0);
        } finally {
            httpExchange.close();
        }
    }

    protected void handleThrowable(HttpExchange httpExchange) throws IOException  {
        this.sessionHandler.handle(httpExchange);

        if (Sessions.getInstance().isValidSession()) {
            final SessionInfo sessionInfo = SessionContext.getSession();

            if(authorizationService.isAuthorized(httpExchange.getRequestMethod(),
                    httpExchange.getRequestURI().toString(), sessionInfo.getUsername())) {

                pagesController.handle(httpExchange);

            } else {
                this.doErrorResponse(httpExchange);
            }

            Sessions.getInstance().refreshSession(sessionInfo.getUuid(), sessionInfo.getUsername());
        } else {
            this.doRedirect(httpExchange);
        }
    }

    protected void doRedirect(HttpExchange httpExchange) throws IOException  {
        URI requestURI = httpExchange.getRequestURI();
        String requestURIString = requestURI.toString();
        Headers responseHeaders = httpExchange.getResponseHeaders();

        responseHeaders.add("Location", SERVER_ADDRESS + LoginHandler.LOGIN_PAGE + requestURIString);
        httpExchange.sendResponseHeaders(302, 0);
    }

    protected void doErrorResponse(HttpExchange httpExchange) throws IOException {
        final UnAuthorizedImpl view = new UnAuthorizedImpl();
        final String html = view.doNotAuthorized();

        httpExchange.sendResponseHeaders(403, html.length());
        try (final OutputStream os = httpExchange.getResponseBody()) {
            os.write(html.getBytes());
        }
    }
}
