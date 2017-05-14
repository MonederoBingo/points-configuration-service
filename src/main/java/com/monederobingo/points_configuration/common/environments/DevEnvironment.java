package com.monederobingo.points_configuration.common.environments;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DevEnvironment extends Environment
{
    @Value("${db_driver}")
    private String dbDriver;

    @Value("${db_driver_class}")
    private String dbDriverClass;

    @Value("${dev.db_url}")
    private String dbUrl;

    @Value("${dev.db_user}")
    private String dbUser;

    @Value("${dev.db_password}")
    private String dbPassword;

    @Value("${dev.images_dir}")
    private String imagesDir;

    @Value("${dev.client_url}")
    private String clientUrl;

    @Override
    public String getDatabasePath()
    {
        return dbDriver + dbUrl;
    }

    public String getDbUrl()
    {
        return dbUrl;
    }

    public String getDatabaseDriverClass()
    {
        return dbDriverClass;
    }

    public String getDatabaseUsername()
    {
        return dbUser;
    }

    public String getDatabasePassword()
    {
        return dbPassword;
    }

    public String getImageDir()
    {
        return imagesDir;
    }

    public String getClientUrl()
    {
        return clientUrl;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (!(obj instanceof DevEnvironment))
        {
            return false;
        }
        DevEnvironment that = (DevEnvironment) obj;
        return getDatabasePath().equals(that.getDatabasePath());
    }

    @Override
    public int hashCode()
    {
        return getDatabasePath().hashCode();
    }
}
