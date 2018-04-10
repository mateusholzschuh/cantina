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
        //Handle the functions [UPDATE - DELETE - ADD]
        if(request.getParameter("funcao") != null) {
            //DELETE
            if(request.getParameter("funcao").equals("del") && request.getParameter("codigo") != null) {
                Cliente cliente = ClienteWS.listar().get(Integer.valueOf(request.getParameter("codigo")));
                
                String msg = "Você deletou Cliente: { nome : " + cliente.getNome() + ", codigo : " + cliente.getCodigo() + ", tipo : " + cliente.getTipo().getNome() +" };";
                request.setAttribute("msg", msg);
                
                RequestDispatcher destino;
                destino = request.getRequestDispatcher("html/cliente/cliente-del.jsp");
                destino.forward(request, response);
            }
            //UPDATE
            if(request.getParameter("funcao").equals("upd") && request.getParameter("codigo") != null){
                RequestDispatcher destino;
                destino = request.getRequestDispatcher("html/cliente/cliente-upd.jsp");
                destino.forward(request, response);
            }
            //ADD
            if(request.getParameter("funcao").equals("add")){
                RequestDispatcher destino;
                destino = request.getRequestDispatcher("html/cliente/cliente-add.jsp");
                destino.forward(request, response);
            }
        }
        else{        
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
        Cliente cliente;        
        cliente = new Cliente(
                Integer.valueOf(request.getParameter("txtCodigo")), 
                request.getParameter("txtNome"), 
                TipoClienteWS.listar().get(Integer.valueOf(request.getParameter("txtTipo")))
        );
        
        String msg = "Você adicionou um novo Cliente: { codigo : " + 
                cliente.getCodigo() + ", nome : " + cliente.getNome() +", tipo : " + cliente.getTipo().getNome() + " };";
        
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
        List<Cliente> lista = new ArrayList<>();
        Cliente cliente;
        
        cliente = new Cliente(0, "Pedro", TipoClienteWS.listar().get(0)); //Aluno
        lista.add(cliente);
        
        cliente = new Cliente(1, "João", TipoClienteWS.listar().get(0)); //Aluno
        lista.add(cliente);
        
        cliente = new Cliente(2, "Marcelo", TipoClienteWS.listar().get(1)); //Professor
        lista.add(cliente);
        
        cliente = new Cliente(3, "Aline", TipoClienteWS.listar().get(1)); //Professor
        lista.add(cliente);
        
        cliente = new Cliente(4, "Josi", TipoClienteWS.listar().get(3)); //TAD
        lista.add(cliente);
        
        cliente = new Cliente(5, "Marcio", TipoClienteWS.listar().get(2)); //Externo
        lista.add(cliente);
        
        return lista;
    }
}
