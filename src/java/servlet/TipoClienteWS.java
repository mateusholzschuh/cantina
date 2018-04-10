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
        
        //Handle the functions [UPDATE - DELETE - ADD]
        if(request.getParameter("funcao") != null) {
            //DELETE
            if(request.getParameter("funcao").equals("del") && request.getParameter("codigo") != null) {
                TipoCliente tipo = TipoClienteWS.listar().get(Integer.valueOf(request.getParameter("codigo")));
                
                String msg = "Você deletou TipoCliente: { nome : " + tipo.getNome() + ", codigo : " + tipo.getCodigo() + " };";
                request.setAttribute("msg", msg);
                
                RequestDispatcher destino;
                destino = request.getRequestDispatcher("html/tipocliente/tipo-cliente-del.jsp");
                destino.forward(request, response);
            }
            //UPDATE
            if(request.getParameter("funcao").equals("upd") && request.getParameter("codigo") != null){
                RequestDispatcher destino;
                destino = request.getRequestDispatcher("html/tipocliente/tipo-cliente-upd.jsp");
                destino.forward(request, response);
            }
            //ADD
            if(request.getParameter("funcao").equals("add")){
                RequestDispatcher destino;
                destino = request.getRequestDispatcher("html/tipocliente/tipo-cliente-add.jsp");
                destino.forward(request, response);
            }
        }
        else{
        
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
        
        String msg = "Você adicionou um novo TipoCliente: { codigo : " + tipo.getCodigo() + ", nome : " + tipo.getNome() +" }";
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
        List<TipoCliente> lista = new ArrayList<>();
        TipoCliente tipo;
        
        tipo = new TipoCliente(0, "Aluno");
        lista.add(tipo);
        
        tipo = new TipoCliente(1, "Professor");
        lista.add(tipo);
        
        tipo = new TipoCliente(2, "TAD");
        lista.add(tipo);
        
        tipo = new TipoCliente(3, "Externo");
        lista.add(tipo);
        
        return lista;
    }
}
