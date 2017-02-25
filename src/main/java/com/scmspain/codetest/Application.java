package com.scmspain.codetest;

import com.scmspain.codetest.config.context.AppWebContext;
import com.scmspain.codetest.config.context.ContextApp;
import com.scmspain.codetest.handler.LoginHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Logger;

/**
 * Created by josep.carne on 11/02/2017.
 */
public class Application {
    private static final int port = 8080;

    public static void main(String[] args) throws IOException {
        final ContextApp contextApp = AppWebContext.getInstance();
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext(LoginHandler.CONTEXT, contextApp.getLoginHandler());
        server.setExecutor(null);

        server.start();
    }
}
