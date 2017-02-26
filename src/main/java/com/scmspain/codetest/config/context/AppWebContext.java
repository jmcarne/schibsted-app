package com.scmspain.codetest.config.context;

import com.scmspain.codetest.config.datasource.impl.DataSourceAccessImpl;
import com.scmspain.codetest.handler.ApiHandler;
import com.scmspain.codetest.handler.LoginHandler;
import com.scmspain.codetest.handler.PagesHandler;
import com.scmspain.codetest.handler.SessionHandler;
import com.sun.net.httpserver.HttpHandler;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by josep.carne on 11/02/2017.
 */
public class AppWebContext implements ContextApp {
    private final DataSource dataSource;
    private final HttpHandler sessionHandler;
    private final HttpHandler webHttpHandler;
    private final HttpHandler loginHandler;
    private final HttpHandler apiHandler;

    private AppWebContext() {
        this.dataSource = DataSourceAccessImpl.getInstance().getDataSource();
        this.init();
        this.sessionHandler = new SessionHandler();
        this.apiHandler = new ApiHandler();
        this.webHttpHandler = new PagesHandler(sessionHandler);
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
    public HttpHandler getWebHandler() {
        return this.webHttpHandler;
    }

    @Override
    public HttpHandler getLoginHandler() {
        return this.loginHandler;
    }

    @Override
    public HttpHandler getSessionHandler() {
        return this.sessionHandler;
    }

    @Override
    public HttpHandler getApiHandler() {
        return this.apiHandler;
    }

    private static class AppWebContextHolder {
        private static final ContextApp INSTANCE = new AppWebContext();
    }

    public void init() {
        try {
            this.initThrowable();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    protected void initThrowable() throws SQLException {
        final Connection connection = this.dataSource.getConnection();
        try {
            final Thread currentThread = Thread.currentThread();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }
}
