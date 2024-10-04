package com.mg.station.station_perso.controller;

import com.mg.station.station_perso.Database;
import com.mg.station.station_perso.entity.Compteur;
import com.mg.station.station_perso.entity.Pompe;
import com.mg.station.station_perso.entity.Pompiste;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@WebServlet(name = "CompteurHandler", value = "/compteurHandler")
public class CompteurHandler extends HttpServlet {
    private EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Pompiste[] pompistes = em.createQuery("FROM Pompiste p", Pompiste.class).getResultList().toArray(new Pompiste[0]);
        Pompe[] pompes = em.createQuery("FROM Pompe p", Pompe.class).getResultList().toArray(new Pompe[0]);
        req.setAttribute("pompistes", pompistes);
        req.setAttribute("pompes", pompes);
        RequestDispatcher dispatcher = req.getRequestDispatcher("insertionCompteur.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Start a transaction
        EntityTransaction transaction = em.getTransaction();

        try {
            // Begin the transaction
            transaction.begin();

            // Extract data from the request
            String idPompiste = req.getParameter("pompiste");
            String idPompe = req.getParameter("pompe");
            Double compteurNum = Double.valueOf(req.getParameter("compteur"));
            String datySTR = req.getParameter("date");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime datyHeure = LocalDateTime.parse(datySTR, formatter);

            // Build the Compteur object
            Compteur compteurPerso = new Compteur();
            compteurPerso.setPompiste(em.find(Pompiste.class, idPompiste));
            compteurPerso.setPompe(em.find(Pompe.class, idPompe));
            compteurPerso.setValeur(compteurNum);
            compteurPerso.setDate(datyHeure);

            // Persist the entity
            em.persist(compteurPerso);

            // Commit the transaction
            transaction.commit();

            // Handle post-persistence logic
            if (compteurPerso.sortie(compteurPerso.getPompiste())) {
                req.setAttribute("compteur", compteurPerso);
                double mtN = compteurPerso.getEncaissementNormal(compteurPerso.getPompiste());
                req.setAttribute("montantNorm", mtN);
                RequestDispatcher dispatcher = req.getRequestDispatcher("Encaissement.jsp");
                dispatcher.forward(req, resp);  // Forward to the Encaissement.jsp page
            } else {
                resp.sendRedirect("compteurHandler");  // Redirect back to the form
            }

        } catch (Exception e) {
            // Rollback the transaction if something goes wrong
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();  // Log the exception (you can replace this with proper logging)
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error occurred while saving the compteur.");
        }
    }

}
