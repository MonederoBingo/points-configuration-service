package com.monederobingo.points_configuration.common.db.util;

import javax.sql.DataSource;

import com.monederobingo.points_configuration.common.PropertyManager;
import com.monederobingo.points_configuration.common.environments.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DbUtil {

    public static String getUnitTestDatabasePath() {
        return PropertyManager.getProperties().getProperty("unit_test.db_url");
    }

    public static String getDevDatabasePath() {
        return PropertyManager.getProperties().getProperty("dev.db_url");
    }

    public static DataSource createDataSource(Environment environment) {
        return createDataSourceSpecifyingUrl(environment, environment.getDatabasePath());
    }

    public static DataSource createDataSourceWithoutDatabaseName(Environment environment) {
        return createDataSourceSpecifyingUrl(environment, PropertyManager.getProperty("db_url"));
    }

    private static DataSource createDataSourceSpecifyingUrl(Environment environment, String url) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getDatabaseDriverClass());
        dataSource.setUrl(url);
        dataSource.setUsername(environment.getDatabaseUsername());
        dataSource.setPassword(environment.getDatabasePassword());
        return dataSource;
    }
}
