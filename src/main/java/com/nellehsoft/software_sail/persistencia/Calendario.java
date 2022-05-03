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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Duverdiel
 */
@Entity
@Table(name = "calendario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Calendario.findAll", query = "SELECT c FROM Calendario c")
    , @NamedQuery(name = "Calendario.findByIdCalendario", query = "SELECT c FROM Calendario c WHERE c.idCalendario = :idCalendario")
    , @NamedQuery(name = "Calendario.findByTituloEvento", query = "SELECT c FROM Calendario c WHERE c.tituloEvento = :tituloEvento")
    , @NamedQuery(name = "Calendario.findByFechaInicio", query = "SELECT c FROM Calendario c WHERE c.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Calendario.findByFechaFin", query = "SELECT c FROM Calendario c WHERE c.fechaFin = :fechaFin")
    , @NamedQuery(name = "Calendario.findByCalendarioRol", query = "SELECT c FROM Calendario c JOIN C.idRol AS a WHERE  a.idRol = :idRol")           
    , @NamedQuery(name = "Calendario.findByLugar", query = "SELECT c FROM Calendario c WHERE c.lugar = :lugar")        
    , @NamedQuery(name = "Calendario.findByTipo", query = "SELECT DISTINCT c FROM Calendario c WHERE c.tipo = :tipo ORDER BY c.fechaInicio DESC")                
    , @NamedQuery(name = "Calendario.findByDescripcionEvento", query = "SELECT c FROM Calendario c WHERE c.descripcionEvento = :descripcionEvento")
    , @NamedQuery(name = "Calendario.findByEstadoEvento", query = "SELECT c FROM Calendario c WHERE c.estadoEvento = :estadoEvento")
})
public class Calendario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_calendario")
    private Integer idCalendario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "titulo_evento")
    private String tituloEvento;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Size(max = 250)
    @Column(name = "descripcion_evento")
    private String descripcionEvento;
    @Column(name = "estado_evento")
    private boolean estadoEvento;
    @JoinColumn(name = "id_comite", referencedColumnName = "id_comite")
    @ManyToOne(optional = false)
    private Comite idComite;
    @Size(max = 100)
    @Column(name = "lugar")
    private String lugar;
    @Size(max = 45)
    @Column(name = "tipo")
    private String tipo;
    @Size(max = 45)
    @Column(name = "hora_inicio")
    private String horaInicio;    
    @Size(max = 45)
    @Column(name = "hora_fin")
    private String horaFin;    
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    @ManyToOne(optional = false)
    private Rol idRol; 
    @Column(name = "documento_identidad")    
    private Integer documentoIdentidad;
    
    public Calendario() {
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public boolean isEstadoEvento() {
        return estadoEvento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
       
    public Calendario(Integer idCalendario) {
        this.idCalendario = idCalendario;
    }

    public Calendario(Integer idCalendario, String tituloEvento) {
        this.idCalendario = idCalendario;
        this.tituloEvento = tituloEvento;
    }

    public Integer getIdCalendario() {
        return idCalendario;
    }

    public void setIdCalendario(Integer idCalendario) {
        this.idCalendario = idCalendario;
    }

    public String getTituloEvento() {
        return tituloEvento;
    }

    public void setTituloEvento(String tituloEvento) {
        this.tituloEvento = tituloEvento;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getDescripcionEvento() {
        return descripcionEvento;
    }

    public void setDescripcionEvento(String descripcionEvento) {
        this.descripcionEvento = descripcionEvento;
    }

    public Boolean getEstadoEvento() {
        return estadoEvento;
    }

    public void setEstadoEvento(Boolean estadoEvento) {
        this.estadoEvento = estadoEvento;
    }

    public Comite getIdComite() {
        return idComite;
    }

    public void setIdComite(Comite idComite) {
        this.idComite = idComite;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Rol getIdRol() {
        return idRol;
    }

    public void setIdRol(Rol idRol) {
        this.idRol = idRol;
    }

    public Integer getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(Integer documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCalendario != null ? idCalendario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calendario)) {
            return false;
        }
        Calendario other = (Calendario) object;
        if ((this.idCalendario == null && other.idCalendario != null) || (this.idCalendario != null && !this.idCalendario.equals(other.idCalendario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nellehsoft.software_sail.persistencia.Calendario[ idCalendario=" + idCalendario + " ]";
    }
    
}
