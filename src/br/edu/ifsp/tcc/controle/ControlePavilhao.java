/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.tcc.controle;
import java.io.Serializable;
import javax.persistence.EntityManager;
import br.edu.ifsp.tcc.modelo.Pavilhao;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author macel
 */
public class ControlePavilhao extends DAO<Pavilhao>{
    
     public List<Pavilhao> listarNome(String nome) {
        // criar uma conexao com o banco
        EntityManager em = getEntityManager();
        TypedQuery<Pavilhao> consulta = em.createNamedQuery("Pavilhao.buscarPorNome", Pavilhao.class);
        consulta.setParameter("nome", "%" + nome + "%");
        return consulta.getResultList();
    }
        
        public List<Pavilhao> listarSigla(String sigla) {
        // criar uma conexao com o banco
        EntityManager em = getEntityManager();
        TypedQuery<Pavilhao> consulta  = em.createNamedQuery("Pavilhao.buscarPorSigla", Pavilhao.class);
        consulta.setParameter("sigla", "%" + sigla + "%");
        return consulta.getResultList();
    }


}
