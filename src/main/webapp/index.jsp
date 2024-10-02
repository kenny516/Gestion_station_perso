<%@ page import="com.mg.station.station_perso.model.Produit" %>
<%@ page import="com.mg.station.station_perso.service.ProduitService" %>
<%@ page import="org.springframework.beans.factory.annotation.Autowired" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <style>
        body {
            text-align: center;
        }
        a {
            display: inline-block;
            margin: 10px;
            padding: 10px;
            text-decoration: none;
            color: white;
            background-color: #007BFF;
            border-radius: 5px;
        }
        a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <%

        ProduitService produitService ;

    %>
    <h1>Hello World!</h1>
    <br/>
    <a href="hello-servlet">Hello Servlet</a>
    <a href="insertionAchat.jsp">achat</a>
    <a href="insertionCompteur.jsp">compteur</a>
</body>
</html>