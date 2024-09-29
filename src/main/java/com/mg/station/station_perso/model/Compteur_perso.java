package com.mg.station.station_perso.model;

import bean.CGenUtil;
import bean.ClassEtat;

import java.sql.Connection;
import java.sql.Date;

public class Compteur_perso extends ClassEtat {

    String id;
    Date daty;
    String heure;
    double compteur;
    String idPompiste;
    String idPompe;

    public Compteur_perso(String id, Date daty, String heure, double compteur, String idPompiste, String idPompe) {
        this.setNomTable("COMPTEUR_PERSO");
        this.setId(id);
        this.setDaty(daty);
        this.setHeure(heure);
        this.setCompteur(compteur);
        this.setIdPompiste(idPompiste);
        this.setIdPompe(idPompe);
    }

    public Compteur_perso() {
        this.setNomTable("COMPTEUR_PERSO");
    }
    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public double getCompteur() {
        return compteur;
    }

    public void setCompteur(double compteur) {
        this.compteur = compteur;
    }

    public String getIdPompiste() {
        return idPompiste;
    }

    public void setIdPompiste(String idPompiste) {
        this.idPompiste = idPompiste;
    }

    public String getIdPompe() {
        return idPompe;
    }

    public void setIdPompe(String idPompe) {
        this.idPompe = idPompe;
    }

    @Override
    public String getTuppleID() {
        return id;
    }

    @Override
    public String getAttributIDName() {
        return "id";
    }
    @Override
    public void construirePK(Connection c) throws Exception {
        this.preparePk("CMPP", "compteur_perso_seq");
        this.setId(makePK(c));
    }

    public Compteur_perso[] getAll(Connection c)throws Exception{
        Compteur_perso cp = new Compteur_perso();
        Compteur_perso[] cps = (Compteur_perso[]) CGenUtil.rechercher(cp,null,null,c,"");
        return  cps;
    }


    public


}
