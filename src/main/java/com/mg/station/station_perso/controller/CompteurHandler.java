package com.mg.station.station_perso.controller;

import com.mg.station.station_perso.model.CompteurPerso;
import com.mg.station.station_perso.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;


@WebServlet(name = "CompteurHandler", value = "/compteur-handler")
public class CompteurHandler extends HttpServlet {

    private ProduitService produitService;
    private CompteurPersoService compteurPersoService;
    private AchatProduitService achatProduitService;
    private PompisteService pompisteService;
    private PompeService pompeService;
    private FournisseurService fournisseurService;

    public CompteurHandler() {
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String current_page = req.getParameter("current_page");

        String idPompiste = req.getParameter("idPompiste");
        String idPompe = req.getParameter("idPompe");
        Double compteurNum = Double.valueOf(req.getParameter("compteurNum"));
        LocalDate daty = LocalDate.parse(req.getParameter("daty"));
        // build CompteurPerso object
        CompteurPerso compteurPerso = new CompteurPerso();
        compteurPerso.setPompiste(pompisteService.getPompisteById(idPompiste));
        compteurPerso.setPompe(pompeService.getPompeById(idPompe));
        compteurPerso.setCompteurNum(compteurNum);
        compteurPerso.setDaty(daty);

        if (compteurPersoService.sortie(compteurPerso.getPompiste())) {
            req.setAttribute("compteur", compteurPerso);
            double mtN = compteurPersoService.getEncaissementPompisteNormal(compteurPerso.getPompiste());
            req.setAttribute("montantNorm",mtN);
            resp.sendRedirect("Encaissement.jsp");
        } else {
            compteurPersoService.createCompteurPerso(compteurPerso);
        }
    }
}
