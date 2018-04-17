/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.ClienteDAO;
import dao.ProdutoDAO;
import dao.VendaDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Venda;

/**
 *
 * @author Mateus
 */
@WebServlet(name = "VendaWS", urlPatterns = {"/VendaWS"})
public class VendaWS extends HttpServlet {

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
                VendaDAO dao = new VendaDAO();
                Venda venda = dao.buscarPorChavePrimaria(Integer.parseInt(request.getParameter("codigo")));
                //Fecha dao
                dao.fecharConexao();

                String msg = "Você deletou uma venda: "
                        + "{ codigo : " + venda.getCodigo()
                        + ", data : " + venda.getData()
                        + ", produto : " + venda.getProduto().getNome()
                        + ", quantidade : " + venda.getQuantidade()
                        + ", cliente : " + venda.getCliente().getNome() + "}";
                request.setAttribute("msg", msg);

                RequestDispatcher destino;
                destino = request.getRequestDispatcher("html/venda/venda-del.jsp");
                destino.forward(request, response);
            }

            //UPDATE
            if (request.getParameter("funcao").equals("upd") && request.getParameter("codigo") != null) {
                RequestDispatcher destino;
                destino = request.getRequestDispatcher("html/venda/venda-upd.jsp");
                destino.forward(request, response);
            }

            //ADD
            if (request.getParameter("funcao").equals("add")) {
                RequestDispatcher destino;
                destino = request.getRequestDispatcher("html/venda/venda-add.jsp");
                destino.forward(request, response);
            }
        } else {
            List<Venda> lista = VendaWS.listar();
            request.setAttribute("dados", lista);

            RequestDispatcher destino;
            destino = request.getRequestDispatcher("html/venda/venda.jsp");
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
        //Abre conexão com daos
        ProdutoDAO pdao = new ProdutoDAO();
        ClienteDAO cdao = new ClienteDAO();
        Venda venda;
        venda = new Venda(
                Integer.valueOf(request.getParameter("txtCodigo")),
                request.getParameter("txtData"),
                pdao.buscarPorChavePrimaria(Integer.parseInt(request.getParameter("txtProduto"))),
                Integer.valueOf(request.getParameter("txtQuantidade")),
                cdao.buscarPorChavePrimaria(Integer.parseInt(request.getParameter("txtCliente")))
        );
        //Fecha conexão com daos
        pdao.fecharConexao();
        cdao.fecharConexao();

        /* Adicionar no banco de dados */
        //Abre conexão
        VendaDAO dao = new VendaDAO();
        //Insere venda
        dao.incluir(venda);
        //Fecha conexão
        dao.fecharConexao();
        /* -------------------------- */

        String msg = "Você adicionou uma nova venda: "
                + "{ codigo : " + venda.getCodigo()
                + ", data : " + venda.getData()
                + ", produto : " + venda.getProduto().getNome()
                + ", quantidade : " + venda.getQuantidade()
                + ", cliente : " + venda.getCliente().getNome() + "}";

        request.setAttribute("msg", msg);

        RequestDispatcher destino;
        destino = request.getRequestDispatcher("html/venda/venda-add-ok.jsp");
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

    public static List<Venda> listar() {
        //Abre conexão com o banco de dados
        VendaDAO dao = new VendaDAO();
        //Faz a listagem dos itens
        List<Venda> lista = dao.listar();
        //Retorna a lista
        return lista;
    }
}
