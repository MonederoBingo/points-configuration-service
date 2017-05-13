package com.monederobingo.points_configuration.api.interfaces;

import com.monederobingo.points_configuration.model.PointsConfiguration;
import com.monederobingo.points_configuration.model.ServiceResult;

public interface PointsConfigurationService
{
    ServiceResult<PointsConfiguration> getByCompanyId(long companyId);

    ServiceResult<Boolean> update(PointsConfiguration pointsConfiguration);
}
