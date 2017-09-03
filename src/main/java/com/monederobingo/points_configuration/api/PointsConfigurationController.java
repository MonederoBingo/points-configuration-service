package com.monederobingo.points_configuration.api;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import com.monederobingo.points_configuration.model.PointsConfiguration;
import com.monederobingo.points_configuration.model.ServiceResult;
import com.monederobingo.points_configuration.storage.PointsConfigurationRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    private PointsConfigurationRepositoryImpl _pointsConfigurationRepository;

    @Autowired
    public PointsConfigurationController(PointsConfigurationService pointsConfigurationService, PointsConfigurationRepositoryImpl pointsConfigurationRepository) {
        _pointsConfigurationService = pointsConfigurationService;
        _pointsConfigurationRepository = pointsConfigurationRepository;
    }

    @RequestMapping(value = "/{companyId}", method = GET)
    public ResponseEntity<xyz.greatapp.libs.service.ServiceResult> get(@PathVariable("companyId") long companyId) {
        xyz.greatapp.libs.service.ServiceResult serviceResult = _pointsConfigurationService.getByCompanyId(companyId);
        return new ResponseEntity<>(serviceResult, HttpStatus.OK);
    }

    @RequestMapping(method = PUT)
    public ResponseEntity<ServiceResult<Boolean>> update(@RequestBody PointsConfiguration pointsConfiguration) {
        ServiceResult<Boolean> serviceResult = _pointsConfigurationService.update(pointsConfiguration);
        return new ResponseEntity<>(serviceResult, HttpStatus.OK);
    }

    @RequestMapping(value = "/{companyId}", method = POST)
    public void registerPointsConfiguration(@PathVariable("companyId") long companyId) throws Exception {
        PointsConfiguration pointsConfiguration = new PointsConfiguration();
        pointsConfiguration.setCompanyId(companyId);
        pointsConfiguration.setPointsToEarn(1);
        pointsConfiguration.setRequiredAmount(1);
        _pointsConfigurationRepository.insert(pointsConfiguration);
    }
}
