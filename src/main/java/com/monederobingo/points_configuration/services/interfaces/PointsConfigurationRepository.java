/* Copyright 2017 Sabre Holdings */
package com.monederobingo.points_configuration.services.interfaces;

import com.monederobingo.points_configuration.model.PointsConfiguration;

public interface PointsConfigurationRepository
{
    PointsConfiguration getByCompanyId(long companyId);

    int update(PointsConfiguration pointsConfiguration);
}
