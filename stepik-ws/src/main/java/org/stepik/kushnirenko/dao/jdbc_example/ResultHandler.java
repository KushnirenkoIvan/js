package org.stepik.kushnirenko.dao.jdbc_example;

import java.sql.ResultSet;
import java.sql.SQLException;


@FunctionalInterface
public interface ResultHandler<T> {

    T handle(ResultSet resultSet) throws SQLException;

}
