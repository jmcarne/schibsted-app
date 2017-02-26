package com.scmspain.codetest.model.dao;

import com.scmspain.codetest.config.context.AppWebContext;
import com.scmspain.codetest.config.database.impl.DataBaseAccessImpl;
import com.scmspain.codetest.model.ApplicationResource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by josep.carne on 13/02/2017.
 */
public class ApplicationResourceDao {
    private static final String URL_PATTERN = "URL_PATTERN";
    private static final String HTTP_METHOD = "HTTP_METHOD";

    public List<ApplicationResource> findURLsByUserName(String userName) {
        final DataSource dataSource = AppWebContext.getInstance().getDataSource();
        final DataBaseAccessImpl dataBaseAccess = new DataBaseAccessImpl(dataSource);

        final List<Map<String, String>> results = dataBaseAccess.executeQuery(""
                        + "SELECT APP_RES.URL_PATTERN, APP_RES.HTTP_METHOD FROM APPLICATION_ROLE APP_ROLE "
                        + "INNER JOIN APPLICATION_RESOURCE_APPLICATION_ROLE APP_RES_APP_ROLE ON APP_ROLE.CODE = APP_RES_APP_ROLE.APPLICATION_ROLE_CODE "
                        + "INNER JOIN APPLICATION_RESOURCE APP_RES ON APP_RES.URL_PATTERN = APP_RES_APP_ROLE.APPLICATION_RESOURCE_URL_PATTERN "
                        + "INNER JOIN ACCOUNT ACC ON ACC.APPLICATION_ROLE_CODE = APP_ROLE.CODE "
                        + "WHERE ACC.CODE = ? ",
                answer ->
                {
                    final List<Map<String, String>> result = new ArrayList<>();
                    while (answer.next()) {
                        final Map<String, String> row = new HashMap<>();
                        String urlPatternValue = answer.getString(URL_PATTERN);
                        String httpMethodValue = answer.getString(HTTP_METHOD);
                        row.put(URL_PATTERN, urlPatternValue);
                        row.put(HTTP_METHOD, httpMethodValue);
                        result.add(row);
                    }

                    return result;
                },
                preparedStatement -> {
                    preparedStatement.setString(1, userName);
                });

        final List<ApplicationResource> applicationResources = new ArrayList<>();
        if (!results.isEmpty()) {
            results.forEach(row ->
            {
                ApplicationResource resource =
                        new ApplicationResource(row.get(URL_PATTERN), row.get(HTTP_METHOD));
                applicationResources.add(resource);
            });
        }

        return applicationResources;
    }
}
