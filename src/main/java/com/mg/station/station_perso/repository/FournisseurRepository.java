package com.mg.station.station_perso.repository;

import com.mg.station.station_perso.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FournisseurRepository extends JpaRepository<Fournisseur, String> {
}