package com.scmspain.codetest.view;

import com.scmspain.codetest.model.ApplicationResource;
import com.scmspain.codetest.model.dao.ApplicationResourceDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by Josep Maria on 26/02/2017.
 */
public class AuthorizationServicesImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationServicesImpl.class);
    private static final String USER_NAME_PARAM = "username";
    private static final String API_URL_PATTERN = "/app/api/users/{" + USER_NAME_PARAM + "}";

    public boolean isAuthorized(String httpMethod, String uri, String userName) {
        final String userNameParam = this.getSafeUserNameParam(uri);

        final ApplicationResourceDao dao = new ApplicationResourceDao();

        final List<ApplicationResource> urls = dao.findURLsByUserName(userName);

        return urls.stream().anyMatch(url ->
        {
            final String urlPatternValue = url.getUrlPattern();
            final String urlReplacedPatternValue = urlPatternValue.replace("{" + USER_NAME_PARAM + "}", userNameParam);

            final String httpMethodValue = url.getHttpMethod();

            return urlReplacedPatternValue.equals(uri) && httpMethodValue.equals(httpMethod);
        });

    }

    protected String getSafeUserNameParam(String uri) {
        //final AntPathMatcher pathMatcher = new AntPathMatcher();

        String userNameParam = "";
        try {
            //final Map<String, String> variables = pathMatcher.extractUriTemplateVariables(API_URL_PATTERN, uri);
            userNameParam = variables.get(USER_NAME_PARAM);
        } catch (IllegalStateException exception) {

            LOGGER.warn("Error exception: ", exception);
        }

        return userNameParam;
    }
}
