package com.scmspain.codetest.controller;

import com.scmspain.codetest.config.session.SessionContext;
import com.scmspain.codetest.config.session.SessionInfo;
import com.scmspain.codetest.view.PageImpl;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Josep Maria on 26/02/2017.
 */
public class PagesController implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        final String requestMethod = httpExchange.getRequestMethod();

        switch (requestMethod) {
            case "GET":
                this.processPages(httpExchange);
                break;
            default:
                httpExchange.sendResponseHeaders(404, 0);
                break;
        }

    }

    protected  void processPages(HttpExchange httpExchange) throws IOException {
        final String requestedURI = httpExchange.getRequestURI().toString();
        final PageImpl pageImpl = new PageImpl();

        int responseStatus = 200;
        String html = "";
        switch (requestedURI) {
            case "/app/pages/page_1.html":
                html = pageImpl.doPage(1, getSafeUserName());
                break;
            case "/app/pages/page_2.html":
                html = pageImpl.doPage(2, getSafeUserName());
                break;
            case "/app/pages/page_3.html":
                html = pageImpl.doPage(3, getSafeUserName());
                break;
            default:
                responseStatus = 404;
                break;
        }

        httpExchange.sendResponseHeaders(responseStatus, html.length());
        try (final OutputStream os = httpExchange.getResponseBody()) {
            os.write(html.getBytes());
        }
    }

    protected String getSafeUserName() {
        SessionInfo sessionInfo = SessionContext.getSession();
        String userName = "";

        if (sessionInfo != null) {
            userName = sessionInfo.getUsername();
        }

        return userName;
    }
}
