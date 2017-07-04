/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ali_sualei
 */
@Entity
@Table(catalog = "esade", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parametroautoavaliacao.findAll", query = "SELECT p FROM Parametroautoavaliacao p")
    , @NamedQuery(name = "Parametroautoavaliacao.findByIdparametroautoavaliacao", query = "SELECT p FROM Parametroautoavaliacao p WHERE p.idparametroautoavaliacao = :idparametroautoavaliacao")
    , @NamedQuery(name = "Parametroautoavaliacao.findByDescricao", query = "SELECT p FROM Parametroautoavaliacao p WHERE p.descricao = :descricao")
    , @NamedQuery(name = "Parametroautoavaliacao.findByCodigo", query = "SELECT p FROM Parametroautoavaliacao p WHERE p.codigo = :codigo")
    , @NamedQuery(name = "Parametroautoavaliacao.findByQuantidade", query = "SELECT p FROM Parametroautoavaliacao p WHERE p.quantidade = :quantidade")
    , @NamedQuery(name = "Parametroautoavaliacao.findByPontos", query = "SELECT p FROM Parametroautoavaliacao p WHERE p.pontos = :pontos")
    , @NamedQuery(name = "Parametroautoavaliacao.findByBonos", query = "SELECT p FROM Parametroautoavaliacao p WHERE p.bonos = :bonos")})
public class Parametroautoavaliacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Short idparametroautoavaliacao;
    @Column(length = 255)
    private String descricao;
    @Column(length = 16)
    private String codigo;
    @Column(length = 16)
    private String quantidade;
    @Column(length = 16)
    private String pontos;
    @Column(length = 16)
    private String bonos;
    @JoinColumn(name = "idquestaoautoavaliacao", referencedColumnName = "idquestaoautoavaliacao")
    @ManyToOne(fetch = FetchType.LAZY)
    private Questaoautoavaliacao idquestaoautoavaliacao;

    public Parametroautoavaliacao() {
    }

    public Parametroautoavaliacao(Short idparametroautoavaliacao) {
        this.idparametroautoavaliacao = idparametroautoavaliacao;
    }

    public Short getIdparametroautoavaliacao() {
        return idparametroautoavaliacao;
    }

    public void setIdparametroautoavaliacao(Short idparametroautoavaliacao) {
        this.idparametroautoavaliacao = idparametroautoavaliacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getPontos() {
        return pontos;
    }

    public void setPontos(String pontos) {
        this.pontos = pontos;
    }

    public String getBonos() {
        return bonos;
    }

    public void setBonos(String bonos) {
        this.bonos = bonos;
    }

    public Questaoautoavaliacao getIdquestaoautoavaliacao() {
        return idquestaoautoavaliacao;
    }

    public void setIdquestaoautoavaliacao(Questaoautoavaliacao idquestaoautoavaliacao) {
        this.idquestaoautoavaliacao = idquestaoautoavaliacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idparametroautoavaliacao != null ? idparametroautoavaliacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parametroautoavaliacao)) {
            return false;
        }
        Parametroautoavaliacao other = (Parametroautoavaliacao) object;
        if ((this.idparametroautoavaliacao == null && other.idparametroautoavaliacao != null) || (this.idparametroautoavaliacao != null && !this.idparametroautoavaliacao.equals(other.idparametroautoavaliacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Parametroautoavaliacao[ idparametroautoavaliacao=" + idparametroautoavaliacao + " ]";
    }
    
}
