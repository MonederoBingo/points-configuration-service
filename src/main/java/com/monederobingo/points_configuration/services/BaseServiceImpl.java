package com.monederobingo.points_configuration.services;

import com.monederobingo.points_configuration.common.context.ThreadContext;
import com.monederobingo.points_configuration.common.context.ThreadContextService;
import com.monederobingo.points_configuration.common.db.queryagent.QueryAgent;
import com.monederobingo.points_configuration.common.environments.DevEnvironment;
import com.monederobingo.points_configuration.common.environments.Environment;
import com.monederobingo.points_configuration.common.environments.FunctionalTestEnvironment;
import com.monederobingo.points_configuration.common.environments.ProdEnvironment;
import com.monederobingo.points_configuration.common.environments.UATEnvironment;

public class BaseServiceImpl {

    private final ThreadContextService _threadContextService;

    public BaseServiceImpl(ThreadContextService threadContextService) {
        _threadContextService = threadContextService;
    }

    public boolean isProdEnvironment() {
        return _threadContextService.getThreadContext().getEnvironment() instanceof ProdEnvironment;
    }

    public boolean isUATEnvironment() {
        return _threadContextService.getThreadContext().getEnvironment() instanceof UATEnvironment;
    }

    public boolean isDevEnvironment() {
        return _threadContextService.getThreadContext().getEnvironment() instanceof DevEnvironment;
    }

    public boolean isFunctionalTestEnvironment() {
        return _threadContextService.getThreadContext().getEnvironment() instanceof FunctionalTestEnvironment;
    }

    public Environment getEnvironment() {
        return _threadContextService.getThreadContext().getEnvironment();
    }

    public ThreadContextService getThreadContextService() {
        return _threadContextService;
    }

    public ThreadContext getThreadContext() {
        return _threadContextService.getThreadContext();
    }

    public QueryAgent getQueryAgent() {
        return _threadContextService.getQueryAgent();
    }
}
