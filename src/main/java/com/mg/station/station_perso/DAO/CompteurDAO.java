package com.mg.station.station_perso.DAO;

import com.mg.station.station_perso.Database;
import com.mg.station.station_perso.entity.Compteur;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class CompteurDAO {

    public void create(Compteur compteur) {
        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(compteur);
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

    public Compteur read(String id) {
        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.find(Compteur.class, id);
        } finally {
            em.close();
        }
    }

    public List<Compteur> readAll() {
        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Compteur c", Compteur.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(Compteur compteur) {
        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(compteur);
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
            Compteur compteur = em.find(Compteur.class, id);
            if (compteur != null) {
                em.remove(compteur);
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
