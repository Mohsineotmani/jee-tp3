<%--
  Created by IntelliJ IDEA.
  User: Otmani
  Date: 10/11/2026
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Produits</title>
    <jsp:include page="/WEB-INF/views/header.jsp"/>
</head>
<body class="${bodyClass}">
<h1>Catalogue de produits</h1>

<%-- TODO 1 : message flash : affiche une seule fois puis retire de la session --%>
<c:if test="${not empty sessionScope.flash}">
    <div class="flash"><c:out value="${sessionScope.flash}"/></div>
    <c:remove var="flash" scope="session"/>
</c:if>

<p><a href="${ctx}/produits?action=new">Ajouter un produit</a></p>

<%-- Bonus : recherche par nom --%>
<form method="get" action="${ctx}/produits">
    <input type="text" name="q" placeholder="Rechercher par nom" value="${fn:escapeXml(q)}">
    <button type="submit">Rechercher</button>
    <c:if test="${not empty q}"><a href="${ctx}/produits">Réinitialiser</a></c:if>
</form>

<%-- TODO 2 : liste vide --%>
<c:if test="${empty produits}">
    <p>Aucun produit.</p>
</c:if>

<c:if test="${not empty produits}">
    <table>
        <tr>
            <th>Nom</th>
            <th>Description</th>
            <th>Prix</th>
            <th>Stock</th>
            <th>Actions</th>
        </tr>

            <%-- TODO 3 : une ligne par produit --%>
        <c:forEach var="p" items="${produits}">
            <tr>
                <td><c:out value="${p.nom}"/></td>
                <td><c:out value="${p.description}"/></td>
                <td><fmt:formatNumber value="${p.prix}" minFractionDigits="2" maxFractionDigits="2"
                                      groupingUsed="false"/> MAD
                </td>
                <td class="${p.stock == 0 ? 'stock0' : ''}">${p.stock}</td>
                <td>
                    <a href="${ctx}/produits?action=edit&amp;id=${p.id}">Éditer</a>
                    <form method="post" action="${ctx}/produits?action=delete" style="display:inline"
                          onsubmit="return confirm('Supprimer ce produit ?');">
                        <input type="hidden" name="id" value="${p.id}">
                        <button type="submit">Supprimer</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<a href="${ctx}/dashboard">Retour au tableau de bord</a>
</body>
</html>