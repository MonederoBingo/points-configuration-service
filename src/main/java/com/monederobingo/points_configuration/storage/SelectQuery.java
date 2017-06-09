package com.monederobingo.points_configuration.storage;

public class SelectQuery
{
    private final String query;

    public SelectQuery(String query)
    {
        this.query = query;
    }

    public String getQuery()
    {
        return query;
    }
}
