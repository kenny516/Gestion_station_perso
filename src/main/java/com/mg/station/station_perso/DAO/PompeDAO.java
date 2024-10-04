package com.mg.station.station_perso.DAO;

import com.mg.station.station_perso.Database;
import com.mg.station.station_perso.entity.Pompe;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class PompeDAO {

    public void create(Pompe pompe) {
        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(pompe);
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

    public Pompe read(String id) {
        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.find(Pompe.class, id);
        } finally {
            em.close();
        }
    }

    public List<Pompe> readAll() {
        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Pompe p", Pompe.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(Pompe pompe) {
        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(pompe);
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
            Pompe pompe = em.find(Pompe.class, id);
            if (pompe != null) {
                em.remove(pompe);
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