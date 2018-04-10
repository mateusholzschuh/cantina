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
        <h1>Vendas</h1>
        <h2>
            <a href="VendaWS?funcao=add">Novo</a> - 
            <a href="index.html">Menu</a>
        </h2>
        <table border="1">
            <thead>
                <tr>
                    <th>Código</th>
                    <th>Data</th>
                    <th>Cliente</th>
                    <th>Produto</th>
                    <th>Valor Unitario</th>
                    <th>Quantidade</th>
                    <th>Valor total</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${dados}" var="obj">
                <tr>
                    <td>${obj.codigo}</td>
                    <td>${obj.data}</td>
                    <td>${obj.cliente.nome}</td>
                    <td>${obj.produto.nome}</td>
                    <td>${obj.produto.preco}</td>
                    <td>${obj.quantidade}</td>
                    <td>${obj.getValorTotal()}</td>
                    <td>
                        <a href="VendaWS?codigo=${obj.codigo}&funcao=upd">Alterar</a>
                        <a href="VendaWS?codigo=${obj.codigo}&funcao=del">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </body>
</html>
