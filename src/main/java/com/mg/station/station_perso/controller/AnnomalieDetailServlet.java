package com.mg.station.station_perso.controller;

import com.mg.station.station_perso.Database;
import com.mg.station.station_perso.entity.*;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@WebServlet(name = "AnnomalieDetailServlet", urlPatterns = "/annomalieDetail")
public class AnnomalieDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        Pompe[] pompes = em.createQuery("FROM Pompe p", Pompe.class).getResultList().toArray(new Pompe[0]);
        req.setAttribute("pompes", pompes);
        RequestDispatcher dispatcher = req.getRequestDispatcher("page/verifyCuve/MenuPompe.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idPompe = req.getParameter("pompe");
        LocalDateTime date = LocalDateTime.parse((req.getParameter("date")));

        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        Pompe p = em.find(Pompe.class, idPompe);

        Jauge[] jauge = Jauge.getTwoJaugesBeforeAndAfterDateByPompe(p, LocalDate.from(date));



        Cuve c = p.getCuve();

        GraduationCuve[] j1 = c.getCuveGraduationBetween(jauge[0].getHauteurJauge());
        GraduationCuve[] j2 = c.getCuveGraduationBetween(jauge[1].getHauteurJauge());

        double qtNormal1 = c.getVolumeByHauteur(j1, jauge[0].getHauteurJauge());
        double qtNormal2 = c.getVolumeByHauteur(j2  , jauge[1].getHauteurJauge());

        double compteurQT = Compteur.getFuelSaleByDateByPompe(p, date);


        req.setAttribute("qtNormal1", qtNormal1);
        req.setAttribute("qtNormal2", qtNormal2);
        req.setAttribute("compteurQT", compteurQT);


        RequestDispatcher requestDispatcher = req.getRequestDispatcher("page/verifyCuve/DetailCuveAndCompteur.jsp");
        requestDispatcher.forward(req, resp);
    }
}
