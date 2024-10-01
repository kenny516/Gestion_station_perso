package com.mg.station.station_perso.repository.implement;

import com.mg.station.station_perso.model.CompteurPerso;
import com.mg.station.station_perso.repository.CompteurPersoRepository;
import jakarta.transaction.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CompteurPersoRepositoryImpl implements CompteurPersoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public CompteurPerso create(CompteurPerso compteurPerso) {
        entityManager.persist(compteurPerso);
        return compteurPerso;
    }

    @Override
    public CompteurPerso read(String id) {
        return entityManager.find(CompteurPerso.class, id);
    }

    @Override
    @Transactional
    public CompteurPerso update(CompteurPerso compteurPerso) {
        return entityManager.merge(compteurPerso);
    }

    @Override
    @Transactional
    public void delete(String id) {
        CompteurPerso compteurPerso = read(id);
        if (compteurPerso != null) {
            entityManager.remove(compteurPerso);
        }
    }

    @Override
    public List<CompteurPerso> findAll() {
        return entityManager.createQuery("SELECT c FROM CompteurPerso c", CompteurPerso.class).getResultList();
    }
}