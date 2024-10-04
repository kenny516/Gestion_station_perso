<%@ page import="com.mg.station.station_perso.entity.Compteur" %><%
    // Récupérer l'objet CompteurPerso depuis l'attribut de requête
    Compteur cmpP = (Compteur) request.getAttribute("compteur");
    double montantNormal = (double) request.getAttribute("montantNorm");
 %>
<html>
<head>
    <title>Encaissement</title>
</head>
<link href="assets/compteur.css">
<body>
<div class="container">
    <h1>Insertion Encaissement Form</h1>
    <form action="EncaissementServlet" method="post">
        <input type="hidden" name="current_page" value="Encaissement">

        <!-- Champs cachés pour les données du compteur -->
        <input type="hidden" name="montantNorm" value="<%= montantNormal %>">
        <input type="hidden" name="compteur_valeur" value="<%= cmpP.getDate() %>">

        <label for="montant">Montant :</label>
        <input type="number" id="montant" name="montant" required>
        <button type="submit">Submit</button>
    </form>
</div>
</body>
</html>
