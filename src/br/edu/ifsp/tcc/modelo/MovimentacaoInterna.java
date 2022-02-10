/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.tcc.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
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

/**
 *
 * @author macel
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "MovimentacaoInterna.Todos", query = "SELECT d FROM MovimentacaoInterna d"),
//    @NamedQuery(name = "MovimentacaoInterna.buscarPorCela",query = "SELECT s FROM MovimentacaoExterna s WHERE s.cela_id like :cela"),
    @NamedQuery(name = "MovimentacaoInterna.buscarPorCela",query = "SELECT s FROM MovimentacaoInterna s WHERE s.cela.numeroCela like :cela"),
    @NamedQuery(name = "MovimentacaoInterna.buscarPorMatricula",query = "SELECT s FROM MovimentacaoInterna s WHERE s.sentenciado.matricula like :matricula"),
    @NamedQuery(name = "MovimentacaoInterna.buscarPorPavilhao",query = "SELECT s FROM MovimentacaoInterna s WHERE s.cela.pavilhao.nome like :nome"),
    
  
})
@Table(name = "movimentacao_interna")
public class MovimentacaoInterna implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Integer id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_movimentacao", nullable = false)
    private Date dataMovimentacao;
    @Temporal(TemporalType.DATE)
    @Column(name = "data_registro",nullable = false)
    private Date DataRegistro;
    @Column(name = "tipoMi", length = 3, nullable = false) /*EX -> Exclusão ************ IN -> Inclusão*/
    private String tipoMi;
     @Column(name = "motivo", length = 3, nullable = false) /*EX -> Exclusão ************ IN -> Inclusão*/
    private String motivo;

    
    @ManyToOne
    @JoinColumn(name = "cela_id", referencedColumnName = "id",nullable = true)// Aqui mudei pra treu para setar fora da Unidade
    private Cela cela;
    
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

    public MovimentacaoInterna() {
    }

    public MovimentacaoInterna(Integer id, Date dataMovimentacao, Date setDataRegistro, String tipoMi, String motivo, Cela cela, Sentenciado sentenciado) {
        this.id = id;
        this.dataMovimentacao = dataMovimentacao;
        this.DataRegistro = DataRegistro;
        this.tipoMi = tipoMi;
        this.motivo = motivo;
        this.cela = cela;
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
        return DataRegistro;
    }

    public void setDataRegistro(Date DataRegistro) {
        this.DataRegistro = DataRegistro;
    }

    

    public String getTipoMi() {
        return tipoMi;
    }

    public void setTipoMi(String tipoMi) {
        this.tipoMi = tipoMi;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Cela getCela() {
        return cela;
    }

    public void setCela(Cela cela) {
        this.cela = cela;
    }

    public Sentenciado getSentenciado() {
        return sentenciado;
    }

    public void setSentenciado(Sentenciado sentenciado) {
        this.sentenciado = sentenciado;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final MovimentacaoInterna other = (MovimentacaoInterna) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MovimentacaoInterna{" + "id=" + id + ", dataMovimentacao=" + dataMovimentacao + ", DataRegistro=" + DataRegistro + ", tipoMi=" + tipoMi + ", motivo=" + motivo + ", cela=" + cela + ", sentenciado=" + sentenciado + '}';
    }

   
   
}
