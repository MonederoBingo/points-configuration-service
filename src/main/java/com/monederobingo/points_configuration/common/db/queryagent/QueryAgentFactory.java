package com.monederobingo.points_configuration.common.db.queryagent;

import com.monederobingo.points_configuration.common.environments.Environment;

public interface QueryAgentFactory {
    QueryAgent getQueryAgent(Environment environment);
}
