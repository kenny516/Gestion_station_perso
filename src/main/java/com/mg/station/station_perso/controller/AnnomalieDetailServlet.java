package com.mg.station.station_perso.controller;

import com.mg.station.station_perso.Database;
import com.mg.station.station_perso.entity.Compteur;
import com.mg.station.station_perso.entity.Pompe;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@WebServlet(name = "AnnomalieDetailServlet", urlPatterns = "/annomalie-detail")
public class AnnomalieDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idPompe = req.getParameter("pompe");
        LocalDateTime date = LocalDate.parse(req.getParameter("date")).atStartOfDay();

        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        Pompe p = em.find(Pompe.class, idPompe);
         Compteur.getFuelSaleByDateByPompe(p, date);






    }
}
