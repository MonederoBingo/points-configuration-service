package com.monederobingo.points_configuration.storage;

import com.monederobingo.points_configuration.common.db.util.DbBuilder;
import com.monederobingo.points_configuration.model.PointsConfiguration;
import com.monederobingo.points_configuration.services.interfaces.PointsConfigurationRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

@Component
public class PointsConfigurationRepositoryImpl extends BaseRepository implements PointsConfigurationRepository
{

    public PointsConfiguration getByCompanyId(final long companyId) throws Exception
    {
        return getQueryAgent().selectObject(new DbBuilder<PointsConfiguration>()
        {
            @Override
            public String sql()
            {
                return "SELECT * FROM points_configuration WHERE company_id = ? ;";
            }

            @Override
            public Object[] values()
            {
                return new Object[] { companyId };
            }

            @Override
            public PointsConfiguration build(ResultSet resultSet) throws SQLException
            {
                PointsConfiguration pointsConfiguration = new PointsConfiguration();
                pointsConfiguration.setPointsConfigurationId(resultSet.getLong("points_configuration_id"));
                pointsConfiguration.setPointsToEarn(resultSet.getFloat("points_to_earn"));
                pointsConfiguration.setRequiredAmount(resultSet.getFloat("required_amount"));
                return pointsConfiguration;
            }
        });
    }

    public long insert(PointsConfiguration pointsConfiguration) throws Exception
    {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO points_configuration(company_id, points_to_earn, required_amount)");
        sql.append(" VALUES (");
        sql.append(pointsConfiguration.getCompanyId()).append(", ");
        sql.append(pointsConfiguration.getPointsToEarn()).append(", ");
        sql.append(pointsConfiguration.getRequiredAmount()).append(");");

        return getQueryAgent().executeInsert(sql.toString(), "points_configuration_id");
    }

    public int update(PointsConfiguration pointsConfiguration) throws Exception
    {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE points_configuration");
        sql.append(" SET points_to_earn = ").append(pointsConfiguration.getPointsToEarn()).append(",");
        sql.append(" required_amount = ").append(pointsConfiguration.getRequiredAmount());
        sql.append(" WHERE company_id = ").append(pointsConfiguration.getCompanyId()).append(";");

        return getQueryAgent().executeUpdate(sql.toString());
    }
}
