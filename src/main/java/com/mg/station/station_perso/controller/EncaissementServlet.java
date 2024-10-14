package com.mg.station.station_perso.controller;

import EJBPERSO.StationServiceEJB;
import com.mg.station.station_perso.DAO.VenteDAO;
import com.mg.station.station_perso.Database;
import com.mg.station.station_perso.entity.Pompe;
import com.mg.station.station_perso.entity.Vente;

import javax.ejb.EJB;
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

//14
//CLI000054 (divers)
@WebServlet(name = "EncaissementServlet", value = "/EncaissementServlet")
public class EncaissementServlet extends HttpServlet {
    @EJB
    StationServiceEJB stationServiceEJB;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double quantiteRestant = Double.parseDouble(req.getParameter("quantiteRestant"));
        LocalDate daty = LocalDate.from(LocalDateTime.parse((req.getParameter("daty"))));
        String RefMagasin = req.getParameter("RefMagasin");
        stationServiceEJB.nouvelleVenteCarburant(quantiteRestant, 14, "CLI000054",RefMagasin, daty);
        resp.sendRedirect("compteurHandler");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDate daty = LocalDate.from(LocalDateTime.parse((req.getParameter("daty"))));
        String idCLient = req.getParameter("client");
        String idPompe = req.getParameter("idPompe");
        String refMagasin = req.getParameter("RefMagasin");
        double quantiteRestant = Double.parseDouble(req.getParameter("quantiteRestante"));
        double quantite = Double.parseDouble(req.getParameter("quantite"));

        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();

        Vente vente = new Vente();
        vente.setRefClient(idCLient);
        vente.setPompe(em.find(Pompe.class, idPompe));
        vente.setQuantite(quantite);
        vente.setDate(daty);
        vente.setMontant(quantite * stationServiceEJB.getGasoilPrixUnitaireVente());
        VenteDAO venteDAO = new VenteDAO();
        venteDAO.create(vente);

        stationServiceEJB.nouvelleVenteCarburant(quantiteRestant, 12, "CLI000105",refMagasin, daty);

        if (quantiteRestant - quantite > 0) {
            req.setAttribute("date",req.getParameter("daty"));
            req.setAttribute("RefMagasin",refMagasin);
            req.setAttribute("idPompe",idPompe);
            req.setAttribute("quantiteRestante", quantiteRestant - quantite);

            try {
                req.setAttribute("clients", stationServiceEJB.getClient());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            // redirect
            RequestDispatcher dispatcher = req.getRequestDispatcher("Encaissement.jsp");
            dispatcher.forward(req, resp);
        }else {
            resp.sendRedirect("compteurHandler");
        }

    }
}
