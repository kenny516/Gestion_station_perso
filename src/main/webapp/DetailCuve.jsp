<%@ page import="com.mg.station.station_perso.entity.CuveGraduation" %>
<%@ page import="java.time.LocalDate" %><%--
  Created by IntelliJ IDEA.
  User: kenny
  Date: 05/10/2024
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    CuveGraduation[] cuveGraduation = (CuveGraduation[]) request.getAttribute("cuveGraduation");
    double capaciteCalculer = (double) request.getAttribute("capaciteCalculer");
    double hauteur = (double) request.getAttribute("hauteur");
    LocalDate date = (LocalDate) request.getAttribute("dateJauge");
%>
<html>
<head>
    <title>Cuve Detail</title>
</head>
<body>
<div>
    <h1>Jauge hauteur: <%=hauteur%> a la date <%=date%>
    </h1>
    <h1>Capacite entre jauge hauteur :</h1>
    <%for (int i = 0; i < cuveGraduation.length; i++) {%>
    <p>
        Hauteur = <%= cuveGraduation[i].getHauteur()%> et capacite = <%=cuveGraduation[i].getCapacite()%>
    </p>
    <%}%>
    <h1>
        Capacite calcule : <%=capaciteCalculer%>
    </h1>
</div>

</body>
</html>
