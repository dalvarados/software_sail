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
@Table(name = "tipo_contable")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoContable.findAll", query = "SELECT t FROM TipoContable t")
    , @NamedQuery(name = "TipoContable.findByIdTipoContable", query = "SELECT t FROM TipoContable t WHERE t.idTipoContable = :idTipoContable")
    , @NamedQuery(name = "TipoContable.findByNombre", query = "SELECT t FROM TipoContable t WHERE t.nombre = :nombre")})
public class TipoContable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_contable")
    private Integer idTipoContable;
    @Size(max = 250)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "idTipoContable")
    private Collection<AsientoContable> asientoContableCollection;

    public TipoContable() {
    }

    public TipoContable(Integer idTipoContable) {
        this.idTipoContable = idTipoContable;
    }

    public Integer getIdTipoContable() {
        return idTipoContable;
    }

    public void setIdTipoContable(Integer idTipoContable) {
        this.idTipoContable = idTipoContable;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<AsientoContable> getAsientoContableCollection() {
        return asientoContableCollection;
    }

    public void setAsientoContableCollection(Collection<AsientoContable> asientoContableCollection) {
        this.asientoContableCollection = asientoContableCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoContable != null ? idTipoContable.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoContable)) {
            return false;
        }
        TipoContable other = (TipoContable) object;
        if ((this.idTipoContable == null && other.idTipoContable != null) || (this.idTipoContable != null && !this.idTipoContable.equals(other.idTipoContable))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nellehsoft.software_sail.persistencia.TipoContable[ idTipoContable=" + idTipoContable + " ]";
    }
    
}
