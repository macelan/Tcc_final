package br.edu.ifsp.tcc.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    @NamedQuery(name = "Coordenadoria.Todos", query = "SELECT d FROM Coordenadoria d"),
    @NamedQuery(name = "Coordenadoria.buscarPorNome", query = "SELECT s FROM Coordenadoria s WHERE s.nome like :nome"),
    @NamedQuery(name = "Coordenadoria.buscarPorSigla", query = "SELECT s FROM Coordenadoria s WHERE s.sigla like :sigla"),
    })
    @Table(name = "coordenadoria")
    public class Coordenadoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "nome", length = 170, nullable = false)
    private String nome;
    @Column(name = "sigla", length = 10, nullable = false)
    private String sigla;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "coordenadoria")
    private List<Presidio> presidios;

    public Coordenadoria() {
    }

    public Coordenadoria(int id, String nome, String sigla, List<Presidio> presidios) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
        this.presidios = presidios;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public List<Presidio> getPresidios() {
        return presidios;
    }

    public void setPresidios(List<Presidio> presidios) {
        this.presidios = presidios;
    }

    

//    @Override
//    public String toString() {
//        return "Coordenadoria{" + "id=" + id + ", nome=" + nome + ", sigla=" + sigla + ", Presidio=" + Presidio + '}';
//    }
    @Override
    public String toString() {
        return this.getSigla();
    }

}
