package com.mg.station.station_perso.repository.implement;

import com.mg.station.station_perso.model.AchatProduit;
import com.mg.station.station_perso.repository.AchatProduitRepository;
import jakarta.transaction.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class AchatProduitRepositoryImpl implements AchatProduitRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public AchatProduit create(AchatProduit achatProduit) {
        entityManager.persist(achatProduit);
        return achatProduit;
    }

    @Override
    public AchatProduit read(String id) {
        return entityManager.find(AchatProduit.class, id);
    }

    @Override
    @Transactional
    public AchatProduit update(AchatProduit achatProduit) {
        return entityManager.merge(achatProduit);
    }

    @Override
    @Transactional
    public void delete(String id) {
        AchatProduit achatProduit = read(id);
        if (achatProduit != null) {
            entityManager.remove(achatProduit);
        }
    }

    @Override
    public List<AchatProduit> findAll() {
        return entityManager.createQuery("SELECT a FROM AchatProduit a", AchatProduit.class).getResultList();
    }
}