package com.scmspain.codetest.controller;

import com.scmspain.codetest.service.LoginServiceImpl;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

/**
 * Created by josep.carne on 11/02/2017.
 */
public class LoginController implements Controller {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        final LoginServiceImpl loginService = new LoginServiceImpl();
        final String requestedURI = httpExchange.getRequestURI().toString();

        if (requestedURI.startsWith("/app/login/login.html")) {

            final String requestMethod = httpExchange.getRequestMethod();

            switch (requestMethod) {
                case "GET":
                    loginService.processLoginGet(httpExchange);
                    break;
                case "POST":
                    loginService.processLoginPost(httpExchange);
                    break;
                default:
                    httpExchange.sendResponseHeaders(404, 0);
                    break;
            }

        } else if (requestedURI.startsWith("/app/login/logout.html")) {
            loginService.processLogoutGet(httpExchange);
        } else {
            httpExchange.sendResponseHeaders(404, 0);
        }
    }
}
