package com.scmspain.codetest.model;

/**
 * Created by Josep Maria on 27/02/2017.
 */
public class ApplicationResource {
    private final String urlPattern;
    private final String httpMethod;

    public ApplicationResource() {
        this.urlPattern = null;
        this.httpMethod = null;
    }

    public ApplicationResource(String urlPattern, String httpMethod) {
        this.urlPattern = urlPattern;
        this.httpMethod = httpMethod;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public String getHttpMethod() {
        return httpMethod;
    }
}
