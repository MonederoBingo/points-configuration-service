package com.monederobingo.points_configuration.storage;

public class UpdateQuery
{
    private final String query;

    public UpdateQuery(String query)
    {
        this.query = query;
    }

    public String getQuery()
    {
        return query;
    }
}
