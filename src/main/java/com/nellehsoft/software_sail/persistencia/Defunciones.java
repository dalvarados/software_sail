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
@Table(name = "defunciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Defunciones.findAll", query = "SELECT d FROM Defunciones d")
    , @NamedQuery(name = "Defunciones.findByIdDefunciones", query = "SELECT d FROM Defunciones d WHERE d.idDefunciones = :idDefunciones")
    , @NamedQuery(name = "Defunciones.findByFechaNacimiento", query = "SELECT d FROM Defunciones d WHERE d.fechaNacimiento = :fechaNacimiento")
    , @NamedQuery(name = "Defunciones.findByFechaFallecimiento", query = "SELECT d FROM Defunciones d WHERE d.fechaFallecimiento = :fechaFallecimiento")
    , @NamedQuery(name = "Defunciones.findByCausa", query = "SELECT d FROM Defunciones d WHERE d.causa = :causa")
    , @NamedQuery(name = "Defunciones.findByLugar", query = "SELECT d FROM Defunciones d WHERE d.lugar = :lugar")
    , @NamedQuery(name = "Defunciones.findByFechaFuneral", query = "SELECT d FROM Defunciones d WHERE d.fechaFuneral = :fechaFuneral")
    , @NamedQuery(name = "Defunciones.findByObservaciones", query = "SELECT d FROM Defunciones d WHERE d.observaciones = :observaciones")})
public class Defunciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_defunciones")
    private Integer idDefunciones;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "fecha_fallecimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFallecimiento;
    @Size(max = 250)
    @Column(name = "causa")
    private String causa;
    @Size(max = 100)
    @Column(name = "lugar")
    private String lugar;
    @Column(name = "fecha_funeral")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFuneral;
    @Size(max = 250)
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne
    private Persona idPersona;

    public Defunciones() {
    }

    public Defunciones(Integer idDefunciones) {
        this.idDefunciones = idDefunciones;
    }

    public Integer getIdDefunciones() {
        return idDefunciones;
    }

    public void setIdDefunciones(Integer idDefunciones) {
        this.idDefunciones = idDefunciones;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(Date fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public String getCausa() {
        return causa;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Date getFechaFuneral() {
        return fechaFuneral;
    }

    public void setFechaFuneral(Date fechaFuneral) {
        this.fechaFuneral = fechaFuneral;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Persona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Persona idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDefunciones != null ? idDefunciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Defunciones)) {
            return false;
        }
        Defunciones other = (Defunciones) object;
        if ((this.idDefunciones == null && other.idDefunciones != null) || (this.idDefunciones != null && !this.idDefunciones.equals(other.idDefunciones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nellehsoft.software_sail.persistencia1.Defunciones[ idDefunciones=" + idDefunciones + " ]";
    }
    
}

