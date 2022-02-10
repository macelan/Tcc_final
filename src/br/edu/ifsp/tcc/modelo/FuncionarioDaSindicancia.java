/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.tcc.modelo;

import br.edu.ifsp.tcc.pk.sindicancia_funcionarioId;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Programar
 */
@Entity
@Table(name = "sindicancia_funcionario")
//@IdClass(FuncionarioDaSindicanciaPK.class)
public class FuncionarioDaSindicancia implements Serializable {
  
    @Id
    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Sentenciado funcionario;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "sindicancia_id")
    private Sindicancia sindicancia;

    public FuncionarioDaSindicancia() {
    }

    public FuncionarioDaSindicancia(Sentenciado sentenciado, Sindicancia sindicancia) {
        this.funcionario = sentenciado;
        this.sindicancia = sindicancia;
    }

    public Sentenciado getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Sentenciado funcionario) {
        this.funcionario = funcionario;
    }

    public Sindicancia getSindicancia() {
        return sindicancia;
    }

    public void setSindicancia(Sindicancia sindicancia) {
        this.sindicancia = sindicancia;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.funcionario);
        hash = 19 * hash + Objects.hashCode(this.sindicancia);
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
        final FuncionarioDaSindicancia other = (FuncionarioDaSindicancia) obj;
        if (!Objects.equals(this.funcionario, other.funcionario)) {
            return false;
        }
        if (!Objects.equals(this.sindicancia, other.sindicancia)) {
            return false;
        }
        return true;
    }

    
    
    @Override
    public String toString() {
        return "FuncionarioDaSindicancia{" + "funcionario=" + funcionario + ", sindicancia=" + sindicancia + '}';
    }

    
    
   
    
}
