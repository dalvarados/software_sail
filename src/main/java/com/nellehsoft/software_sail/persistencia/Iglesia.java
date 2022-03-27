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
@Table(name = "iglesia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Iglesia.findAll", query = "SELECT i FROM Iglesia i")
    , @NamedQuery(name = "Iglesia.findByIdIglesia", query = "SELECT i FROM Iglesia i WHERE i.idIglesia = :idIglesia")
    , @NamedQuery(name = "Iglesia.findByPastor", query = "SELECT i FROM Iglesia i WHERE i.pastor = :pastor")
    , @NamedQuery(name = "Iglesia.findByDireccion", query = "SELECT i FROM Iglesia i WHERE i.direccion = :direccion")  
    , @NamedQuery(name = "Iglesia.findByTelefono", query = "SELECT i FROM Iglesia i WHERE i.telefono = :telefono")  
    , @NamedQuery(name = "Iglesia.findByCorreo", query = "SELECT i FROM Iglesia i WHERE i.correo = :correo")        
    , @NamedQuery(name = "Iglesia.findByTipoIglesia", query = "SELECT i.nombreIglesia FROM Iglesia i WHERE i.tipoIglesia = :tipoIglesia AND i.idIglesia IN(1)")                
    , @NamedQuery(name = "Iglesia.findByTipoIglesiaLocal", query = "SELECT i FROM Iglesia i WHERE i.tipoIglesia = :tipoIglesia AND i.idIglesia IN(1)")                        
    , @NamedQuery(name = "Iglesia.findByTipoListaIglesia", query = "SELECT  i FROM Iglesia i WHERE  i.tipoIglesia = :tipoIglesia AND i.idIglesia IN(-1,1)")                        
    , @NamedQuery(name = "Iglesia.findByNombreIglesia", query = "SELECT i FROM Iglesia i WHERE i.nombreIglesia = :nombreIglesia")})
public class Iglesia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_iglesia")
    private Integer idIglesia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "nombre_iglesia")
    private String nombreIglesia;
    
    @Size(min = 1, max = 250)
    @Column(name = "pastor")
    private String pastor;
    
    @Size(min = 1, max = 250)
    @Column(name = "direccion")
    private String direccion;

    @Size(min = 1, max = 250)
    @Column(name = "tipo_iglesia")
    private String tipoIglesia;
    
    @Size(min = 1, max = 250)
    @Column(name = "telefono")
    private String telefono;

    @Size(min = 1, max = 250)
    @Column(name = "correo")
    private String correo;
    
    @OneToMany(mappedBy = "idIglesiaBautismo")
    private Collection<Persona> personaCollection;
    @OneToMany(mappedBy = "idIglesiaAnterior")
    private Collection<Persona> personaCollection1;
    @JoinColumn(name = "id_ciudad", referencedColumnName = "id_ciudad")
    @ManyToOne
    private Ciudad idCiudad;
    @JoinColumn(name = "id_tipo_iglesia", referencedColumnName = "id_tipo_iglesia")
    @ManyToOne
    private TipoIglesia idTipoIglesia;

    public Iglesia() {
    }

    public Iglesia(Integer idIglesia) {
        this.idIglesia = idIglesia;
    }

    public String getTipoIglesia() {
        return tipoIglesia;
    }

    public void setTipoIglesia(String tipoIglesia) {
        this.tipoIglesia = tipoIglesia;
    }

    
    public Iglesia(Integer idIglesia, String nombreIglesia) {
        this.idIglesia = idIglesia;
        this.nombreIglesia = nombreIglesia;
    }

    public Integer getIdIglesia() {
        return idIglesia;
    }

    public void setIdIglesia(Integer idIglesia) {
        this.idIglesia = idIglesia;
    }

    public String getNombreIglesia() {
        return nombreIglesia;
    }

    public void setNombreIglesia(String nombreIglesia) {
        this.nombreIglesia = nombreIglesia;
    }

    @XmlTransient
    public Collection<Persona> getPersonaCollection() {
        return personaCollection;
    }

    public void setPersonaCollection(Collection<Persona> personaCollection) {
        this.personaCollection = personaCollection;
    }

    @XmlTransient
    public Collection<Persona> getPersonaCollection1() {
        return personaCollection1;
    }

    public void setPersonaCollection1(Collection<Persona> personaCollection1) {
        this.personaCollection1 = personaCollection1;
    }

    public Ciudad getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Ciudad idCiudad) {
        this.idCiudad = idCiudad;
    }

    public TipoIglesia getIdTipoIglesia() {
        return idTipoIglesia;
    }

    public void setIdTipoIglesia(TipoIglesia idTipoIglesia) {
        this.idTipoIglesia = idTipoIglesia;
    }

    public String getPastor() {
        return pastor;
    }

    public void setPastor(String pastor) {
        this.pastor = pastor;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIglesia != null ? idIglesia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Iglesia)) {
            return false;
        }
        Iglesia other = (Iglesia) object;
        if ((this.idIglesia == null && other.idIglesia != null) || (this.idIglesia != null && !this.idIglesia.equals(other.idIglesia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nellehsoft.software_sail.persistencia.Iglesia[ idIglesia=" + idIglesia + " ]";
    }
    
}
