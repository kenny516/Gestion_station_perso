package com.mg.station.station_perso.controller;

import com.mg.station.station_perso.DAO.JaugeDAO;
import com.mg.station.station_perso.Database;
import com.mg.station.station_perso.entity.Cuve;
import com.mg.station.station_perso.entity.GraduationCuve;
import com.mg.station.station_perso.entity.PrelevementCuve;
import com.mg.station.station_perso.entity.Pompe;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "CuveJaugeServlet", value = "/cuveJauge")
public class CuveJaugeServlet extends HttpServlet {
    private EntityManager em = Database.ENTITY_MANAGER_FACTORY.createEntityManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Pompe[] pompes = em.createQuery("FROM Pompe p", Pompe.class).getResultList().toArray(new Pompe[0]);
        req.setAttribute("pompes", pompes);
        RequestDispatcher dispatcher = req.getRequestDispatcher("JaugeCarbu.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDate dateJauge = LocalDate.parse(req.getParameter("dateJauge"));
        double jauge = Double.parseDouble(req.getParameter("hauteur"));

        Pompe pompe = em.find(Pompe.class, req.getParameter("pompe"));
        Cuve cuve = pompe.getCuve();
        GraduationCuve[] graduationCuves = em.createQuery("FROM GraduationCuve cg WHERE cg.cuve = :cuve", GraduationCuve.class)
                .setParameter("cuve", cuve)
                .getResultList().toArray(new GraduationCuve[0]);

        double volumeCalc = cuve.getVolumeByHauteur(graduationCuves, jauge);
        req.setAttribute("cuveGraduation", graduationCuves);
        req.setAttribute("capaciteCalculer", volumeCalc);
        req.setAttribute("hauteur", jauge);
        req.setAttribute("dateJauge", dateJauge);
// creation de jauge
        PrelevementCuve prelevementCuvePerso = new PrelevementCuve();
        prelevementCuvePerso.setHauteurJauge(jauge);
        prelevementCuvePerso.setDaty(dateJauge);
        prelevementCuvePerso.setPompe(pompe);

        JaugeDAO jaugeDAO = new JaugeDAO();
        jaugeDAO.create(prelevementCuvePerso);
// redirection vers details cuve

        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);
    }


}










