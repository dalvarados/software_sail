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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Duverdiel
 */
@Entity
@Table(name = "clase_cuenta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClaseCuenta.findAll", query = "SELECT c FROM ClaseCuenta c")
    , @NamedQuery(name = "ClaseCuenta.findByIdClaseCuenta", query = "SELECT c FROM ClaseCuenta c WHERE c.idClaseCuenta = :idClaseCuenta")
    , @NamedQuery(name = "ClaseCuenta.findByNombreClaseCuenta", query = "SELECT c FROM ClaseCuenta c WHERE c.nombreClaseCuenta = :nombreClaseCuenta")})
public class ClaseCuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_clase_cuenta")
    private Integer idClaseCuenta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "nombre_clase_cuenta")
    private String nombreClaseCuenta;
    @OneToMany(mappedBy = "idClaseCuenta")
    private Collection<Cuenta> cuentaCollection;

    public ClaseCuenta() {
    }

    public ClaseCuenta(Integer idClaseCuenta) {
        this.idClaseCuenta = idClaseCuenta;
    }

    public ClaseCuenta(Integer idClaseCuenta, String nombreClaseCuenta) {
        this.idClaseCuenta = idClaseCuenta;
        this.nombreClaseCuenta = nombreClaseCuenta;
    }

    public Integer getIdClaseCuenta() {
        return idClaseCuenta;
    }

    public void setIdClaseCuenta(Integer idClaseCuenta) {
        this.idClaseCuenta = idClaseCuenta;
    }

    public String getNombreClaseCuenta() {
        return nombreClaseCuenta;
    }

    public void setNombreClaseCuenta(String nombreClaseCuenta) {
        this.nombreClaseCuenta = nombreClaseCuenta;
    }

    @XmlTransient
    public Collection<Cuenta> getCuentaCollection() {
        return cuentaCollection;
    }

    public void setCuentaCollection(Collection<Cuenta> cuentaCollection) {
        this.cuentaCollection = cuentaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClaseCuenta != null ? idClaseCuenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClaseCuenta)) {
            return false;
        }
        ClaseCuenta other = (ClaseCuenta) object;
        if ((this.idClaseCuenta == null && other.idClaseCuenta != null) || (this.idClaseCuenta != null && !this.idClaseCuenta.equals(other.idClaseCuenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nellehsoft.software_sail.persistencia.ClaseCuenta[ idClaseCuenta=" + idClaseCuenta + " ]";
    }
    
}
