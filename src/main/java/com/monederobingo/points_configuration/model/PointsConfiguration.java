package com.monederobingo.points_configuration.model;

public class PointsConfiguration
{
    private long companyId;
    private int pointsToEarn;
    private int requiredAmount;

    public long getCompanyId()
    {
        return companyId;
    }

    public void setCompanyId(long companyId)
    {
        this.companyId = companyId;
    }

    public void setPointsToEarn(int pointsToEarn)
    {
        this.pointsToEarn = pointsToEarn;
    }

    public int getPointsToEarn()
    {
        return pointsToEarn;
    }

    public void setRequiredAmount(int requiredAmount)
    {
        this.requiredAmount = requiredAmount;
    }

    public int getRequiredAmount()
    {
        return requiredAmount;
    }
}
