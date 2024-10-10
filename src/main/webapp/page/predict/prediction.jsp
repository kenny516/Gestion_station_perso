<%@ page import="prevision.Prevision" %><%--
  Created by IntelliJ IDEA.
  User: kenny
  Date: 10/10/2024
  Time: 08:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Prevision[] prevision = new Prevision[5];
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Prediction of date of </h1>
<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>Value</th>
        <th>Confidence</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (int i = 0; i < prevision.length; i++) {
    %>
    <tr>
        <td><%=prevision[i].getId()%>
        </td>
        <td><%=prevision[i].getDesignation()%>
        </td>
        <td><%=prevision[i].getIdCaisse()%>
        </td>
        <td><%=prevision[i].getDebit()%>
        </td>
        <td><%=prevision[i].getCredit()%>
        </td>
        <td><%=prevision[i].getDaty()%>
        </td>
        <td><%= prevision[i].getIdFacture()%>
        </td>

    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>
