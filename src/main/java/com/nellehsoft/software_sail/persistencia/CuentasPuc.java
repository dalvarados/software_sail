/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nellehsoft.software_sail.persistencia;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Duverdiel
 */
@Entity
@Table(name = "cuentas_puc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CuentasPuc.findAll", query = "SELECT c FROM CuentasPuc c")
    , @NamedQuery(name = "CuentasPuc.findByIdPuc", query = "SELECT c FROM CuentasPuc c WHERE c.idPuc = :idPuc")
    , @NamedQuery(name = "CuentasPuc.findByCodigoCuenta", query = "SELECT c FROM CuentasPuc c WHERE c.codigoCuenta = :codigoCuenta")
    , @NamedQuery(name = "CuentasPuc.findByNombre", query = "SELECT c FROM CuentasPuc c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "CuentasPuc.findByNombreMayuscula", query = "SELECT c FROM CuentasPuc c WHERE UPPER(c.nombre) = :nombre")        
    , @NamedQuery(name = "CuentasPuc.findByFlag", query = "SELECT c FROM CuentasPuc c WHERE c.flag = :flag")})
public class CuentasPuc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_puc")
    private Integer idPuc;
    @Column(name = "codigo_cuenta")
    private Integer codigoCuenta;
    @Size(max = 500)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "flag")
    private Integer flag;
    @OneToMany(mappedBy = "idPuc")
    private Collection<Ofrenda> ofrendaCollection;

    public CuentasPuc() {
    }

    public CuentasPuc(Integer idPuc) {
        this.idPuc = idPuc;
    }

    public Integer getIdPuc() {
        return idPuc;
    }

    public void setIdPuc(Integer idPuc) {
        this.idPuc = idPuc;
    }

    public Integer getCodigoCuenta() {
        return codigoCuenta;
    }

    public void setCodigoCuenta(Integer codigoCuenta) {
        this.codigoCuenta = codigoCuenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @XmlTransient
    public Collection<Ofrenda> getOfrendaCollection() {
        return ofrendaCollection;
    }

    public void setOfrendaCollection(Collection<Ofrenda> ofrendaCollection) {
        this.ofrendaCollection = ofrendaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPuc != null ? idPuc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuentasPuc)) {
            return false;
        }
        CuentasPuc other = (CuentasPuc) object;
        if ((this.idPuc == null && other.idPuc != null) || (this.idPuc != null && !this.idPuc.equals(other.idPuc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nellehsoft.software_sail.persistencia.CuentasPuc[ idPuc=" + idPuc + " ]";
    }
    
}
