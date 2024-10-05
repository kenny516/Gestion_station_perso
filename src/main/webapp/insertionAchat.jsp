<%@ page import="com.mg.station.station_perso.entity.Pompe" %>
<%@ page import="com.mg.station.station_perso.entity.Pompiste" %>
<%@ page import="faturefournisseur.Fournisseur" %><%--
  Created by IntelliJ IDEA.
  User: kenny
  Date: 28/09/2024
  Time: 12:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Fournisseur[] fournisseurs = (Fournisseur[]) request.getAttribute("fournisseurs");
    Pompe[] pompe = (Pompe[]) request.getAttribute("pompes");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="assets/compteur.css">
    <title>Achat Carburent Form</title>
</head>
<body>
<div class="container">
    <h1>Achat Carburent Form</h1>
    <form action="" method="post">
        <label for="quantite">Quantité:</label>
        <input type="number" id="quantite" name="quantite" required>

        <label for="date">Date:</label>
        <input type="date" id="date" name="date" required>

        <label for="refFournisseur">Fournisseur:</label>
        <select id="refFournisseur" name="refFournisseur" required>
            <option value="" disabled selected>Select fournisseur</option>
            <% for (Fournisseur f : fournisseurs) { %>
            <option value="<%= f.getId() %>">
                <%= f.getNom() %>
            </option>
            <% } %>
        </select>

        <label for="pompe">Pompe:</label>
        <select id="pompe" name="pompe" required>
            <% for (Pompe p : pompe) { %>
            <option value="<%= p.getId() %>">
                <%= p.getLibelle() %>
            </option>
            <% } %>
        </select>

        <button type="submit">Submit</button>
    </form>
</div>
</body>
</html>
