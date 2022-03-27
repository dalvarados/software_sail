/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nellehsoft.software_sail.persistencia;
 
import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Duverdiel
 */
@Entity
@Table(name = "asiento_contable")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsientoContable.findAll", query = "SELECT a FROM AsientoContable a")
    , @NamedQuery(name = "AsientoContable.findByIdAsiento", query = "SELECT a FROM AsientoContable a WHERE a.idAsiento = :idAsiento")
    , @NamedQuery(name = "AsientoContable.findByFechaRegistro", query = "SELECT a FROM AsientoContable a WHERE a.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "AsientoContable.findByFechaTipoContable", query = "SELECT a FROM AsientoContable a WHERE a.fechaTipoContable = :fechaTipoContable")        
    , @NamedQuery(name = "AsientoContable.findByNumeroTipoContable", query = "SELECT a FROM AsientoContable a WHERE a.numeroTipoContable = :numeroTipoContable")
    , @NamedQuery(name = "AsientoContable.findByListaEgresosDetalle", query = "SELECT a FROM AsientoContable a WHERE a.numeroTipoContable = :numeroTipoContable and a.idTipoContable.idTipoContable= :idTipoContable")        
    , @NamedQuery(name = "AsientoContable.findTipoContable", query = "SELECT a FROM AsientoContable a JOIN A.idTipoContable AS c WHERE  c.nombre= :tipo") 
    , @NamedQuery(name = "AsientoContable.findTipoContableIngresos", query = "SELECT a FROM AsientoContable a JOIN A.idTipoContable AS c WHERE  c.nombre= :tipo   and  a.descripcion NOT LIKE 'SI-%' and a.valorDebito is not null")         
    , @NamedQuery(name = "AsientoContable.findTipoContableUnico", query = "SELECT a FROM AsientoContable a JOIN A.idTipoContable AS c WHERE  c.nombre= :tipo and a.valorCredito is not null and SUBSTRING(a.descripcion,1,3)<>'Liq'")         
    , @NamedQuery(name = "AsientoContable.findByDescripcion", query = "SELECT a FROM AsientoContable a WHERE a.descripcion = :descripcion")
    , @NamedQuery(name = "AsientoContable.findByDescripcionTransf", query = "SELECT a FROM AsientoContable a WHERE a.descripcion LIKE 'TR-%'")
    , @NamedQuery(name = "AsientoContable.findByDescripcionSaldoIni", query = "SELECT a FROM AsientoContable a WHERE a.descripcion LIKE 'SI-%' and a.valorDebito is not null")        
    , @NamedQuery(name = "AsientoContable.findByNumCheque", query = "SELECT a FROM AsientoContable a WHERE a.numCheque = :numCheque")
    , @NamedQuery(name = "AsientoContable.findByDocReferencia", query = "SELECT a FROM AsientoContable a WHERE a.docReferencia = :docReferencia")
    , @NamedQuery(name = "AsientoContable.findByValorDebito", query = "SELECT a FROM AsientoContable a WHERE a.valorDebito = :valorDebito")
    , @NamedQuery(name = "AsientoContable.findByValorCredito", query = "SELECT a FROM AsientoContable a WHERE a.valorCredito = :valorCredito")
    , @NamedQuery(name = "AsientoContable.findByAnulado", query = "SELECT a FROM AsientoContable a WHERE a.anulado = :anulado")        
    , @NamedQuery(name = "AsientoContable.findByLegalizacion", query = "SELECT a FROM AsientoContable a WHERE a.legalizacion = :legalizacion")                
    , @NamedQuery(name = "AsientoContable.findByDiferencia", query = "SELECT a FROM AsientoContable a WHERE a.diferencia = :diferencia")})
public class AsientoContable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asiento")
    private Integer idAsiento;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Column(name = "fecha_tipo_contable")
    @Temporal(TemporalType.DATE)
    private Date fechaTipoContable;    
    @Size(max = 100)
    @Column(name = "numero_tipo_contable")
    private String numeroTipoContable;
    @Size(max = 500)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 200)
    @Column(name = "num_cheque")
    private String numCheque;
    @Size(max = 200)
    @Column(name = "doc_referencia")
    private String docReferencia;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_debito")
    private Integer valorDebito;
    @Column(name = "valor_credito")
    private Integer valorCredito;
    @Column(name = "diferencia")
    private Integer diferencia;
    @Column(name = "anulado")
    private Integer anulado;    
    @Column(name = "legalizacion")
    private Integer legalizacion;
//    @OneToMany(mappedBy = "idAsiento")
//    private Collection<Legalizaciones> legalizacionesCollection;
    
    @JoinColumn(name = "id_puc", referencedColumnName = "id_puc")
    @ManyToOne
    private CuentasPuc idPuc;
    @JoinColumn(name = "id_tipo_contable", referencedColumnName = "id_tipo_contable")
    @ManyToOne
    private TipoContable idTipoContable;
    @JoinColumn(name = "id_tercero", referencedColumnName = "id_persona")
    @ManyToOne
    private Persona idTercero;
    @JoinColumn(name = "id_tesorero", referencedColumnName = "id_persona")
    @ManyToOne
    private Persona idTesorero;
    @JoinColumn(name = "id_ciudad", referencedColumnName = "id_ciudad")
    @ManyToOne
    private Ciudad idCiudad;
    @JoinColumn(name = "id_comite", referencedColumnName = "id_comite")
    @ManyToOne
    private Comite idComite;
    @JoinColumn(name = "id_proposito", referencedColumnName = "id_proposito")
    @ManyToOne
    private Proposito idProposito;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;

    public AsientoContable() {
    }

    public AsientoContable(Integer idAsiento) {
        this.idAsiento = idAsiento;
    }

    public Integer getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(Integer idAsiento) {
        this.idAsiento = idAsiento;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    public Date getFechaTipoContable() {
        return fechaTipoContable;
    }

    public void setFechaTipoContable(Date fechaTipoContable) {
        this.fechaTipoContable = fechaTipoContable;
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

    public String getNumCheque() {
        return numCheque;
    }

    public void setNumCheque(String numCheque) {
        this.numCheque = numCheque;
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

    public Persona getIdTesorero() {
        return idTesorero;
    }

    public void setIdTesorero(Persona idTesorero) {
        this.idTesorero = idTesorero;
    }

    public Ciudad getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Ciudad idCiudad) {
        this.idCiudad = idCiudad;
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

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getAnulado() {
        return anulado;
    }

    public void setAnulado(Integer anulado) {
        this.anulado = anulado;
    }

    public Integer getLegalizacion() {
        return legalizacion;
    }

    public void setLegalizacion(Integer legalizacion) {
        this.legalizacion = legalizacion;
    }
    
//    @XmlTransient
//    public Collection<Legalizaciones> getLegalizacionesCollection() {
//        return legalizacionesCollection;
//    }
//
//    public void setLegalizacionesCollection(Collection<Legalizaciones> legalizacionesCollection) {
//        this.legalizacionesCollection = legalizacionesCollection;
//    }    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsiento != null ? idAsiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsientoContable)) {
            return false;
        }
        AsientoContable other = (AsientoContable) object;
        if ((this.idAsiento == null && other.idAsiento != null) || (this.idAsiento != null && !this.idAsiento.equals(other.idAsiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nellehsoft.software_sail.persistencia.AsientoContable[ idAsiento=" + idAsiento + " ]";
    }
    
}
