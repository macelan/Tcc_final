/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.tcc.controle;

import java.io.Serializable;
import javax.persistence.EntityManager;
import br.edu.ifsp.tcc.modelo.Sentenciado;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author macel
 */
public class ControleSentenciado extends DAO<Sentenciado> {
    
    public List<Sentenciado> listarNome(String nome) {
        // criar uma conexao com o banco
        EntityManager em = getEntityManager();
        TypedQuery<Sentenciado> consulta = em.createNamedQuery("Sentenciado.buscarPorNome", Sentenciado.class);
        consulta.setParameter("nome", "%" + nome + "%");
        return consulta.getResultList();
    }
    
    public List<Sentenciado> listarMatricula(String matricula) {
        // criar uma conexao com o banco
        EntityManager em = getEntityManager();
        TypedQuery<Sentenciado> consulta = em.createNamedQuery("Sentenciado.buscarPorMatricula", Sentenciado.class);
        consulta.setParameter("matricula", "%" + matricula + "%");
        return consulta.getResultList();
    }
    
    public List<Sentenciado> listarRG(String rg) {
        // criar uma conexao com o banco
        EntityManager em = getEntityManager();
        TypedQuery<Sentenciado> consulta = em.createNamedQuery("Sentenciado.buscarPorRG", Sentenciado.class);
        consulta.setParameter("rg", "%" + rg + "%");
        return consulta.getResultList();
    }
    
    public String consultarMatricula(String matricula) {
        // criar uma conexão com o banco - gerente de entidades
        Sentenciado sen = null;
        EntityManager em = getEntityManager();
        TypedQuery<Sentenciado> query = em.createNamedQuery("Sentenciado.consultarMatricula", Sentenciado.class);
        // passando o nome a ser procurado como parâmetro para a query
        query.setParameter("matricula", matricula);
        sen = (Sentenciado) query.getSingleResult();
        System.out.println(sen);
        return sen.getMatricula();
    }

 
}
