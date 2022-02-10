package br.edu.ifsp.tcc.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.swing.JComboBox;

/**
 *
 * @author macelan
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Cela.Todos", query = "SELECT d FROM Cela d"),
   // @NamedQuery(name = "Cela.buscarPorNome",query = "SELECT s FROM Cela s WHERE s.nome like :nome"),
    @NamedQuery(name = "Cela.buscarPorSigla",query = "SELECT s FROM Cela s WHERE s.pavilhao.sigla like :sigla"),
    @NamedQuery(name = "Cela.confirmaInsercaoCela", query = "SELECT u FROM Cela u WHERE u.numeroCela = :p1 and u.pavilhao.id = :p2"),  
})

@Table(name = "cela", uniqueConstraints = {
    @UniqueConstraint(name = "cela_pavilhao_unique", columnNames = {"numero_cela", "pavilhao_id"})
})

//@IdClass(CelaPK.class)
public class Cela implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "numero_cela")
    private Integer numeroCela;
    
    @Column(name = "lotacao", nullable = false)
    private Integer lotacao; 
     
    @ManyToOne
    @JoinColumn(name = "pavilhao_id", referencedColumnName = "id")
    private Pavilhao pavilhao;
   
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cela")
    private List<MovimentacaoInterna> movimentacoesInternas = new ArrayList<>();
 
 
////  @JoinColumn(name = "pav_cod", referencedColumnName = "pav_cod", insertable = false, updatable = false)
//    @ManyToOne(optional = false)
//    private Pavilhao pavilhao;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cela")
//    private List<movimentacao> inclusaoList;
//
//    public void incluirCela() {
//
//    }

    public Cela() {
    }

    public Cela(Integer id, Integer numeroCela, Integer lotacao, Pavilhao pavilhao) {
        this.id = id;
        this.numeroCela = numeroCela;
        this.lotacao = lotacao;
        this.pavilhao = pavilhao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

  

    public Integer getNumeroCela() {
        return numeroCela;
    }

    public void setNumeroCela(Integer numeroCela) {
        this.numeroCela = numeroCela;
    }

    public Integer getLotacao() {
        return lotacao;
    }

    public void setLotacao(Integer lotacao) {
        this.lotacao = lotacao;
    }

    public Pavilhao getPavilhao() {
        return pavilhao;
    }

    public void setPavilhao(Pavilhao pavilhao) {
        this.pavilhao = pavilhao;
    }

    public List<MovimentacaoInterna> getMovimentacoesInternas() {
        return movimentacoesInternas;
    }

    public void setMovimentacoesInternas(List<MovimentacaoInterna> movimentacoesInternas) {
        this.movimentacoesInternas = movimentacoesInternas;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final Cela other = (Cela) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

  

    @Override
    public String toString() {
        return "Cela{" + "id=" + id + ", numeroCela=" + numeroCela + ", lotacao=" + lotacao + ", pavilhao=" + pavilhao + ", movimentacoesInternas=" + movimentacoesInternas + '}';
    }

}

