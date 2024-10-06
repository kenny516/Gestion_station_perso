package com.mg.station.station_perso.entity;

import com.mg.station.station_perso.Database;

import javax.persistence.*;
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


    //function calculate the volume of fuel in cuve by Height of cuve
    public CuveGraduation[] getCuveGraduationBetween(double hauteur) {
        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT * FROM (" +
                "   SELECT * FROM (" +
                "       SELECT c.* FROM CUVE_GRADUATION c " +
                "       WHERE c.ID_CUVE = :cuveId AND c.hauteur < :hauteur " +
                "       ORDER BY c.hauteur DESC " +
                "   ) WHERE ROWNUM = 1 " +
                "   UNION ALL " +
                "   SELECT * FROM (" +
                "       SELECT c.* FROM CUVE_GRADUATION c " +
                "       WHERE c.ID_CUVE = :cuveId AND c.hauteur > :hauteur " +
                "       ORDER BY c.hauteur ASC " +
                "   ) WHERE ROWNUM = 1 " +
                ") cuveGraduations";

        List graduations = em.createNativeQuery(query, CuveGraduation.class)
                .setParameter("cuveId", this.getId())  // Assuming your cuve has an ID field
                .setParameter("hauteur", hauteur)
                .getResultList();

        em.close(); // Close the EntityManager to avoid resource leaks

        // Check if there are enough results before accessing the list
        if (graduations.isEmpty()) {
            throw new IllegalArgumentException("No graduation found for the given hauteur.");
        }

        // Interpolate volume or any additional logic you need
        return (CuveGraduation[]) graduations.toArray(new CuveGraduation[0]);
    }


    public double getVolumeByHauteur(CuveGraduation[] cuveGraduations, double hauteur) {
        double volume = 0;
        if (cuveGraduations.length == 2) {
            double rg3one = cuveGraduations[0].getCapacite() * hauteur / cuveGraduations[0].getHauteur();
            double rg3two = cuveGraduations[1].getCapacite() * hauteur / cuveGraduations[1].getHauteur();
            volume = (rg3one + rg3two) / 2;
        } else if (cuveGraduations.length == 1) {
            volume = cuveGraduations[0].getCapacite() * hauteur / cuveGraduations[0].getHauteur();
        }
        return volume;
    }

    public 

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
