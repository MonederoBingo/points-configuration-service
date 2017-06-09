package com.monederobingo.points_configuration.common.context;

import com.monederobingo.libs.common.environments.Environment;
import com.monederobingo.points_configuration.common.db.queryagent.QueryAgent;

public interface ThreadContextService {

    void initializeContext(Environment env, String language);

    ThreadContext getThreadContext();

    QueryAgent getQueryAgent();

    void setThreadContextOnThread(ThreadContext threadContext);
}
