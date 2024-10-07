package com.mg.station.station_perso.entity;

import com.mg.station.station_perso.Database;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "JAUGE")
public class Jauge extends AbstractPrefixedIdEntity {

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

    @Transient
    private double volume;

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

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

    // get two jauges before and after date by pompe
    public static Jauge[] getTwoJaugesBeforeAndAfterDateByPompe(Pompe pompe, LocalDate date) {
        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        List<Jauge> beforeList = em.createQuery("SELECT j FROM Jauge j WHERE j.pompe = :pompe AND j.daty < :date ORDER BY j.daty DESC", Jauge.class)
                .setParameter("pompe", pompe)
                .setParameter("date", date)
                .setMaxResults(2)
                .getResultList();
        List<Jauge> afterList = em.createQuery("SELECT j FROM Jauge j WHERE j.pompe = :pompe AND j.daty >= :date ORDER BY j.daty ASC", Jauge.class)
                .setParameter("pompe", pompe)
                .setParameter("date", date)
                .setMaxResults(2)
                .getResultList();
        em.close();
        List<Jauge> resultList = new ArrayList<>();
        resultList.addAll(beforeList);
        resultList.addAll(afterList);
        return resultList.toArray(new Jauge[0]);
    }

    //function calculate the volume of fuel in cuve by Height of cuve
    public  CuveGraduation[] getCuveGraduationBetween(String idCuve) {
        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        List<CuveGraduation> result = new ArrayList<>(); // Use generics for type safety

        String query = "SELECT * FROM CUVE_GRADUATION WHERE ID_CUVE = :cuveId AND hauteur = :hauteur";
        List cuveGraduations = em.createNativeQuery(query, CuveGraduation.class)
                .setParameter("cuveId", idCuve)
                .setParameter("hauteur", this.getHauteurJauge())
                .getResultList();
        if (!cuveGraduations.isEmpty()) {
            return (CuveGraduation[]) cuveGraduations.toArray(new CuveGraduation[0]);
        }

        try {
            // First Query: Get closest graduation below the specified hauteur
            String queryBefore = "SELECT * FROM (" +
                    "   SELECT c.* FROM CUVE_GRADUATION c " +
                    "   WHERE c.ID_CUVE = :cuveId AND c.hauteur < :hauteur " +
                    "   ORDER BY c.hauteur DESC " +
                    ") WHERE ROWNUM = 1";

            List beforeGraduations = em.createNativeQuery(queryBefore, CuveGraduation.class)
                    .setParameter("cuveId", idCuve)
                    .setParameter("hauteur", this.getHauteurJauge())
                    .getResultList();

            if (!beforeGraduations.isEmpty()) {
                result.add((CuveGraduation) beforeGraduations.get(0)); // Assign the found graduation below
            }

            // Second Query: Get closest graduation above the specified hauteur
            String queryAfter = "SELECT * FROM (" +
                    "   SELECT c.* FROM CUVE_GRADUATION c " +
                    "   WHERE c.ID_CUVE = :cuveId AND c.hauteur > :hauteur " +
                    "   ORDER BY c.hauteur ASC " +

                    ") WHERE ROWNUM = 1";

            List afterGraduations = em.createNativeQuery(queryAfter, CuveGraduation.class)
                    .setParameter("cuveId", idCuve)
                    .setParameter("hauteur", this.getHauteurJauge())
                    .getResultList();

            if (!afterGraduations.isEmpty()) {
                result.add((CuveGraduation) afterGraduations.get(0)); // Assign the found graduation above
            }

        } catch (Exception e) {
            // Log specific exceptions here
            // Example: Logger.log("Error retrieving cuve graduations: " + e.getMessage());
            throw new RuntimeException("Error retrieving cuve graduations: " + e.getMessage(), e);
        } finally {
            em.close(); // Ensure the EntityManager is closed
        }

        return result.toArray(new CuveGraduation[0]); // Return the array containing both graduations
    }


    public double getVolumeByHauteur(CuveGraduation[] cuveGraduations) {
        double volume = 0;
        if (cuveGraduations.length == 2) {
            double hauteurTemp = cuveGraduations[1].getHauteur() - cuveGraduations[0].getHauteur();
            double capacite = cuveGraduations[1].getCapacite() - cuveGraduations[0].getCapacite();

            double hauteurNewRP = this.getHauteurJauge() - cuveGraduations[0].getHauteur();

            volume = cuveGraduations[0].getCapacite() + (hauteurNewRP * capacite / hauteurTemp);
        } else if (cuveGraduations.length == 1) {
            volume = cuveGraduations[0].getCapacite() * this.getHauteurJauge() / cuveGraduations[0].getHauteur();
        }
        if (this.getVolume() == 0 && this.getHauteurJauge() != 0 ){
            this.setVolume(volume);
        }
        return volume;
    }


}