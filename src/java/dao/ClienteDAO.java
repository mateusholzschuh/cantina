/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.Cliente;

/**
 *
 * @author Mateus
 */
public class ClienteDAO {

    protected EntityManager em;

    public ClienteDAO() {
        EntityManagerFactory emf;
        try {
            emf = Conexao.getConexao();
            em = emf.createEntityManager();
        } catch (Exception ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger("Não foi possível realizar a conexão com a unidade de persistência. Verifique a conexão");

        }

    }

    public Boolean incluir(Cliente obj) {
        Boolean retorno;
        try {
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
            retorno = true;
        } catch (RuntimeException e) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, e);
            Logger.getLogger("Erro ao incluir, veja o código acima");

            em.getTransaction().rollback();

            retorno = false;
            throw e;
        } finally {
            //em.close();

        }
        return retorno;
    }

    public Boolean alterar(Cliente obj) {
        Boolean retorno;
        try {
            em.getTransaction().begin();
            em.merge(obj);
            em.getTransaction().commit();
            retorno = true;
        } catch (RuntimeException e) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, e);
            Logger.getLogger("Erro ao alterar, veja o código acima");
            em.getTransaction().rollback();
            retorno = false;
            throw e;
        } finally {
            // em.close();
        }
        return retorno;
    }

    public Boolean excluir(Cliente obj) {
        Boolean retorno;
        try {
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
            retorno = true;
        } catch (RuntimeException e) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, e);
            Logger.getLogger("Erro ao excluir, veja o código acima");

            em.getTransaction().rollback();
            retorno = false;
        } finally {
            //em.close();
        }
        return retorno;
    }

    public List<Cliente> listar() {
        return em.createQuery("Select c from Cliente c").getResultList();
    }

    public List<Cliente> listar(String filtro) throws Exception {
        return em.createNamedQuery("Categoria.findFilter").setParameter("filtro", "%" + filtro.toUpperCase() + "%").getResultList();
    }

    public Cliente buscarPorChavePrimaria(Integer chaveprimaria) {
        return em.find(Cliente.class, chaveprimaria);
    }

    public void fecharConexao() {
        Conexao.closeConexao();
    }

}
