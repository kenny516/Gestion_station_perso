<%@ page import="com.mg.station.station_perso.entity.Compteur" %>
<%@ page import="client.Client" %>
<%@ page import="com.mg.station.station_perso.entity.Pompiste" %>
<%
    // Récupérer l'objet CompteurPerso depuis l'attribut de requête

    Client[] clients = (Client[]) request.getAttribute("clients");

    String date = (String) request.getAttribute("date");
    String refMagasin = (String) request.getAttribute("RefMagasin");
    String idPompe = (String) request.getAttribute("idPompe");

    double quantiteRestante = (double) request.getAttribute("quantiteRestante");

%>
<html>
<head>
    <title>Encaissement</title>
</head>
<link rel="stylesheet" href="assets/compteur.css">
<body>
<div class="container">
    <h1>Insertion Encaissement Form</h1>
    <form action="EncaissementServlet" method="post">
        <input type="hidden" name="current_page" value="Encaissement">
        <input type="hidden" name="quantiteRestant" value="<%= quantiteRestante %>">
        <input type="hidden" name="daty" value="<%= date %>">
        <input type="hidden" name="idPompe" value="<%= idPompe %>">
        <input type="hidden" name="RefMagasin" value="<%= refMagasin %>">


        <label for="client">Clients:</label>
        <select id="client" name="client" required>
            <% for (Client client : clients) { %>
            <option value="<%= client.getId() %>">
                <%= client.getNom() %>
            </option>
            <% } %>
        </select>
        <label for="quantite">Quantite :</label>
        <input type="number" id="quantite" name="quantite" required>
        <button type="submit">Submit</button>
    </form>
    <a href="EncaissementServlet?RefMagasin=<%=refMagasin%>&quantiteRestant=<%=quantiteRestante%>&daty=<%=date%>">
        TOUT POUR DIVERS
    </a>
</div>
</body>
</html>
