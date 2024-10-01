package com.mg.station.station_perso.repository.implement;

import com.mg.station.station_perso.model.Produit;
import com.mg.station.station_perso.repository.ProduitRepository;
import jakarta.transaction.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ProduitRepositoryImpl implements ProduitRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Produit create(Produit produit) {
        entityManager.persist(produit);
        return produit;
    }

    @Override
    public Produit read(String id) {
        return entityManager.find(Produit.class, id);
    }

    @Override
    @Transactional
    public Produit update(Produit produit) {
        return entityManager.merge(produit);
    }

    @Override
    @Transactional
    public void delete(String id) {
        Produit produit = read(id);
        if (produit != null) {
            entityManager.remove(produit);
        }
    }

    @Override
    public List<Produit> findAll() {
        return entityManager.createQuery("SELECT p FROM Produit p", Produit.class).getResultList();
    }
}