package com.monederobingo.points_configuration.common.db.datasources;

import com.monederobingo.points_configuration.common.environments.Environment;

import javax.sql.DataSource;

public interface DataSourceFactory {
    DataSource getDataSource(Environment environment);
}
