package com.mg.station.station_perso.repository.implement;

import com.mg.station.station_perso.model.Pompe;
import com.mg.station.station_perso.repository.PompeRepository;
import jakarta.transaction.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class PompeRepositoryImpl implements PompeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Pompe create(Pompe pompe) {
        entityManager.persist(pompe);
        return pompe;
    }

    @Override
    public Pompe read(String id) {
        return entityManager.find(Pompe.class, id);
    }

    @Override
    @Transactional
    public Pompe update(Pompe pompe) {
        return entityManager.merge(pompe);
    }

    @Override
    @Transactional
    public void delete(String id) {
        Pompe pompe = read(id);
        if (pompe != null) {
            entityManager.remove(pompe);
        }
    }

    @Override
    public List<Pompe> findAll() {
        return entityManager.createQuery("SELECT p FROM Pompe p", Pompe.class).getResultList();
    }
}