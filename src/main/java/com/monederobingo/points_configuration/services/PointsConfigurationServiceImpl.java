package com.monederobingo.points_configuration.services;

import com.monederobingo.points_configuration.api.interfaces.PointsConfigurationService;
import com.monederobingo.points_configuration.common.context.ThreadContextService;
import com.monederobingo.points_configuration.common.i18n.Message;
import com.monederobingo.points_configuration.model.PointsConfiguration;
import com.monederobingo.points_configuration.model.ServiceMessage;
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
    public PointsConfigurationServiceImpl(PointsConfigurationRepository pointsConfigurationRepository, ThreadContextService threadContextService) {
        super(threadContextService);
        _pointsConfigurationRepository = pointsConfigurationRepository;
    }

    public ServiceResult<PointsConfiguration> getByCompanyId(long companyId) {
        try {
            PointsConfiguration pointsConfiguration = _pointsConfigurationRepository.getByCompanyId(companyId);
            return new ServiceResult<>(true, ServiceMessage.EMPTY, pointsConfiguration);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ServiceResult<>(false, getServiceMessage(Message.COMMON_USER_ERROR), null);
        }
    }

    public ServiceResult<Boolean> update(PointsConfiguration pointsConfiguration) {
        try {
            int updatedRows = _pointsConfigurationRepository.update(pointsConfiguration);
            return new ServiceResult<>(true, getServiceMessage(Message.CONFIGURATION_UPDATED), updatedRows == 1);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ServiceResult<>(false, getServiceMessage(Message.COMMON_USER_ERROR), null);
        }
    }
}
