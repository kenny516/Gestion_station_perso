package com.mg.station.station_perso.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "POMPISTE", schema = "KENNY")
public class Pompiste {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "NOM")
    private String nom;

    @Column(name = "PRENOM")
    private String prenom;

    @Column(name = "SEXE", length = 1)
    private String sexe;

    @Column(name = "DATY")
    private LocalDate daty;

    @Column(name = "IDUSER")
    private String iduser;

    @OneToMany(mappedBy = "pompiste")
    private Set<CompteurPerso> compteurPersos = new LinkedHashSet<>();
}