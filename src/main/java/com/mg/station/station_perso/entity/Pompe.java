package com.mg.station.station_perso.entity;


import com.mg.station.station_perso.Database;

import javax.persistence.*;

@Entity
@Table(name = "POMPE")
public class Pompe extends AbstractPrefixedIdEntity {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "LIBELLE", nullable = false)
    private String libelle;

    @OneToOne
    @JoinColumn(name = "ID_CUVE", nullable = false)
    private Cuve cuve;

    public String getId() {
        return id;
    }

    public Pompe setId(String id) {
        this.id = id;
        return this;
    }

    public String getLibelle() {
        return libelle;
    }

    public Pompe setLibelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public Cuve getCuve() {
        return cuve;
    }

    public Pompe setCuve(Cuve cuve) {
        this.cuve = cuve;
        return this;
    }

    @Override
    protected void beforePersist() {
        id = Database.generateId("PMP", "ID_POMPE_SEQ");
    }
}
