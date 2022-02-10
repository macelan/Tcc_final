/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.tcc.controle;

import br.edu.ifsp.tcc.modelo.Sentenciado;
import br.edu.ifsp.tcc.modelo.SentenciadoDaSindicancia;
import br.edu.ifsp.tcc.pk.sindicancia_sentenciadoId;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Programar
 */
public class ControleSentenciadoDaSindicancia extends DAO<ControleSentenciadoDaSindicancia>{
 
    public List<Sentenciado> listarMatricula(String matricula) {
        // criar uma conexao com o banco
        EntityManager em = getEntityManager();
        TypedQuery<Sentenciado> consulta  = em.createNamedQuery("Sentenciado.buscarPorMatricula", Sentenciado.class);
        consulta.setParameter("matricula", "%" + matricula + "%");
        return consulta.getResultList();
    }
    
}
