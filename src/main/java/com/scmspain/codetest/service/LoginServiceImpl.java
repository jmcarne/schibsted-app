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

        /* TODO */

        httpExchange.sendResponseHeaders(200, 0);
    }

    public void processLoginGet(HttpExchange httpExchange) throws IOException {

        /* TODO */
    }

    public void processLoginPost(HttpExchange httpExchange) throws IOException {

        /* TODO */
    }
}
