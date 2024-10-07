package com.mg.station.station_perso.entity;

import com.mg.station.station_perso.Database;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "REF_MAGASIN")
    private String ref_magasin;

    public Cuve() {
    }

    public Cuve(String id, double capacite, Pompe pompe, String ref_magasin) {
        this.id = id;
        this.capacite = capacite;
        this.pompe = pompe;
        this.ref_magasin = ref_magasin;
    }

    public String getRef_magasin() {
        return ref_magasin;
    }

    public void setRef_magasin(String ref_magasin) {
        this.ref_magasin = ref_magasin;
    }

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


    // function calculate the volume of fuel in cuve by Height of cuve
    public GraduationCuve[] getCuveGraduationBetween(double hauteur) {
        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        List<GraduationCuve> result = new ArrayList<>(); // Use generics for type safety

        try {

            // First Query: Get closest graduation below the specified hauteur
            String queryBefore = "SELECT * FROM (" +
                    "   SELECT c.* FROM CUVE_GRADUATION c " +
                    "   WHERE c.ID_CUVE = :cuveId AND c.hauteur < :hauteur " +
                    "   ORDER BY c.hauteur DESC " +
                    ") WHERE ROWNUM = 1";

            List beforeGraduations = em.createNativeQuery(queryBefore, GraduationCuve.class)
                    .setParameter("cuveId", this.getId())
                    .setParameter("hauteur", hauteur)
                    .getResultList();

            if (!beforeGraduations.isEmpty()) {
                result.add((GraduationCuve) beforeGraduations.get(0)); // Assign the found graduation below
            }

            // Second Query: Get closest graduation above the specified hauteur
            String queryAfter = "SELECT * FROM (" +
                    "   SELECT c.* FROM CUVE_GRADUATION c " +
                    "   WHERE c.ID_CUVE = :cuveId AND c.hauteur > :hauteur " +
                    "   ORDER BY c.hauteur ASC " +
                    ") WHERE ROWNUM = 1";

            List afterGraduations = em.createNativeQuery(queryAfter, GraduationCuve.class)
                    .setParameter("cuveId", this.getId())
                    .setParameter("hauteur", hauteur)
                    .getResultList();

            if (!afterGraduations.isEmpty()) {
                result.add((GraduationCuve) afterGraduations.get(0)); // Assign the found graduation above
            }

        } catch (Exception e) {
            // Log specific exceptions here
            // Example: Logger.log("Error retrieving cuve graduations: " + e.getMessage());
            throw new RuntimeException("Error retrieving cuve graduations: " + e.getMessage(), e);
        } finally {
            em.close(); // Ensure the EntityManager is closed
        }

        return result.toArray(new GraduationCuve[0]); // Return the array containing both graduations
    }


    public double getVolumeByHauteur(GraduationCuve[] graduationCuves, double hauteur) {
        double volume = 0;
        if (graduationCuves.length == 2) {
            double rg3one = graduationCuves[0].getVolume() * hauteur / graduationCuves[0].getHauteur();
            double rg3two = graduationCuves[1].getVolume() * hauteur / graduationCuves[1].getHauteur();
            volume = (rg3one + rg3two) / 2;
        } else if (graduationCuves.length == 1) {
            volume = graduationCuves[0].getVolume() * hauteur / graduationCuves[0].getHauteur();
        }
        return volume;
    }


    public static double interpolateVolume(double height, double[] heights, double[] volumes) {
        for (int i = 0; i < heights.length - 1; i++) {
            if (height >= heights[i] && height <= heights[i + 1]) {
                // Linear interpolation formula
                return volumes[i] + (height - heights[i]) / (heights[i + 1] - heights[i]) * (volumes[i + 1] - volumes[i]);
            }
        }
        return -1; // If height is outside known range
    }

}
