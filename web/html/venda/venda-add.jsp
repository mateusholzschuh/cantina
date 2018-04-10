<%-- 
    Document   : tipo-produto-add
    Created on : 06/04/2018, 12:11:26
    Author     : aluno
--%>

<%@page import="servlet.ClienteWS"%>
<%@page import="modelo.Cliente"%>
<%@page import="servlet.ProdutoWS"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Add venda</h1>
        <h2><a href="index.html">Menu</a> - <a href="VendaWS">Voltar</a></h2>
        <form action="VendaWS" method="POST">
            <label>Código</label>
            <input type="text" name="txtCodigo"/> <br>
            <label>Data</label>
            <input type="text" name="txtData"/> <br>
            <label>Produto</label>
            <select name="txtProduto">
                <%
                    List<Produto> lista = ProdutoWS.listar();
                    
                    for(Produto p : lista) {
                        out.print("<option value='" + p.getCodigo() + "'>" + p.getNome() + "</option>");
                    }
                %>
            </select><br>
            <label>Quantidade</label>
            <input type="text" name="txtQuantidade"/> <br>
            <label>Cliente</label>
            <select name="txtCliente">            
                <%
                    List<Cliente> clientes = ClienteWS.listar();
                    
                    for(Cliente c : clientes) {
                        out.print("<option value='" + c.getCodigo() + "'>" + c.getNome() + "</option>");
                    }
                %>
            </select><br>
            <input type="submit" value="Inserir">
        </form>
    </body>
</html>
