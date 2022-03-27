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
@Table(name = "comite")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comite.findAll", query = "SELECT c FROM Comite c")
    , @NamedQuery(name = "Comite.findByIdComite", query = "SELECT c FROM Comite c WHERE c.idComite = :idComite")
    , @NamedQuery(name = "Comite.findByNombreComite", query = "SELECT c FROM Comite c WHERE c.nombreComite = :nombreComite")})
public class Comite implements Serializable {

    @OneToMany(mappedBy = "idComite")
    private Collection<Asistencia> asistenciaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idComite")
    private Collection<Ofrenda> ofrendaCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idComite")
    private Collection<Calendario> calendarioCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_comite")
    private Integer idComite;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "nombre_comite")
    private String nombreComite;

    public Comite() {
    }

    public Comite(Integer idComite) {
        this.idComite = idComite;
    }

    public Comite(Integer idComite, String nombreComite) {
        this.idComite = idComite;
        this.nombreComite = nombreComite;
    }

    public Integer getIdComite() {
        return idComite;
    }

    public void setIdComite(Integer idComite) {
        this.idComite = idComite;
    }

    public String getNombreComite() {
        return nombreComite;
    }

    public void setNombreComite(String nombreComite) {
        this.nombreComite = nombreComite;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComite != null ? idComite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comite)) {
            return false;
        }
        Comite other = (Comite) object;
        if ((this.idComite == null && other.idComite != null) || (this.idComite != null && !this.idComite.equals(other.idComite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nellehsoft.software_sail.bean.Comite[ idComite=" + idComite + " ]";
    }

    @XmlTransient
    public Collection<Calendario> getCalendarioCollection() {
        return calendarioCollection;
    }

    public void setCalendarioCollection(Collection<Calendario> calendarioCollection) {
        this.calendarioCollection = calendarioCollection;
    }

    @XmlTransient
    public Collection<Asistencia> getAsistenciaCollection() {
        return asistenciaCollection;
    }

    public void setAsistenciaCollection(Collection<Asistencia> asistenciaCollection) {
        this.asistenciaCollection = asistenciaCollection;
    }

    @XmlTransient
    public Collection<Ofrenda> getOfrendaCollection() {
        return ofrendaCollection;
    }

    public void setOfrendaCollection(Collection<Ofrenda> ofrendaCollection) {
        this.ofrendaCollection = ofrendaCollection;
    }
    
}
