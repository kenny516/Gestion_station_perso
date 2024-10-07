package com.mg.station.station_perso.entity;

import com.mg.station.station_perso.Database;

import javax.persistence.*;

@Entity
@Table(name = "GRADUATION_CUVE")
public class GraduationCuve extends AbstractPrefixedIdEntity {
    @Id
    @Column(name = "ID")
    private String id;

    @ManyToOne
    @JoinColumn(name = "ID_CUVE")
    private Cuve cuve;

    @Column(name = "HAUTEUR")
    private double hauteur;

    @Column(name = "VOLUME")
    private double volume;

    public String getId() {
        return id;
    }

    public GraduationCuve setId(String id) {
        this.id = id;
        return this;
    }

    public Cuve getCuve() {
        return cuve;
    }

    public GraduationCuve setCuve(Cuve cuve) {
        this.cuve = cuve;
        return this;
    }

    public double getHauteur() {
        return hauteur;
    }

    public GraduationCuve setHauteur(double hauteur) {
        this.hauteur = hauteur;
        return this;
    }

    public double getVolume() {
        return volume;
    }

    public GraduationCuve setVolume(double volume) {
        this.volume = volume;
        return this;
    }

    @Override
    protected void beforePersist() {
        id = Database.generateId("GCUV", "ID_CUVE_GRADUATION_SEQ");
    }
}