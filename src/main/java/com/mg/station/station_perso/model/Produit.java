package com.mg.station.station_perso.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PRODUIT", schema = "KENNY")
public class Produit {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "NOM")
    private String nom;

    @Column(name = "PRIX_ACHAT")
    private Double prixAchat;

    @Column(name = "PRIX_VENTE")
    private Double prixVente;

}