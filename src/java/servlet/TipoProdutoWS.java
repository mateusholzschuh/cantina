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
        if(request.getParameter("funcao") != null) {
            //DELETE
            if(request.getParameter("funcao").equals("del") && request.getParameter("codigo") != null) {
                TipoProduto tipo = TipoProdutoWS.listar().get(Integer.valueOf(request.getParameter("codigo")));
                
                String msg = "Você deletou TipoProduto: { nome : " + tipo.getNome() + ", codigo : " + tipo.getCodigo() + " };";
                request.setAttribute("msg", msg);
                
                RequestDispatcher destino;
                destino = request.getRequestDispatcher("html/tipoproduto/tipo-produto-del.jsp");
                destino.forward(request, response);
            }
            //UPDATE
            if(request.getParameter("funcao").equals("upd") && request.getParameter("codigo") != null){
                RequestDispatcher destino;
                destino = request.getRequestDispatcher("html/tipoproduto/tipo-produto-upd.jsp");
                destino.forward(request, response);
            }
            //ADD
            if(request.getParameter("funcao").equals("add")){
                RequestDispatcher destino;
                destino = request.getRequestDispatcher("html/tipoproduto/tipo-produto-add.jsp");
                destino.forward(request, response);
            }
        }
        else{
        
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
        
        String msg = "Você adicionou um novo TipoProduto: { codigo : " + tipo.getCodigo() + ", nome : " + tipo.getNome() +" }";
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
        List<TipoProduto> lista = new ArrayList<>();
        TipoProduto tipo;
        
        tipo = new TipoProduto(0, "Bebida");
        lista.add(tipo);
        
        tipo = new TipoProduto(1, "Salgado");
        lista.add(tipo);
        
        tipo = new TipoProduto(2, "Doces");
        lista.add(tipo);
        
        tipo = new TipoProduto(3, "Almoço");
        lista.add(tipo);
                
        return lista;
    }
}
