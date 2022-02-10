/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.tcc.controle;
import br.edu.ifsp.tcc.modelo.EventoColetivo;
import java.io.Serializable;
import javax.persistence.EntityManager;
import br.edu.ifsp.tcc.modelo.MovimentacaoExterna;
import br.edu.ifsp.tcc.modelo.Sentenciado;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author macel
 */
public class ControleMovimentacaoExterna extends DAO<MovimentacaoExterna>{
  
      public List<MovimentacaoExterna> listarPresidio(String presidio) {
        // criar uma conexao com o banco
        EntityManager em = getEntityManager();
        TypedQuery<MovimentacaoExterna> consulta = em.createNamedQuery("MovimentacaoExterna.buscarPorPresidio", MovimentacaoExterna.class);
        consulta.setParameter("presidio", "%" + presidio + "%");
        return consulta.getResultList();
    }
        
        public List<MovimentacaoExterna> listarMatricula(String matricula) {
        // criar uma conexao com o banco
        EntityManager em = getEntityManager();
        TypedQuery<MovimentacaoExterna> consulta  = em.createNamedQuery("MovimentacaoExterna.buscarPorMatricula", MovimentacaoExterna.class);
        consulta.setParameter("matricula", "%" + matricula + "%");
        return consulta.getResultList();
    }
 
}
