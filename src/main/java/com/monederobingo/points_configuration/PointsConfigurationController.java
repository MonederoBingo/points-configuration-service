/* Copyright 2017 Sabre Holdings */
package com.monederobingo.points_configuration;

import com.monederobingo.points_configuration.db.DBProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PointsConfigurationController
{

    private final DBProperties dbProperties;

    @Autowired
    public PointsConfigurationController(DBProperties dbProperties)
    {
        this.dbProperties = dbProperties;
    }

    @RequestMapping(value = "/")
    public String sayHello()
    {
        return dbProperties.getDbDriver();
    }
}
