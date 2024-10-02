package com.mg.station.station_perso.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "InsertCompteurServlet", value = "/insert-compteur")
public class InsertCompteurServlet extends HttpServlet {
//    @EJB
//    MainEJB mainEJB;
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute("pompistes", mainEJB.getAll());
//        req.setAttribute("pompes", mainEJB.getAll());
//        req.getRequestDispatcher("insertionAchat.jsp").forward(req, resp);
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter print = response.getWriter();
//        UtilDB utilDB = new UtilDB();
//        Connection c = null;
//
//        try {
//            c = utilDB.GetConn();
//            double compteur = Double.parseDouble(request.getParameter("compteur"));
//            Date date = Date.valueOf(request.getParameter("date"));
//            String pompiste = request.getParameter("pompiste");
//            String pompe = request.getParameter("pompe");
//
//            Compteur_perso compteur_perso = new Compteur_perso("", date,"",compteur,pompiste, pompe);
//            compteur_perso.insertToTable(c);
//
//
//
//            print.println("Record inserted successfully.");
//        } catch (Exception e) {
//            print.println("An error occurred: " + e.getMessage());
//            e.printStackTrace();
//        } finally {
//            if (c != null) {
//                try {
//                    c.close();
//                } catch (Exception ex) {
//                    print.println("Failed to close the connection: " + ex.getMessage());
//                }
//            }
//        }
//    }

}