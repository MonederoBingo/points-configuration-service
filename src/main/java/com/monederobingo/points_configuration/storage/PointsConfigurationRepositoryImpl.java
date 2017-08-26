package com.monederobingo.points_configuration.storage;

import com.monederobingo.libs.common.context.ThreadContextService;
import com.monederobingo.points_configuration.model.PointsConfiguration;
import com.monederobingo.points_configuration.services.interfaces.PointsConfigurationRepository;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import xyz.greatapp.libs.service.requests.database.ColumnValue;
import xyz.greatapp.libs.service.requests.database.InsertQueryRQ;
import xyz.greatapp.libs.service.requests.database.SelectQueryRQ;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

@Component
public class PointsConfigurationRepositoryImpl extends BaseRepository implements PointsConfigurationRepository {
    private final EurekaClient eurekaClient;
    private final ThreadContextService threadContextService;

    @Autowired
    public PointsConfigurationRepositoryImpl(@Qualifier("eurekaClient") EurekaClient eurekaClient, ThreadContextService threadContextService) {
        this.eurekaClient = eurekaClient;
        this.threadContextService = threadContextService;
    }

    public PointsConfiguration getByCompanyId(final long companyId) throws Exception {

        ColumnValue[] filters = new ColumnValue[]{
                new ColumnValue("company_id", companyId)
        };
        HttpEntity<SelectQueryRQ> entity = new HttpEntity<>(
                new SelectQueryRQ("points_configuration", filters),
                getHttpHeaders());
        ResponseEntity<DatabaseServiceResult> responseEntity = getRestTemplate().postForEntity(
                getDatabaseURL() + "/select",
                entity,
                DatabaseServiceResult.class);
        if (responseEntity.getBody().getObject() == null) {
            return null;
        }
        return buildPointsConfiguration(new JSONObject(responseEntity.getBody()).getString("object"));
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> list = new ArrayList<>();
        list.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(list);
        return restTemplate;
    }

    private PointsConfiguration buildPointsConfiguration(String stringObject) throws SQLException, JSONException {
        JSONObject object = new JSONObject(stringObject);
        PointsConfiguration pointsConfiguration = new PointsConfiguration();
        pointsConfiguration.setPointsConfigurationId(object.getLong("points_configuration_id"));
        pointsConfiguration.setPointsToEarn((float) object.getDouble("points_to_earn"));
        pointsConfiguration.setRequiredAmount((float) object.getDouble("required_amount"));
        return pointsConfiguration;
    }

    public long insert(PointsConfiguration pointsConfiguration) throws Exception {
        ColumnValue[] values = new ColumnValue[]{
                new ColumnValue("company_id", pointsConfiguration.getCompanyId()),
                new ColumnValue("points_to_earn", pointsConfiguration.getPointsToEarn()),
                new ColumnValue("required_amount", pointsConfiguration.getRequiredAmount())
        };
        HttpEntity<InsertQueryRQ> entity = new HttpEntity<>(
                new InsertQueryRQ("points_configuration", values, "points_configuration_id"),
                getHttpHeaders());
        ResponseEntity<DatabaseServiceResult> responseEntity = getRestTemplate().postForEntity(
                getDatabaseURL() + "/insert",
                entity,
                DatabaseServiceResult.class);
        if (responseEntity.getBody().getObject() == null) {
            return 0L;
        }
        return Long.parseLong(responseEntity.getBody().getObject().toString());
    }

    public int update(PointsConfiguration pointsConfiguration) throws Exception {
        String sql = "UPDATE points_configuration" +
                " SET points_to_earn = " + pointsConfiguration.getPointsToEarn() + "," +
                " required_amount = " + pointsConfiguration.getRequiredAmount() +
                " WHERE company_id = " + pointsConfiguration.getCompanyId() + ";";

        RestTemplate restTemplate = getRestTemplate();
        UpdateQuery updateQuery = new UpdateQuery(sql);
        ResponseEntity<DatabaseServiceResult> responseEntity = restTemplate.postForEntity(
                getDatabaseURL() + "/update",
                updateQuery,
                DatabaseServiceResult.class);
        return parseInt(responseEntity.getBody().getObject().toString());
    }

    private String getDatabaseURL() {
        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("database", false);
        String homePageUrl = instanceInfo.getHomePageUrl();
        boolean hasHttps = homePageUrl.contains("https://");
        homePageUrl = homePageUrl.replace("http://", "");
        homePageUrl = homePageUrl.replace("https://", "");
        homePageUrl = threadContextService.getEnvironment().getURIPrefix() + homePageUrl;
        return hasHttps ? "https://" + homePageUrl : "http://" + homePageUrl;
    }
}
