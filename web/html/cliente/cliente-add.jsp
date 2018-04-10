<%-- 
    Document   : tipo-produto-add
    Created on : 06/04/2018, 12:11:26
    Author     : aluno
--%>

<%@page import="servlet.TipoClienteWS"%>
<%@page import="modelo.TipoCliente"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Add cliente</h1>
        <h2><a href="index.html">Menu</a> - <a href="ClienteWS">Voltar</a></h2>
        <form action="ClienteWS" method="POST">
            <label>Código</label>
            <input type="text" name="txtCodigo"/> <br>
            <label>Nome</label>
            <input type="text" name="txtNome"/> <br>
            <label>Tipo</label>
            <select name="txtTipo">
            <%
                List<TipoCliente> lista = TipoClienteWS.listar();
                for(TipoCliente tc : lista) {
                    out.print("<option value='" + tc.getCodigo() + "'>" + tc.getNome() + "</option>");
                }
            %>
            </select><br>
            <input type="submit" value="Inserir">
        </form>
    </body>
</html>
