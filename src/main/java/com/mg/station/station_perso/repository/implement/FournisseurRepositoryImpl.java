package com.mg.station.station_perso.repository.implement;

import com.mg.station.station_perso.model.Fournisseur;
import com.mg.station.station_perso.repository.FournisseurRepository;
import jakarta.transaction.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class FournisseurRepositoryImpl implements FournisseurRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Fournisseur create(Fournisseur fournisseur) {
        entityManager.persist(fournisseur);
        return fournisseur;
    }

    @Override
    public Fournisseur read(String id) {
        return entityManager.find(Fournisseur.class, id);
    }

    @Override
    @Transactional
    public Fournisseur update(Fournisseur fournisseur) {
        return entityManager.merge(fournisseur);
    }

    @Override
    @Transactional
    public void delete(String id) {
        Fournisseur fournisseur = read(id);
        if (fournisseur != null) {
            entityManager.remove(fournisseur);
        }
    }

    @Override
    public List<Fournisseur> findAll() {
        return entityManager.createQuery("SELECT f FROM Fournisseur f", Fournisseur.class).getResultList();
    }
}