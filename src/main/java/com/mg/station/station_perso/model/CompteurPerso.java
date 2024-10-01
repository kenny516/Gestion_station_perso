package com.mg.station.station_perso.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "COMPTEUR_PERSO", schema = "KENNY")
public class CompteurPerso {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDPOMPISTE")
    private com.mg.station.station_perso.model.Pompiste idpompiste;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDPOMPE")
    private com.mg.station.station_perso.model.Pompe idpompe;

    @Column(name = "IDCOMPTEUR")
    private String idcompteur;

    @Column(name = "DATY")
    private LocalDate daty;

    @Column(name = "IDUSER")
    private String iduser;

}