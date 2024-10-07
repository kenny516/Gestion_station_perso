package com.mg.station.station_perso.entity;

import com.mg.station.station_perso.Database;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "COMPTEUR")
public class Compteur extends AbstractPrefixedIdEntity {

    @Id
    @Column(name = "ID")
    private String id;

    @ManyToOne
    @JoinColumn(name = "ID_POMPISTE")
    private Pompiste pompiste;

    @ManyToOne
    @JoinColumn(name = "ID_POMPE")
    private Pompe pompe;

    @Column(name = "VALEUR")
    private double valeur;

    @Column(name = "DATY")
    private LocalDateTime date;

    public String getId() {
        return id;
    }

    public Compteur setId(String id) {
        this.id = id;
        return this;
    }

    public Pompe getPompe() {
        return pompe;
    }

    public Compteur setPompe(Pompe pompe) {
        this.pompe = pompe;
        return this;
    }

    public Pompiste getPompiste() {
        return pompiste;
    }

    public Compteur setPompiste(Pompiste pompiste) {
        this.pompiste = pompiste;
        return this;
    }

    public double getValeur() {
        return valeur;
    }

    public Compteur setValeur(double valeur) {
        this.valeur = valeur;
        return this;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Compteur setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    @Override
    protected void beforePersist() {
        id = Database.generateId("COMPT", "ID_COMPTEUR_SEQ");
    }

    public boolean sortie(Pompiste pompiste) {
        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            List<Compteur> compteurs = em.createQuery("SELECT c FROM Compteur c WHERE c.pompiste = :pompiste", Compteur.class)
                    .setParameter("pompiste", pompiste)
                    .getResultList();

            int count = compteurs.size(); // Store the size in a variable
            // Check if count is even
            return count > 0 && count % 2 == 0; // Count must be greater than 0 to be even
        } finally {
            em.close(); // Ensure the EntityManager is closed
        }
    }


    public static double getFuelSale(Compteur compteurEntree, Compteur compteurSortie) {
        double qunatite = 0;
        if (compteurEntree.getValeur() > compteurSortie.getValeur()) {
            return (9999 + compteurSortie.getValeur() - compteurEntree.getValeur());
        } else {
            return (compteurSortie.getValeur() - compteurEntree.getValeur());
        }
    }

    public double getEncaissementNormal(Pompiste pompiste) {
        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            List<Compteur> compteurs = em.createQuery("SELECT c FROM Compteur c WHERE c.pompiste = :pompiste ORDER BY c.date DESC", Compteur.class)
                    .setParameter("pompiste", pompiste)
                    .setMaxResults(2)
                    .getResultList();

            if (compteurs.size() == 2) {
                return getFuelSale(compteurs.get(1), compteurs.get(0));
            }
            return 0;
        } finally {
            em.close(); // Ensure the EntityManager is closed
        }
    }

    public double getEncaissementNormalByDate(Pompiste pompiste, LocalDateTime date) {
        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            List<Compteur> compteurs = em.createQuery("SELECT c FROM Compteur c WHERE c.pompiste = :pompiste AND c.date < :date ORDER BY c.date DESC", Compteur.class)
                    .setParameter("pompiste", pompiste)
                    .setParameter("date", date)
                    .setMaxResults(2)
                    .getResultList();

            if (compteurs.size() == 2) {
                return getFuelSale(compteurs.get(1), compteurs.get(0)); // Calculate fuel sale difference
            }
            return 0;
        } finally {
            em.close();
        }
    }


    public Compteur[] getCompteurFramesDateByPompe(Pompe pompe, LocalDateTime date) {
        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            List<Compteur> compteurs = em.createQuery("SELECT c FROM Compteur c WHERE c.pompe = :pompe AND c.date = :date", Compteur.class)
                    .setParameter("pompe", pompe)
                    .setParameter("date", date)
                    .getResultList();
            return compteurs.toArray(new Compteur[0]);
        } finally {
            em.close(); // Ensure the EntityManager is closed
        }
    }

    // a utiliser pour chercher la quantite par le compteur
    public static double getFuelSaleByDateByPompe(Pompe pompe, LocalDateTime date) {
        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            String sql = "SELECT c FROM Compteur c " +
                    "WHERE c.pompe = :pompe AND EXTRACT(YEAR FROM c.date) = EXTRACT(YEAR FROM :date) " +
                    "AND EXTRACT(MONTH FROM c.date) = EXTRACT(MONTH FROM :date) " +
                    "AND EXTRACT(DAY FROM c.date) <= EXTRACT(DAY FROM :date) ORDER BY c.date DESC";
            List compteurs = em.createQuery(sql, Compteur.class)
                    .setParameter("pompe", pompe)
                    .setParameter("date", date)
                    .getResultList();

//            if (compteurs.size() == 2) {
//                return getFuelSale((Compteur) compteurs.get(1), (Compteur) compteurs.get(0));
//            }
            double vendue = 0;
            for (int i = 1; i < compteurs.size(); i++) {
                vendue += getFuelSale((Compteur) compteurs.get(i), (Compteur) compteurs.get(i - 1));
                ;
            }
            if (!(compteurs.size() % 2 == 0) && compteurs.size() > 1) {
                vendue += vendue - ((Compteur) compteurs.get(compteurs.size() - 1)).getValeur();
            }
            return vendue;
        } finally {
            em.close();
        }
    }

    public static double getFuelSaleByDateRangeByPompe(Pompe pompe, LocalDateTime startDate, LocalDateTime endDate) {
        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            // Requête JPQL pour récupérer les compteurs entre deux dates
            String sql = "SELECT c FROM Compteur c " +
                    "WHERE c.pompe = :pompe " +
                    "AND c.date BETWEEN :startDate AND :endDate " +
                    "ORDER BY c.date ASC";

            List<Compteur> compteurs = em.createQuery(sql, Compteur.class)
                    .setParameter("pompe", pompe)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getResultList();

            // Calcul de la quantité vendue
            double vendue = 0;
            for (int i = 1; i < compteurs.size(); i++) {
                vendue += getFuelSale(compteurs.get(i-1), compteurs.get(i));
            }
            return vendue;
        } finally {
            em.close();
        }
    }


}
