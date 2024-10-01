package com.mg.station.station_perso.repository;

import com.mg.station.station_perso.model.CompteurPerso;

import java.util.List;

public interface CompteurPersoRepository {
    CompteurPerso create(CompteurPerso compteurPerso);

    CompteurPerso read(String id);

    CompteurPerso update(CompteurPerso compteurPerso);

    void delete(String id);

    List<CompteurPerso> findAll();
}