package com.mg.station.station_perso.entity;


import com.mg.station.station_perso.Database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "POMPISTE")
public class Pompiste extends AbstractPrefixedIdEntity {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NOM")
    private String nom;

    @Column(name = "PRENOMS")
    private String prenoms;

    @Column(name = "SEXE")
    private short sexe;

    public String getId() {
        return id;
    }

    public Pompiste setId(String id) {
        this.id = id;
        return this;
    }

    public String getNom() {
        return nom;
    }

    public Pompiste setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public String getPrenoms() {
        return prenoms;
    }

    public Pompiste setPrenoms(String prenoms) {
        this.prenoms = prenoms;
        return this;
    }

    public short getSexe() {
        return sexe;
    }

    public Pompiste setSexe(short sexe) {
        this.sexe = sexe;
        return this;
    }

    @Override
    protected void beforePersist() {
        id = Database.generateId("PMPISTE", "ID_POMPISTE_SEQ");
    }

    public String getNomComplet() {
        return nom + " " + prenoms;
    }
}
