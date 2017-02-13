package com.scmspain.codetest.config.database.impl;

import com.scmspain.codetest.config.database.DataBaseAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by josep.carne on 11/02/2017.
 */
public class DataBaseAccessImpl implements DataBaseAccess {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataBaseAccessImpl.class);

    private final DataSource dataSource;

    public DataBaseAccessImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Map<String, String>> executeQuery(
            String query, ExecuteResultSet<ResultSet> executeResultSet, FillPreparedStatement fillStatement) {

        List<Map<String, String>> result = null;
        try {
            result = this.executeQueryThrowable(query, executeResultSet, fillStatement);
        } catch (SQLException exception) {
            LOGGER.error("Query error: ", exception);

            throw new IllegalStateException("Querry error", exception);
        }

        return result;
    }

    protected List<Map<String, String>> executeQueryThrowable(
            String query, ExecuteResultSet<ResultSet> executeResultSet, FillPreparedStatement fillStatement) throws SQLException {

        try (final Connection connection = this.dataSource.getConnection()) {
            return this.doExecuteQuery(
                    query,
                    connection,
                    preparedStatement ->
                    {
                        fillStatement.doFill(preparedStatement);

                        return preparedStatement.executeQuery();
                    },
                    executeResultSet);
        }
    }

    protected List<Map<String, String>> doExecuteQuery(
            final String query,
            final Connection connection,
            final ExecuteStatement<ResultSet> executeStatement,
            final ExecuteResultSet<ResultSet> executeResultSet) throws SQLException {

        try (final PreparedStatement preparedStatement = connection.prepareStatement(query);
             final ResultSet answer = executeStatement.executeStatement(preparedStatement)) {
            return executeResultSet.executeResultSet(answer);
        }

    }

    @Override
    public void executeUpdate(String query, FillPreparedStatement fillStatement) {

        try {
            this.executeUpdateThrowable(query, fillStatement);
        } catch (SQLException exception) {
            LOGGER.error("Update error: ", exception);

            throw new IllegalStateException("Update error", exception);
        }

    }

    protected void executeUpdateThrowable(
            String query, FillPreparedStatement fillStatement) throws SQLException {

        try (final Connection connection = this.dataSource.getConnection()) {
            this.doExecuteUpdate(
                    query,
                    connection,
                    preparedStatement ->
                    {
                        fillStatement.doFill(preparedStatement);

                        preparedStatement.executeUpdate();
                    });
        }
    }

    protected void doExecuteUpdate(
            final String query,
            final Connection connection,
            final ExecuteUpdateStatement<ResultSet> executeUpdateStatement) throws SQLException {

        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            executeUpdateStatement.executeStatement(preparedStatement);
        }

    }
}
