<%@ page import="com.mg.station.station_perso.entity.Jauge" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Double qtNormal1 = (Double) request.getAttribute("qtNormal1");
    Double qtNormal2 = (Double) request.getAttribute("qtNormal2");
    Double compteurQT = (Double) request.getAttribute("compteurQT");

    Jauge[] jauges = (Jauge[]) request.getAttribute("jauge");

    // 0 si null
    qtNormal1 = (qtNormal1 != null) ? qtNormal1 : 0.0;
    qtNormal2 = (qtNormal2 != null) ? qtNormal2 : 0.0;
    compteurQT = (compteurQT != null) ? compteurQT : 0.0;

    double quantiteVendu = qtNormal1 - qtNormal2;
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cuve and Compteur Details</title>
    <link rel="stylesheet" href="../../assets/DetailJauge.css">
</head>
<body>
<div class="container">
    <header>
        <h1>Cuve and Compteur Details</h1>
    </header>

    <section class="details">
        <table border="1" cellspacing="0" cellpadding="8" class="details-table">
            <thead>
            <tr>
                <th colspan="2">Jauge Information</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>Jauge Before:</td>
                <td><strong><%= String.format("%.2f", qtNormal1) %></strong></td>
            </tr>
            <tr>
                <td>Jauge After:</td>
                <td><strong><%= String.format("%.2f", qtNormal2) %></strong></td>
            </tr>
            <tr>
                <td>Quantity Sold (by Jauge):</td>
                <td><strong><%= String.format("%.2f", quantiteVendu) %></strong></td>
            </tr>
            </tbody>
        </table>

        <table border="1" cellspacing="0" cellpadding="8" class="jauges-table">
            <thead>
            <tr>
                <th>Jauge #</th>
                <th>Hauteur</th>
                <th>Volume</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (int i = 0; i < jauges.length; i++) {
            %>
            <tr>
                <td>Jauge <%= i + 1 %></td>
                <td><%= jauges[i].getHauteurJauge() %></td>
                <td><%= jauges[i].getVolume() %></td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>

        <table border="1" cellspacing="0" cellpadding="8" class="details-table">
            <thead>
            <tr>
                <th colspan="2">Compteur Information</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>Compteur Quantity:</td>
                <td><strong><%= String.format("%.2f", compteurQT) %></strong></td>
            </tr>
            </tbody>
        </table>
    </section>
</div>
<H1>Anomalie trouvée => <%= Math.abs(compteurQT - quantiteVendu) %></H1>


</body>
</html>
