<%--
  Created by IntelliJ IDEA.
  User: Otmani
  Date: 10/11/2026
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="titre" value="${empty produit.id ? 'Nouveau produit' : 'Modifier le produit'}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${titre}</title>
    <jsp:include page="/WEB-INF/views/header.jsp"/>
</head>
<body class="${bodyClass}">
<h1>${titre}</h1>

<form method="post" action="${ctx}/produits">
    <%-- id cache : uniquement en modification --%>
    <c:if test="${not empty produit.id}">
        <input type="hidden" name="id" value="${produit.id}">
    </c:if>

    <label>Nom
        <input type="text" name="nom" value="${fn:escapeXml(produit.nom)}">
    </label>
    <c:if test="${not empty erreurs.nom}"><span class="erreur"><c:out value="${erreurs.nom}"/></span></c:if>

    <label>Description
        <textarea name="description" rows="3" cols="40"><c:out value="${produit.description}"/></textarea>
    </label>
    <c:if test="${not empty erreurs.description}"><span class="erreur"><c:out
            value="${erreurs.description}"/></span></c:if>

    <label>Prix (MAD)
        <input type="text" name="prix" value="${fn:escapeXml(empty prixSaisi ? produit.prix : prixSaisi)}">
    </label>
    <c:if test="${not empty erreurs.prix}"><span class="erreur"><c:out value="${erreurs.prix}"/></span></c:if>

    <label>Stock
        <input type="text" name="stock" value="${fn:escapeXml(empty stockSaisi ? produit.stock : stockSaisi)}">
    </label>
    <c:if test="${not empty erreurs.stock}"><span class="erreur"><c:out value="${erreurs.stock}"/></span></c:if>

    <p>
        <button type="submit">Enregistrer</button>
        <a href="${ctx}/produits">Annuler</a>
    </p>
</form>
</body>
</html>