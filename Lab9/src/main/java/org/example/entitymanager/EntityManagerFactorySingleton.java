package org.example.entitymanager;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactorySingleton {

    private EntityManagerFactorySingleton() {}

    private static class SingletonHelper {
        private static final EntityManagerFactory INSTANCE = Persistence.createEntityManagerFactory("persistence");
    }

    public static EntityManagerFactory getInstance() {
        return SingletonHelper.INSTANCE;
    }
}
