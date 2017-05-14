package com.monederobingo.points_configuration.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DBProperties
{
    private String dbDriver;

    public String getDbDriver()
    {
        return dbDriver;
    }
}
