/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.tcc.controle;


import br.edu.ifsp.tcc.modelo.Funcionario;
import java.io.Serializable;
import javax.persistence.EntityManager;
import br.edu.ifsp.tcc.modelo.Presidio;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author macel
 */
public class ControlePresidio extends DAO<Presidio> {

     public List<Presidio> listarNome(String nome) {
        // criar uma conexao com o banco
        EntityManager em = getEntityManager();
        TypedQuery<Presidio> consulta = em.createNamedQuery("Presidio.buscarPorNome", Presidio.class);
        consulta.setParameter("nome", "%" + nome + "%");
        return consulta.getResultList();
    }
        
        public List<Presidio> listarSigla(String sigla) {
        // criar uma conexao com o banco
        EntityManager em = getEntityManager();
        TypedQuery<Presidio> consulta  = em.createNamedQuery("Presidio.buscarPorSigla", Presidio.class);
        consulta.setParameter("sigla", "%" + sigla + "%");
        return consulta.getResultList();
    }
        public List<Presidio> listarCidade(String cidade) {
        // criar uma conexao com o banco
        EntityManager em = getEntityManager();
        TypedQuery<Presidio> consulta  = em.createNamedQuery("Presidio.buscarPorCidade", Presidio.class);
        consulta.setParameter("cidade", "%" + cidade + "%");
        return consulta.getResultList();
    }
        
        public List<Presidio> listarCoordenadoria(String coordenadoria) {
        // criar uma conexao com o banco
        EntityManager em = getEntityManager();
        TypedQuery<Presidio> consulta  = em.createNamedQuery("Presidio.buscarPorSiglaCoordenadoria", Presidio.class);
        consulta.setParameter("coordenadoria", "%" + coordenadoria + "%");
        return consulta.getResultList();
    }
    
}
