package com.monederobingo.points_configuration.common.environments;

public interface EnvironmentFactory {
    public DevEnvironment getDevEnvironment();

    public UnitTestEnvironment getUnitTestEnvironment();

    public FunctionalTestEnvironment getFunctionalTestEnvironment();

    public UATEnvironment getUATEnvironment();

    public ProdEnvironment getProdEnvironment();
}
