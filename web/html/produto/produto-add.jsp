<%-- 
    Document   : tipo-produto-add
    Created on : 06/04/2018, 12:11:26
    Author     : aluno
--%>

<%@page import="servlet.TipoProdutoWS"%>
<%@page import="java.util.List"%>
<%@page import="modelo.TipoProduto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Add Produto</h1>
        <h2><a href="index.html">Menu</a> - <a href="ProdutoWS">Voltar</a></h2>
        <form action="ProdutoWS" method="POST">
            <label>Código</label>
            <input type="text" name="txtCodigo"/> <br>
            <label>Nome</label>
            <input type="text" name="txtNome"/> <br>
            <label>Preço</label>
            <input type="text" name="txtPreco"/> <br>
            <label>Tipo</label>
            <select name="txtTipo">
                <% 
                    List<TipoProduto> lista = TipoProdutoWS.listar();
                    for(TipoProduto tp : lista) {
                        out.print("<option value='" + tp.getCodigo()+ "'>" + tp.getNome() + "</option>");
                    }
                %>
            </select> <br>
            <input type="submit" value="Inserir">
        </form>
    </body>
</html>
