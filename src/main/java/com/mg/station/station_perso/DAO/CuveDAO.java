package com.mg.station.station_perso.DAO;

import com.mg.station.station_perso.Database;
import com.mg.station.station_perso.entity.Cuve;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class CuveDAO {

    public void create(Cuve cuve) {
        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(cuve);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Cuve read(String id) {
        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.find(Cuve.class, id);
        } finally {
            em.close();
        }
    }

    public List<Cuve> readAll() {
        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Cuve c", Cuve.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(Cuve cuve) {
        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(cuve);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void delete(String id) {
        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Cuve cuve = em.find(Cuve.class, id);
            if (cuve != null) {
                em.remove(cuve);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}