
package com.monederobingo.points_configuration.services.interfaces;

import com.monederobingo.points_configuration.model.PointsConfiguration;
import xyz.greatapp.libs.service.ServiceResult;

public interface PointsConfigurationRepository
{
    ServiceResult getByCompanyId(long companyId) throws Exception;

    int update(PointsConfiguration pointsConfiguration) throws Exception;
}
