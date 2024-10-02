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
    private Pompiste pompiste;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDPOMPE")
    private Pompe pompe;

    @Column(name = "COMPTEURNUM")
    private Double compteurNum;

    @Column(name = "DATY")
    private LocalDate daty;
}