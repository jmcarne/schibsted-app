package com.scmspain.codetest.handler;

import com.scmspain.codetest.controller.ApiController;
import com.scmspain.codetest.controller.Controller;
import com.sun.net.httpserver.HttpExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by josep.carne on 20/02/2017.
 */
public class ApiHandler implements Controller {
    public static final String CONTEXT = "/app/api/users/";

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiHandler.class);

    private final ApiController apiController = new ApiController();
    //private final AuthorizationServicesImpl authorizationService = new AuthorizationServicesImpl();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        try {
            this.handleThrowable(httpExchange);
        } catch (Exception exception) {
            LOGGER.error("ApiHandler error: ", exception);

            httpExchange.sendResponseHeaders(500, 0);
        } finally {
            httpExchange.close();
        }
    }

    protected void handleThrowable(HttpExchange httpExchange) throws IOException  {
        /* TODO */
    }

}
