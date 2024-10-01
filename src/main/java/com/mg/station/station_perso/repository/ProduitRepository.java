package com.mg.station.station_perso.repository;

import com.mg.station.station_perso.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit, String> {
}