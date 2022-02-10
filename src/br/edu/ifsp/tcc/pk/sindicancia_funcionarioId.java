/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.tcc.pk;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Programar
 */
@Embeddable
public class sindicancia_funcionarioId implements Serializable{
    
    
    @Column(name = "funcionario_id")
    private Integer funcionario_id;
    @Column(name = "sindicancia_id")
    private Integer sindicancia_id;

    public sindicancia_funcionarioId() {
    }

    public Integer getFuncionario_id() {
        return funcionario_id;
    }

    public void setFuncionario_id(Integer funcionario_id) {
        this.funcionario_id = funcionario_id;
    }

    public Integer getSindicancia_id() {
        return sindicancia_id;
    }

    public void setSindicancia_id(Integer sindicancia_id) {
        this.sindicancia_id = sindicancia_id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.funcionario_id);
        hash = 67 * hash + Objects.hashCode(this.sindicancia_id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final sindicancia_funcionarioId other = (sindicancia_funcionarioId) obj;
        if (!Objects.equals(this.funcionario_id, other.funcionario_id)) {
            return false;
        }
        if (!Objects.equals(this.sindicancia_id, other.sindicancia_id)) {
            return false;
        }
        return true;
    }

      
}
