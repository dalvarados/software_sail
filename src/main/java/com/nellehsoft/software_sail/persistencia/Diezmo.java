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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Duverdiel
 */
@Entity
@Table(name = "diezmo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Diezmo.findAll", query = "SELECT d FROM Diezmo d")
    , @NamedQuery(name = "Diezmo.findByIdDiezmo", query = "SELECT d FROM Diezmo d WHERE d.idDiezmo = :idDiezmo")
    , @NamedQuery(name = "Diezmo.findByFechaDiezmo", query = "SELECT d FROM Diezmo d WHERE d.fechaDiezmo = :fechaDiezmo")
    , @NamedQuery(name = "Diezmo.findByValorDiezmo", query = "SELECT d FROM Diezmo d WHERE d.valorDiezmo = :valorDiezmo")
    , @NamedQuery(name = "Diezmo.findByNumIngreso", query = "SELECT d FROM Diezmo d WHERE d.numIngreso = :numIngreso")        
    , @NamedQuery(name = "Diezmo.findByValorCorpenteUnida", query = "SELECT d FROM Diezmo d WHERE d.valorCorpenteUnida = :valorCorpenteUnida")
    , @NamedQuery(name = "Diezmo.findByValorFondoLocal", query = "SELECT d FROM Diezmo d WHERE d.valorFondoLocal = :valorFondoLocal")
    , @NamedQuery(name = "Diezmo.findByValorPastor", query = "SELECT d FROM Diezmo d WHERE d.valorPastor = :valorPastor")
    , @NamedQuery(name = "Diezmo.findByFechaInicio", query = "SELECT d FROM Diezmo d WHERE d.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Diezmo.findByFechaFin", query = "SELECT d FROM Diezmo d WHERE d.fechaFin = :fechaFin")})
public class Diezmo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_diezmo")
    private Integer idDiezmo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_diezmo")
    @Temporal(TemporalType.DATE)
    private Date fechaDiezmo;
    @Column(name = "valor_diezmo")
    private Integer valorDiezmo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_corpente_unida")
    private Double valorCorpenteUnida;
    @Column(name = "valor_fondo_local")
    private Double valorFondoLocal;
    @Column(name = "valor_pastor")
    private Double valorPastor;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Size(max = 100)
    @Column(name = "num_ingreso")
    private String numIngreso;    
    @JoinColumn(name = "id_puc", referencedColumnName = "id_puc")
    @ManyToOne
    private CuentasPuc idPuc;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne
    private Persona idPersona;
    @JoinColumn(name = "id_responsable_ppal", referencedColumnName = "id_persona")
    @ManyToOne
    private Persona idResponsablePpal;
    @JoinColumn(name = "id_responsable_sec", referencedColumnName = "id_persona")
    @ManyToOne
    private Persona idResponsableSec;
    @JoinColumn(name = "id_cuenta", referencedColumnName = "id_cuenta")
    @ManyToOne
    private Cuenta idCuenta;

    public Diezmo() {
    }

    public Diezmo(Integer idDiezmo) {
        this.idDiezmo = idDiezmo;
    }

    public Diezmo(Integer idDiezmo, Date fechaDiezmo) {
        this.idDiezmo = idDiezmo;
        this.fechaDiezmo = fechaDiezmo;
    }

    public Integer getIdDiezmo() {
        return idDiezmo;
    }

    public void setIdDiezmo(Integer idDiezmo) {
        this.idDiezmo = idDiezmo;
    }

    public Date getFechaDiezmo() {
        return fechaDiezmo;
    }

    public void setFechaDiezmo(Date fechaDiezmo) {
        this.fechaDiezmo = fechaDiezmo;
    }

    public String getNumIngreso() {
        return numIngreso;
    }

    public void setNumIngreso(String numIngreso) {
        this.numIngreso = numIngreso;
    }

    public Integer getValorDiezmo() {
        return valorDiezmo;
    }

    public void setValorDiezmo(Integer valorDiezmo) {
        this.valorDiezmo = valorDiezmo;
    }

    public Double getValorCorpenteUnida() {
        return valorCorpenteUnida;
    }

    public void setValorCorpenteUnida(Double valorCorpenteUnida) {
        this.valorCorpenteUnida = valorCorpenteUnida;
    }

    public Double getValorFondoLocal() {
        return valorFondoLocal;
    }

    public void setValorFondoLocal(Double valorFondoLocal) {
        this.valorFondoLocal = valorFondoLocal;
    }

    public Double getValorPastor() {
        return valorPastor;
    }

    public void setValorPastor(Double valorPastor) {
        this.valorPastor = valorPastor;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public CuentasPuc getIdPuc() {
        return idPuc;
    }

    public void setIdPuc(CuentasPuc idPuc) {
        this.idPuc = idPuc;
    }

    public Persona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Persona idPersona) {
        this.idPersona = idPersona;
    }

    public Persona getIdResponsablePpal() {
        return idResponsablePpal;
    }

    public void setIdResponsablePpal(Persona idResponsablePpal) {
        this.idResponsablePpal = idResponsablePpal;
    }

    public Persona getIdResponsableSec() {
        return idResponsableSec;
    }

    public void setIdResponsableSec(Persona idResponsableSec) {
        this.idResponsableSec = idResponsableSec;
    }

    public Cuenta getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Cuenta idCuenta) {
        this.idCuenta = idCuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDiezmo != null ? idDiezmo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Diezmo)) {
            return false;
        }
        Diezmo other = (Diezmo) object;
        if ((this.idDiezmo == null && other.idDiezmo != null) || (this.idDiezmo != null && !this.idDiezmo.equals(other.idDiezmo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nellehsoft.software_sail.persistencia.Diezmo[ idDiezmo=" + idDiezmo + " ]";
    }
    
}
