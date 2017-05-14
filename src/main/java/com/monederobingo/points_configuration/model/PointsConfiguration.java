package com.monederobingo.points_configuration.model;

public class PointsConfiguration
{
    private long companyId;
    private float pointsToEarn;
    private float requiredAmount;
    private long pointsConfigurationId;

    public long getCompanyId()
    {
        return companyId;
    }

    public void setCompanyId(long companyId)
    {
        this.companyId = companyId;
    }

    public void setPointsToEarn(float pointsToEarn)
    {
        this.pointsToEarn = pointsToEarn;
    }

    public float getPointsToEarn()
    {
        return pointsToEarn;
    }

    public void setRequiredAmount(float requiredAmount)
    {
        this.requiredAmount = requiredAmount;
    }

    public float getRequiredAmount()
    {
        return requiredAmount;
    }

    public void setPointsConfigurationId(long pointsConfigurationId)
    {
        this.pointsConfigurationId = pointsConfigurationId;
    }

    public long getPointsConfigurationId()
    {
        return pointsConfigurationId;
    }
}
