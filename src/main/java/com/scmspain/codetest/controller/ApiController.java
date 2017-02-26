package com.scmspain.codetest.controller;

import com.scmspain.codetest.model.UserResource;
import com.scmspain.codetest.service.ApiServiceImpl;
import com.sun.net.httpserver.HttpExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Josep Maria on 26/02/2017.
 */
public class ApiController implements Controller {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);
    private static final String USER_NAME_PARAM = "username";
    private static final String API_URL_PATTERN = "/app/api/users/{" + USER_NAME_PARAM + "}";

    private final ApiServiceImpl apiService = new ApiServiceImpl();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        final String requestMethod = httpExchange.getRequestMethod();

        switch (requestMethod) {
            case "GET":
                this.processGet(httpExchange);

                break;
            case "POST":
                this.processPost(httpExchange);
                httpExchange.sendResponseHeaders(415, 0);
                break;
            case "DELETE":
                this.processDelete(httpExchange);

                break;
            default:
                // Not found
                httpExchange.sendResponseHeaders(404, 0);
                break;
        }
    }

    protected void processGet(HttpExchange httpExchange) throws IOException {
        final String userNameParam = this.getSafeUserNameParam(httpExchange);
        final UserResource userResource = apiService.findAccountByCode(userNameParam);

    }

    protected void processDelete(HttpExchange httpExchange) throws IOException {
        final String userNameParam = getSafeUserNameParam(httpExchange);

        apiService.deleteAccountByCode(userNameParam);

        httpExchange.sendResponseHeaders(204, 0);
    }

    protected void processPost(HttpExchange httpExchange) throws IOException {
        UserResource account = null;
        //account = this.jsonDeserializer(httpExchange);
        apiService.createAccount(account);
    }

    protected String getSafeUserNameParam(HttpExchange httpExchange) {
        final String uri = httpExchange.getRequestURI().toString();
        final AntPathMatcher pathMatcher = new AntPathMatcher();

        String userNameParam = "";
        try {
            final Map<String, String> variables = pathMatcher.extractUriTemplateVariables(API_URL_PATTERN, uri);
            userNameParam = variables.get(USER_NAME_PARAM);
        } catch (IllegalStateException exception) {

            LOGGER.warn("AntPathMatcher: ", exception);
        }

        return userNameParam;
    }
}
