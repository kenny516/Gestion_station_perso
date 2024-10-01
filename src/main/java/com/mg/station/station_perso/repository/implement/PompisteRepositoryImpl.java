package com.mg.station.station_perso.repository.implement;

import com.mg.station.station_perso.model.Pompiste;
import com.mg.station.station_perso.repository.PompisteRepository;
import jakarta.transaction.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class PompisteRepositoryImpl implements PompisteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Pompiste create(Pompiste pompiste) {
        entityManager.persist(pompiste);
        return pompiste;
    }

    @Override
    public Pompiste read(String id) {
        return entityManager.find(Pompiste.class, id);
    }

    @Override
    @Transactional
    public Pompiste update(Pompiste pompiste) {
        return entityManager.merge(pompiste);
    }

    @Override
    @Transactional
    public void delete(String id) {
        Pompiste pompiste = read(id);
        if (pompiste != null) {
            entityManager.remove(pompiste);
        }
    }

    @Override
    public List<Pompiste> findAll() {
        return entityManager.createQuery("SELECT p FROM Pompiste p", Pompiste.class).getResultList();
    }
}