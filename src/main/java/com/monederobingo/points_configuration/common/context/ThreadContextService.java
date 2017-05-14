package com.monederobingo.points_configuration.common.context;

import com.monederobingo.points_configuration.common.db.queryagent.QueryAgent;
import com.monederobingo.points_configuration.common.environments.Environment;

public interface ThreadContextService {

    void initializeContext(Environment env, String language);

    ThreadContext getThreadContext();

    QueryAgent getQueryAgent();

    void setThreadContextOnThread(ThreadContext threadContext);
}
