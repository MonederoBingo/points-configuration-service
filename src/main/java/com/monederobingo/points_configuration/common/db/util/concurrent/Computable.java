package com.monederobingo.points_configuration.common.db.util.concurrent;

public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}
