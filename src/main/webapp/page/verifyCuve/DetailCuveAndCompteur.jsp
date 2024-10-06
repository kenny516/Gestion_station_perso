<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Retrieve and validate request attributes safely
    Double qtNormal1 = (Double) request.getAttribute("qtNormal1");
    Double qtNormal2 = (Double) request.getAttribute("qtNormal2");
    Double compteurQT = (Double) request.getAttribute("compteurQT");

    // Default values if attributes are null
    qtNormal1 = (qtNormal1 != null) ? qtNormal1 : 0.0;
    qtNormal2 = (qtNormal2 != null) ? qtNormal2 : 0.0;
    compteurQT = (compteurQT != null) ? compteurQT : 0.0;

    // Calculate the difference
    double quantiteVendu = qtNormal1 - qtNormal2;
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Detail cuve and compteur</title>
    <link rel="stylesheet" href="../../assets/DetailJauge.css">
</head>
<body>
<div class="container">
    <header>
        <h1>Details of Cuve and Compteur</h1>
    </header>

    <section class="details">
        <article class="jauge-info">
            <h2>JAUGE Details</h2>
            <p>JAUGE before: <strong><%= String.format("%.2f", qtNormal1) %></strong></p>
            <p>JAUGE after: <strong><%= String.format("%.2f", qtNormal2) %></strong></p>
            <p>Quantity sold (by jauge): <strong><%= String.format("%.2f", quantiteVendu) %></strong></p>
        </article>

        <article class="compteur-info">
            <h2>Compteur Details</h2>
            <p>Compteur Quantity: <strong><%= String.format("%.2f", compteurQT) %></strong></p>
        </article>
    </section>
</div>
</body>
</html>
