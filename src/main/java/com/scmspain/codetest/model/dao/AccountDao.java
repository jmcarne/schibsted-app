package com.scmspain.codetest.model.dao;

import com.scmspain.codetest.config.context.AppWebContext;
import com.scmspain.codetest.config.database.DataBaseAccess;
import com.scmspain.codetest.config.database.impl.DataBaseAccessImpl;
import com.scmspain.codetest.model.UserResource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by josep.carne on 13/02/2017.
 */
public class AccountDao {
    private static final String CODE = "CODE";
    private static final String NAME = "NAME";
    private static final String SURNAME = "SURNAME";
    private static final String PASSWORD = "PASSWORD";
    private static final String ROLE = "ROLE_APP";

    public void create(UserResource account) {
        final DataSource dataSource = AppWebContext.getInstance().getDataSource();
        final DataBaseAccessImpl dataBaseAccess = new DataBaseAccessImpl(dataSource);

        dataBaseAccess.executeUpdate(
                "INSERT INTO USER VALUES (?, ?, ?, ?, ?)",
                preparedStatement -> {
                    preparedStatement.setString(1, account.getCode());
                    preparedStatement.setString(2, account.getName());
                    preparedStatement.setString(3, account.getSurname());
                    preparedStatement.setString(4, account.getPassword());
                    preparedStatement.setString(5, account.getRole());
                });

    }

    public UserResource findByCode(String accountCode) {
        final DataSource dataSource = AppWebContext.getInstance().getDataSource();
        final DataBaseAccess dataBaseAccess = new DataBaseAccessImpl(dataSource);

        final List<Map<String, String>> results =
                dataBaseAccess.executeQuery("SELECT * FROM USER WHERE CODE = ?",
                        answer ->
                        {
                            final List<Map<String, String>> result = new ArrayList<>();
                            while (answer.next()) {
                                final Map<String, String> row = new HashMap<>();
                                row.put(CODE, answer.getString(CODE));
                                row.put(NAME, answer.getString(NAME));
                                row.put(SURNAME, answer.getString(SURNAME));
                                row.put(PASSWORD, answer.getString(PASSWORD));
                                row.put(ROLE, answer.getString(ROLE));
                                result.add(row);
                            }

                            return result;
                        },
                        preparedStatement -> {
                            preparedStatement.setString(1, accountCode);
                        });

        UserResource account = null;
        if (!results.isEmpty()) {
            final Map<String, String> row = results.get(0);

            account = new UserResource(row.get(CODE), row.get(NAME),
                    row.get(SURNAME), null, row.get(ROLE));
        }

        return account;
    }

    public void deleteByCode(String code) {
        final DataSource dataSource = AppWebContext.getInstance().getDataSource();
        final DataBaseAccessImpl dataBaseAccess = new DataBaseAccessImpl(dataSource);

        dataBaseAccess.executeUpdate("DELETE FROM USER WHERE CODE = ?",
                preparedStatement -> {
                    preparedStatement.setString(1, code);
                });
    }

    public UserResource findByCodeAndPassword(String username, String password) {
        final DataSource dataSource = AppWebContext.getInstance().getDataSource();
        final DataBaseAccessImpl dataBaseAccess = new DataBaseAccessImpl(dataSource);

        final List<Map<String, String>> results =
                dataBaseAccess.executeQuery("SELECT * FROM USER WHERE CODE = ? AND PASSWORD = ?",
                        answer ->
                        {
                            final List<Map<String, String>> result = new ArrayList<>();
                            while (answer.next()) {
                                final Map<String, String> row = new HashMap<>();
                                row.put(CODE, answer.getString(CODE));
                                row.put(NAME, answer.getString(NAME));
                                row.put(SURNAME, answer.getString(SURNAME));
                                row.put(PASSWORD, answer.getString(PASSWORD));
                                row.put(ROLE, answer.getString(ROLE));
                                result.add(row);
                            }

                            return result;
                        },
                        preparedStatement -> {
                            preparedStatement.setString(1, username);
                            preparedStatement.setString(2, password);
                        });

        UserResource account = null;
        if (!results.isEmpty()) {
            final Map<String, String> row = results.get(0);

            account = new UserResource(row.get(CODE), row.get(NAME),
                    row.get(SURNAME), null, row.get(ROLE));
        }

        return account;
    }
}
