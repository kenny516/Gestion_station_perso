package com.mg.station.station_perso.entity;

import com.mg.station.station_perso.Database;

import javax.persistence.*;

@Entity
@Table(name = "CUVE")
public class Cuve extends AbstractPrefixedIdEntity {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "CAPACITE")
    private double capacite;

    @OneToOne(mappedBy = "cuve")
    private Pompe pompe;

    public String getId() {
        return id;
    }

    public Cuve setId(String id) {
        this.id = id;
        return this;
    }

    public double getCapacite() {
        return capacite;
    }

    public Cuve setCapacite(double capacite) {
        this.capacite = capacite;
        return this;
    }

    public Pompe getPompe() {
        return pompe;
    }

    public Cuve setPompe(Pompe pompe) {
        this.pompe = pompe;
        return this;
    }

    @Override
    protected void beforePersist() {
        id = Database.generateId("CUV", "ID_CUVE_SEQ");
    }
}
