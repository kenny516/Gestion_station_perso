package com.mg.station.station_perso.repository;

import com.mg.station.station_perso.model.Pompiste;

import java.util.List;

public interface PompisteRepository {
    Pompiste create(Pompiste pompiste);

    Pompiste read(String id);

    Pompiste update(Pompiste pompiste);

    void delete(String id);

    List<Pompiste> findAll();
}