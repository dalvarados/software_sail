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
@Table(name = "persona_cargo_grupo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonaCargoGrupo.findAll", query = "SELECT p FROM PersonaCargoGrupo p")
    , @NamedQuery(name = "PersonaCargoGrupo.findByIdPersonaCargoGrupo", query = "SELECT p FROM PersonaCargoGrupo p WHERE p.idPersonaCargoGrupo = :idPersonaCargoGrupo")
    , @NamedQuery(name = "PersonaCargoGrupo.findByFechaGrupo", query = "SELECT p FROM PersonaCargoGrupo p WHERE p.fecha = :fecha")        
    , @NamedQuery(name = "PersonaCargoGrupo.findByPersonaCargoGrupo", query = "SELECT p FROM PersonaCargoGrupo p JOIN P.idGrupo AS a WHERE a.nombreGrupo = :nombreGrupo and P.fecha= :fecha")                
    , @NamedQuery(name = "PersonaCargoGrupo.findByFechaAsignacion", query = "SELECT p FROM PersonaCargoGrupo p WHERE p.fechaAsignacion = :fechaAsignacion")})
public class PersonaCargoGrupo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_persona_cargo_grupo")
    private Integer idPersonaCargoGrupo;
    @Column(name = "fecha_asignacion")
    @Temporal(TemporalType.DATE)
    private Date fechaAsignacion;
    @Size(max = 8)
    @Column(name = "fecha")
    private String fecha;    
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne
    private Persona idPersona;    
    @JoinColumn(name = "id_grupo", referencedColumnName = "id_grupo")
    @ManyToOne
    private Grupo idGrupo;
    @JoinColumn(name = "id_cargo", referencedColumnName = "id_cargo")
    @ManyToOne
    private Cargo idCargo;    

    public PersonaCargoGrupo() {
    }

    public Persona getIdPersona() {
        return idPersona;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public void setIdPersona(Persona idPersona) {
        this.idPersona = idPersona;
    }

    public Cargo getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Cargo idCargo) {
        this.idCargo = idCargo;
    }

    
    public PersonaCargoGrupo(Integer idPersonaCargoGrupo) {
        this.idPersonaCargoGrupo = idPersonaCargoGrupo;
    }

    public Integer getIdPersonaCargoGrupo() {
        return idPersonaCargoGrupo;
    }

    public void setIdPersonaCargoGrupo(Integer idPersonaCargoGrupo) {
        this.idPersonaCargoGrupo = idPersonaCargoGrupo;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public Grupo getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Grupo idGrupo) {
        this.idGrupo = idGrupo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersonaCargoGrupo != null ? idPersonaCargoGrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaCargoGrupo)) {
            return false;
        }
        PersonaCargoGrupo other = (PersonaCargoGrupo) object;
        if ((this.idPersonaCargoGrupo == null && other.idPersonaCargoGrupo != null) || (this.idPersonaCargoGrupo != null && !this.idPersonaCargoGrupo.equals(other.idPersonaCargoGrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nellehsoft.software_sail.persistencia.PersonaCargoGrupo[ idPersonaCargoGrupo=" + idPersonaCargoGrupo + " ]";
    }
    
}
