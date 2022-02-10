/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.tcc.controle;

import java.io.Serializable;
import javax.persistence.EntityManager;
import br.edu.ifsp.tcc.modelo.Funcionario;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author macel
 */
public class ControleFuncionario extends DAO<Funcionario> {

    public List<Funcionario> listarNome(String nome) {
        // criar uma conexao com o banco
        EntityManager em = getEntityManager();
        TypedQuery<Funcionario> consulta = em.createNamedQuery("Funcionario.buscarPorNome", Funcionario.class);
        consulta.setParameter("nome", "%" + nome + "%");
        return consulta.getResultList();
    }

    public List<Funcionario> listarRg(String rg) {
        // criar uma conexao com o banco
        EntityManager em = getEntityManager();
        TypedQuery<Funcionario> consulta = em.createNamedQuery("Funcionario.buscarPorRg", Funcionario.class);
        consulta.setParameter("rg", "%" + rg + "%");
        return consulta.getResultList();
    }

    public List<Funcionario> listarPresidio(String presidio) {
        // criar uma conexao com o banco
        EntityManager em = getEntityManager();
        TypedQuery<Funcionario> consulta = em.createNamedQuery("Funcionario.buscarPorPresidio", Funcionario.class);
        consulta.setParameter("presidio", "%" + presidio + "%");
        return consulta.getResultList();
    }

    public boolean confirmaRg(String rg) {
        //  lista de usuários a ser retornada
        List<Funcionario> lista;
        // criar uma conexão com o banco - gerente de entidades
        EntityManager em = getEntityManager();
        // Criar um identificar para uma consulta SQL
        TypedQuery<Funcionario> consulta;

        // criar a consulta 
        consulta = (TypedQuery<Funcionario>) em.createQuery("SELECT u FROM Funcionario u WHERE u.rg = :rg");

        // passando o nome a ser procurado como parâmetro para a query
        consulta.setParameter("rg", rg);

        // pegar o resultado da consulta realizada
        try {
            lista = consulta.getResultList();
            if (lista.size() > 0) {
                return true;
            }
        } catch (NoResultException e) {
            return false;
        }
        return false;
    }

     /**
     * METODO LOCALIZA UM USUARIO NO BANCO DE DADOS VERIFICANDO A SENHA E O
     * LOGIN E O RETORNA CASO ENCONTRADO
     *
     * @param login
     * @param senha
     * @return
     */
    
    public Funcionario confirmaLoginSenha(String usuario, String senha) {
        // Uusario a ser retornado
        Funcionario funcionario = null;
        // criar uma conexão com o banco - gerente de entidades
        EntityManager em = getEntityManager();
        TypedQuery<Funcionario> query = em.createNamedQuery("Funcionario.confirmaLoginSenha", Funcionario.class);
        // passando o nome a ser procurado como parâmetro para a query
        query.setParameter("usuario", usuario);
        query.setParameter("senha", senha);
        return query.getSingleResult();
    }

     
//    public boolean confirmaLogin(String usuario) {
//        // Uusario a ser retornado
//        Funcionario funcionario = null;
//        // criar uma conexão com o banco - gerente de entidades
//        EntityManager em = getEntityManager();
//        TypedQuery<Funcionario> query = em.createNamedQuery("Funcionario.confirmaLogin", Funcionario.class);
//        // passando o nome a ser procurado como parâmetro para a query
//        query.setParameter("usuario", usuario);
//        //return query.getSingleResult
//               
//        try {
//            funcionario = query.getSingleResult().getUsuario();
//            if (funcionario != null) {
//                return true;
//            }
//        } catch (NoResultException e) {
//            return false;
//        }
//        return false;
//    }
    
    public boolean confirmaLogin(String usuario) {
       //  lista de usuários a ser retornada
        List<Funcionario> lista;
        // criar uma conexão com o banco - gerente de entidades
        EntityManager em = getEntityManager();
        // Criar um identificar para uma consulta SQL
        TypedQuery<Funcionario> consulta;

        // criar a consulta 
        consulta = (TypedQuery<Funcionario>) em.createQuery("SELECT u FROM Funcionario u WHERE u.usuario = :usuario");

        // passando o nome a ser procurado como parâmetro para a query
        consulta.setParameter("usuario", usuario);

        // pegar o resultado da consulta realizada
        try {
            lista = consulta.getResultList();
            if (lista.size() > 0) {
                return true;
            }
        } catch (NoResultException e) {
            return false;
        }
        return false;
    }
}
