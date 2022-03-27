/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nellehsoft.software_sail.persistencia;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "vw_reporte_bautizados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VwReporteBautizados.findAll", query = "SELECT v FROM VwReporteBautizados v")
    , @NamedQuery(name = "VwReporteBautizados.findByIdPersona", query = "SELECT v FROM VwReporteBautizados v WHERE v.idPersona = :idPersona")
    , @NamedQuery(name = "VwReporteBautizados.findByFechaBautismo", query = "SELECT v FROM VwReporteBautizados v WHERE v.fechaBautismo BETWEEN :fechaInicio AND :fechaFin")
    , @NamedQuery(name = "VwReporteBautizados.findByDocumentoIdentidad", query = "SELECT v FROM VwReporteBautizados v WHERE v.documentoIdentidad = :documentoIdentidad")
    , @NamedQuery(name = "VwReporteBautizados.findByNombre", query = "SELECT v FROM VwReporteBautizados v WHERE v.nombre = :nombre")
    , @NamedQuery(name = "VwReporteBautizados.findByApellidos", query = "SELECT v FROM VwReporteBautizados v WHERE v.apellidos = :apellidos")

    , @NamedQuery(name = "VwReporteBautizados.findBytotalBautizados", query = "SELECT COUNT(v) FROM VwReporteBautizados v WHERE v.fechaBautismo BETWEEN :fechaInicio AND :fechaFin")        
    , @NamedQuery(name = "VwReporteBautizados.findBytotalBautizadosHombres", query = "SELECT COUNT(v) FROM VwReporteBautizados v WHERE v.fechaBautismo BETWEEN :fechaInicio AND :fechaFin AND v.sexo='Masculino' ")        
    , @NamedQuery(name = "VwReporteBautizados.findBytotalBautizadosMujeres", query = "SELECT COUNT(v) FROM VwReporteBautizados v WHERE v.fechaBautismo BETWEEN :fechaInicio AND :fechaFin AND v.sexo='Femenino' ")                
    , @NamedQuery(name = "VwReporteBautizados.findBytotalBautizadosMembresia", query = "SELECT COUNT(v) FROM VwReporteBautizados v WHERE v.fechaBautismo BETWEEN :fechaInicio AND :fechaFin AND v.estadoMembrecia='Activo' ")                        
    , @NamedQuery(name = "VwReporteBautizados.findBytotalBautizadosSellado", query = "SELECT COUNT(v) FROM VwReporteBautizados v WHERE v.fechaBautismo BETWEEN :fechaInicio AND :fechaFin AND v.sellado='SI' ")                                

    , @NamedQuery(name = "VwReporteBautizados.findBySexo", query = "SELECT v FROM VwReporteBautizados v WHERE v.sexo = :sexo")
    , @NamedQuery(name = "VwReporteBautizados.findBySellado", query = "SELECT v FROM VwReporteBautizados v WHERE v.sellado = :sellado")
    , @NamedQuery(name = "VwReporteBautizados.findByFechaMembreciaLocal", query = "SELECT v FROM VwReporteBautizados v WHERE v.fechaMembreciaLocal = :fechaMembreciaLocal")
    , @NamedQuery(name = "VwReporteBautizados.findByEstadoMembrecia", query = "SELECT v FROM VwReporteBautizados v WHERE v.estadoMembrecia = :estadoMembrecia")
    , @NamedQuery(name = "VwReporteBautizados.findByEdad", query = "SELECT v FROM VwReporteBautizados v WHERE v.edad = :edad")
    , @NamedQuery(name = "VwReporteBautizados.findByClasificacion", query = "SELECT v FROM VwReporteBautizados v WHERE v.clasificacion = :clasificacion")})
public class VwReporteBautizados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "id_persona")
    private int idPersona;
    @Column(name = "fecha_bautismo")
    @Temporal(TemporalType.DATE)
    private Date fechaBautismo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "documento_identidad")
    private String documentoIdentidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "apellidos")
    private String apellidos;
    @Size(max = 50)
    @Column(name = "sexo")
    private String sexo;
    @Size(max = 2)
    @Column(name = "sellado")
    private String sellado;
    @Column(name = "fecha_membrecia_local")
    @Temporal(TemporalType.DATE)
    private Date fechaMembreciaLocal;
    @Size(max = 150)
    @Column(name = "estado_membrecia")
    private String estadoMembrecia;
    @Column(name = "edad")
    private BigInteger edad;
    @Size(max = 11)
    @Column(name = "clasificacion")
    private String clasificacion;

    public VwReporteBautizados() {
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    } 

    public Date getFechaBautismo() {
        return fechaBautismo;
    }

    public void setFechaBautismo(Date fechaBautismo) {
        this.fechaBautismo = fechaBautismo;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSellado() {
        return sellado;
    }

    public void setSellado(String sellado) {
        this.sellado = sellado;
    }

    public Date getFechaMembreciaLocal() {
        return fechaMembreciaLocal;
    }

    public void setFechaMembreciaLocal(Date fechaMembreciaLocal) {
        this.fechaMembreciaLocal = fechaMembreciaLocal;
    }

    public String getEstadoMembrecia() {
        return estadoMembrecia;
    }

    public void setEstadoMembrecia(String estadoMembrecia) {
        this.estadoMembrecia = estadoMembrecia;
    }

    public BigInteger getEdad() {
        return edad;
    }

    public void setEdad(BigInteger edad) {
        this.edad = edad;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }
    
}
