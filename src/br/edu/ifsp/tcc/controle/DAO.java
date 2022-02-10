/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.tcc.controle;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Macelan
 */

public abstract class DAO<T> {

    private EntityManagerFactory emf;
    private Class classe;
    

    public DAO() {
        this.emf = Persistence.createEntityManagerFactory("PersistenciaPU");
        ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();
        this.classe = ((Class) pt.getActualTypeArguments()[0]);
    }

    // Criado automaticamente para ter acesso e gerar o Relatório
    // para tornar o getEntityManager publico;
    public EntityManager getEntityManager() {
        return this.emf.createEntityManager();
    }

    public void inserir(T entity) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
    }

    public void alterar(T entity) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        // insere ou altera
        em.merge(entity);
        em.getTransaction().commit();
        em.close();
    }

    public void remover(T entity) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();

//        em.find(class, primaryKey);
        em.remove(em.merge(entity));
        em.getTransaction().commit();
        em.close();
    }

    public List<T> buscar() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT e FROM " + this.classe.getName() + " e")
                    .getResultList();
        } finally {
            em.close();
        }
    }

}