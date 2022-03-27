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
@Table(name = "matrimonio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Matrimonio.findAll", query = "SELECT m FROM Matrimonio m")
    , @NamedQuery(name = "Matrimonio.findByIdMatrimonio", query = "SELECT m FROM Matrimonio m WHERE m.idMatrimonio = :idMatrimonio")
    , @NamedQuery(name = "Matrimonio.findByOtroPastor", query = "SELECT m FROM Matrimonio m WHERE m.otroPastor = :otroPastor")
    , @NamedQuery(name = "Matrimonio.findByFolio", query = "SELECT m FROM Matrimonio m WHERE m.folio = :folio")
    , @NamedQuery(name = "Matrimonio.findByCodRegistro", query = "SELECT m FROM Matrimonio m WHERE m.codRegistro = :codRegistro")
    , @NamedQuery(name = "Matrimonio.findByNotaria", query = "SELECT m FROM Matrimonio m WHERE m.notaria = :notaria")
    , @NamedQuery(name = "Matrimonio.findBynombreEsposoExterno", query = "SELECT m FROM Matrimonio m WHERE m.esposoExterno = :esposoExterno") 
    , @NamedQuery(name = "Matrimonio.findBynombreEsposaExterna", query = "SELECT m FROM Matrimonio m WHERE m.esposaExterna = :esposaExterna")
    , @NamedQuery(name = "Matrimonio.findByFechaMatrimonioCivil", query = "SELECT m FROM Matrimonio m WHERE m.fechaMatrimonioCivil = :fechaMatrimonioCivil")
    , @NamedQuery(name = "Matrimonio.findByFechaMatrimonioIglesia", query = "SELECT m FROM Matrimonio m WHERE m.fechaMatrimonioIglesia = :fechaMatrimonioIglesia")
    , @NamedQuery(name = "Matrimonio.findByLugar", query = "SELECT m FROM Matrimonio m WHERE m.lugar = :lugar")
    , @NamedQuery(name = "Matrimonio.findByCursoPrematrimonial", query = "SELECT m FROM Matrimonio m WHERE m.cursoPrematrimonial = :cursoPrematrimonial")})
public class Matrimonio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_matrimonio")
    private Integer idMatrimonio;
    @Size(max = 100)
    @Column(name = "otro_pastor")
    private String otroPastor;
    @Size(max = 50)
    @Column(name = "folio")
    private String folio;
    @Size(max = 50)
    @Column(name = "cod_registro")
    private String codRegistro;
    @Size(max = 50)
    @Column(name = "notaria")
    private String notaria;
    @Column(name = "fecha_matrimonio_civil")
    @Temporal(TemporalType.DATE)
    private Date fechaMatrimonioCivil;
    @Column(name = "fecha_matrimonio_iglesia")
    @Temporal(TemporalType.DATE)
    private Date fechaMatrimonioIglesia;
    @Size(max = 70)
    @Column(name = "lugar")
    private String lugar;
    @Column(name = "curso_prematrimonial")
    private Boolean cursoPrematrimonial;
    @JoinColumn(name = "id_esposo",nullable = true, referencedColumnName = "id_persona")
    @ManyToOne(optional = true)
    private Persona idEsposo;
    @JoinColumn(name = "id_esposa",nullable = true, referencedColumnName = "id_persona")
    @ManyToOne(optional = true)
    private Persona idEsposa;
    @JoinColumn(name = "id_pastor",nullable = true, referencedColumnName = "id_persona")
    @ManyToOne(optional = true)
    private Persona idPastor;
    @Size(max = 150)
    @Column(name = "nombre_esposo_externo")
    private String nombreEsposoExterno;    
    @Size(max = 150)
    @Column(name = "nombre_esposa_externa")
    private String nombreEsposaExterna;
    @Column(name = "esposo_externo")
    private Boolean esposoExterno; 
    @Column(name = "esposa_externa")
    private Boolean esposaExterna; 
    @Column(name = "pastor_externo")
    private Boolean pastorExterno; 
    @Size(max = 300)
    @Column(name = "observaciones")
    private String observaciones;
    
    public Matrimonio() {
    }

    public Matrimonio(Integer idMatrimonio) {
        this.idMatrimonio = idMatrimonio;
    }

    public Integer getIdMatrimonio() {
        return idMatrimonio;
    }

    public void setIdMatrimonio(Integer idMatrimonio) {
        this.idMatrimonio = idMatrimonio;
    }

    public String getOtroPastor() {
        return otroPastor;
    }

    public void setOtroPastor(String otroPastor) {
        this.otroPastor = otroPastor;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getCodRegistro() {
        return codRegistro;
    }

    public void setCodRegistro(String codRegistro) {
        this.codRegistro = codRegistro;
    }

    public String getNotaria() {
        return notaria;
    }

    public void setNotaria(String notaria) {
        this.notaria = notaria;
    }

    public Date getFechaMatrimonioCivil() {
        return fechaMatrimonioCivil;
    }

    public void setFechaMatrimonioCivil(Date fechaMatrimonioCivil) {
        this.fechaMatrimonioCivil = fechaMatrimonioCivil;
    }

    public Date getFechaMatrimonioIglesia() {
        return fechaMatrimonioIglesia;
    }

    public void setFechaMatrimonioIglesia(Date fechaMatrimonioIglesia) {
        this.fechaMatrimonioIglesia = fechaMatrimonioIglesia;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Boolean getCursoPrematrimonial() {
        return cursoPrematrimonial;
    }

    public void setCursoPrematrimonial(Boolean cursoPrematrimonial) {
        this.cursoPrematrimonial = cursoPrematrimonial;
    }

    public Persona getIdEsposo() {
        return idEsposo;
    }

    public void setIdEsposo(Persona idEsposo) {
        this.idEsposo = idEsposo;
    }

    public Persona getIdEsposa() {
        return idEsposa;
    }

    public void setIdEsposa(Persona idEsposa) {
        this.idEsposa = idEsposa;
    }

    public Persona getIdPastor() {
        return idPastor;
    }

    public void setIdPastor(Persona idPastor) {
        this.idPastor = idPastor;
    }

    public String getNombreEsposoExterno() {
        return nombreEsposoExterno;
    }

    public void setNombreEsposoExterno(String nombreEsposoExterno) {
        this.nombreEsposoExterno = nombreEsposoExterno;
    }

    public String getNombreEsposaExterna() {
        return nombreEsposaExterna;
    }

    public void setNombreEsposaExterna(String nombreEsposaExterna) {
        this.nombreEsposaExterna = nombreEsposaExterna;
    }



    public Boolean getEsposoExterno() {
        return esposoExterno;
    }

    public void setEsposoExterno(Boolean esposoExterno) {
        this.esposoExterno = esposoExterno;
    }

    public Boolean getEsposaExterna() {
        return esposaExterna;
    }

    public void setEsposaExterna(Boolean esposaExterna) {
        this.esposaExterna = esposaExterna;
    }

    public Boolean getPastorExterno() {
        return pastorExterno;
    }

    public void setPastorExterno(Boolean pastorExterno) {
        this.pastorExterno = pastorExterno;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    
   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMatrimonio != null ? idMatrimonio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Matrimonio)) {
            return false;
        }
        Matrimonio other = (Matrimonio) object;
        if ((this.idMatrimonio == null && other.idMatrimonio != null) || (this.idMatrimonio != null && !this.idMatrimonio.equals(other.idMatrimonio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nellehsoft.software_sail.controlador1.Matrimonio[ idMatrimonio=" + idMatrimonio + " ]";
    }
    
}
