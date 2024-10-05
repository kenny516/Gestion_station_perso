<%@ page import="com.mg.station.station_perso.entity.Pompe" %><%--
  Created by IntelliJ IDEA.
  User: kenny
  Date: 03/10/2024
  Time: 08:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Pompe[] pompe = (Pompe[]) request.getAttribute("pompes");
%>

<html>
<head>
    <title>Garbu jauge</title>
</head>
<link href="assets/compteur.css">
<body>
<form action="" method="post">
    <label for="pompe">Pompe:</label>
    <select id="pompe" name="pompe" required>
        <% for (Pompe p : pompe) { %>
        <option value="<%= p.getId() %>">
            <%= p.getLibelle() %>
        </option>
        <% } %>
    </select>
    <label for="dateJauge">Date de jauge</label>
    <input type="date" id="dateJauge" name="dateJauge">
    <label for="hauteur">Hauteur jauge</label>
    <input type="number" id="hauteur" name="hauteur">
    <button type="submit">Submit</button>
</form>

</body>
</html>
