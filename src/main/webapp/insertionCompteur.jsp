<%@ page import="com.mg.station.station_perso.model.Pompe" %>
<%@ page import="com.mg.station.station_perso.model.Pompiste" %>
Created by IntelliJ IDEA.
  User: kenny
  Date: 28/09/2024
  Time: 12:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    Pompiste[] pompisteLibs = (Pompiste[]) request.getAttribute("pompistes");
    Pompe[] pompe = (Pompe[]) request.getAttribute("pompes");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="assets/compteur.css">
    <title>Formulaire</title>
</head>
<body>
<div class="container">
    <h1>Insertion compteur Form</h1>
    <form action="#" method="post">
        <input type="hidden" name="current_page" value="Compteur">
        <label for="quantite">Compteur :</label>
        <input type="number" id="quantite" name="compteur" required>

        <label for="date">Date:</label>
        <input type="date" id="date" name="date" required>

        <label for="pompiste">Pompiste:</label>
        <select id="pompiste" name="pompiste" required>
            <% for (Pompiste pompiste : pompisteLibs) { %>
            <option value="<%= pompiste.getId() %>">
                <%= pompiste.getNom() %>
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



