package org.stepik.kushnirenko.dao.jdbc_example;

import java.sql.Connection;
import java.util.Map;

public interface Executor {

    Integer execUpdate(Connection connection, String update);

    void execUpdate(Connection connection, String[] update);

    void execUpdate(Connection connection, Map<Integer, String> idToName);

    <T> T execQuery(Connection connection, String query, ResultHandler<T> handler);

}
