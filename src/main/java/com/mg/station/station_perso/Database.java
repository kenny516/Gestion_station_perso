package com.mg.station.station_perso;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class Database {
    public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("default");
    private static final String ID_GENERATION_SQL_TEMPLATE = "SELECT CONCAT(:prefix, %s.NEXTVAL) FROM DUAL";

    private Database() { }

    public static String generateId(String prefix, String sequenceName) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return (String) entityManager.createNativeQuery(String.format(ID_GENERATION_SQL_TEMPLATE, sequenceName))
                    .setParameter("prefix", prefix + "00")
                    .getSingleResult();
        } finally {
            entityManager.close();
        }
    }
}
