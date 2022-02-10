package br.edu.ifsp.tcc.modelo;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
import javax.persistence.Transient;

/**
 *
 * @author macelan
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Pavilhao.Todos", query = "SELECT d FROM Pavilhao d"),
    @NamedQuery(name = "Pavilhao.buscarPorNome",query = "SELECT s FROM Pavilhao s WHERE s.nome like :nome"),
    @NamedQuery(name = "Pavilhao.buscarPorSigla",query = "SELECT s FROM Pavilhao s WHERE s.sigla like :sigla"),

})
@Table(name = "pavilhao")
public class Pavilhao implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Integer id;
    @Column(name = "nome", length = 23, nullable = false)
    private String nome;
    @Column(name = "sigla_pavilhao", length = 15, nullable = false)
    private String sigla;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pavilhao")
    private List<EventoColetivo> eventoColetivos = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pavilhao")
    private List<Cela> celas;

       

////        
////  @JoinColumn(name = "eve_cod", referencedColumnName = "eve_cod")
//    @ManyToOne
//    private EventoColetivo eveCod;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pavilhao")
//    private List<Cela> celaList;

    public Pavilhao() {
        celas = new ArrayList<> ();
    }

    public Pavilhao(Integer id, String nome, String sigla, List<Cela> celas) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
        this.celas = celas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        String oldNome = this.nome;
        this.nome = nome;
        changeSupport.firePropertyChange("nome", oldNome, nome);
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        String oldSigla = this.sigla;
        this.sigla = sigla;
        changeSupport.firePropertyChange("sigla", oldSigla, sigla);
    }

    public List<EventoColetivo> getEventoColetivos() {
        return eventoColetivos;
    }

    public void setEventoColetivos(List<EventoColetivo> eventoColetivos) {
        this.eventoColetivos = eventoColetivos;
    }

    public List<Cela> getCelas() {
        return celas;
    }

    public void setCelas(List<Cela> celas) {
        this.celas = celas;
    }

//    @Override
//    public String toString() {
//        return this.getSigla();
//    }

    public void addCela(Cela c){
        celas.add(c);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
 
}
