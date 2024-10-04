package com.mg.station.station_perso.controller;

import EJBPERSO.StationServiceEJB;
import com.mg.station.station_perso.Database;
import com.mg.station.station_perso.entity.Compteur;
import com.mg.station.station_perso.entity.Pompiste;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "test", value = "test")
public class Test extends HttpServlet {

    @EJB
    private StationServiceEJB stationService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String[] mg = stationService.getMagasinRef();
        for (String s : mg) {
            resp.getWriter().println(s);
        }

        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        Compteur compteur = em.find(Compteur.class, "COMPT1");
        Pompiste pompiste = em.find(Pompiste.class, "PST1");
        resp.getWriter().println(compteur.sortie(pompiste));
        resp.getWriter().println(compteur.getEncaissementNormal(pompiste));
    }
}
