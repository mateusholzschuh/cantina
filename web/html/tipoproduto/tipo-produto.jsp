<%-- 
    Document   : tipo-produto
    Created on : Apr 9, 2018, 6:26:27 PM
    Author     : Mateus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>TipoProduto</h1>
        <h2>
            <a href="TipoProdutoWS?funcao=add">Novo</a> - 
            <a href="index.html">Menu</a>
        </h2>
        <table border="1">
            <thead>
                <tr>
                    <th>Código</th>
                    <th>Nome</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${dados}" var="obj">
                <tr>
                    <td>${obj.codigo}</td>
                    <td>${obj.nome}</td>
                    <td>
                        <a href="TipoProdutoWS?codigo=${obj.codigo}&funcao=upd">Alterar</a>
                        <a href="TipoProdutoWS?codigo=${obj.codigo}&funcao=del">Deletar</a>
                    </td>
                </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>
