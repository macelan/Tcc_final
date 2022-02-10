/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.tcc.controle;
import br.edu.ifsp.tcc.modelo.*;
import java.io.Serializable;
import javax.persistence.EntityManager;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author macel
 */
public class ControleEventoColetivo extends DAO<EventoColetivo>{
    
        public List<EventoColetivo> listarNome(String nome) {
        // criar uma conexao com o banco
        EntityManager em = getEntityManager();
        TypedQuery<EventoColetivo> consulta = em.createNamedQuery("EventoColetivo.buscarPorNome", EventoColetivo.class);
        consulta.setParameter("nome", "%" + nome + "%");
        return consulta.getResultList();
    }
        
        public List<EventoColetivo> listarRelato(String relato) {
        // criar uma conexao com o banco
        EntityManager em = getEntityManager();
        TypedQuery<EventoColetivo> consulta  = em.createNamedQuery("EventoColetivo.buscarPorRelato", EventoColetivo.class);
        consulta.setParameter("relato", "%" + relato + "%");
        return consulta.getResultList();
    }
}
