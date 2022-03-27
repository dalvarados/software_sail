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
@Table(name = "ofrenda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ofrenda.findAll", query = "SELECT o FROM Ofrenda o")
    , @NamedQuery(name = "Ofrenda.findByIdOfrenda", query = "SELECT o FROM Ofrenda o WHERE o.idOfrenda = :idOfrenda")
    , @NamedQuery(name = "Ofrenda.findByFechaOfrenda", query = "SELECT o FROM Ofrenda o WHERE o.fechaOfrenda = :fechaOfrenda")
    , @NamedQuery(name = "Ofrenda.findByValorOfrenda", query = "SELECT o FROM Ofrenda o WHERE o.valorOfrenda = :valorOfrenda")
    , @NamedQuery(name = "Ofrenda.findByNumIngreso", query = "SELECT o FROM Ofrenda o WHERE o.numIngreso = :numIngreso")})
public class Ofrenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ofrenda")
    private Integer idOfrenda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_ofrenda")
    @Temporal(TemporalType.DATE)
    private Date fechaOfrenda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_ofrenda")
    private int valorOfrenda;
    @Size(max = 50)
    @Column(name = "num_ingreso")
    private String numIngreso;
    @JoinColumn(name = "id_comite", referencedColumnName = "id_comite")
    @ManyToOne(optional = false)
    private Comite idComite;
    @JoinColumn(name = "id_proposito", referencedColumnName = "id_proposito")
    @ManyToOne(optional = false)
    private Proposito idProposito;
    @JoinColumn(name = "id_responsable_ppal", referencedColumnName = "id_persona")
    @ManyToOne
    private Persona idResponsablePpal;
    @JoinColumn(name = "id_responsable_sec", referencedColumnName = "id_persona")
    @ManyToOne
    private Persona idResponsableSec;
    @JoinColumn(name = "id_cuenta", referencedColumnName = "id_cuenta")
    @ManyToOne
    private Cuenta idCuenta;
    @JoinColumn(name = "id_puc", referencedColumnName = "id_puc")
    @ManyToOne
    private CuentasPuc idPuc;

    public Ofrenda() {
    }

    public Ofrenda(Integer idOfrenda) {
        this.idOfrenda = idOfrenda;
    }

    public Ofrenda(Integer idOfrenda, Date fechaOfrenda, int valorOfrenda) {
        this.idOfrenda = idOfrenda;
        this.fechaOfrenda = fechaOfrenda;
        this.valorOfrenda = valorOfrenda;
    }

    public Integer getIdOfrenda() {
        return idOfrenda;
    }

    public void setIdOfrenda(Integer idOfrenda) {
        this.idOfrenda = idOfrenda;
    }

    public Date getFechaOfrenda() {
        return fechaOfrenda;
    }

    public void setFechaOfrenda(Date fechaOfrenda) {
        this.fechaOfrenda = fechaOfrenda;
    }

    public int getValorOfrenda() {
        return valorOfrenda;
    }

    public void setValorOfrenda(int valorOfrenda) {
        this.valorOfrenda = valorOfrenda;
    }

    public String getNumIngreso() {
        return numIngreso;
    }

    public void setNumIngreso(String numIngreso) {
        this.numIngreso = numIngreso;
    }

    public Comite getIdComite() {
        return idComite;
    }

    public void setIdComite(Comite idComite) {
        this.idComite = idComite;
    }

    public Proposito getIdProposito() {
        return idProposito;
    }

    public void setIdProposito(Proposito idProposito) {
        this.idProposito = idProposito;
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

    public CuentasPuc getIdPuc() {
        return idPuc;
    }

    public void setIdPuc(CuentasPuc idPuc) {
        this.idPuc = idPuc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOfrenda != null ? idOfrenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ofrenda)) {
            return false;
        }
        Ofrenda other = (Ofrenda) object;
        if ((this.idOfrenda == null && other.idOfrenda != null) || (this.idOfrenda != null && !this.idOfrenda.equals(other.idOfrenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nellehsoft.software_sail.persistencia.Ofrenda[ idOfrenda=" + idOfrenda + " ]";
    }
    
}
