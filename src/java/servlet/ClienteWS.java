/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.ClienteDAO;
import dao.TipoClienteDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cliente;

/**
 *
 * @author Mateus
 */
@WebServlet(name = "ClienteWS", urlPatterns = {"/ClienteWS"})
public class ClienteWS extends HttpServlet {

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
                ClienteDAO dao = new ClienteDAO();
                Cliente cliente = dao.buscarPorChavePrimaria(Integer.parseInt(request.getParameter("codigo")));
                //Fecha dao
                dao.fecharConexao();
                
                String msg = "Você deletou Cliente: { nome : " + cliente.getNome() + ", codigo : " + cliente.getCodigo() + ", tipo : " + cliente.getTipo().getNome() + " };";
                request.setAttribute("msg", msg);

                RequestDispatcher destino;
                destino = request.getRequestDispatcher("html/cliente/cliente-del.jsp");
                destino.forward(request, response);
            }

            //UPDATE
            if (request.getParameter("funcao").equals("upd") && request.getParameter("codigo") != null) {
                RequestDispatcher destino;
                destino = request.getRequestDispatcher("html/cliente/cliente-upd.jsp");
                destino.forward(request, response);
            }

            //ADD
            if (request.getParameter("funcao").equals("add")) {
                RequestDispatcher destino;
                destino = request.getRequestDispatcher("html/cliente/cliente-add.jsp");
                destino.forward(request, response);
            }
        } else {
            //Cria uma lista com os dados resgatados do banco de dados
            List<Cliente> lista = ClienteWS.listar();
            request.setAttribute("dados", lista);

            RequestDispatcher destino;
            destino = request.getRequestDispatcher("html/cliente/cliente.jsp");
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
        //Abre conexão com dao
        TipoClienteDAO tipodao = new TipoClienteDAO();
        Cliente cliente;
        cliente = new Cliente(
                Integer.valueOf(request.getParameter("txtCodigo")),
                request.getParameter("txtNome"),
                tipodao.buscarPorChavePrimaria(Integer.parseInt(request.getParameter("txtTipo")))
        );
        //Fecha dao
        tipodao.fecharConexao();

        /* Adicionar no banco de dados */
        //Abre conexão
        ClienteDAO dao = new ClienteDAO();
        //Insere cliente
        dao.incluir(cliente);
        //Fecha conexão
        dao.fecharConexao();
        /* -------------------------- */

        String msg = "Você adicionou um novo Cliente: { codigo : "
                + cliente.getCodigo() + ", nome : " + cliente.getNome() + ", tipo : " + cliente.getTipo().getNome() + " };";

        request.setAttribute("msg", msg);

        RequestDispatcher destino;
        destino = request.getRequestDispatcher("html/cliente/cliente-add-ok.jsp");
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

    public static List<Cliente> listar() {
        //Conecta com o banco de dados
        ClienteDAO dao = new ClienteDAO();
        //Faz a listagem
        List<Cliente> lista = dao.listar();
        //Fecha conexão com o banco
        dao.fecharConexao();
        //Retorna a lista
        return lista;
    }
}
