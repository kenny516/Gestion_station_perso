package com.mg.station.station_perso.repository;

import com.mg.station.station_perso.model.Fournisseur;

import java.util.List;

public interface FournisseurRepository {
    Fournisseur create(Fournisseur fournisseur);

    Fournisseur read(String id);

    Fournisseur update(Fournisseur fournisseur);

    void delete(String id);

    List<Fournisseur> findAll();
}