/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;


import dao.TipoClienteDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.TipoCliente;

/**
 *
 * @author Mateus
 */
@WebServlet(name = "TipoClienteWS", urlPatterns = {"/TipoClienteWS"})
public class TipoClienteWS extends HttpServlet {

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

        /* Gerencia funções [DELETE - UPDATE - ADD] */
        if (request.getParameter("funcao") != null) {
            //DELETE
            if (request.getParameter("funcao").equals("del") && request.getParameter("codigo") != null) {
                //Abre conexão com dao
                TipoClienteDAO tipodao = new TipoClienteDAO();
                TipoCliente tipo = tipodao.buscarPorChavePrimaria(Integer.parseInt(request.getParameter("codigo")));
                //Fecha conexão com dao
                tipodao.fecharConexao();

                String msg = "Você deletou TipoCliente: { nome : " + tipo.getNome() + ", codigo : " + tipo.getCodigo() + " };";
                request.setAttribute("msg", msg);

                RequestDispatcher destino;
                destino = request.getRequestDispatcher("html/tipocliente/tipo-cliente-del.jsp");
                destino.forward(request, response);
            }

            //UPDATE
            if (request.getParameter("funcao").equals("upd") && request.getParameter("codigo") != null) {
                RequestDispatcher destino;
                destino = request.getRequestDispatcher("html/tipocliente/tipo-cliente-upd.jsp");
                destino.forward(request, response);
            }

            //ADD
            if (request.getParameter("funcao").equals("add")) {
                RequestDispatcher destino;
                destino = request.getRequestDispatcher("html/tipocliente/tipo-cliente-add.jsp");
                destino.forward(request, response);
            }
        } else {

            List<TipoCliente> lista = TipoClienteWS.listar();
            request.setAttribute("dados", lista);

            RequestDispatcher destino;
            destino = request.getRequestDispatcher("html/tipocliente/tipo-cliente.jsp");
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
        TipoCliente tipo;
        tipo = new TipoCliente(Integer.valueOf(request.getParameter("txtCodigo")), request.getParameter("txtNome"));

        /* Adicionar no banco de dados */
        //Abre conexão
        TipoClienteDAO dao = new TipoClienteDAO();
        //Insere tipoCliente
        dao.incluir(tipo);
        //Fecha conexão
        dao.fecharConexao();
        /* -------------------------- */

        String msg = "Você adicionou um novo TipoCliente: { codigo : " + tipo.getCodigo() + ", nome : " + tipo.getNome() + " }";
        request.setAttribute("msg", msg);

        RequestDispatcher destino;
        destino = request.getRequestDispatcher("html/tipocliente/tipo-cliente-add-ok.jsp");
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

    public static List<TipoCliente> listar() {
        //Conecta com o banco de dados
        TipoClienteDAO dao = new TipoClienteDAO();
        //Faz a listagem
        List<TipoCliente> lista = dao.listar();
        //Fecha conexão com o banco
        dao.fecharConexao();
        //Retorna a lista
        return lista;
    }
}
