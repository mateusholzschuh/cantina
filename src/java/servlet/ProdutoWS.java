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
        //Handle the functions [UPDATE - DELETE - ADD]
        if(request.getParameter("funcao") != null) {
            //DELETE
            if(request.getParameter("funcao").equals("del") && request.getParameter("codigo") != null) {
                Produto prod = ProdutoWS.listar().get(Integer.valueOf(request.getParameter("codigo")));
                
                String msg = "Você deletou Produto: { nome : " + prod.getNome() + ", codigo : " + prod.getCodigo() + " };";
                request.setAttribute("msg", msg);
                
                RequestDispatcher destino;
                destino = request.getRequestDispatcher("html/produto/produto-del.jsp");
                destino.forward(request, response);
            }
            //UPDATE
            if(request.getParameter("funcao").equals("upd") && request.getParameter("codigo") != null){
                RequestDispatcher destino;
                destino = request.getRequestDispatcher("html/produto/produto-upd.jsp");
                destino.forward(request, response);
            }
            //ADD
            if(request.getParameter("funcao").equals("add")){
                RequestDispatcher destino;
                destino = request.getRequestDispatcher("html/produto/produto-add.jsp");
                destino.forward(request, response);
            }
        }
        else{
        
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
        Produto tipo;        
        tipo = new Produto(
                Integer.valueOf(request.getParameter("txtCodigo")), 
                request.getParameter("txtNome"), 
                Double.valueOf(request.getParameter("txtPreco")), 
                TipoProdutoWS.listar().get(Integer.valueOf(request.getParameter("txtTipo")))
        );
        
        String msg = "Você adicionou um novo TipoProduto: { codigo : " + 
                tipo.getCodigo() + ", nome : " + tipo.getNome() +", preco : " + 
                tipo.getPreco() + ", tipo : " + tipo.getTipo().getNome() + " }";
        
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
        List<Produto> lista = new ArrayList<>();
        Produto produto;
        
        produto = new Produto(0, "Fruki Laranja 350ml", 3.50, TipoProdutoWS.listar().get(0)); //Bebida
        lista.add(produto);
        
        produto = new Produto(1, "Coca-cola 350ml", 4.50, TipoProdutoWS.listar().get(0)); //Bebida
        lista.add(produto);
        
        produto = new Produto(2, "Croquete", 4.50, TipoProdutoWS.listar().get(1)); //Salgado
        lista.add(produto);
        
        produto = new Produto(3, "Pastel Folhado", 4.00, TipoProdutoWS.listar().get(1)); //Salgado
        lista.add(produto);
        
        produto = new Produto(4, "Balas", 0.25, TipoProdutoWS.listar().get(2)); //Doce
        lista.add(produto);
        
        produto = new Produto(5, "Almoço (kg)", 28.00, TipoProdutoWS.listar().get(3)); //Almoço
        lista.add(produto);
        
        return lista;
    }
}
