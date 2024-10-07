package com.mg.station.station_perso.controller;

import EJBPERSO.StationServiceEJB;
import com.mg.station.station_perso.Database;
import com.mg.station.station_perso.entity.*;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "test", value = "test")
public class Test extends HttpServlet {

    @EJB
    private StationServiceEJB stationService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

        // Conversion de la chaîne en LocalDateTime en utilisant le format
        LocalDateTime date = LocalDateTime.parse("2024-10-07 16:19:00.000000", formatter);

        Pompe p = em.find(Pompe.class, "PMP1");
        double cmp = Compteur.getFuelSaleByDateByPompe(p,date);

        PrelevementCuve[] prelevementCuve = PrelevementCuve.getTwoJaugesBeforeAndAfterDateByPompe(p, LocalDate.from(date));

        resp.getWriter().println("j0 "+ prelevementCuve[0].getHauteurJauge()+"\n");
        resp.getWriter().println("j1 "+ prelevementCuve[1].getHauteurJauge()+"\n");

        Cuve c = p.getCuve();


        GraduationCuve[] j1 = c.getCuveGraduationBetween(prelevementCuve[0].getHauteurJauge());
        GraduationCuve[] j2 = c.getCuveGraduationBetween(prelevementCuve[1].getHauteurJauge());

        resp.getWriter().println("j1 "+j1.length+"\n");
        resp.getWriter().println("j2 "+j2.length+"\n");

        double qtNormal1 = c.getVolumeByHauteur(j1, prelevementCuve[0].getHauteurJauge());
        double qtNormal2 = c.getVolumeByHauteur(j2, prelevementCuve[1].getHauteurJauge());



        resp.getWriter().println("qtNormal1 "+qtNormal1+"\n");
        resp.getWriter().println("qtNormal2 "+qtNormal2+"\n");
        resp.getWriter().println("cmp "+cmp+"\n");
    }
}
