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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Duverdiel
 */
@Entity
@Table(name = "asistencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asistencia.findAll", query = "SELECT a FROM Asistencia a")
    , @NamedQuery(name = "Asistencia.findById", query = "SELECT a FROM Asistencia a WHERE a.id = :id")
    , @NamedQuery(name = "Asistencia.findByFechaAsistencia", query = "SELECT a FROM Asistencia a WHERE a.fechaAsistencia = :fechaAsistencia")
    , @NamedQuery(name = "Asistencia.findByCantNinos", query = "SELECT a FROM Asistencia a WHERE a.cantNinos = :cantNinos")
    , @NamedQuery(name = "Asistencia.findByCantJovenes", query = "SELECT a FROM Asistencia a WHERE a.cantJovenes = :cantJovenes")
    , @NamedQuery(name = "Asistencia.findByCantAdultos", query = "SELECT a FROM Asistencia a WHERE a.cantAdultos = :cantAdultos")
    , @NamedQuery(name = "Asistencia.findByCantAncianos", query = "SELECT a FROM Asistencia a WHERE a.cantAncianos = :cantAncianos")
    , @NamedQuery(name = "Asistencia.findByCantVisitas", query = "SELECT a FROM Asistencia a WHERE a.cantVisitas = :cantVisitas")
    , @NamedQuery(name = "Asistencia.findByCantTotal", query = "SELECT a FROM Asistencia a WHERE a.cantTotal = :cantTotal")})
public class Asistencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_asistencia")
    @Temporal(TemporalType.DATE)
    private Date fechaAsistencia;
    @Column(name = "cant_ninos")
    private Integer cantNinos;
    @Column(name = "cant_jovenes")
    private Integer cantJovenes;
    @Column(name = "cant_adultos")
    private Integer cantAdultos;
    @Column(name = "cant_ancianos")
    private Integer cantAncianos;
    @Column(name = "cant_visitas")
    private Integer cantVisitas;
    @Column(name = "cant_total")
    private Integer cantTotal;
    @JoinColumn(name = "id_comite", referencedColumnName = "id_comite")
    @ManyToOne
    private Comite idComite;
    @JoinColumn(name = "id_responsable_ppal", referencedColumnName = "id_persona")
    @ManyToOne
    private Persona idResponsablePpal;

    public Asistencia() {
    }

    public Asistencia(Integer id) {
        this.id = id;
    }

    public Asistencia(Integer id, Date fechaAsistencia) {
        this.id = id;
        this.fechaAsistencia = fechaAsistencia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaAsistencia() {
        return fechaAsistencia;
    }

    public void setFechaAsistencia(Date fechaAsistencia) {
        this.fechaAsistencia = fechaAsistencia;
    }

    public Integer getCantNinos() {
        return cantNinos;
    }

    public void setCantNinos(Integer cantNinos) {
        this.cantNinos = cantNinos;
    }

    public Integer getCantJovenes() {
        return cantJovenes;
    }

    public void setCantJovenes(Integer cantJovenes) {
        this.cantJovenes = cantJovenes;
    }

    public Integer getCantAdultos() {
        return cantAdultos;
    }

    public void setCantAdultos(Integer cantAdultos) {
        this.cantAdultos = cantAdultos;
    }

    public Integer getCantAncianos() {
        return cantAncianos;
    }

    public void setCantAncianos(Integer cantAncianos) {
        this.cantAncianos = cantAncianos;
    }

    public Integer getCantVisitas() {
        return cantVisitas;
    }

    public void setCantVisitas(Integer cantVisitas) {
        this.cantVisitas = cantVisitas;
    }

    public Integer getCantTotal() {
        return cantTotal;
    }

    public void setCantTotal(Integer cantTotal) {
        this.cantTotal = cantTotal;
    }

    public Comite getIdComite() {
        return idComite;
    }

    public void setIdComite(Comite idComite) {
        this.idComite = idComite;
    }

    public Persona getIdResponsablePpal() {
        return idResponsablePpal;
    }

    public void setIdResponsablePpal(Persona idResponsablePpal) {
        this.idResponsablePpal = idResponsablePpal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asistencia)) {
            return false;
        }
        Asistencia other = (Asistencia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nellehsoft.software_sail.persistencia.Asistencia[ id=" + id + " ]";
    }
    
}
