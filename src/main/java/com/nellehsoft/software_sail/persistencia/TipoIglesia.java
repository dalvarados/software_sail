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
@Table(name = "tipo_iglesia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoIglesia.findAll", query = "SELECT t FROM TipoIglesia t")
    , @NamedQuery(name = "TipoIglesia.findByIdTipoIglesia", query = "SELECT t FROM TipoIglesia t WHERE t.idTipoIglesia = :idTipoIglesia")
    , @NamedQuery(name = "TipoIglesia.findByNombreTipoIglesia", query = "SELECT t FROM TipoIglesia t WHERE t.nombreTipoIglesia = :nombreTipoIglesia")})
public class TipoIglesia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_iglesia")
    private Integer idTipoIglesia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "nombre_tipo_iglesia")
    private String nombreTipoIglesia;
    @OneToMany(mappedBy = "idTipoIglesia")
    private Collection<Iglesia> iglesiaCollection;

    public TipoIglesia() {
    }

    public TipoIglesia(Integer idTipoIglesia) {
        this.idTipoIglesia = idTipoIglesia;
    }

    public TipoIglesia(Integer idTipoIglesia, String nombreTipoIglesia) {
        this.idTipoIglesia = idTipoIglesia;
        this.nombreTipoIglesia = nombreTipoIglesia;
    }

    public Integer getIdTipoIglesia() {
        return idTipoIglesia;
    }

    public void setIdTipoIglesia(Integer idTipoIglesia) {
        this.idTipoIglesia = idTipoIglesia;
    }

    public String getNombreTipoIglesia() {
        return nombreTipoIglesia;
    }

    public void setNombreTipoIglesia(String nombreTipoIglesia) {
        this.nombreTipoIglesia = nombreTipoIglesia;
    }

    @XmlTransient
    public Collection<Iglesia> getIglesiaCollection() {
        return iglesiaCollection;
    }

    public void setIglesiaCollection(Collection<Iglesia> iglesiaCollection) {
        this.iglesiaCollection = iglesiaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoIglesia != null ? idTipoIglesia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoIglesia)) {
            return false;
        }
        TipoIglesia other = (TipoIglesia) object;
        if ((this.idTipoIglesia == null && other.idTipoIglesia != null) || (this.idTipoIglesia != null && !this.idTipoIglesia.equals(other.idTipoIglesia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nellehsoft.software_sail.persistencia.TipoIglesia[ idTipoIglesia=" + idTipoIglesia + " ]";
    }
    
}
