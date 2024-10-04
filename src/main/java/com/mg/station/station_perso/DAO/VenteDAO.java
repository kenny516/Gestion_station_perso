package com.mg.station.station_perso.DAO;

import com.mg.station.station_perso.Database;
import com.mg.station.station_perso.entity.Vente;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class VenteDAO {

    public void create(Vente vente) {
        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(vente);
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

    public Vente read(String id) {
        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.find(Vente.class, id);
        } finally {
            em.close();
        }
    }

    public List<Vente> readAll() {
        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.createQuery("SELECT v FROM Vente v", Vente.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(Vente vente) {
        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(vente);
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
            Vente vente = em.find(Vente.class, id);
            if (vente != null) {
                em.remove(vente);
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