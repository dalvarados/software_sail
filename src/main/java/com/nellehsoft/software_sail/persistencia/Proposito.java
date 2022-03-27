/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nellehsoft.software_sail.persistencia;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Duverdiel
 */
@Entity
@Table(name = "proposito")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proposito.findAll", query = "SELECT p FROM Proposito p")
    , @NamedQuery(name = "Proposito.findByIdProposito", query = "SELECT p FROM Proposito p WHERE p.idProposito = :idProposito")
    , @NamedQuery(name = "Proposito.findByNombreProposito", query = "SELECT p FROM Proposito p WHERE p.nombreProposito = :nombreProposito")})
public class Proposito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_proposito")
    private Integer idProposito;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "nombre_proposito")
    private String nombreProposito;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProposito")
    private Collection<Ofrenda> ofrendaCollection;

    public Proposito() {
    }

    public Proposito(Integer idProposito) {
        this.idProposito = idProposito;
    }

    public Proposito(Integer idProposito, String nombreProposito) {
        this.idProposito = idProposito;
        this.nombreProposito = nombreProposito;
    }

    public Integer getIdProposito() {
        return idProposito;
    }

    public void setIdProposito(Integer idProposito) {
        this.idProposito = idProposito;
    }

    public String getNombreProposito() {
        return nombreProposito;
    }

    public void setNombreProposito(String nombreProposito) {
        this.nombreProposito = nombreProposito;
    }

    @XmlTransient
    public Collection<Ofrenda> getOfrendaCollection() {
        return ofrendaCollection;
    }

    public void setOfrendaCollection(Collection<Ofrenda> ofrendaCollection) {
        this.ofrendaCollection = ofrendaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProposito != null ? idProposito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proposito)) {
            return false;
        }
        Proposito other = (Proposito) object;
        if ((this.idProposito == null && other.idProposito != null) || (this.idProposito != null && !this.idProposito.equals(other.idProposito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nellehsoft.software_sail.persistencia.Proposito[ idProposito=" + idProposito + " ]";
    }
    
}
