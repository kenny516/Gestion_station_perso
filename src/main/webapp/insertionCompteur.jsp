<%@ page import="pompe.PompisteLib" %>
<%@ page import="pompe.Pompe" %><%--
  Created by IntelliJ IDEA.
  User: kenny
  Date: 28/09/2024
  Time: 12:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    PompisteLib[] pompisteLibs = (PompisteLib[]) request.getAttribute("pompistes");
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
        <label for="quantite">Compteur :</label>
        <input type="number" id="quantite" name="compteur" required>

        <label for="date">Date:</label>
        <input type="date" id="date" name="date" required>

        <label for="pompiste">Pompiste:</label>
        <select id="pompiste" name="pompiste" required>
            <% for (PompisteLib pompiste : pompisteLibs) { %>
            <option value="<%= pompiste.getRefuser() %>">
                <%= pompiste.getNomuser() %>
            </option>
            <% } %>
        </select>

        <label for="pompe">Pompe:</label>
        <select id="pompe" name="pompe" required>
            <% for (Pompe p : pompe) { %>
            <option value="<%= p.getId() %>">
                <%= p.getVal() %>
            </option>
            <% } %>
        </select>

        <button type="submit">Submit</button>
    </form>
</div>
</body>
</html>



