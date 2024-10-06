package com.mg.station.station_perso.entity;

import com.mg.station.station_perso.Database;

import javax.persistence.*;

@Entity
@Table(name = "CUVE_GRADUATION")
public class CuveGraduation extends AbstractPrefixedIdEntity {

    @Id
    @Column(name = "ID")
    private String id;

    @ManyToOne
    @JoinColumn(name = "ID_CUVE")
    private Cuve cuve;

    @Column(name = "CAPACITE")
    private double capacite;

    @Column(name = "HAUTEUR")
    private double hauteur;

    public String getId() {
        return id;
    }

    public CuveGraduation setId(String id) {
        this.id = id;
        return this;
    }

    public Cuve getCuve() {
        return cuve;
    }

    public CuveGraduation setCuve(Cuve cuve) {
        this.cuve = cuve;
        return this;
    }

    public double getCapacite() {
        return capacite;
    }

    public CuveGraduation setCapacite(double capacite) {
        this.capacite = capacite;
        return this;
    }

    public double getHauteur() {
        return hauteur;
    }

    public CuveGraduation setHauteur(double hauteur) {
        this.hauteur = hauteur;
        return this;
    }
    @Override
    protected void beforePersist() {
        id = Database.generateId("CVG", "ID_CUVE_GRADUATION_SEQ");
    }

}