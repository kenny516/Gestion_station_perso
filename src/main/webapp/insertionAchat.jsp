<%--
  Created by IntelliJ IDEA.
  User: kenny
  Date: 28/09/2024
  Time: 12:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="assets/achat.css">
    <title>Formulaire</title>
</head>
<body>
<div class="container">
    <h1>Contact Form</h1>
    <form action="#" method="post">
        <label for="quantite">Quantité:</label>
        <input type="number" id="quantite" name="quantite" required>

        <label for="date">Date:</label>
        <input type="date" id="date" name="date" required>

        <label for="pompiste">Fournisseur:</label>
        <select id="pompiste" name="pompiste" required>
            <option value="" disabled selected>Select fournisseur</option>
            <option value="pompiste1">Pompiste 1</option>
            <option value="pompiste2">Fournisseur 2</option>
            <option value="pompiste3">Pompiste 3</option>
        </select>

        <label for="pompe">Pompe:</label>
        <select id="pompe" name="pompe" required>
            <option value="" disabled selected>Select Pompe</option>
            <option value="pompe1">Pompe 1</option>
            <option value="pompe2">Pompe 2</option>
            <option value="pompe3">Pompe 3</option>
        </select>

        <button type="submit">Submit</button>
    </form>
</div>
</body>
</html>
