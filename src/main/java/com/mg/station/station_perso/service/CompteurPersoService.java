package com.mg.station.station_perso.service;

import com.mg.station.station_perso.Hb.HibernateUtil;
import com.mg.station.station_perso.model.CompteurPerso;
import com.mg.station.station_perso.model.Pompiste;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class CompteurPersoService {

    public CompteurPerso createCompteurPerso(CompteurPerso compteurPerso) {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(compteurPerso);
            transaction.commit();
            return compteurPerso;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;
    }

    public CompteurPerso getCompteurPersoById(String id) {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        try {
            return entityManager.find(CompteurPerso.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;
    }

    public List<CompteurPerso> getAllCompteursPerso() {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        try {
            return entityManager.createQuery("FROM CompteurPerso", CompteurPerso.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;
    }

    public CompteurPerso updateCompteurPerso(CompteurPerso compteurPerso) {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            CompteurPerso updatedCompteurPerso = entityManager.merge(compteurPerso);
            transaction.commit();
            return updatedCompteurPerso;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;
    }

    public void deleteCompteurPerso(String id) {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            CompteurPerso compteurPerso = entityManager.find(CompteurPerso.class, id);
            if (compteurPerso != null) {
                entityManager.remove(compteurPerso);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public boolean compteurPersoExists(String id) {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        try {
            CompteurPerso compteurPerso = entityManager.find(CompteurPerso.class, id);
            return compteurPerso != null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return false;
    }

    public long countCompteursPerso() {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        try {
            return entityManager.createQuery("SELECT COUNT(c) FROM CompteurPerso c", Long.class).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return 0;
    }

    public boolean sortie(Pompiste pompiste) {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<CompteurPerso> query = entityManager.createQuery(
                    "FROM CompteurPerso WHERE pompiste = :pompiste", CompteurPerso.class);
            query.setParameter("pompiste", pompiste);
            List<CompteurPerso> jeanPompisteHistori = query.getResultList();
            return jeanPompisteHistori.size() % 2 == 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return false;
    }

    public double getFuelSale(CompteurPerso compteurPersoEntre, CompteurPerso compteurPersoSortie) {
        return ((9999) + compteurPersoSortie.getCompteurNum()) - compteurPersoEntre.getCompteurNum();
    }

    public double getEncaissementPompisteNormal(Pompiste pompiste) {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<CompteurPerso> query = entityManager.createQuery(
                    "FROM CompteurPerso WHERE pompiste = :pompiste ORDER BY id DESC", CompteurPerso.class);
            query.setParameter("pompiste", pompiste);
            query.setMaxResults(2);
            List<CompteurPerso> compteurPersoList = query.getResultList();
            if (compteurPersoList.size() == 2) {
                return getFuelSale(compteurPersoList.get(1), compteurPersoList.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return 0;
    }
}