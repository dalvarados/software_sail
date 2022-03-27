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
@Table(name = "vw_recomendacion_carta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VwRecomendacionCarta.findAll", query = "SELECT v FROM VwRecomendacionCarta v")
    , @NamedQuery(name = "VwRecomendacionCarta.findByIdPersona", query = "SELECT v FROM VwRecomendacionCarta v WHERE v.idPersona = :idPersona")
    , @NamedQuery(name = "VwRecomendacionCarta.findByDia", query = "SELECT v FROM VwRecomendacionCarta v WHERE v.dia = :dia")
    , @NamedQuery(name = "VwRecomendacionCarta.findByMes", query = "SELECT v FROM VwRecomendacionCarta v WHERE v.mes = :mes")
    , @NamedQuery(name = "VwRecomendacionCarta.findByAnio", query = "SELECT v FROM VwRecomendacionCarta v WHERE v.anio = :anio")
    , @NamedQuery(name = "VwRecomendacionCarta.findByTipoDocumento", query = "SELECT v FROM VwRecomendacionCarta v WHERE v.tipoDocumento = :tipoDocumento")
    , @NamedQuery(name = "VwRecomendacionCarta.findByDocumentoIdentidad", query = "SELECT v FROM VwRecomendacionCarta v WHERE v.documentoIdentidad = :documentoIdentidad")
    , @NamedQuery(name = "VwRecomendacionCarta.findByNombre", query = "SELECT v FROM VwRecomendacionCarta v WHERE v.nombre = :nombre")
    , @NamedQuery(name = "VwRecomendacionCarta.findByNombreIglesia", query = "SELECT v FROM VwRecomendacionCarta v WHERE v.nombreIglesia = :nombreIglesia")
    , @NamedQuery(name = "VwRecomendacionCarta.findByDireccion", query = "SELECT v FROM VwRecomendacionCarta v WHERE v.direccion = :direccion")
    , @NamedQuery(name = "VwRecomendacionCarta.findByTelefono", query = "SELECT v FROM VwRecomendacionCarta v WHERE v.telefono = :telefono")
    , @NamedQuery(name = "VwRecomendacionCarta.findByPastor", query = "SELECT v FROM VwRecomendacionCarta v WHERE v.pastor = :pastor")
    , @NamedQuery(name = "VwRecomendacionCarta.findByIdentificacionPastor", query = "SELECT v FROM VwRecomendacionCarta v WHERE v.identificacionPastor = :identificacionPastor")
    , @NamedQuery(name = "VwRecomendacionCarta.findByNombreCiudad", query = "SELECT v FROM VwRecomendacionCarta v WHERE v.nombreCiudad = :nombreCiudad")
    , @NamedQuery(name = "VwRecomendacionCarta.findByNombreDepartamento", query = "SELECT v FROM VwRecomendacionCarta v WHERE v.nombreDepartamento = :nombreDepartamento")
    , @NamedQuery(name = "VwRecomendacionCarta.findByNombrePais", query = "SELECT v FROM VwRecomendacionCarta v WHERE v.nombrePais = :nombrePais")})
public class VwRecomendacionCarta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_persona")
    private int idPersona;
    @Size(max = 2)
    @Column(name = "dia")
    private String dia;
    @Size(max = 64)
    @Column(name = "mes")
    private String mes;
    @Size(max = 4)
    @Column(name = "anio")
    private String anio;
    @Size(max = 50)
    @Column(name = "tipo_documento")
    private String tipoDocumento;
    @Size(max = 250)
    @Column(name = "documento_identidad")
    private String documentoIdentidad;
    @Size(max = 501)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(max = 50)
    @Column(name = "hermano")
    private String hermano;
    @Size(max = 50)
    @Column(name = "elhermano")
    private String elhermano;    
    @Size(max = 50)
    @Column(name = "identificado")
    private String identificado;    
    @Size(max = 50)
    @Column(name = "integro")
    private String integro; 
    @Size(max = 50)
    @Column(name = "temeroso")
    private String temeroso;  
    @Size(max = 250)
    @Column(name = "descripcion")
    private String descripcion;    
    @Size(min = 1, max = 250)
    @Column(name = "nombre_iglesia")
    private String nombreIglesia;
    @Size(max = 150)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 45)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 100)
    @Column(name = "pastor")
    private String pastor;
    @Size(max = 45)
    @Column(name = "identificacion_pastor")
    private String identificacionPastor;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "nombre_pais")
    private String nombrePais;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_membrecia_local")
    @Temporal(TemporalType.DATE)
    private Date fechaMembreciaLocal;
    
    public VwRecomendacionCarta() {
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
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

    public String getHermano() {
        return hermano;
    }

    public void setHermano(String hermano) {
        this.hermano = hermano;
    }

    public String getElhermano() {
        return elhermano;
    }

    public void setElhermano(String elhermano) {
        this.elhermano = elhermano;
    }

   
    public String getIdentificado() {
        return identificado;
    }

    public void setIdentificado(String identificado) {
        this.identificado = identificado;
    }

    public String getIntegro() {
        return integro;
    }

    public void setIntegro(String integro) {
        this.integro = integro;
    }

    public String getTemeroso() {
        return temeroso;
    }

    public void setTemeroso(String temeroso) {
        this.temeroso = temeroso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaMembreciaLocal() {
        return fechaMembreciaLocal;
    }

    public void setFechaMembreciaLocal(Date fechaMembreciaLocal) {
        this.fechaMembreciaLocal = fechaMembreciaLocal;
    }

    public String getNombreIglesia() {
        return nombreIglesia;
    }

    public void setNombreIglesia(String nombreIglesia) {
        this.nombreIglesia = nombreIglesia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPastor() {
        return pastor;
    }

    public void setPastor(String pastor) {
        this.pastor = pastor;
    }

    public String getIdentificacionPastor() {
        return identificacionPastor;
    }

    public void setIdentificacionPastor(String identificacionPastor) {
        this.identificacionPastor = identificacionPastor;
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

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }
    
}
