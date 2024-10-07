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
        LocalDateTime date2 = LocalDateTime.parse((req.getParameter("date2")));

        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        Pompe p = em.find(Pompe.class, idPompe);

        //Jauge[] jauge = Jauge.getTwoJaugesBeforeAndAfterDateByPompe(p, LocalDate.from(date));
        Jauge[] jauge = Jauge.getInnerBoundaryJaugesByPompe(p, LocalDate.from(date),LocalDate.from(date2));


        Cuve c = p.getCuve();

        CuveGraduation[] j1 = jauge[0].getCuveGraduationBetween(c.getId());

        CuveGraduation[] j2 = jauge[1].getCuveGraduationBetween(c.getId());

        double qtNormal1 = jauge[0].getVolumeByHauteur(j1);
        double qtNormal2 = jauge[1].getVolumeByHauteur(j2);

        //double compteurQT = Compteur.getFuelSaleByDateByPompe(p, date);
        double compteurQT = Compteur.getFuelSaleByDateRangeByPompe(p, date,date2);


        req.setAttribute("jauge",jauge);
        req.setAttribute("qtNormal1", qtNormal1);
        req.setAttribute("qtNormal2", qtNormal2);
        req.setAttribute("compteurQT", compteurQT);


        RequestDispatcher requestDispatcher = req.getRequestDispatcher("page/verifyCuve/DetailCuveAndCompteur.jsp");
        requestDispatcher.forward(req, resp);
    }
}
