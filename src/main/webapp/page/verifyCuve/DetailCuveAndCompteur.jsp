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
    double qtNormal1 = (double) request.getAttribute("qtNormal1");
    double qtNormal2 = (double) request.getAttribute("qtNormal2");
    double compteurQT = (double) request.getAttribute("compteurQT");

%>
<html>
<head>
    <link rel="stylesheet" href="../../assets/DetailJauge.css">
    <title>Detail cuve and compteur</title>
</head>
<body>
<div>
    <h1>Detail cuve and compteur</h1>

    <h2>JAUGE before : <%= qtNormal1 %>
    </h2>
    <h2>JAUGE after : <%= qtNormal2 %>
    </h2>

    <h2>Quantite normalement vendu (By jauge) :<%= qtNormal1 - qtNormal2 %>
    </h2>
    <h2>Compteur Quantite : <%= compteurQT %>
    </h2>


</body>
</html>
