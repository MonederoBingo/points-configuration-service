package com.monederobingo.points_configuration.api;

import static org.easymock.EasyMock.anyLong;
import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.monederobingo.points_configuration.model.PointsConfiguration;
import com.monederobingo.points_configuration.model.ServiceMessage;
import com.monederobingo.points_configuration.model.ServiceResult;
import com.monederobingo.points_configuration.services.PointsConfigurationServiceImpl;
import com.monederobingo.points_configuration.storage.PointsConfigurationRepositoryImpl;
import org.easymock.EasyMock;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

public class PointsConfigurationControllerTest
{
    private PointsConfigurationRepositoryImpl pointsConfigurationRepository = createMock(PointsConfigurationRepositoryImpl.class);

    @Test
    public void testGet() throws Exception {
        PointsConfiguration expectedPointsConfiguration = new PointsConfiguration();
        expectedPointsConfiguration.setCompanyId(1);
        expectedPointsConfiguration.setPointsToEarn(10);
        expectedPointsConfiguration.setRequiredAmount(100);
        ServiceResult<PointsConfiguration> expectedServiceResult = new ServiceResult<>(true, ServiceMessage.EMPTY,
                expectedPointsConfiguration);
        PointsConfigurationServiceImpl pointsConfigurationService = createPointsConfigurationServiceForGet(expectedServiceResult);
        PointsConfigurationController pointsConfigurationController = new PointsConfigurationController(pointsConfigurationService, pointsConfigurationRepository);

        ResponseEntity<ServiceResult<PointsConfiguration>> responseEntity = pointsConfigurationController.get(1);
        assertNotNull(responseEntity);
        ServiceResult<PointsConfiguration> actualServiceResults = responseEntity.getBody();
        assertNotNull(actualServiceResults);
        assertEquals(expectedServiceResult.isSuccess(), actualServiceResults.isSuccess());
        assertEquals(expectedServiceResult.getMessage(), actualServiceResults.getMessage());
        assertNotNull(actualServiceResults.getObject());
        PointsConfiguration actualPointsConfiguration = actualServiceResults.getObject();
        assertNotNull(actualPointsConfiguration);
        assertEquals(expectedPointsConfiguration.getCompanyId(), actualPointsConfiguration.getCompanyId());
        assertEquals(expectedPointsConfiguration.getPointsToEarn(), actualPointsConfiguration.getPointsToEarn(), 0.00);
        assertEquals(expectedPointsConfiguration.getRequiredAmount(), actualPointsConfiguration.getRequiredAmount(), 0.00);

        EasyMock.verify(pointsConfigurationService);
    }

    @Test
    public void testUpdate() throws Exception {
        ServiceResult<Boolean> expectedServiceResult = new ServiceResult<>(true, ServiceMessage.EMPTY, true);
        PointsConfigurationServiceImpl pointsConfigurationService = createPointsConfigurationServiceForUpdate(expectedServiceResult);
        PointsConfigurationController pointsConfigurationController = new PointsConfigurationController(pointsConfigurationService, pointsConfigurationRepository);

        ResponseEntity<ServiceResult<Boolean>> responseEntity = pointsConfigurationController.update(new PointsConfiguration());
        assertNotNull(responseEntity);
        ServiceResult actualServiceResults = responseEntity.getBody();
        assertNotNull(actualServiceResults);
        assertEquals(expectedServiceResult.isSuccess(), actualServiceResults.isSuccess());
        assertEquals(expectedServiceResult.getMessage(), actualServiceResults.getMessage());
        assertEquals(expectedServiceResult.getObject(), actualServiceResults.getObject());

        EasyMock.verify(pointsConfigurationService);
    }

    private PointsConfigurationServiceImpl createPointsConfigurationServiceForGet(ServiceResult<PointsConfiguration> serviceResult) throws Exception {
        PointsConfigurationServiceImpl pointsConfigurationService = createMock(PointsConfigurationServiceImpl.class);
        expect(pointsConfigurationService.getByCompanyId(anyLong())).andReturn(serviceResult);
        replay(pointsConfigurationService);
        return pointsConfigurationService;
    }

    private PointsConfigurationServiceImpl createPointsConfigurationServiceForUpdate(ServiceResult<Boolean> serviceResult) throws Exception {
        PointsConfigurationServiceImpl pointsConfigurationService = createMock(PointsConfigurationServiceImpl.class);
        expect(pointsConfigurationService.update((PointsConfiguration) anyObject())).andReturn(serviceResult);
        replay(pointsConfigurationService);
        return pointsConfigurationService;
    }
}
