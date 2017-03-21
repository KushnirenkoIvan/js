package org.stepik.kushnirenko.dao.jdbc_example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class ExecutorImpl implements Executor {

    @Override
    public Integer execUpdate(Connection connection, String update) {
        try (Statement stmt = connection.createStatement();) {

            stmt.execute(update);

            return stmt.getUpdateCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void execUpdate(Connection connection, String[] updates) {
        try {
            connection.setAutoCommit(false);

            for (String update : updates) {
                Statement stmt = connection.createStatement();
                stmt.execute(update);
                stmt.close();
            }

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void execUpdate(Connection connection, Map<Integer, String> idToName) {
        try {
            String example = "insert into users(id, user_name) values(?,?)";
            PreparedStatement stmt = connection.prepareStatement(example);

            for (Integer id : idToName.keySet()) {
                stmt.setInt(1, id);
                stmt.setString(2, idToName.get(id));
                stmt.executeUpdate();
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> T execQuery(Connection connection, String query, ResultHandler<T> handler) {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(query);

            return handler.handle(stmt.getResultSet());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
