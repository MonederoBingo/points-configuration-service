package com.monederobingo.points_configuration.common.db.datasources;


import javax.sql.DataSource;

import com.monederobingo.points_configuration.common.db.util.concurrent.Computable;
import com.monederobingo.points_configuration.common.db.util.concurrent.Memoizer;
import com.monederobingo.points_configuration.common.environments.Environment;
import org.springframework.stereotype.Component;

import static com.monederobingo.points_configuration.common.db.util.DbUtil.createDataSource;

@Component
public class DataSourceFactoryImpl implements DataSourceFactory {


    private final Computable<Environment, DataSource> _computable = new Computable<Environment, DataSource>() {
        @Override
        public DataSource compute(Environment arg) throws InterruptedException {
            return createDataSource(arg);
        }
    };

    private final Computable<Environment, DataSource> _dataSources = new Memoizer<>(_computable);

    @Override
    public DataSource getDataSource(Environment environment) {
        try {
            final DataSource dataSource = _dataSources.compute(environment);
            if (dataSource == null) {
                throw new RuntimeException("DataSource cannot be null!");
            }
            return dataSource;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
