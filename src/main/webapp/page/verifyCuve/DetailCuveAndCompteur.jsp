<%@ page import="com.mg.station.station_perso.entity.Compteur" %>
<%@ page import="com.mg.station.station_perso.entity.CuveGraduation" %>
<%@ page import="com.mg.station.station_perso.entity.Pompe" %><%--
  Created by IntelliJ IDEA.
  User: kenny
  Date: 05/10/2024
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Pompe[] pompe = (Pompe[]) request.getAttribute("pompes");


%>
<html>
<head>
    <title>Detail cuve and compteur</title>
</head>
<body>
<div>
    <h1>Detail cuve and compteur</h1>
    <form action="" method="post">
        <input type="hidden" name="current_page" value="DetailCuveAndCompteur">
        <label for="pompe">Pompe:</label>
        <select id="pompe" name="pompe" required>
            <% for (Pompe p : pompe) { %>
            <option value="<%= p.getId() %>">
                <%= p.getLibelle() %>
            </option>
            <% } %>
        </select>
        <label for="date">Date:</label>
        <input type="date" id="date" name="date" required>
        <button type="submit">Submit</button>
    </form>

    <h1>Detail + Dateee </h1>
    <h2>Compteur liste et get</h2>
    <h2>jauge</h2>


</body>
</html>
