/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nellehsoft.software_sail.persistencia;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Duverdiel
 */
@Entity
@Table(name = "banco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Banco.findAll", query = "SELECT b FROM Banco b")
    , @NamedQuery(name = "Banco.findByIdBanco", query = "SELECT b FROM Banco b WHERE b.idBanco = :idBanco")
    , @NamedQuery(name = "Banco.findByNombreBanco", query = "SELECT b FROM Banco b WHERE b.nombreBanco = :nombreBanco")
    , @NamedQuery(name = "Banco.findByNumeroCuentaBanco", query = "SELECT b FROM Banco b WHERE b.numeroCuentaBanco = :numeroCuentaBanco")
    , @NamedQuery(name = "Banco.findByTipoCuenta", query = "SELECT b FROM Banco b WHERE b.tipoCuenta = :tipoCuenta")})
public class Banco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_banco")
    private Integer idBanco;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "nombre_banco")
    private String nombreBanco;
    @Size(max = 150)
    @Column(name = "numero_cuenta_banco")
    private String numeroCuentaBanco;
    @Size(max = 45)
    @Column(name = "tipo_cuenta")
    private String tipoCuenta;
    @JoinColumn(name = "id_puc", referencedColumnName = "id_puc")
    @ManyToOne
    private CuentasPuc idPuc;
    public Banco() {
    }

    public Banco(Integer idBanco) {
        this.idBanco = idBanco;
    }

    public Banco(Integer idBanco, String nombreBanco) {
        this.idBanco = idBanco;
        this.nombreBanco = nombreBanco;
    }

    public Integer getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public String getNumeroCuentaBanco() {
        return numeroCuentaBanco;
    }

    public void setNumeroCuentaBanco(String numeroCuentaBanco) {
        this.numeroCuentaBanco = numeroCuentaBanco;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }
    
    public CuentasPuc getIdPuc() {
        return idPuc;
    }

    public void setIdPuc(CuentasPuc idPuc) {
        this.idPuc = idPuc;
    }    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBanco != null ? idBanco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Banco)) {
            return false;
        }
        Banco other = (Banco) object;
        if ((this.idBanco == null && other.idBanco != null) || (this.idBanco != null && !this.idBanco.equals(other.idBanco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nellehsoft.software_sail.persistencia.Banco[ idBanco=" + idBanco + " ]";
    }
    
}
