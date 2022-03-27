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
@Table(name = "vw_presentacion_ninos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VwPresentacionNinos.findAll", query = "SELECT v FROM VwPresentacionNinos v")
    , @NamedQuery(name = "VwPresentacionNinos.findByIdPersona", query = "SELECT v FROM VwPresentacionNinos v WHERE v.idPersona = :idPersona")
    , @NamedQuery(name = "VwPresentacionNinos.findByFechaRegistro", query = "SELECT v FROM VwPresentacionNinos v WHERE v.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "VwPresentacionNinos.findByNombre", query = "SELECT v FROM VwPresentacionNinos v WHERE v.nombre = :nombre")
    , @NamedQuery(name = "VwPresentacionNinos.findByApellidos", query = "SELECT v FROM VwPresentacionNinos v WHERE v.apellidos = :apellidos")
    , @NamedQuery(name = "VwPresentacionNinos.findByFechaNacimiento", query = "SELECT v FROM VwPresentacionNinos v WHERE v.fechaNacimiento = :fechaNacimiento")
    , @NamedQuery(name = "VwPresentacionNinos.findByDocumentoIdentidad", query = "SELECT v FROM VwPresentacionNinos v WHERE v.documentoIdentidad = :documentoIdentidad")
    , @NamedQuery(name = "VwPresentacionNinos.findByNombrePadres", query = "SELECT v FROM VwPresentacionNinos v WHERE v.nombrePadres = :nombrePadres")
    , @NamedQuery(name = "VwPresentacionNinos.findByFechaInicio", query = "SELECT v FROM VwPresentacionNinos v WHERE v.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "VwPresentacionNinos.findByDescripcionEvento", query = "SELECT v FROM VwPresentacionNinos v WHERE v.descripcionEvento = :descripcionEvento")
    , @NamedQuery(name = "VwPresentacionNinos.findByNombreIglesia", query = "SELECT v FROM VwPresentacionNinos v WHERE v.nombreIglesia = :nombreIglesia")
    , @NamedQuery(name = "VwPresentacionNinos.findByNombreCiudad", query = "SELECT v FROM VwPresentacionNinos v WHERE v.nombreCiudad = :nombreCiudad")
    , @NamedQuery(name = "VwPresentacionNinos.findByNombreDepartamento", query = "SELECT v FROM VwPresentacionNinos v WHERE v.nombreDepartamento = :nombreDepartamento")
    , @NamedQuery(name = "VwPresentacionNinos.findByPastor", query = "SELECT v FROM VwPresentacionNinos v WHERE v.pastor = :pastor")
    , @NamedQuery(name = "VwPresentacionNinos.findByEdad", query = "SELECT v FROM VwPresentacionNinos v WHERE v.edad = :edad")
    , @NamedQuery(name = "VwPresentacionNinos.findByClasificacion", query = "SELECT v FROM VwPresentacionNinos v WHERE v.clasificacion = :clasificacion")})
public class VwPresentacionNinos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_persona")
    @Id
    private int idPersona;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
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
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Size(max = 250)
    @Column(name = "documento_identidad")
    private String documentoIdentidad;
    @Size(max = 200)
    @Column(name = "nombre_padres")
    private String nombrePadres;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Size(max = 250)
    @Column(name = "descripcion_evento")
    private String descripcionEvento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "nombre_iglesia")
    private String nombreIglesia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "nombre_ciudad")
    private String nombreCiudad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "nombre_departamento")
    private String nombreDepartamento;
    @Size(max = 100)
    @Column(name = "pastor")
    private String pastor;
    @Column(name = "edad")
    private BigInteger edad;
    @Size(max = 11)
    @Column(name = "clasificacion")
    private String clasificacion;

    public VwPresentacionNinos() {
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public String getNombrePadres() {
        return nombrePadres;
    }

    public void setNombrePadres(String nombrePadres) {
        this.nombrePadres = nombrePadres;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getDescripcionEvento() {
        return descripcionEvento;
    }

    public void setDescripcionEvento(String descripcionEvento) {
        this.descripcionEvento = descripcionEvento;
    }

    public String getNombreIglesia() {
        return nombreIglesia;
    }

    public void setNombreIglesia(String nombreIglesia) {
        this.nombreIglesia = nombreIglesia;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public String getPastor() {
        return pastor;
    }

    public void setPastor(String pastor) {
        this.pastor = pastor;
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
