package com.mg.station.station_perso.repository;

import com.mg.station.station_perso.model.Pompiste;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PompisteRepository extends JpaRepository<Pompiste, String> {
}