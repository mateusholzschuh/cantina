<%-- 
    Document   : tipo-produto-add
    Created on : 06/04/2018, 12:11:26
    Author     : aluno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Add TipoProduto</h1>
        <h2><a href="index.html">Menu</a> - <a href="TipoProdutoWS">Voltar</a></h2>
        <form action="TipoProdutoWS" method="POST">
            <label title="Não é necessário, banco adiciona automaticamente"><s>Código</s></label>
            <input type="hidden" name="txtCodigo" value="1"/> <br>
            <label>Nome</label>
            <input type="text" name="txtNome"/> <br>
            <input type="submit" value="Inserir">
        </form>
    </body>
</html>
