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
public class ControleCoordenadoria extends DAO<Coordenadoria> {

    
    public List<Coordenadoria> listarNome(String nome) {
        // criar uma conexao com o banco
        EntityManager em = getEntityManager();
        TypedQuery<Coordenadoria> consulta = em.createNamedQuery("Coordenadoria.buscarPorNome", Coordenadoria.class);
        consulta.setParameter("nome", "%" + nome + "%");
        return consulta.getResultList();
    }
        
        public List<Coordenadoria> listarTurno(String sigla) {
        // criar uma conexao com o banco
        EntityManager em = getEntityManager();
        TypedQuery<Coordenadoria> consulta  = em.createNamedQuery("Coordenadoria.buscarPorSigla", Coordenadoria.class);
        consulta.setParameter("sigla", "%" + sigla + "%");
        return consulta.getResultList();
    }

   
}
