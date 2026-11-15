<%--
  Created by IntelliJ IDEA.
  User: Otmani
  Date: 10/11/2026
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Dashboard</title>
</head>
<body>

<h1>Bienvenue ${user}</h1>

<a href="${pageContext.request.contextPath}/produits">
    Gestion des produits
</a>

<br><br>

<form action="${pageContext.request.contextPath}/logout"
      method="POST">

    <button type="submit">Logout</button>

</form>

</body>
</html>