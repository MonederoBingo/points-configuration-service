package com.monederobingo.points_configuration.common.db.util;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DbBuilder<T> {
    public abstract String sql() throws SQLException;

    public abstract Object[] values();
    public abstract T build(ResultSet resultSet) throws SQLException;
}
