package br.edu.ifsp.tcc.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author macelan
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Funcionario.Todos", query = "SELECT d FROM Funcionario d"),
    @NamedQuery(name = "Funcionario.buscarPorNome",query = "SELECT s FROM Funcionario s WHERE s.nome like :nome"),
    @NamedQuery(name = "Funcionario.buscaPorRegistroAtivo", query = "SELECT s FROM Funcionario s WHERE s.ativo = :ativo"), //ativo é o que esta na classe e não no banco
    @NamedQuery(name = "Funcionario.buscarPorRg",query = "SELECT s FROM Funcionario s WHERE s.rg like :rg"),
    @NamedQuery(name = "Funcionario.buscarPorPresidio",query = "SELECT s FROM Funcionario s WHERE s.presidio.nome like :presidio"),
    @NamedQuery(name = "Funcionario.confirmaLoginSenha", query = "SELECT u FROM Funcionario u WHERE u.usuario = :usuario and u.senha = :senha"),
    @NamedQuery(name = "Funcionario.confirmaLogin",query = "SELECT s FROM Funcionario s WHERE s.usuario like :usuario"),
})
@Table(name = "funcionario")
public class Funcionario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Integer id;
    @Column(name = "rg", length = 18, nullable = false, unique = true)
    private String rg;
    @Column(name = "nome", length = 60, nullable = false)
    private String nome;
    @Temporal(TemporalType.DATE)
    @Column(name = "data_nascimento",nullable = false)
    private Date dataNascimento;
    @Column(name = "usuario", length = 70, nullable = false)
    private String usuario;
    @Column(name = "senha", length = 50, nullable = false)
    private String senha;
    @Column(name = "registro_ativo", nullable = false)
    private Boolean ativo;
     // data do registro pega do sistema
    @Column(name = "data_cadastro", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
    
    @Column(name = "tipo", length = 15, nullable = false)
    private String tipoUsuario;
    
   @ManyToOne
   @JoinColumn(name="presidio_id", referencedColumnName = "id")
   private Presidio presidio; 
   
   //Coleção
   @ManyToMany (mappedBy = "funcionarios", fetch = FetchType.LAZY)//Carraega ao Precisar
   private List<Sindicancia> sindicancias = new ArrayList<>();
 
 
//        
//    @JoinTable(name = "oco_fun", joinColumns = {
//    @JoinColumn(name = "of_fun_cod", referencedColumnName = "fun_cod")}, inverseJoinColumns = {
//    @JoinColumn(name = "of_oco_cod", referencedColumnName = "oco_cod")})
//    @ManyToMany
//    private List<Ocorrencia> ocorrenciaList;
//    @JoinColumn(name = "pre_cod", referencedColumnName = "pre_cod")
//    @ManyToOne
//    @JoinColumn(name = "presidio_id")
//    private Presidio presidio;

    public Funcionario() {
    }

    public Funcionario(Integer id, String rg, String nome, Date dataNascimento, String usuario, String senha, Boolean ativo, Date dataCadastro, String tipoUsuario, Presidio presidio) {
        this.id = id;
        this.rg = rg;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.usuario = usuario;
        this.senha = senha;
        this.ativo = ativo;
        this.dataCadastro = dataCadastro;
        this.tipoUsuario = tipoUsuario;
        this.presidio = presidio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Presidio getPresidio() {
        return presidio;
    }

    public void setPresidio(Presidio presidio) {
        this.presidio = presidio;
    }

    public List<Sindicancia> getSindicancias() {
        return sindicancias;
    }

    public void setSindicancias(List<Sindicancia> sindicancias) {
        this.sindicancias = sindicancias;
    }

   

    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final Funcionario other = (Funcionario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Funcionario{" + "id=" + id + ", rg=" + rg + ", nome=" + nome + ", dataNascimento=" + dataNascimento + ", usuario=" + usuario + ", senha=" + senha + ", ativo=" + ativo + ", dataCadastro=" + dataCadastro + ", tipoUsuario=" + tipoUsuario + ", presidio=" + presidio + ", sindicancias=" + sindicancias + '}';
    }
    
}
