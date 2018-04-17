/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.ProdutoDAO;
import dao.TipoProdutoDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Produto;

/**
 *
 * @author Mateus
 */
@WebServlet(name = "ProdutoWS", urlPatterns = {"/ProdutoWS"})
public class ProdutoWS extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /* Gerencia as funções [DELETE - UPDATE - ADD] */
        if (request.getParameter("funcao") != null) {
            //DELETE
            if (request.getParameter("funcao").equals("del") && request.getParameter("codigo") != null) {
                //Abre conexão com dao
                ProdutoDAO pdao = new ProdutoDAO();
                Produto produto = pdao.buscarPorChavePrimaria(Integer.parseInt(request.getParameter("codigo")));
                //Fecha conexão com dao
                pdao.fecharConexao();
                
                String msg = "Você deletou Produto: { nome : " + produto.getNome() + ", codigo : " + produto.getCodigo() + " };";
                request.setAttribute("msg", msg);

                RequestDispatcher destino;
                destino = request.getRequestDispatcher("html/produto/produto-del.jsp");
                destino.forward(request, response);
            }

            //UPDATE
            if (request.getParameter("funcao").equals("upd") && request.getParameter("codigo") != null) {
                RequestDispatcher destino;
                destino = request.getRequestDispatcher("html/produto/produto-upd.jsp");
                destino.forward(request, response);
            }

            //ADD
            if (request.getParameter("funcao").equals("add")) {
                RequestDispatcher destino;
                destino = request.getRequestDispatcher("html/produto/produto-add.jsp");
                destino.forward(request, response);
            }
        } else {
            //FAZ A LISTAGEM
            List<Produto> lista = ProdutoWS.listar();
            request.setAttribute("dados", lista);

            RequestDispatcher destino;
            destino = request.getRequestDispatcher("html/produto/produto.jsp");
            destino.forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Abre conexão com o dao
        TipoProdutoDAO tipodao = new TipoProdutoDAO();
        Produto produto;
        produto = new Produto(
                Integer.valueOf(request.getParameter("txtCodigo")),
                request.getParameter("txtNome"),
                Double.valueOf(request.getParameter("txtPreco")),
                tipodao.buscarPorChavePrimaria(Integer.parseInt(request.getParameter("txtTipo")))
        );
        //Fecha conexão com dao
        tipodao.fecharConexao();

        /* Adicionar no banco de dados */
        //Abre conexão
        ProdutoDAO dao = new ProdutoDAO();
        //Insere produto
        dao.incluir(produto);
        //Fecha conexão
        dao.fecharConexao();
        /* -------------------------- */

        String msg = "Você adicionou um novo TipoProduto: { codigo : "
                + produto.getCodigo() + ", nome : " + produto.getNome() + ", preco : "
                + produto.getPreco() + ", tipo : " + produto.getTipo().getNome() + " }";

        request.setAttribute("msg", msg);

        RequestDispatcher destino;
        destino = request.getRequestDispatcher("html/produto/produto-add-ok.jsp");
        destino.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public static List<Produto> listar() {
        //Conecta com o banco de dados
        ProdutoDAO dao = new ProdutoDAO();
        //Faz a listagem
        List<Produto> lista = dao.listar();
        //Fecha conexão com o banco
        dao.fecharConexao();
        //Retorna a lista
        return lista;
    }
}
