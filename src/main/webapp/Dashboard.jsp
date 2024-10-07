<%@ page import="faturefournisseur.FactureFournisseur" %>
<%@ page import="vente.VenteDetails" %>
<%@ page import="faturefournisseur.FactureFournisseurDetails" %><%--
  Created by IntelliJ IDEA.
  User: kenny
  Date: 01/10/2024
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    VenteDetails[] vente = (VenteDetails[]) request.getAttribute("vente");
    FactureFournisseurDetails[] facfournisseurs = (FactureFournisseurDetails[]) request.getAttribute("facfournisseurs");
    double benefice = (double) request.getAttribute("benefice");
    double creance = (double) request.getAttribute("creance");
%>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="assets/dashboard.css">
</head>
<body>
<form action="dashboard" method="post">
    <label>choisir une date </label>
    <input type="date" name="date">
</form>
<div>
    <div>liste depense et vente</div>
    <table>
        <tr>
            <th>id</th>
            <th>date</th>
            <th>montant</th>
        </tr>
        <% for (VenteDetails v : vente) { %>
        <tr>
            <td><%= v.getId() %>
            </td>
            <td><%= v.get() %>
            </td>
            <td><%= v.getMontantttc() %>
            </td>
        </tr>
        <% } %>
    </table>
    <table>
        <tr>
            <th>id</th>
            <th>nom</th>
            <th>solde</th>
        </tr>
        <% for (FactureFournisseur f : facfournisseurs) { %>
        <tr>
            <td><%= f.getId() %>
            </td>
            <td><%=f.getFournisseurlib()%></td>
            <td><%= f.getFactureWithMontant(null).getMontantttc() %>
            </td>
        </tr>
        <% } %>
    </table>
    <div>benefice = <%=benefice%>
    </div>
    <div>creance = <%=creance%>
    </div>
</div>

</body>
</html>
