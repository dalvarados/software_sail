/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nellehsoft.software_sail.persistencia;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Duverdiel
 */
@Entity
@Table(name = "persona_cargo_comite")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonaCargoComite.findAll", query = "SELECT p FROM PersonaCargoComite p ORDER BY P.idComite.idComite DESC")
    , @NamedQuery(name = "PersonaCargoComite.findByIdPersonaCargoComite", query = "SELECT p FROM PersonaCargoComite p WHERE p.idPersonaCargoComite = :idPersonaCargoComite")
    , @NamedQuery(name = "PersonaCargoComite.findByPersonaCargo", query = "SELECT p FROM PersonaCargoComite p JOIN P.idComite AS a WHERE a.nombreComite = :nombreComite  and P.fecha= :fecha")        
    , @NamedQuery(name = "PersonaCargoComite.findByFechaComite", query = "SELECT p FROM PersonaCargoComite p WHERE p.fecha = :fecha")                
    , @NamedQuery(name = "PersonaCargoComite.findByFechaAsignacion", query = "SELECT p FROM PersonaCargoComite p WHERE p.fechaAsignacion = :fechaAsignacion")})
public class PersonaCargoComite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_persona_cargo_comite")
    private Integer idPersonaCargoComite;
    @Column(name = "fecha_asignacion")
    @Temporal(TemporalType.DATE)
    private Date fechaAsignacion;
    @Size(max = 8)
    @Column(name = "fecha")
    private String fecha;      
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne
    private Persona idPersona;
    @JoinColumn(name = "id_comite", referencedColumnName = "id_comite")
    @ManyToOne
    private Comite idComite;
    @JoinColumn(name = "id_cargo", referencedColumnName = "id_cargo")
    @ManyToOne
    private Cargo idCargo;

    public PersonaCargoComite() {
    }

    public PersonaCargoComite(Integer idPersonaCargoComite) {
        this.idPersonaCargoComite = idPersonaCargoComite;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    
    public Integer getIdPersonaCargoComite() {
        return idPersonaCargoComite;
    }

    public void setIdPersonaCargoComite(Integer idPersonaCargoComite) {
        this.idPersonaCargoComite = idPersonaCargoComite;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public Persona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Persona idPersona) {
        this.idPersona = idPersona;
    }

    public Comite getIdComite() {
        return idComite;
    }

    public void setIdComite(Comite idComite) {
        this.idComite = idComite;
    }

    public Cargo getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Cargo idCargo) {
        this.idCargo = idCargo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersonaCargoComite != null ? idPersonaCargoComite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaCargoComite)) {
            return false;
        }
        PersonaCargoComite other = (PersonaCargoComite) object;
        if ((this.idPersonaCargoComite == null && other.idPersonaCargoComite != null) || (this.idPersonaCargoComite != null && !this.idPersonaCargoComite.equals(other.idPersonaCargoComite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.PersonaCargoComite[ idPersonaCargoComite=" + idPersonaCargoComite + " ]";
    }
    
}

