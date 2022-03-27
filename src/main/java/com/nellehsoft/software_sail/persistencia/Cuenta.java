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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "cuenta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuenta.findAll", query = "SELECT c FROM Cuenta c")
    , @NamedQuery(name = "Cuenta.findByIdCuenta", query = "SELECT c FROM Cuenta c WHERE c.idCuenta = :idCuenta")
    , @NamedQuery(name = "Cuenta.findByCodigoPuc", query = "SELECT c FROM Cuenta c WHERE c.codigoPuc = :codigoPuc")
    , @NamedQuery(name = "Cuenta.findByNombreCuentaSinDiezmo", query = "SELECT c FROM Cuenta c WHERE c.nombreCuenta <> 'Diezmo' ")        
    , @NamedQuery(name = "Cuenta.findByNombreCuenta", query = "SELECT c FROM Cuenta c WHERE c.nombreCuenta = :nombreCuenta")})
public class Cuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cuenta")
    private Integer idCuenta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "codigo_puc")
    private String codigoPuc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "nombre_cuenta")
    private String nombreCuenta;
    @OneToMany(mappedBy = "idCuenta")
    private Collection<Diezmo> diezmoCollection;
    @JoinColumn(name = "id_clase_cuenta", referencedColumnName = "id_clase_cuenta")
    @ManyToOne
    private ClaseCuenta idClaseCuenta;
    @OneToMany(mappedBy = "idCuenta")
    private Collection<Ofrenda> ofrendaCollection;

    public Cuenta() {
    }

    public Cuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Cuenta(Integer idCuenta, String codigoPuc, String nombreCuenta) {
        this.idCuenta = idCuenta;
        this.codigoPuc = codigoPuc;
        this.nombreCuenta = nombreCuenta;
    }

    public Integer getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getCodigoPuc() {
        return codigoPuc;
    }

    public void setCodigoPuc(String codigoPuc) {
        this.codigoPuc = codigoPuc;
    }

    public String getNombreCuenta() {
        return nombreCuenta;
    }

    public void setNombreCuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
    }

    @XmlTransient
    public Collection<Diezmo> getDiezmoCollection() {
        return diezmoCollection;
    }

    public void setDiezmoCollection(Collection<Diezmo> diezmoCollection) {
        this.diezmoCollection = diezmoCollection;
    }

    public ClaseCuenta getIdClaseCuenta() {
        return idClaseCuenta;
    }

    public void setIdClaseCuenta(ClaseCuenta idClaseCuenta) {
        this.idClaseCuenta = idClaseCuenta;
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
        hash += (idCuenta != null ? idCuenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuenta)) {
            return false;
        }
        Cuenta other = (Cuenta) object;
        if ((this.idCuenta == null && other.idCuenta != null) || (this.idCuenta != null && !this.idCuenta.equals(other.idCuenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nellehsoft.software_sail.persistencia.Cuenta[ idCuenta=" + idCuenta + " ]";
    }
    
}
