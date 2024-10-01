package com.mg.station.station_perso.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CompteurHandler", value = "/compteur-handler")
public class CompteurHandler extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String current_page = req.getParameter("current_page");
        if ("Compteur".equals(current_page)) {

            // a faire lors de l entre du compteur par un pompiste

            req.getRequestDispatcher("compteur.jsp").forward(req, resp);
        } else if ("Avoir".equals(current_page)) {
            req.getRequestDispatcher("avoir.jsp").forward(req, resp);
        } else if ("EncaissementAvoir".equals(current_page)) {
            req.getRequestDispatcher("encaissementAvoir.jsp").forward(req, resp);
        } else if ("Encaissement".equals(current_page)) {
            req.getRequestDispatcher("encaissement.jsp").forward(req, resp);
        }
    }
}
