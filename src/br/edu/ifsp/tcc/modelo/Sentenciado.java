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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
    @NamedQuery(name = "Sentenciado.Todos", query = "SELECT d FROM Sentenciado d"),
    @NamedQuery(name = "Sentenciado.buscarPorNome",query = "SELECT s FROM Sentenciado s WHERE s.nome like :nome"),
    @NamedQuery(name = "Sentenciado.buscarPorMatricula",query = "SELECT s FROM Sentenciado s WHERE s.matricula like :matricula"),
    @NamedQuery(name = "Sentenciado.buscarPorRG",query = "SELECT s FROM Sentenciado s WHERE s.rg like :rg"),
    @NamedQuery(name = "Sentenciado.consultarMatricula", query = "Select s FROM Sentenciado s WHERE s.matricula =:matricula"),  
   
})
@Table(name = "sentenciado")
public class Sentenciado implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Integer id;
    @Column(name = "matricula",length = 13, nullable = false, unique = true)
    private String matricula;
    @Column(name = "nome",length = 60, nullable = false)
    private String nome;
    @Column(name = "rg",length = 13, nullable = false, unique = true)
    private String rg;
    @Temporal(TemporalType.DATE)
    @Column(name = "data_nascimento", nullable = true)
    private Date dataNascimento;
    @Column(name = "aliases",length = 30, nullable = true)
    private String aliases;
    @Column(name = "registro_ativo", nullable = false)
    private Boolean ativo;
    @Column(name = "quantida_sindicancia", nullable = true)
    private Integer quantidadeSindicancia;
    @Column(name = "quantida_observacao", nullable = true)
    private Integer quantidadeObservacao;
     // data do registro pega do sistema
    @Column(name = "data_cadastro", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
    
    @ManyToOne
    @JoinColumn(name = "faccao_id", referencedColumnName = "id")
    private Faccao faccao;
    
    //Coleções
    @ManyToMany (mappedBy = "sentenciados",fetch = FetchType.LAZY)//Carraega ao Precisar
    private List<Sindicancia> sindicancias = new ArrayList<>();
    
    @OneToMany(mappedBy = "sentenciado",fetch = FetchType.LAZY)
    private List<MovimentacaoExterna> movimentacoesExternas = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sentenciado")
    private List<MovimentacaoInterna> movimentacoesInternas = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sentenciado")
    private List<Observacao> observacoes = new ArrayList<>();
        
        
//  @JoinTable(name = "oco_sen", joinColumns = {
//  @JoinColumn(name = "os_sen_cod", referencedColumnName = "sen_cod")}, inverseJoinColumns = {
////  @JoinColumn(name = "os_oco_cod", referencedColumnName = "oco_cod")})
//    @ManyToMany
//    private List<Ocorrencia> ocorrenciaList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sentenciado")
//    private List<Observacao> observacaoList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sentenciado")
//    private List<Inclusao> inclusaoList;     

    public Sentenciado() {
    }

    public Sentenciado(Integer id, String matricula, String nome, String rg, Date dataNascimento, String aliases, Boolean ativo, Integer quantidadeSindicancia, Integer quantidadeObservacao, Date dataCadastro, Faccao faccao) {
        this.id = id;
        this.matricula = matricula;
        this.nome = nome;
        this.rg = rg;
        this.dataNascimento = dataNascimento;
        this.aliases = aliases;
        this.ativo = ativo;
        this.quantidadeSindicancia = quantidadeSindicancia;
        this.quantidadeObservacao = quantidadeObservacao;
        this.dataCadastro = dataCadastro;
        this.faccao = faccao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getAliases() {
        return aliases;
    }

    public void setAliases(String aliases) {
        this.aliases = aliases;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Integer getQuantidadeSindicancia() {
        return quantidadeSindicancia;
    }

    public void setQuantidadeSindicancia(Integer quantidadeSindicancia) {
        this.quantidadeSindicancia = quantidadeSindicancia;
    }

    public Integer getQuantidadeObservacao() {
        return quantidadeObservacao;
    }

    public void setQuantidadeObservacao(Integer quantidadeObservacao) {
        this.quantidadeObservacao = quantidadeObservacao;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Faccao getFaccao() {
        return faccao;
    }

    public void setFaccao(Faccao faccao) {
        this.faccao = faccao;
    }

    public List<Sindicancia> getSindicancias() {
        return sindicancias;
    }

    public void setSindicancias(List<Sindicancia> sindicancias) {
        this.sindicancias = sindicancias;
    }

    public List<MovimentacaoExterna> getMovimentacoesExternas() {
        return movimentacoesExternas;
    }

    public void setMovimentacoesExternas(List<MovimentacaoExterna> movimentacoesExternas) {
        this.movimentacoesExternas = movimentacoesExternas;
    }

    public List<MovimentacaoInterna> getMovimentacoesInternas() {
        return movimentacoesInternas;
    }

    public void setMovimentacoesInternas(List<MovimentacaoInterna> movimentacoesInternas) {
        this.movimentacoesInternas = movimentacoesInternas;
    }

    public List<Observacao> getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(List<Observacao> observacoes) {
        this.observacoes = observacoes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
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
        final Sentenciado other = (Sentenciado) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }



    
    
    
    
    @Override
    public String toString() {
        return "Sentenciado{" + "id=" + id + ", matricula=" + matricula + ", nome=" + nome + ", rg=" + rg + ", dataNascimento=" + dataNascimento + ", aliases=" + aliases + ", ativo=" + ativo + ", quantidadeSindicancia=" + quantidadeSindicancia + ", quantidadeObservacao=" + quantidadeObservacao + ", dataCadastro=" + dataCadastro + ", faccao=" + faccao + ", sindicancias=" + sindicancias + ", movimentacoesExternas=" + movimentacoesExternas + ", movimentacoesInternas=" + movimentacoesInternas + ", observacoes=" + observacoes + '}';
    }

    

}
