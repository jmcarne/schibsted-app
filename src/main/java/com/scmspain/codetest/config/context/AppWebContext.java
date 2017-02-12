package com.scmspain.codetest.config.context;

import com.scmspain.codetest.config.datasource.impl.DataSourceAccessImpl;
import com.scmspain.codetest.handler.LoginHandler;
import com.scmspain.codetest.handler.SessionHandler;
import com.sun.net.httpserver.HttpHandler;

import javax.sql.DataSource;

/**
 * Created by josep.carne on 11/02/2017.
 */
public class AppWebContext implements ContextApp {
    private final DataSource dataSource;
    private final HttpHandler sessionHandler;
    private final HttpHandler loginHandler;

    private AppWebContext() {
        this.dataSource = DataSourceAccessImpl.getInstance().getDataSource();
        //this.liquibaseContext = new LiquibaseContext(dataSource);
        //this.liquibaseContext.init();
        this.sessionHandler = new SessionHandler();
        //this.apiHandler = new ApiHandler();
        //this.webHttpHandler = new PagesHandler(sessionHandler);
        this.loginHandler = new LoginHandler(sessionHandler);
    }

    public static ContextApp getInstance() {
        return AppWebContextHolder.INSTANCE;
    }

    @Override
    public DataSource getDataSource() {
        return this.dataSource;
    }

    @Override
    public HttpHandler getLoginHandler() {
        return this.loginHandler;
    }

    @Override
    public HttpHandler getSessionHandler() {
        return this.sessionHandler;
    }

    private static class AppWebContextHolder {
        private static final ContextApp INSTANCE = new AppWebContext();
    }
}
