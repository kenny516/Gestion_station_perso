package com.mg.station.station_perso.repository;

import com.mg.station.station_perso.model.Produit;

import java.util.List;

public interface ProduitRepository {
    Produit create(Produit produit);
    Produit read(String id);
    Produit update(Produit produit);
    void delete(String id);
    List<Produit> findAll();
}