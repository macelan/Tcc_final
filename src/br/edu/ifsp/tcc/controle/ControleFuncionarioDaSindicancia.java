/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.tcc.controle;

import br.edu.ifsp.tcc.modelo.Funcionario;
import br.edu.ifsp.tcc.modelo.Sindicancia;
import br.edu.ifsp.tcc.pk.sindicancia_funcionarioId;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Programar
 */
public class ControleFuncionarioDaSindicancia extends DAO<sindicancia_funcionarioId> {

   public List<Funcionario> listarNome(String nome) {
        // criar uma conexao com o banco
        EntityManager em = getEntityManager();
        TypedQuery<Funcionario> consulta = em.createNamedQuery("Funcionario.buscarPorNome", Funcionario.class);
        consulta.setParameter("nome", "%" + nome + "%");
        return consulta.getResultList();
    }
    
}
