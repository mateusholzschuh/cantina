/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.TipoProdutoDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.TipoProduto;

/**
 *
 * @author Mateus
 */
@WebServlet(name = "TipoProdutoWS", urlPatterns = {"/TipoProdutoWS"})
public class TipoProdutoWS extends HttpServlet {

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

        //Handle the functions [UPDATE - DELETE]
        if (request.getParameter("funcao") != null) {
            //DELETE
            if (request.getParameter("funcao").equals("del") && request.getParameter("codigo") != null) {
                //Abre conexão com dao
                TipoProdutoDAO tipodao = new TipoProdutoDAO();
                TipoProduto tipo = tipodao.buscarPorChavePrimaria(Integer.parseInt(request.getParameter("codigo")));
                //Fecha dao
                tipodao.fecharConexao();

                String msg = "Você deletou TipoProduto: { nome : " + tipo.getNome() + ", codigo : " + tipo.getCodigo() + " };";
                request.setAttribute("msg", msg);

                RequestDispatcher destino;
                destino = request.getRequestDispatcher("html/tipoproduto/tipo-produto-del.jsp");
                destino.forward(request, response);
            }

            //UPDATE
            if (request.getParameter("funcao").equals("upd") && request.getParameter("codigo") != null) {
                RequestDispatcher destino;
                destino = request.getRequestDispatcher("html/tipoproduto/tipo-produto-upd.jsp");
                destino.forward(request, response);
            }

            //ADD
            if (request.getParameter("funcao").equals("add")) {
                RequestDispatcher destino;
                destino = request.getRequestDispatcher("html/tipoproduto/tipo-produto-add.jsp");
                destino.forward(request, response);
            }
        } else {

            List<TipoProduto> lista = TipoProdutoWS.listar();
            request.setAttribute("dados", lista);

            RequestDispatcher destino;
            destino = request.getRequestDispatcher("html/tipoproduto/tipo-produto.jsp");
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
        TipoProduto tipo;
        tipo = new TipoProduto(Integer.valueOf(request.getParameter("txtCodigo")), request.getParameter("txtNome"));

        /* Adicionar no banco de dados */
        //Abre conexão
        TipoProdutoDAO dao = new TipoProdutoDAO();
        //Insere tipoProduto
        dao.incluir(tipo);
        //Fecha conexão
        dao.fecharConexao();
        /* -------------------------- */

        String msg = "Você adicionou um novo TipoProduto: { codigo : " + tipo.getCodigo() + ", nome : " + tipo.getNome() + " }";
        request.setAttribute("msg", msg);

        RequestDispatcher destino;
        destino = request.getRequestDispatcher("html/tipoproduto/tipo-produto-add-ok.jsp");
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
    }

    public static List<TipoProduto> listar() {
        //Abre conexão com o banco de dados
        TipoProdutoDAO dao = new TipoProdutoDAO();
        //Faz a listagem dos dados
        List<TipoProduto> lista = dao.listar();
        //Retorna a lista
        return lista;
    }
}
