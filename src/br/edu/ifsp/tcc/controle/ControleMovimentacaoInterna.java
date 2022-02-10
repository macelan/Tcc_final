/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.tcc.controle;
import br.edu.ifsp.tcc.modelo.MovimentacaoExterna;
import java.io.Serializable;
import javax.persistence.EntityManager;
import br.edu.ifsp.tcc.modelo.MovimentacaoInterna;
import br.edu.ifsp.tcc.modelo.Sentenciado;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author macel
 */
public class ControleMovimentacaoInterna extends DAO<MovimentacaoInterna>{
       public List<MovimentacaoInterna> listarCela(String cela) {
        // criar uma conexao com o banco
        EntityManager em = getEntityManager();
        TypedQuery<MovimentacaoInterna> consulta = em.createNamedQuery("MovimentacaoInterna.buscarPorCela", MovimentacaoInterna.class);
        consulta.setParameter("cela", "%" + cela + "%");
        return consulta.getResultList();
    }
        
        public List<MovimentacaoInterna> listarMatricula(String matricula) {
        // criar uma conexao com o banco
        EntityManager em = getEntityManager();
        TypedQuery<MovimentacaoInterna> consulta  = em.createNamedQuery("MovimentacaoInterna.buscarPorMatricula", MovimentacaoInterna.class);
        consulta.setParameter("matricula", "%" + matricula + "%");
        return consulta.getResultList();
    }
        
        public List<MovimentacaoInterna> listarPavilhao(String nome) {
        // criar uma conexao com o banco
        EntityManager em = getEntityManager();
        TypedQuery<MovimentacaoInterna> consulta  = em.createNamedQuery("MovimentacaoInterna.buscarPorPavilhao", MovimentacaoInterna.class);
        consulta.setParameter("nome", "%" + nome + "%");
        return consulta.getResultList();
    }
}
