package com.mg.station.station_perso.entity;

import com.mg.station.station_perso.Database;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "JAUGE")
public class Jauge  extends AbstractPrefixedIdEntity{

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "HAUTEUR_JAUGE")
    private double hauteurJauge;

    @Column(name = "DATY")
    private LocalDate daty;

    @ManyToOne
    @JoinColumn(name = "ID_POMPE")
    private Pompe pompe;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public Jauge setId(String id) {
        this.id = id;
        return this;
    }

    public double getHauteurJauge() {
        return hauteurJauge;
    }

    public Jauge setHauteurJauge(double hauteurJauge) {
        this.hauteurJauge = hauteurJauge;
        return this;
    }

    public LocalDate getDaty() {
        return daty;
    }

    public Jauge setDaty(LocalDate daty) {
        this.daty = daty;
        return this;
    }

    public Pompe getPompe() {
        return pompe;
    }

    public Jauge setPompe(Pompe pompe) {
        this.pompe = pompe;
        return this;
    }

    @Override
    protected void beforePersist() {
        id = Database.generateId("JG", "ID_JAUGE_SEQ");
    }
}