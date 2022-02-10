/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.tcc.controle;


import java.io.Serializable;
import javax.persistence.EntityManager;
import br.edu.ifsp.tcc.modelo.Faccao;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author macel
 */
public class ControleFaccao extends DAO<Faccao> {

public List<Faccao> listarNome(String nome) {
        // criar uma conexao com o banco
        EntityManager em = getEntityManager();
        TypedQuery<Faccao> consulta = em.createNamedQuery("Faccao.buscarPorNome", Faccao.class);
        consulta.setParameter("nome", "%" + nome + "%");
        return consulta.getResultList();
    }
        
        public List<Faccao> listarSigla(String sigla) {
        // criar uma conexao com o banco
        EntityManager em = getEntityManager();
        TypedQuery<Faccao> consulta  = em.createNamedQuery("Faccao.buscarPorSigla", Faccao.class);
        consulta.setParameter("sigla", "%" + sigla + "%");
        return consulta.getResultList();
    }
}
