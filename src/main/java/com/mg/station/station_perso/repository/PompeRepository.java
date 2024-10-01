package com.mg.station.station_perso.repository;

import com.mg.station.station_perso.model.Pompe;

import java.util.List;

public interface PompeRepository {
    Pompe create(Pompe pompe);

    Pompe read(String id);

    Pompe update(Pompe pompe);

    void delete(String id);

    List<Pompe> findAll();
}
