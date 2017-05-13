package com.monederobingo.points_configuration.api;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import com.monederobingo.points_configuration.model.PointsConfiguration;
import com.monederobingo.points_configuration.model.ServiceResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monederobingo.points_configuration.api.interfaces.*;

@RestController
public class PointsConfigurationController
{

    private PointsConfigurationService _pointsConfigurationService;

    public PointsConfigurationController(PointsConfigurationService pointsConfigurationService) {
        _pointsConfigurationService = pointsConfigurationService;
    }

    @RequestMapping(value = "/{companyId}", method = GET)
    public ResponseEntity<ServiceResult<PointsConfiguration>> get(@PathVariable("companyId") long companyId) {
        ServiceResult<PointsConfiguration> serviceResult = _pointsConfigurationService.getByCompanyId(companyId);
        return new ResponseEntity<>(serviceResult, HttpStatus.OK);
    }

    @RequestMapping(method = PUT)
    public ResponseEntity<ServiceResult<Boolean>> update(@RequestBody PointsConfiguration pointsConfiguration) {
        ServiceResult<Boolean> serviceResult = _pointsConfigurationService.update(pointsConfiguration);
        return new ResponseEntity<>(serviceResult, HttpStatus.OK);
    }
}
