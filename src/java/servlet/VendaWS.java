/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
        
        //Handle the functions [UPDATE - DELETE]
        if(request.getParameter("funcao") != null) {
            //DELETE
            if(request.getParameter("funcao").equals("del") && request.getParameter("codigo") != null) {
                Venda venda = VendaWS.listar().get(Integer.valueOf(request.getParameter("codigo")));
                
                String msg = "Você deletou uma venda: "
                + "{ codigo : " + venda.getCodigo() + 
                ", data : " + venda.getData() + 
                ", produto : " + venda.getProduto().getNome() + 
                ", quantidade : " + venda.getQuantidade() + 
                ", cliente : " + venda.getCliente().getNome() + "}";
                request.setAttribute("msg", msg);
                
                RequestDispatcher destino;
                destino = request.getRequestDispatcher("html/venda/venda-del.jsp");
                destino.forward(request, response);
            }
            //UPDATE
            if(request.getParameter("funcao").equals("upd") && request.getParameter("codigo") != null){
                RequestDispatcher destino;
                destino = request.getRequestDispatcher("html/venda/venda-upd.jsp");
                destino.forward(request, response);
            }
            //ADD
            if(request.getParameter("funcao").equals("add")){
                RequestDispatcher destino;
                destino = request.getRequestDispatcher("html/venda/venda-add.jsp");
                destino.forward(request, response);
            }
        }
        else{
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
        Venda venda;        
        venda = new Venda(
                Integer.valueOf(request.getParameter("txtCodigo")), 
                request.getParameter("txtData"), 
                ProdutoWS.listar().get(Integer.valueOf(request.getParameter("txtProduto"))), 
                Integer.valueOf(request.getParameter("txtQuantidade")), 
                ClienteWS.listar().get(Integer.valueOf(request.getParameter("txtCliente")))
        );
        
        String msg = "Você adicionou uma nova venda: "
                + "{ codigo : " + venda.getCodigo() + 
                ", data : " + venda.getData() + 
                ", produto : " + venda.getProduto().getNome() + 
                ", quantidade : " + venda.getQuantidade() + 
                ", cliente : " + venda.getCliente().getNome() + "}";
        
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
        List<Venda> lista = new ArrayList<>();
        Venda venda;
        
        venda = new Venda(0, "12/04/2018", ProdutoWS.listar().get(3), 2, ClienteWS.listar().get(4)); 
        lista.add(venda);
        
        venda = new Venda(1, "12/04/2018", ProdutoWS.listar().get(3), 2, ClienteWS.listar().get(2)); 
        lista.add(venda);
        
        venda = new Venda(2, "12/04/2018", ProdutoWS.listar().get(4), 2, ClienteWS.listar().get(3)); 
        lista.add(venda);
        
        venda = new Venda(3, "15/04/2018", ProdutoWS.listar().get(5), 2, ClienteWS.listar().get(2)); 
        lista.add(venda);
        
        venda = new Venda(4, "16/04/2018", ProdutoWS.listar().get(2), 2, ClienteWS.listar().get(1));
        lista.add(venda);
        
        venda = new Venda(5, "16/04/2018", ProdutoWS.listar().get(1), 2, ClienteWS.listar().get(3));
        lista.add(venda);
        
        venda = new Venda(6, "17/04/2018", ProdutoWS.listar().get(0), 2, ClienteWS.listar().get(4));
        lista.add(venda);
        
        venda = new Venda(7, "18/04/2018", ProdutoWS.listar().get(0), 2, ClienteWS.listar().get(5));
        lista.add(venda);
        
        venda = new Venda(8, "19/04/2018", ProdutoWS.listar().get(1), 2, ClienteWS.listar().get(0));
        lista.add(venda);
        
        venda = new Venda(9, "19/04/2018", ProdutoWS.listar().get(2), 2, ClienteWS.listar().get(1));
        lista.add(venda);
        
        venda = new Venda(10, "20/04/2018", ProdutoWS.listar().get(3), 2, ClienteWS.listar().get(2));
        lista.add(venda);
        
        return lista;
    }
}
