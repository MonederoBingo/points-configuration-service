package com.monederobingo.points_configuration.api.interfaces;

import com.monederobingo.points_configuration.model.PointsConfiguration;
import com.monederobingo.points_configuration.model.ServiceResult;

public interface PointsConfigurationService
{
    xyz.greatapp.libs.service.ServiceResult getByCompanyId(long companyId);

    ServiceResult<Boolean> update(PointsConfiguration pointsConfiguration);
}
