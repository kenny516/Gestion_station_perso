package com.mg.station.station_perso.controller;

import com.mg.station.station_perso.model.Compteur_perso;
import utilitaire.UtilDB;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Connection;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    private String message;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter print = response.getWriter();
        UtilDB utilDB = new UtilDB();
        Connection c = null;
        try {
            c = utilDB.GetConn();
            Compteur_perso cmpPerso = new Compteur_perso();
            Compteur_perso[] cps = cmpPerso.getAll(c);
            for (Compteur_perso cp : cps) {
                print.println(cp.getDaty());
            }
            print.println("atoo aiii");
        } catch (Exception e) {
            print.println("An error occurred while fetching data: " + e.getMessage());
            e.printStackTrace();  // Log the stack trace (for debugging purposes)
        } finally {
            if (c != null) {
                try {
                    c.close();  // Close the connection to prevent resource leaks
                } catch (Exception ex) {
                    print.println("Failed to close the connection: " + ex.getMessage());
                }
            }
        }
    }

    public void destroy() {
    }
}
