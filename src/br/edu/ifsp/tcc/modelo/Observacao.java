/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.tcc.modelo;

import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author macel
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Observacao.Todos", query = "SELECT d FROM Observacao d"),
    //@NamedQuery(name = "Observacao.buscarPorMatricula",query = "SELECT s FROM Observacao s WHERE s.matricula like :matricula"),
    @NamedQuery(name = "Observacao.buscarPorRelato",query = "SELECT s FROM Observacao s WHERE s.relato like :relato"),
  
})
@Table(name = "observacao")
public class Observacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Integer id;
    @Column(name = "relato", length = 500, nullable = false)
    private String relato;
    @Temporal(TemporalType.DATE)
    @Column(name = "data_ocorrido",nullable = false)
    private Date dataOcorrido;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_registro",nullable = true)
    private Date dataRegistro;

    @ManyToOne
    @JoinColumn(name="sentenciado_id", referencedColumnName = "id", nullable = false)
    private Sentenciado sentenciado;
//        
//   @JoinColumn(name = "sen_cod", referencedColumnName = "sen_cod", insertable = false, updatable = false)
//    @ManyToOne(optional = false)
//    private Sentenciado sentenciado;

    public Observacao() {
    }

    public Observacao(Integer id, String relato, Date dataOcorrido, Date dataRegistro, Sentenciado sentenciado) {
        this.id = id;
        this.relato = relato;
        this.dataOcorrido = dataOcorrido;
        this.dataRegistro = dataRegistro;
        this.sentenciado = sentenciado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRelato() {
        return relato;
    }

    public void setRelato(String relato) {
        this.relato = relato;
    }

    public Date getDataOcorrido() {
        return dataOcorrido;
    }

    public void setDataOcorrido(Date dataOcorrido) {
        this.dataOcorrido = dataOcorrido;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public Sentenciado getSentenciado() {
        return sentenciado;
    }

    public void setSentenciado(Sentenciado sentenciado) {
        this.sentenciado = sentenciado;
    }

    @Override
    public String toString() {
        return "Observacao{" + "id=" + id + ", relato=" + relato + ", dataOcorrido=" + dataOcorrido + ", dataRegistro=" + dataRegistro + ", sentenciado=" + sentenciado + '}';
    }

    
     

    
 

}
