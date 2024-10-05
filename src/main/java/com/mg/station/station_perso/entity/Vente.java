package com.mg.station.station_perso.entity;

import com.mg.station.station_perso.Database;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "VENTE")
public class Vente extends AbstractPrefixedIdEntity {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "REF_CLIENT")
    private String refClient;

    @ManyToOne
    @JoinColumn(name = "ID_POMPE")
    private Pompe pompe;

    @Column(name = "QUANTITE")
    private double quantite;

    @Column(name = "MONTANT")
    private double montant;

    @Column(name = "DATY")
    private LocalDate date;

    public Vente() {
    }

    public Vente(String id, String refClient, Pompe pompe, double quantite, double montant, LocalDate date) {
        this.id = id;
        this.refClient = refClient;
        this.pompe = pompe;
        this.quantite = quantite;
        this.montant = montant;
        this.date = date;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRefClient() {
        return refClient;
    }

    public void setRefClient(String refClient) {
        this.refClient = refClient;
    }

    public Pompe getPompe() {
        return pompe;
    }

    public void setPompe(Pompe pompe) {
        this.pompe = pompe;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    protected void beforePersist() {
        id = Database.generateId("VENT", "ID_VENTE_SEQ");

    }
}
