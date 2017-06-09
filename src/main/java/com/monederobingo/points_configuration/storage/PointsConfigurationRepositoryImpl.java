package com.monederobingo.points_configuration.storage;

import static java.lang.Integer.parseInt;

import com.monederobingo.points_configuration.model.PointsConfiguration;
import com.monederobingo.points_configuration.services.interfaces.PointsConfigurationRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PointsConfigurationRepositoryImpl extends BaseRepository implements PointsConfigurationRepository
{
    private final String DATABASE_SERVICE_URL = "http://test.localhost:30001/";

    public PointsConfiguration getByCompanyId(final long companyId) throws Exception
    {
        HttpEntity<SelectQuery> entity = new HttpEntity<>(
                new SelectQuery("SELECT * FROM points_configuration WHERE company_id = " + companyId + ";"),
                getHttpHeaders());
        ResponseEntity<DatabaseServiceResult> responseEntity = getRestTemplate().postForEntity(
                DATABASE_SERVICE_URL + "/select",
                entity,
                DatabaseServiceResult.class);
        if(responseEntity.getBody().getObject() == null)
        {
            return null;
        }
        return buildPointsConfiguration(new JSONObject(responseEntity.getBody()).getString("object"));
    }

    private HttpHeaders getHttpHeaders()
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private RestTemplate getRestTemplate()
    {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> list = new ArrayList<>();
        list.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(list);
        return restTemplate;
    }

    public PointsConfiguration buildPointsConfiguration(String stringObject) throws SQLException, JSONException
    {
        JSONObject object = new JSONObject(stringObject);
        PointsConfiguration pointsConfiguration = new PointsConfiguration();
        pointsConfiguration.setPointsConfigurationId(object.getLong("points_configuration_id"));
        pointsConfiguration.setPointsToEarn((float) object.getDouble("points_to_earn"));
        pointsConfiguration.setRequiredAmount((float) object.getDouble("required_amount"));
        return pointsConfiguration;
    }

    public long insert(PointsConfiguration pointsConfiguration) throws Exception
    {
        String sql = "INSERT INTO points_configuration(company_id, points_to_earn, required_amount)" +
                " VALUES (" +
                pointsConfiguration.getCompanyId() + ", " +
                pointsConfiguration.getPointsToEarn() + ", " +
                pointsConfiguration.getRequiredAmount() + ");";

        HttpEntity<InsertQuery> entity = new HttpEntity<>(
                new InsertQuery(sql, "points_configuration_id"),
                getHttpHeaders());
        ResponseEntity<DatabaseServiceResult> responseEntity = getRestTemplate().postForEntity(
                DATABASE_SERVICE_URL + "/insert",
                entity,
                DatabaseServiceResult.class);
        if(responseEntity.getBody().getObject() == null)
        {
            return 0L;
        }
        return Long.parseLong(responseEntity.getBody().getObject().toString());
    }

    public int update(PointsConfiguration pointsConfiguration) throws Exception
    {
        String sql = "UPDATE points_configuration" +
                " SET points_to_earn = " + pointsConfiguration.getPointsToEarn() + "," +
                " required_amount = " + pointsConfiguration.getRequiredAmount() +
                " WHERE company_id = " + pointsConfiguration.getCompanyId() + ";";

        RestTemplate restTemplate = getRestTemplate();
        UpdateQuery updateQuery = new UpdateQuery(sql);
        ResponseEntity<DatabaseServiceResult> responseEntity = restTemplate.postForEntity(
                DATABASE_SERVICE_URL + "/update",
                updateQuery,
                DatabaseServiceResult.class);
        return parseInt(responseEntity.getBody().getObject().toString());
    }
}
