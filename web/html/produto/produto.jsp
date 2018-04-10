<%-- 
    Document   : tipo-produto
    Created on : 06/04/2018, 12:10:14
    Author     : aluno
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
        <h1>Produto</h1>
        <h2>
            <a href="ProdutoWS?funcao=add">Novo</a> - 
            <a href="index.html">Menu</a>
        </h2>
        <table border="1">
            <thead>
                <tr>
                    <th>Código</th>
                    <th>Nome</th>
                    <th>Preço</th>
                    <th>Tipo</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${dados}" var="obj">
                <tr>
                    <td>${obj.codigo}</td>
                    <td>${obj.nome}</td>
                    <td>${obj.preco}</td>
                    <td>${obj.tipo.nome}</td>
                    <td>
                        <a href="ProdutoWS?codigo=${obj.codigo}&funcao=upd">Alterar</a>
                        <a href="ProdutoWS?codigo=${obj.codigo}&funcao=del">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </body>
</html>
