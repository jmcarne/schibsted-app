package com.scmspain.codetest.config.context;

import com.sun.net.httpserver.HttpHandler;

import javax.sql.DataSource;

/**
 * Created by josep.carne on 11/02/2017.
 */
public interface ContextApp {
    DataSource getDataSource();
    HttpHandler getWebHandler();
    HttpHandler getLoginHandler();
    HttpHandler getSessionHandler();
    HttpHandler getApiHandler();
}
