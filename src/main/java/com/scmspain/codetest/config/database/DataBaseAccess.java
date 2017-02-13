package com.scmspain.codetest.config.database;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by josep.carne on 11/02/2017.
 */
public interface DataBaseAccess {
    List<Map<String, String>> executeQuery(
            final String query, final ExecuteResultSet<ResultSet> executeResultSet, FillPreparedStatement fillStatement);

    void executeUpdate(String query, FillPreparedStatement fillStatement);

    public interface ExecuteStatement<TResult> {
        TResult executeStatement(final PreparedStatement statement) throws SQLException;
    }

    public interface ExecuteResultSet<TResult> {
        List<Map<String, String>> executeResultSet(final TResult resultSet) throws SQLException;
    }

    public interface ExecuteUpdateStatement<TResult> {
        void executeStatement(final PreparedStatement statement) throws SQLException;
    }


    public interface FillPreparedStatement {
        void doFill(final PreparedStatement statement) throws SQLException;
    }
}
