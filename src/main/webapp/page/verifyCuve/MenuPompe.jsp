<%@ page import="com.mg.station.station_perso.entity.Pompe" %><%--
  Created by IntelliJ IDEA.
  User: kenny
  Date: 06/10/2024
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Pompe[] pompe = (Pompe[]) request.getAttribute("pompes");
%>
<html>
<head>
    <title>Menu pompe</title>
</head>
<body>
<form action="cuveJauge" method="post">
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

</body>
</html>
