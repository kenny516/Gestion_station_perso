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
@Table(name = "ACHAT_PRODUIT", schema = "KENNY")
public class AchatProduit {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDPRODUIT")
    private Produit produit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDFOURNISSEUR")
    private Fournisseur fournisseur;

    @Column(name = "QUANTITE")
    private Long quantite;

    @Column(name = "DATY")
    private LocalDate daty;

    @Column(name = "IDUSER")
    private String iduser;

}