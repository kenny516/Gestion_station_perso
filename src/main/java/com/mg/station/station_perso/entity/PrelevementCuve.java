package com.mg.station.station_perso.entity;

import com.mg.station.station_perso.Database;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PRELEVEMENT_CUVE")
public class PrelevementCuve extends AbstractPrefixedIdEntity {

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

    public PrelevementCuve setId(String id) {
        this.id = id;
        return this;
    }

    public double getHauteurJauge() {
        return hauteurJauge;
    }

    public PrelevementCuve setHauteurJauge(double hauteurJauge) {
        this.hauteurJauge = hauteurJauge;
        return this;
    }

    public LocalDate getDaty() {
        return daty;
    }

    public PrelevementCuve setDaty(LocalDate daty) {
        this.daty = daty;
        return this;
    }

    public Pompe getPompe() {
        return pompe;
    }

    public PrelevementCuve setPompe(Pompe pompe) {
        this.pompe = pompe;
        return this;
    }

    @Override
    protected void beforePersist() {
        id = Database.generateId("PC", "ID_PC_SEQ");
    }

    // get two jauges before and after date by pompe
    public static PrelevementCuve[] getTwoJaugesBeforeAndAfterDateByPompe(Pompe pompe, LocalDate date) {
        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        List<PrelevementCuve> beforeList = em.createQuery("SELECT j FROM PrelevementCuve j WHERE j.pompe = :pompe AND j.daty < :date ORDER BY j.daty DESC", PrelevementCuve.class)
                .setParameter("pompe", pompe)
                .setParameter("date", date)
                .setMaxResults(2)
                .getResultList();
        List<PrelevementCuve> afterList = em.createQuery("SELECT j FROM PrelevementCuve j WHERE j.pompe = :pompe AND j.daty >= :date ORDER BY j.daty ASC", PrelevementCuve.class)
                .setParameter("pompe", pompe)
                .setParameter("date", date)
                .setMaxResults(2)
                .getResultList();
        em.close();
        List<PrelevementCuve> resultList = new ArrayList<>();
        resultList.addAll(beforeList);
        resultList.addAll(afterList);
        return resultList.toArray(new PrelevementCuve[0]);
    }
}