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
@Table(name = "legalizaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Legalizaciones.findAll", query = "SELECT l FROM Legalizaciones l")
    , @NamedQuery(name = "Legalizaciones.findByIdLegalizacion", query = "SELECT l FROM Legalizaciones l WHERE l.idLegalizacion = :idLegalizacion")
    , @NamedQuery(name = "Legalizaciones.findByFechaRegistro", query = "SELECT l FROM Legalizaciones l WHERE l.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Legalizaciones.findByNumeroTipoContable", query = "SELECT l FROM Legalizaciones l WHERE l.numeroTipoContable = :numeroTipoContable")
    , @NamedQuery(name = "Legalizaciones.findByDescripcion", query = "SELECT l FROM Legalizaciones l WHERE l.descripcion = :descripcion")
    , @NamedQuery(name = "Legalizaciones.findByDocReferencia", query = "SELECT l FROM Legalizaciones l WHERE l.docReferencia = :docReferencia")
    , @NamedQuery(name = "Legalizaciones.findByValorDebito", query = "SELECT l FROM Legalizaciones l WHERE l.valorDebito = :valorDebito")
    , @NamedQuery(name = "Legalizaciones.findByValorCredito", query = "SELECT l FROM Legalizaciones l WHERE l.valorCredito = :valorCredito")
    , @NamedQuery(name = "Legalizaciones.findByDiferencia", query = "SELECT l FROM Legalizaciones l WHERE l.diferencia = :diferencia")
    , @NamedQuery(name = "Legalizaciones.findByFechaTipoContable", query = "SELECT l FROM Legalizaciones l WHERE l.fechaTipoContable = :fechaTipoContable")
    , @NamedQuery(name = "Legalizaciones.findByAnulado", query = "SELECT l FROM Legalizaciones l WHERE l.anulado = :anulado")})
public class Legalizaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_legalizacion")
    private Integer idLegalizacion;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Size(max = 100)
    @Column(name = "numero_tipo_contable")
    private String numeroTipoContable;
    @Size(max = 500)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 200)
    @Column(name = "doc_referencia")
    private String docReferencia;
    @Column(name = "valor_debito")
    private Integer valorDebito;
    @Column(name = "valor_credito")
    private Integer valorCredito;
    @Column(name = "diferencia")
    private Integer diferencia;
    @Column(name = "fecha_tipo_contable")
    @Temporal(TemporalType.DATE)
    private Date fechaTipoContable;
    @Column(name = "anulado")
    private Integer anulado;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;
    @JoinColumn(name = "id_puc", referencedColumnName = "id_puc")
    @ManyToOne
    private CuentasPuc idPuc;
    @JoinColumn(name = "id_tipo_contable", referencedColumnName = "id_tipo_contable")
    @ManyToOne
    private TipoContable idTipoContable;
    @JoinColumn(name = "id_tercero", referencedColumnName = "id_persona")
    @ManyToOne
    private Persona idTercero;
    @JoinColumn(name = "id_asiento", referencedColumnName = "id_asiento")
    @ManyToOne
    private AsientoContable idAsiento;

    public Legalizaciones() {
    }

    public Legalizaciones(Integer idLegalizacion) {
        this.idLegalizacion = idLegalizacion;
    }

    public Integer getIdLegalizacion() {
        return idLegalizacion;
    }

    public void setIdLegalizacion(Integer idLegalizacion) {
        this.idLegalizacion = idLegalizacion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getNumeroTipoContable() {
        return numeroTipoContable;
    }

    public void setNumeroTipoContable(String numeroTipoContable) {
        this.numeroTipoContable = numeroTipoContable;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDocReferencia() {
        return docReferencia;
    }

    public void setDocReferencia(String docReferencia) {
        this.docReferencia = docReferencia;
    }

    public Integer getValorDebito() {
        return valorDebito;
    }

    public void setValorDebito(Integer valorDebito) {
        this.valorDebito = valorDebito;
    }

    public Integer getValorCredito() {
        return valorCredito;
    }

    public void setValorCredito(Integer valorCredito) {
        this.valorCredito = valorCredito;
    }

    public Integer getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(Integer diferencia) {
        this.diferencia = diferencia;
    }

    public Date getFechaTipoContable() {
        return fechaTipoContable;
    }

    public void setFechaTipoContable(Date fechaTipoContable) {
        this.fechaTipoContable = fechaTipoContable;
    }

    public Integer getAnulado() {
        return anulado;
    }

    public void setAnulado(Integer anulado) {
        this.anulado = anulado;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public CuentasPuc getIdPuc() {
        return idPuc;
    }

    public void setIdPuc(CuentasPuc idPuc) {
        this.idPuc = idPuc;
    }

    public TipoContable getIdTipoContable() {
        return idTipoContable;
    }

    public void setIdTipoContable(TipoContable idTipoContable) {
        this.idTipoContable = idTipoContable;
    }

    public Persona getIdTercero() {
        return idTercero;
    }

    public void setIdTercero(Persona idTercero) {
        this.idTercero = idTercero;
    }

    public AsientoContable getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(AsientoContable idAsiento) {
        this.idAsiento = idAsiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLegalizacion != null ? idLegalizacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Legalizaciones)) {
            return false;
        }
        Legalizaciones other = (Legalizaciones) object;
        if ((this.idLegalizacion == null && other.idLegalizacion != null) || (this.idLegalizacion != null && !this.idLegalizacion.equals(other.idLegalizacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.Legalizaciones[ idLegalizacion=" + idLegalizacion + " ]";
    }
    
}
