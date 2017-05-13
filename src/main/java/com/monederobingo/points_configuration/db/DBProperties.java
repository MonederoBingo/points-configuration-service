/* Copyright 2017 Sabre Holdings */
package com.monederobingo.points_configuration.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DBProperties
{
    @Value("${db_driver}")
    private String dbDriver;

    public String getDbDriver()
    {
        return dbDriver;
    }
}
