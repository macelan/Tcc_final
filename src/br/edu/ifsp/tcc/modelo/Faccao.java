package br.edu.ifsp.tcc.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author macelan
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Faccao.Todos", query = "SELECT d FROM Faccao d"),
    @NamedQuery(name = "Faccao.buscarPorNome",query = "SELECT s FROM Faccao s WHERE s.nome like :nome"),
    @NamedQuery(name = "Faccao.buscarPorSigla",query = "SELECT s FROM Faccao s WHERE s.siglaFaccao like :sigla"),
   
})
@Table(name = "faccao")
public class Faccao implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Integer id;
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;
    @Column(name = "sigla_faccao", length = 10, nullable = false)
    private String siglaFaccao;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "faccao")
    private List<Sentenciado> sentenciados = new ArrayList<>();
    

//    @ManyToOne
//    @JoinColumn(name = "coordenadoria_id")
//    private Coordenadoria coordenadoria;
//    @OneToMany(mappedBy = "preCod")
//    private List<Funcionario> funcionarioList;

    public Faccao() {
    }

    public Faccao(Integer id, String nome, String siglaFaccao) {
        this.id = id;
        this.nome = nome;
        this.siglaFaccao = siglaFaccao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSiglaFaccao() {
        return siglaFaccao;
    }

    public void setSiglaFaccao(String siglaFaccao) {
        this.siglaFaccao = siglaFaccao;
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
        hash = 47 * hash + Objects.hashCode(this.id);
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
        final Faccao other = (Faccao) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
   
    
    
   @Override
    public String toString() {
        return this.getSiglaFaccao();
    }

}
