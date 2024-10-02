package com.mg.station.station_perso.controller;

import com.mg.station.station_perso.service.ProduitService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TestServlet", value = "/test")
public class TestServlet extends HttpServlet {
    private  ProduitService produitService = new ProduitService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        produitService.getAllProduits().forEach(produit -> {
            try {
                resp.getWriter().println(produit.getNom());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
