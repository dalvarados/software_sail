/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nellehsoft.software_sail.persistencia;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Duverdiel
 */
@Entity
@Table(name = "vw_diezmo_mensual")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VwDiezmoMensual.findAll", query = "SELECT v FROM VwDiezmoMensual v")
    , @NamedQuery(name = "VwDiezmoMensual.findByFecha", query = "SELECT v FROM VwDiezmoMensual v WHERE v.fecha = :fecha")
    , @NamedQuery(name = "VwDiezmoMensual.findByValor", query = "SELECT v FROM VwDiezmoMensual v WHERE v.valor = :valor")})
public class VwDiezmoMensual implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 7)
    @Column(name = "fecha")
    @Id
    private String fecha;
    @Column(name = "valor")
    private BigInteger valor;

    public VwDiezmoMensual() {
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public BigInteger getValor() {
        return valor;
    }

    public void setValor(BigInteger valor) {
        this.valor = valor;
    }
    
}
