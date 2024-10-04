package com.mg.station.station_perso.DAO;

import com.mg.station.station_perso.Database;
import com.mg.station.station_perso.entity.Pompiste;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class PompisteDAO {

    public void create(Pompiste pompiste) {
        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(pompiste);
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

    public Pompiste read(String id) {
        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.find(Pompiste.class, id);
        } finally {
            em.close();
        }
    }

    public List<Pompiste> readAll() {
        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Pompiste p", Pompiste.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(Pompiste pompiste) {
        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(pompiste);
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
            Pompiste pompiste = em.find(Pompiste.class, id);
            if (pompiste != null) {
                em.remove(pompiste);
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