<%--
  Created by IntelliJ IDEA.
  User: Otmani
  Date: 15/11/2026
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%-- Bonus : theme (cookie "theme" du TP 2). Inclus dans le <head> de chaque page. --%>
<c:set var="bodyClass" scope="request" value="${cookie.theme.value == 'dark' ? 'dark' : ''}"/>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 2rem;
    }

    body.dark {
        background: #1e1e1e;
        color: #eee;
    }

    body.dark a {
        color: #8ab4f8;
    }

    table {
        border-collapse: collapse;
        margin: 1rem 0;
    }

    th, td {
        border: 1px solid #999;
        padding: .4rem .8rem;
        text-align: left;
    }

    .erreur {
        color: #c00;
        font-size: .9rem;
    }

    .flash {
        background: #d4edda;
        color: #155724;
        padding: .6rem 1rem;
        margin: 1rem 0;
        border-radius: 4px;
    }

    .stock0 {
        color: red;
        font-weight: bold;
    }

    label {
        display: block;
        margin-top: .8rem;
    }
</style>