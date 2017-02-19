package com.scmspain.codetest.service;

import com.scmspain.codetest.model.dao.AccountDao;
import com.scmspain.codetest.view.LoginFormImpl;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * Created by josep.carne on 12/02/2017.
 */
public class LoginServiceImpl {

    public boolean isValidUser(String username, String password) {
        final AccountDao accountDao = new AccountDao();

        if (null != accountDao.findByCodeAndPassword(username, password)) {
            return true;
        } else {
            return false;
        }
    }

    public void processLogoutGet(HttpExchange httpExchange) throws IOException {

        //final Headers headers = httpExchange.getRequestHeaders();
        //final String cookieValue = headers.getFirst(COOKIE_HEADER);

        /*if (cookieValue != null) {
            final UUID uuid = UUID.fromString(cookieValue);
            Sessions.getInstance().removeSession(uuid);
        }*/

        httpExchange.sendResponseHeaders(200, 0);
    }

    public void processLoginGet(HttpExchange httpExchange) throws IOException {

        /*final String requestedURI = httpExchange.getRequestURI().toString();
        final SessionInfo sessionInfo = SessionContext.getSession();
        final LoginFormImpl loginForm = new LoginFormImpl();

        String html = "";
        if (Sessions.getInstance().isValidSession()) {
            html = loginForm.doNoRequiredLogin();
            Sessions.getInstance().refreshSession(sessionInfo.getUUID(), sessionInfo.getUsername());
        } else {
            html = loginForm.doRequiredLogin(requestedURI);
        }

        httpExchange.sendResponseHeaders(200, html.length());
        try (final OutputStream os = httpExchange.getResponseBody()) {
            os.write(html.getBytes());
        }*/
    }

    public void processLoginPost(HttpExchange httpExchange) throws IOException {

        /*final SessionInfo sessionInfo = SessionContext.getSession();

        if (!Sessions.getInstance().isValidSession()) {
            String body = this.getBody(httpExchange);
            String [] formData = body.split("&");
            if (formData.length == 2) {
                String username = formData[0].split("=")[1];
                String password = formData[1].split("=")[1];

                LoginServiceImpl loginService = new LoginServiceImpl();
                if (loginService.isValidUser(username, password)) {
                    UUID uuid = UUID.randomUUID();
                    this.setCookieHeader(httpExchange, uuid.toString());
                    Sessions.getInstance().refreshSession(uuid, username);
                    this.doRedirect(httpExchange);
                } else {

                    final UnAuthenticated view = new UnAuthenticated();
                    String html = view.doAuthenticated();
                    httpExchange.sendResponseHeaders(401, html.length());
                    try (final OutputStream os = httpExchange.getResponseBody()) {
                        os.write(html.getBytes());
                    }
                }
            }
        } else {
            Sessions.getInstance().refreshSession(sessionInfo.getUUID(), sessionInfo.getUsername());
            doRedirect(httpExchange);
        }*/
    }
}
