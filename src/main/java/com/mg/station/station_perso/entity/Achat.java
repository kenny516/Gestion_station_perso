package com.mg.station.station_perso.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ACHAT")
public class Achat {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "REF_FOURNISSEUR")
    private String refFournisseur;

    @ManyToOne
    @JoinColumn(name = "ID_CUVE")
    private Cuve cuve;

    @Column(name = "QUANTITE")
    private double quantite;

    @Column(name = "MONTANT")
    private double montant;

    @Column(name = "DATY")
    private LocalDate daty;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public Achat setId(String id) {
        this.id = id;
        return this;
    }

    public String getRefFournisseur() {
        return refFournisseur;
    }

    public Achat setRefFournisseur(String refFournisseur) {
        this.refFournisseur = refFournisseur;
        return this;
    }

    public Cuve getCuve() {
        return cuve;
    }

    public Achat setCuve(Cuve cuve) {
        this.cuve = cuve;
        return this;
    }

    public double getQuantite() {
        return quantite;
    }

    public Achat setQuantite(double quantite) {
        this.quantite = quantite;
        return this;
    }

    public double getMontant() {
        return montant;
    }

    public Achat setMontant(double montant) {
        this.montant = montant;
        return this;
    }

    public LocalDate getDaty() {
        return daty;
    }

    public Achat setDaty(LocalDate daty) {
        this.daty = daty;
        return this;
    }
}