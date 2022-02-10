/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.tcc.controle;

import br.edu.ifsp.tcc.modelo.Cela;
import br.edu.ifsp.tcc.modelo.Faccao;
import java.io.Serializable;
import javax.persistence.EntityManager;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.swing.JComboBox;

/**
 *
 * @author macel
 */
public class ControleCela extends DAO<Cela> {

    /**
     * METODO LOCALIZA UMA CELA NO BANCO DE DADOS VERIFICANDO A CELA E O
     * PAVILHÃO E O RETORNA CASO ENCONTRADO
     *
     * @param cela
     * @param pavilhao
     * @return
     */
    public Cela confirmaInsercaoCela(Integer parametro1, Integer parametro2) {
        // Cela a ser retornado
        Cela cela = null;
        // criar uma conexão com o banco - gerente de entidades
        try {
            EntityManager em = getEntityManager();
            TypedQuery<Cela> query = em.createNamedQuery("Cela.confirmaInsercaoCela", Cela.class);
            // passando o nome a ser procurado como parâmetro para a query
            query.setParameter("p1", parametro1); //("p1" -> igual variavel no modelo em verde) ** (parametro1 -> veio do metodo)
            query.setParameter("p2", parametro2); //("p2" ->  -> igual variavel no modelo em verde) ** (parametro2) -> veio do metodo)
            cela = (Cela) query.getSingleResult();
            return cela;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

//    public Cela verificarCela(Integer parametro) {
//
//        Cela cela = null;
//
//        try {
//            EntityManager em = getEntityManager();
////            TypedQuery<Cela> query = em.createNamedQuery("Cela.confirmaInsercaoCela", Cela.class);
//            TypedQuery<Cela> query = em.createQuery("From Cela c where c.numeroCela =:pr", Cela.class);
//            // passando o nome a ser procurado como parâmetro para a query
//            query.setParameter("pr", parametro); //("p1" -> igual variavel no modelo em verde) ** (parametro1 -> veio do metodo)
//            cela = (Cela) query.getSingleResult();
//            return cela;
//        } catch (Exception e) {
//            System.out.println(e);
//            return null;
//        }
//    }

}
