<%--
  Created by IntelliJ IDEA.
  User: Otmani
  Date: 10/11/2026
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Connexion</title>
    <jsp:include page="/WEB-INF/views/header.jsp"/>
</head>
<body class="${bodyClass}">
<h1>Connexion</h1>

<c:if test="${not empty erreur}">
    <p class="erreur"><c:out value="${erreur}"/></p>
</c:if>

<form method="post" action="${ctx}/login">
    <label>Login
        <input type="text" name="login" value="${fn:escapeXml(login)}" required>
    </label>
    <label>Mot de passe
        <input type="password" name="password" required>
    </label>
    <p><button type="submit">Se connecter</button></p>
</form>
</body>
</html>