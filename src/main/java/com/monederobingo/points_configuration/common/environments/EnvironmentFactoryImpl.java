package com.monederobingo.points_configuration.common.environments;

public class EnvironmentFactoryImpl implements EnvironmentFactory {

    @Override
    public DevEnvironment getDevEnvironment() {
        return new DevEnvironment();
    }

    @Override
    public UnitTestEnvironment getUnitTestEnvironment() {
        return new UnitTestEnvironment();
    }

    @Override
    public FunctionalTestEnvironment getFunctionalTestEnvironment() {
        return new FunctionalTestEnvironment();
    }

    @Override
    public UATEnvironment getUATEnvironment() {
        return new UATEnvironment();
    }

    @Override
    public ProdEnvironment getProdEnvironment() {
        return new ProdEnvironment();
    }
}
