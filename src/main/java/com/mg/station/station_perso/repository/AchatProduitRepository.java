package com.mg.station.station_perso.repository;

import com.mg.station.station_perso.model.AchatProduit;

import java.util.List;

public interface AchatProduitRepository {
    AchatProduit create(AchatProduit achatProduit);

    AchatProduit read(String id);

    AchatProduit update(AchatProduit achatProduit);

    void delete(String id);

    List<AchatProduit> findAll();
}