/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nellehsoft.software_sail.persistencia;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Duverdiel
 */
@Entity
@Table(name = "vw_asistencia_mensual")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VwAsistenciaMensual.findAll", query = "SELECT v FROM VwAsistenciaMensual v")
    , @NamedQuery(name = "VwAsistenciaMensual.findByFecha", query = "SELECT v FROM VwAsistenciaMensual v WHERE v.fecha = :fecha")
    , @NamedQuery(name = "VwAsistenciaMensual.findByNombreComite", query = "SELECT v FROM VwAsistenciaMensual v WHERE v.nombreComite = :nombreComite")
    , @NamedQuery(name = "VwAsistenciaMensual.findById", query = "SELECT v FROM VwAsistenciaMensual v WHERE v.id = :id")    
    , @NamedQuery(name = "VwAsistenciaMensual.findByValor", query = "SELECT v FROM VwAsistenciaMensual v WHERE v.valor = :valor")})
public class VwAsistenciaMensual implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 7)
    @Column(name = "fecha")
    private String fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "nombre_comite")
    private String nombreComite;
    @Column(name = "valor")
    private BigInteger valor;
    @Id
    private Long id;

    public VwAsistenciaMensual() {
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombreComite() {
        return nombreComite;
    }

    public void setNombreComite(String nombreComite) {
        this.nombreComite = nombreComite;
    }

    public BigInteger getValor() {
        return valor;
    }

    public void setValor(BigInteger valor) {
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
