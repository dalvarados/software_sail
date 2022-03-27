/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nellehsoft.software_sail.persistencia;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "ocupacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ocupacion.findAll", query = "SELECT o FROM Ocupacion o")
    , @NamedQuery(name = "Ocupacion.findByIdOcupacion", query = "SELECT o FROM Ocupacion o WHERE o.idOcupacion = :idOcupacion")
    , @NamedQuery(name = "Ocupacion.findByNombreOcupacion", query = "SELECT o FROM Ocupacion o WHERE o.nombreOcupacion = :nombreOcupacion")})
public class Ocupacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ocupacion")
    private Integer idOcupacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "nombre_ocupacion")
    private String nombreOcupacion;
    @OneToMany(mappedBy = "idOcupacion")
    private Collection<Persona> personaCollection;

    public Ocupacion() {
    }

    public Ocupacion(Integer idOcupacion) {
        this.idOcupacion = idOcupacion;
    }

    public Ocupacion(Integer idOcupacion, String nombreOcupacion) {
        this.idOcupacion = idOcupacion;
        this.nombreOcupacion = nombreOcupacion;
    }

    public Integer getIdOcupacion() {
        return idOcupacion;
    }

    public void setIdOcupacion(Integer idOcupacion) {
        this.idOcupacion = idOcupacion;
    }

    public String getNombreOcupacion() {
        return nombreOcupacion;
    }

    public void setNombreOcupacion(String nombreOcupacion) {
        this.nombreOcupacion = nombreOcupacion;
    }

    @XmlTransient
    public Collection<Persona> getPersonaCollection() {
        return personaCollection;
    }

    public void setPersonaCollection(Collection<Persona> personaCollection) {
        this.personaCollection = personaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOcupacion != null ? idOcupacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ocupacion)) {
            return false;
        }
        Ocupacion other = (Ocupacion) object;
        if ((this.idOcupacion == null && other.idOcupacion != null) || (this.idOcupacion != null && !this.idOcupacion.equals(other.idOcupacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nellehsoft.software_sail.persistencia.Ocupacion[ idOcupacion=" + idOcupacion + " ]";
    }
    
}
