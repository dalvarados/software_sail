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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Duverdiel
 */
@Entity
@Table(name = "inventario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inventario.findAll", query = "SELECT i FROM Inventario i")
    , @NamedQuery(name = "Inventario.findByIdInventario", query = "SELECT i FROM Inventario i WHERE i.idInventario = :idInventario")
    , @NamedQuery(name = "Inventario.findByNombre", query = "SELECT i FROM Inventario i WHERE i.nombre = :nombre")
    , @NamedQuery(name = "Inventario.findByPrecioUnitario", query = "SELECT i FROM Inventario i WHERE i.precioUnitario = :precioUnitario")
    , @NamedQuery(name = "Inventario.findByCantidad", query = "SELECT i FROM Inventario i WHERE i.cantidad = :cantidad")
    , @NamedQuery(name = "Inventario.findByPrecioTotal", query = "SELECT i FROM Inventario i WHERE i.precioTotal = :precioTotal")
    , @NamedQuery(name = "Inventario.findByEstado", query = "SELECT i FROM Inventario i WHERE i.estado = :estado")
    , @NamedQuery(name = "Inventario.findByMarca", query = "SELECT i FROM Inventario i WHERE i.marca = :marca")
    , @NamedQuery(name = "Inventario.findByFechaAdquisicion", query = "SELECT i FROM Inventario i WHERE i.fechaAdquisicion = :fechaAdquisicion")
    , @NamedQuery(name = "Inventario.findByPropio", query = "SELECT i FROM Inventario i WHERE i.propio = :propio")
    , @NamedQuery(name = "Inventario.findByCondicion", query = "SELECT i FROM Inventario i WHERE i.condicion = :condicion")
    , @NamedQuery(name = "Inventario.findByObservaciones", query = "SELECT i FROM Inventario i WHERE i.observaciones = :observaciones")
    , @NamedQuery(name = "Inventario.findByFechaBaja", query = "SELECT i FROM Inventario i WHERE i.fechaBaja = :fechaBaja")})
public class Inventario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_inventario")
    private Integer idInventario;
    @Size(max = 250)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "precio_unitario")
    private Integer precioUnitario;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "precio_total")
    private Integer precioTotal;
    @Size(max = 45)
    @Column(name = "estado")
    private String estado;
    @Size(max = 100)
    @Column(name = "marca")
    private String marca;
    @Column(name = "fecha_adquisicion")
    @Temporal(TemporalType.DATE)
    private Date fechaAdquisicion;
    @Column(name = "propio")
    private Short propio;
    @Size(max = 50)
    @Column(name = "condicion")
    private String condicion;
    @Size(max = 250)
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "fecha_baja")
    @Temporal(TemporalType.DATE)
    private Date fechaBaja;
    @JoinColumn(name = "id_comite", referencedColumnName = "id_comite")
    @ManyToOne
    private Comite idComite;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne
    private Persona idPersona;

    public Inventario() {
    }

    public Inventario(Integer idInventario) {
        this.idInventario = idInventario;
    }

    public Integer getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(Integer idInventario) {
        this.idInventario = idInventario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Integer precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Integer precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Date getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(Date fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public Short getPropio() {
        return propio;
    }

    public void setPropio(Short propio) {
        this.propio = propio;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public Comite getIdComite() {
        return idComite;
    }

    public void setIdComite(Comite idComite) {
        this.idComite = idComite;
    }

    public Persona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Persona idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInventario != null ? idInventario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inventario)) {
            return false;
        }
        Inventario other = (Inventario) object;
        if ((this.idInventario == null && other.idInventario != null) || (this.idInventario != null && !this.idInventario.equals(other.idInventario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nellehsoft.software_sail.persistencia1.Inventario[ idInventario=" + idInventario + " ]";
    }
    
}
