package com.monederobingo.points_configuration.services;

import static com.monederobingo.points_configuration.common.strings.Message.UPDATED_CONFIGURATION;
import static org.easymock.EasyMock.anyLong;
import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.monederobingo.points_configuration.model.PointsConfiguration;
import com.monederobingo.points_configuration.model.ServiceResult;
import com.monederobingo.points_configuration.services.interfaces.PointsConfigurationRepository;
import org.json.JSONObject;
import org.junit.Test;

public class PointsConfigurationServiceImplTest
{
    @Test
    public void testGetByCompanyId() throws Exception
    {
        PointsConfiguration expectedPointsConfiguration = new PointsConfiguration();
        expectedPointsConfiguration.setCompanyId(1);
        expectedPointsConfiguration.setPointsToEarn(10);
        expectedPointsConfiguration.setRequiredAmount(100);
        PointsConfigurationRepository pointsConfigurationRepository = createPointsConfigurationRepositoryForGet(expectedPointsConfiguration);
        PointsConfigurationServiceImpl pointsConfigurationService = new PointsConfigurationServiceImpl(pointsConfigurationRepository, null);

        xyz.greatapp.libs.service.ServiceResult serviceResult = pointsConfigurationService.getByCompanyId(1);
        assertNotNull(serviceResult);
        assertTrue(serviceResult.isSuccess());
        assertEquals("", serviceResult.getMessage());
        assertNotNull(serviceResult.getObject());
        JSONObject actualPointsConfiguration = new JSONObject(serviceResult.getObject());
        assertEquals(expectedPointsConfiguration.getCompanyId(), actualPointsConfiguration.getLong("company_id"));
        assertEquals(expectedPointsConfiguration.getPointsToEarn(), actualPointsConfiguration.getDouble("points_to_earn"), 0.00);
        assertEquals(expectedPointsConfiguration.getRequiredAmount(), actualPointsConfiguration.getDouble("required_amount"), 0.00);

        verify(pointsConfigurationRepository);
    }

    @Test
    public void testUpdate() throws Exception
    {
        PointsConfigurationRepository pointsConfigurationRepository = createPointsConfigurationRepositoryForUpdate();
        PointsConfigurationServiceImpl pointsConfigurationService = new PointsConfigurationServiceImpl(pointsConfigurationRepository, null);
        ServiceResult<Boolean> serviceResult = pointsConfigurationService.update(new PointsConfiguration());
        assertNotNull(serviceResult);
        assertTrue(serviceResult.isSuccess());
        assertEquals(UPDATED_CONFIGURATION.getMessage(), serviceResult.getMessage());
        assertEquals(true, serviceResult.getObject());
        verify(pointsConfigurationRepository);
    }

    private PointsConfigurationRepository createPointsConfigurationRepositoryForUpdate() throws Exception
    {
        PointsConfigurationRepository pointsConfigurationRepository = createMock(PointsConfigurationRepository.class);
        expect(pointsConfigurationRepository.update(anyObject())).andReturn(1);
        replay(pointsConfigurationRepository);
        return pointsConfigurationRepository;
    }

    private PointsConfigurationRepository createPointsConfigurationRepositoryForGet(PointsConfiguration pointsConfiguration) throws Exception
    {
        PointsConfigurationRepository pointsConfigurationRepository = createMock(PointsConfigurationRepository.class);
        expect(pointsConfigurationRepository.getByCompanyId(anyLong()))
                .andReturn(new xyz.greatapp.libs.service.ServiceResult(true, "", pointsConfiguration.toJSONObject().toString()));
        replay(pointsConfigurationRepository);
        return pointsConfigurationRepository;
    }
}
