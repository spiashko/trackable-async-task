package com.spiashko.rfetchexample.utils;

import lombok.Getter;

import javax.persistence.EntityManager;

@Getter
public class EntityManagerHolder {

    private final EntityManager entityManager;

    private static volatile EntityManagerHolder instance;

    public EntityManagerHolder(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public static EntityManagerHolder createInstance(EntityManager entityManager) {
        EntityManagerHolder localInstance = instance;
        if (localInstance == null) {
            synchronized (EntityManagerHolder.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new EntityManagerHolder(entityManager);
                }
            }
        }
        return localInstance;
    }

    public static EntityManagerHolder getInstance() {
        if (instance == null) {
            throw new NullPointerException("EntityManagerHolder is not instantiated");
        }
        return instance;
    }
}
