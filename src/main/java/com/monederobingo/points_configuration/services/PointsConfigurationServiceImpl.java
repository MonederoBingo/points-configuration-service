package com.monederobingo.points_configuration.services;

import static com.monederobingo.points_configuration.common.strings.Message.SERVER_ERROR;
import static com.monederobingo.points_configuration.common.strings.Message.UPDATED_CONFIGURATION;

import com.monederobingo.libs.common.context.ThreadContextService;
import com.monederobingo.points_configuration.api.interfaces.PointsConfigurationService;
import com.monederobingo.points_configuration.model.PointsConfiguration;
import com.monederobingo.points_configuration.model.ServiceResult;
import com.monederobingo.points_configuration.services.interfaces.PointsConfigurationRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PointsConfigurationServiceImpl extends BaseServiceImpl implements PointsConfigurationService
{
    private static final Logger logger = LogManager.getLogger(PointsConfigurationServiceImpl.class.getName());
    private final PointsConfigurationRepository _pointsConfigurationRepository;

    @Autowired
    public PointsConfigurationServiceImpl(PointsConfigurationRepository pointsConfigurationRepository, ThreadContextService threadContextService)
    {
        super(threadContextService);
        _pointsConfigurationRepository = pointsConfigurationRepository;
    }

    public xyz.greatapp.libs.service.ServiceResult getByCompanyId(long companyId)
    {
        try
        {
            return _pointsConfigurationRepository.getByCompanyId(companyId);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(), ex);
            return new xyz.greatapp.libs.service.ServiceResult(false, SERVER_ERROR.getMessage(), null);
        }
    }

    public ServiceResult<Boolean> update(PointsConfiguration pointsConfiguration)
    {
        try
        {
            int updatedRows = _pointsConfigurationRepository.update(pointsConfiguration);
            return new ServiceResult<>(true, UPDATED_CONFIGURATION.getMessage(), updatedRows == 1);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage(), ex);
            return new ServiceResult<>(false, SERVER_ERROR.getMessage(), null);
        }
    }
}
