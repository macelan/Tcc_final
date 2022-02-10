/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.tcc.controle;
import br.edu.ifsp.tcc.modelo.Observacao;
import java.io.Serializable;
import javax.persistence.EntityManager;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author macel
 */
public class ControleObservacao extends DAO<Observacao>{
        public List<Observacao> listarMatricula(String matricula) {
        // criar uma conexao com o banco
        EntityManager em = getEntityManager();
        TypedQuery<Observacao> consulta = em.createNamedQuery("Observacao.buscarPorMatricula", Observacao.class);
        consulta.setParameter("nome", "%" + matricula + "%");
        return consulta.getResultList();
    }
        
        public List<Observacao> listarRelato(String relato) {
        // criar uma conexao com o banco
        EntityManager em = getEntityManager();
        TypedQuery<Observacao> consulta  = em.createNamedQuery("Observacao.buscarPorRelato", Observacao.class);
        consulta.setParameter("relato", "%" + relato + "%");
        return consulta.getResultList();
    }

}
