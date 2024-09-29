package com.mg.station.station_perso.controller;

import utilitaire.UtilDB;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet(name = "InsertCompteurServlet", value = "/insert-compteur")
public class InsertCompteurServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter print = response.getWriter();
        UtilDB utilDB = new UtilDB();
        Connection c = null;

        try {
            c = utilDB.GetConn();
            String quantite = request.getParameter("quantite");
            String date = request.getParameter("date");
            String pompiste = request.getParameter("pompiste");
            String pompe = request.getParameter("pompe");

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