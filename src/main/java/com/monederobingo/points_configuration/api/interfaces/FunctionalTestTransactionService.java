package com.monederobingo.points_configuration.api.interfaces;

public interface FunctionalTestTransactionService
{
    void beginTransaction();

    void rollbackTransaction();
}
