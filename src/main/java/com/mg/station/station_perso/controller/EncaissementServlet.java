package com.mg.station.station_perso.controller;

import com.mg.station.station_perso.Database;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EncaissementServlet", value = "/EncaissementServlet")
public class EncaissementServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String daty = req.getParameter("daty");
        double montantNorm = Double.parseDouble(req.getParameter("montantNorm"));
        double montant = Double.parseDouble(req.getParameter("montant"));

        EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();

        if (montantNorm - montant > 0){
            req.setAttribute("montant", montantNorm - montant);
            resp.sendRedirect("NonEncaisser.jsp");
        }else {
            resp.sendRedirect("insertionCompteur.jsp");
        }

    }
}
