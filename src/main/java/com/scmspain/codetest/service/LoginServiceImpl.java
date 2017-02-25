package com.scmspain.codetest.service;

import com.scmspain.codetest.config.session.SessionContext;
import com.scmspain.codetest.config.session.SessionInfo;
import com.scmspain.codetest.config.session.Sessions;
import com.scmspain.codetest.model.dao.AccountDao;
import com.scmspain.codetest.view.LoginFormImpl;
import com.scmspain.codetest.view.UnAuthenticated;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.UUID;

/**
 * Created by josep.carne on 12/02/2017.
 */
public class LoginServiceImpl {
    private static final String COOKIE_HEADER = "Cookie";

    public boolean isValidUser(String username, String password) {
        final AccountDao accountDao = new AccountDao();

        if (null != accountDao.findByCodeAndPassword(username, password)) {
            return true;
        } else {
            return false;
        }
    }

    public void processLogoutGet(HttpExchange httpExchange) throws IOException {
        final Headers headers = httpExchange.getRequestHeaders();
        final String cookieValue = headers.getFirst(COOKIE_HEADER);

        if (cookieValue != null) {
            final UUID uuid = UUID.fromString(cookieValue);
            Sessions.getInstance().removeSession(uuid);
        }

        httpExchange.sendResponseHeaders(200, 0);
    }

    public void processLoginGet(HttpExchange httpExchange) throws IOException {
        final String requestedURI = httpExchange.getRequestURI().toString();
        final SessionInfo sessionInfo = SessionContext.getSession();
        final LoginFormImpl loginForm = new LoginFormImpl();

        String html = "";
        if (Sessions.getInstance().isValidSession()) {
            html = loginForm.doNoRequiredLogin();
            Sessions.getInstance().refreshSession(sessionInfo.getUuid(), sessionInfo.getUsername());
        } else {
            html = loginForm.doRequiredLogin(requestedURI);
        }

        httpExchange.sendResponseHeaders(200, html.length());
        try (final OutputStream os = httpExchange.getResponseBody()) {
            os.write(html.getBytes());
        }
    }

    public void processLoginPost(HttpExchange httpExchange) throws IOException {
        final SessionInfo sessionInfo = SessionContext.getSession();

        if (!Sessions.getInstance().isValidSession()) {
            String body = this.getBody(httpExchange);
            String[] formData = body.split("&");
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
            Sessions.getInstance().refreshSession(sessionInfo.getUuid(), sessionInfo.getUsername());
            doRedirect(httpExchange);
        }
    }

    protected String getBody (HttpExchange httpExchange) throws IOException {
        try(final InputStream inputStream = httpExchange.getRequestBody();
            final ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream()) {

            final int bufferSize = 1024;
            final byte[] buffer = new byte[bufferSize];

            int len = 0;
            while ((len = inputStream.read(buffer)) != -1) {
                byteBuffer.write(buffer, 0, len);
            }

            return new String(byteBuffer.toByteArray(), Charset.forName("UTF-8"));
        }
    }

    protected void setCookieHeader(HttpExchange httpExchange, String UUIDString) {
        Headers headers = httpExchange.getResponseHeaders();

        headers.remove("Set-Cookie");
        headers.set("Set-Cookie", UUIDString + "; path=/");
    }

    protected void doRedirect(HttpExchange httpExchange) throws IOException  {
        String requestURIString = httpExchange.getRequestURI().toString();
        String[] urls = requestURIString.split("serviceName=");
        String serviceName = "";
        if (urls.length == 2) {
            serviceName = urls[1];
        }

        Headers responseHeaders = httpExchange.getResponseHeaders();
        responseHeaders.add("Location", serviceName);
        httpExchange.sendResponseHeaders(302, 0);
    }
}
