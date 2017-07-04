/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ali_sualei
 */
@Entity
@Table(catalog = "esade", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estudante.findAll", query = "SELECT e FROM Estudante e")
    , @NamedQuery(name = "Estudante.findByIdestudante", query = "SELECT e FROM Estudante e WHERE e.idestudante = :idestudante")
    , @NamedQuery(name = "Estudante.findByNome", query = "SELECT e FROM Estudante e WHERE e.nome = :nome")
    , @NamedQuery(name = "Estudante.findByNumeroestudante", query = "SELECT e FROM Estudante e WHERE e.numeroestudante = :numeroestudante")
    , @NamedQuery(name = "Estudante.findByEmail", query = "SELECT e FROM Estudante e WHERE e.email = :email")})
public class Estudante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idestudante;
    @Column(length = 100)
    private String nome;
    @Column(length = 100)
    private String numeroestudante;
    @Column(length = 45)
    private String email;
    @OneToMany(mappedBy = "idestudante", fetch = FetchType.LAZY)
    private List<Users> usersList;
    @JoinColumn(name = "idcurso", referencedColumnName = "idcurso")
    @ManyToOne(fetch = FetchType.LAZY)
    private Curso idcurso;

    public Estudante() {
    }

    public Estudante(Integer idestudante) {
        this.idestudante = idestudante;
    }

    public Integer getIdestudante() {
        return idestudante;
    }

    public void setIdestudante(Integer idestudante) {
        this.idestudante = idestudante;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroestudante() {
        return numeroestudante;
    }

    public void setNumeroestudante(String numeroestudante) {
        this.numeroestudante = numeroestudante;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }

    public Curso getIdcurso() {
        return idcurso;
    }

    public void setIdcurso(Curso idcurso) {
        this.idcurso = idcurso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idestudante != null ? idestudante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estudante)) {
            return false;
        }
        Estudante other = (Estudante) object;
        if ((this.idestudante == null && other.idestudante != null) || (this.idestudante != null && !this.idestudante.equals(other.idestudante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Estudante[ idestudante=" + idestudante + " ]";
    }
    
}
