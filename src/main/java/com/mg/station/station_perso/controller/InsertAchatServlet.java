package com.mg.station.station_perso.controller;

import com.mg.station.station_perso.model.Compteur_perso;
import utilitaire.UtilDB;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

@WebServlet(name = "InsertAchatServlet", value = "/insert-achat")
public class InsertAchatServlet extends HttpServlet {
    @EJB
    MainEJB mainEJB;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter print = response.getWriter();
        UtilDB utilDB = new UtilDB();
        Connection c = null;

        try {
            c = utilDB.GetConn();
            double compteur = Double.parseDouble(request.getParameter("compteur"));
            Date date = Date.valueOf(request.getParameter("date"));
            String fournisseur = request.getParameter("pompiste");
            String pompe = request.getParameter("pompe");

            Compteur_perso compteur_perso = new Compteur_perso();


            print.println("Record inserted successfully.");
        } catch (Exception e) {
            print.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (c != null) {
                try {
                    c.close();
                } catch (Exception ex) {
                    print.println("Failed to close the connection: " + ex.getMessage());
                }
            }
        }
    }
}