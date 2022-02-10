/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.tcc.modelo;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author macel
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "MovimentacaoExterna.Todos", query = "SELECT d FROM MovimentacaoExterna d"),
    @NamedQuery(name = "MovimentacaoExterna.buscarPorPresidio",query = "SELECT s FROM MovimentacaoExterna s WHERE s.presidio.sigla like :presidio"),
    @NamedQuery(name = "MovimentacaoExterna.buscarPorMatricula",query = "SELECT s FROM MovimentacaoExterna s WHERE s.sentenciado.matricula like :matricula"),
  
})
@Table(name = "movimentacao_externa")
public class MovimentacaoExterna implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Integer id;
    @Temporal(TemporalType.DATE)
    @Column(name = "data_movimentacao", nullable = false)
    private Date dataMovimentacao;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_registro",nullable = false)
    private Date dataRegistro;
    @Column(name = "tipoMe", length = 12, nullable = false)/*IE -> inclusão esterna *************  EE -> exclusão externa  */
    private String tipoMe;
    
    @ManyToOne
    @JoinColumn(name = "presidio_id", referencedColumnName = "id",nullable = false)
    private Presidio presidio;
        
    @ManyToOne
    @JoinColumn(name = "sentenciado_id", referencedColumnName = "id",nullable = false)
    private Sentenciado sentenciado;
    
    

////        
////    @JoinColumn(name = "cel_cod", referencedColumnName = "cel_cod", insertable = false, updatable = false)
//    @ManyToOne(optional = false)
//    private Cela cela;
////    @JoinColumn(name = "sen_cod", referencedColumnName = "sen_cod", insertable = false, updatable = false)
//    @ManyToOne(optional = false)
//    private Sentenciado sentenciado;
////

    public MovimentacaoExterna() {
    }

    public MovimentacaoExterna(Integer id, Date dataMovimentacao, Date dataOcorrido, String tipoMe, Presidio presidio, Sentenciado sentenciado) {
        this.id = id;
        this.dataMovimentacao = dataMovimentacao;
        this.dataRegistro = dataOcorrido;
        this.tipoMe = tipoMe;
        this.presidio = presidio;
        this.sentenciado = sentenciado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(Date dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getTipoMe() {
        return tipoMe;
    }

    public void setTipoMe(String tipo) {
        this.tipoMe = tipo;
    }

    public Presidio getPresidio() {
        return presidio;
    }

    public void setPresidio(Presidio presidio) {
        this.presidio = presidio;
    }

    public Sentenciado getSentenciado() {
        return sentenciado;
    }

    public void setSentenciado(Sentenciado sentenciado) {
        this.sentenciado = sentenciado;
    }

    @Override
    public String toString() {
        return "MovimentacaoExterna{" + "id=" + id + ", dataMovimentacao=" + dataMovimentacao + ", dataOcorrido=" + dataRegistro + ", tipoMe=" + tipoMe + ", presidio=" + presidio + ", sentenciado=" + sentenciado + '}';
    }

   
    
}
