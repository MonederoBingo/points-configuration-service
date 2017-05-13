package com.monederobingo.points_configuration.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.monederobingo.points_configuration.model.PointsConfiguration;
import com.monederobingo.points_configuration.model.ServiceResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.monederobingo.points_configuration.api.interfaces.*;

@RunWith(MockitoJUnitRunner.class)
public class PointsConfigurationControllerTest
{
    @InjectMocks
    private PointsConfigurationController pointsConfigurationController;

    @Mock
    private PointsConfigurationService pointsConfigurationService;
    @Mock
    private ServiceResult<PointsConfiguration> serviceResultOfPointsConfiguration;
    @Mock
    private PointsConfiguration pointsConfiguration;
    @Mock
    private ServiceResult<Boolean> serviceResultOfBoolean;

    @Test
    public void getShouldCallService() throws Exception
    {
        // when
        pointsConfigurationController.get(1);

        // then
        verify(pointsConfigurationService).getByCompanyId(1);
    }

    @Test
    public void getShouldReturnServiceResultFromService()
    {
        //given
        given(pointsConfigurationService.getByCompanyId(1)).willReturn(serviceResultOfPointsConfiguration);

        //when
        ResponseEntity<ServiceResult<PointsConfiguration>> serviceResultResponseEntity = pointsConfigurationController.get(1);

        //then
        assertEquals(serviceResultOfPointsConfiguration, serviceResultResponseEntity.getBody());
    }

    @Test
    public void updateShouldCallService() throws Exception
    {
        // when
        pointsConfigurationController.update(pointsConfiguration);

        // then
        verify(pointsConfigurationService).update(pointsConfiguration);
    }

    @Test
    public void updateShouldReturnServiceResultFromService()
    {
        //given
        given(pointsConfigurationService.update(pointsConfiguration)).willReturn(serviceResultOfBoolean);

        //when
        ResponseEntity<ServiceResult<Boolean>> serviceResultResponseEntity = pointsConfigurationController.update(pointsConfiguration);

        //then
        assertEquals(serviceResultOfBoolean, serviceResultResponseEntity.getBody());
    }
}
