package com.mg.station.station_perso.controller;

import EJBPERSO.ConptaEJB;
import EJBPERSO.StationServiceEJB;
import com.mg.station.station_perso.entity.Vente;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "dashboard", value = "dashboard")
public class Dashboard extends HttpServlet {
    @EJB
    ConptaEJB comptaEJB;
    @EJB
    StationServiceEJB stationServiceEJB;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            double avoirCL = Vente.getMontantVenteByYear(LocalDate.now());

            req.setAttribute("vente", comptaEJB.getVenteDetails(LocalDate.now()));
            req.setAttribute("facfournisseurs", comptaEJB.getFactureFournisseurDetails(LocalDate.now()));
            req.setAttribute("benefice", comptaEJB.Benefice(LocalDate.now()) + avoirCL);
            req.setAttribute("creance", avoirCL);
            RequestDispatcher dispatcher = req.getRequestDispatcher("Dashboard.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDate date = LocalDate.parse(req.getParameter("date"));
        try {
            double avoirCL = Vente.getMontantVenteByYear(date);

            req.setAttribute("vente", comptaEJB.getListeVenteFac(date));
            req.setAttribute("facfournisseurs", comptaEJB.getFacturFournisseur(date));
            req.setAttribute("benefice", comptaEJB.Benefice(date) + avoirCL);
            req.setAttribute("creance", avoirCL);
            RequestDispatcher dispatcher = req.getRequestDispatcher("Dashboard.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
