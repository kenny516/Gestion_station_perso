package com.mg.station.station_perso.entity;

import com.mg.station.station_perso.Database;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ACHAT")
public class Achat extends AbstractPrefixedIdEntity {

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

    public void setId(String id) {
        this.id = id;
    }

    public String getRefFournisseur() {
        return refFournisseur;
    }

    public void setRefFournisseur(String refFournisseur) {
        this.refFournisseur = refFournisseur;
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

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public LocalDate getDaty() {
        return daty;
    }

    public void setDaty(LocalDate daty) {
        this.daty = daty;
    }

    @Override
    protected void beforePersist() {
        id = Database.generateId("ACH", "ID_ACHAT_SEQ");
    }
}