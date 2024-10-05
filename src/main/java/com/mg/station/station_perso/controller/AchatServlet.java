package com.mg.station.station_perso.controller;

import EJBPERSO.StationServiceEJB;
import com.mg.station.station_perso.DAO.AchatDAO;
import com.mg.station.station_perso.Database;
import com.mg.station.station_perso.entity.Achat;
import com.mg.station.station_perso.entity.Cuve;
import com.mg.station.station_perso.entity.Pompe;

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

@WebServlet(name = "AchatServlet", value = "/AchatServlet")
public class AchatServlet extends HttpServlet {
    @EJB
    StationServiceEJB stationServiceEJB;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Fetch necessary data for the form
        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            Pompe[] pompes = em.createQuery("FROM Pompe p", Pompe.class).getResultList().toArray(new Pompe[0]);

            req.setAttribute("fournisseurs", stationServiceEJB.getFournisseur());
            req.setAttribute("pompes", pompes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            em.close();
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("insertionAchat.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Handle form submission
        String refFournisseur = req.getParameter("refFournisseur");
        String idCuve = req.getParameter("idCuve");
        double quantite = Double.parseDouble(req.getParameter("quantite"));
        double montant = Double.parseDouble(req.getParameter("montant"));
        LocalDate daty = LocalDate.parse(req.getParameter("daty"));

        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();
        AchatDAO achatDAO = new AchatDAO();

        try {
            Achat achat = new Achat();
            achat.setRefFournisseur(refFournisseur);
            achat.setCuve(em.find(Cuve.class, idCuve));
            achat.setQuantite(quantite);
            achat.setMontant(montant);
            achat.setDaty(daty);

            achatDAO.create(achat);
        } finally {
            em.close();
        }

        resp.sendRedirect("success.jsp");
    }
}