/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.tcc.controle;
import br.edu.ifsp.tcc.modelo.Sentenciado;
import br.edu.ifsp.tcc.modelo.Sindicancia;
import java.util.Date;
import javax.persistence.EntityManager;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author macel
 */
public class ControleSindicancia extends DAO<Sindicancia>{
    public List<Sindicancia> listarSindicanciaDataEvento(String dataevento) {
        // criar uma conexao com o banco
        EntityManager em = getEntityManager();
        TypedQuery<Sindicancia> consulta = em.createNamedQuery("Sindicancia.buscarPorDataEvento", Sindicancia.class);
        consulta.setParameter("dataOcorrencia", "%" + dataevento + "%");
        return consulta.getResultList();
    }
    
    public List<Sindicancia> listarSindicanciaDataRegistro(String dataregistro) {
        // criar uma conexao com o banco
        EntityManager em = getEntityManager();
        TypedQuery<Sindicancia> consulta = em.createNamedQuery("Sindicancia.buscarPorDataRegistro", Sindicancia.class);
        consulta.setParameter("dataRegisto", "%" + dataregistro + "%");
        return consulta.getResultList();
    }
    
    public List<Sindicancia> listarSindicanciaRelato(String relato) {
        // criar uma conexao com o banco
        //@NamedQuery(name = "Sindicancia.buscarPorRelato", query = "SELECT s FROM Sindicancia s WHERE s.relato like :parametrorelato"),
        EntityManager em = getEntityManager();
        TypedQuery<Sindicancia> consulta = em.createNamedQuery("Sindicancia.buscarPorRelato", Sindicancia.class);
        consulta.setParameter("parametrorelato", "%" + relato + "%");
        return consulta.getResultList();
    }
    
    public List<Sindicancia> listarSindicanciaPorDataOcorrido(String dataEvento) {
        // criar uma conexao com o banco
        EntityManager em = getEntityManager();
        TypedQuery<Sindicancia> consulta = em.createNamedQuery("Sindicancia.buscarPorDataEvento", Sindicancia.class);
        consulta.setParameter("dataOcorrido", "%" + dataEvento + "%");
        return consulta.getResultList();
    }
    
     public List<Sindicancia> listarSindicanciaPorDataRegistro(Date dataRegistroInicio, Date dataRegistroFinal) {
        // criar uma conexao com o banco
        EntityManager em = getEntityManager();
        TypedQuery<Sindicancia> consulta = em.createNamedQuery("Sindicancia.buscarPorDataRegistro", Sindicancia.class);
        consulta.setParameter("dataRegistroInicial", dataRegistroInicio );
        consulta.setParameter("dataRegistroFinal", dataRegistroFinal );
        return consulta.getResultList();
    }

}

