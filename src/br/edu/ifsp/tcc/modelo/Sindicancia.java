/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.tcc.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author macel listarSindicanciaPorDataRegistro
 */
@Entity
@Table(name = "sindicancia")
@NamedQueries({
    @NamedQuery(name = "Sindicancia.buscaTodos", query = "SELECT s FROM Sindicancia s"),
    @NamedQuery(name = "Sindicancia.buscarPorDataRegistro", query = "SELECT s FROM Sindicancia s WHERE s.dataRegistro BETWEEN :dataRegistroInicial and :dataRegistroFinal"),
    @NamedQuery(name = "Sindicancia.buscarPorRelato", query = "SELECT s FROM Sindicancia s WHERE s.relato like :parametrorelato"),
//    @NamedQuery(name = "Sindicancia.buscarPorDataEvento", query = "SELECT s FROM Sindicancia s WHERE s.dataOcorrido like :dataEvento"),
})
public class Sindicancia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Temporal(TemporalType.DATE)
    @Column(name = "data_ocorrido", nullable = false)
    private Date dataOcorrencia;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_registro", nullable = false)
    private Date dataRegistro;
    @Column(name = "relato", length = 500, nullable = false)
    private String relato;

       @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)//Carrega ao Precisar
    @JoinTable(name = "sindicancia_funcionario",
            joinColumns = @JoinColumn(name = "sindicancia_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "funcionario_id", referencedColumnName = "id", nullable = false),
            uniqueConstraints = {
                @UniqueConstraint(columnNames = {"sindicancia_id", "funcionario_id"})})
    
    private List<Funcionario> funcionarios = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)//Carrega ao Precisar
    @JoinTable(name = "sindicancia_sentenciado",
            joinColumns = @JoinColumn(name = "sindicancia_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "sentenciado_id", referencedColumnName = "id", nullable = false),
            uniqueConstraints = {
                @UniqueConstraint(columnNames = {"sindicancia_id", "sentenciado_id"})})
    
    private List<Sentenciado> sentenciados = new ArrayList<>();

    public Sindicancia() {
    }

    public Sindicancia(Integer id, Date dataOcorrencia, Date dataRegisto, String relato) {
        this.id = id;
        this.dataOcorrencia = dataOcorrencia;
        this.dataRegistro = dataRegisto;
        this.relato = relato;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataOcorrencia() {
        return dataOcorrencia;
    }

    public void setDataOcorrencia(Date dataOcorrencia) {
        this.dataOcorrencia = dataOcorrencia;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getRelato() {
        return relato;
    }

    public void setRelato(String relato) {
        this.relato = relato;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public List<Sentenciado> getSentenciados() {
        return sentenciados;
    }

    public void setSentenciados(List<Sentenciado> sentenciados) {
        this.sentenciados = sentenciados;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final Sindicancia other = (Sindicancia) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Sindicancia{" + "id=" + id + ", dataOcorrencia=" + dataOcorrencia + ", dataRegisto=" + dataRegistro + ", relato=" + relato + ", funcionarios=" + funcionarios + ", sentenciados=" + sentenciados + '}';
    }

}
