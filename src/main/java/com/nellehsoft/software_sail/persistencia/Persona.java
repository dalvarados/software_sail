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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Duverdiel
 */
@Entity
@Table(name = "persona")
@XmlRootElement 
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p ORDER BY P.nombre" )
   , @NamedQuery(name = "Persona.findByFechaRegistro", query = "SELECT p FROM Persona p WHERE p.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Persona.findByIdPersona", query = "SELECT p FROM Persona p WHERE p.idPersona = :idPersona")
    , @NamedQuery(name = "Persona.findVisitas", query = "SELECT p FROM Persona p JOIN P.idRol AS a WHERE  a.nombreRol='Visita' ORDER BY P.nombre") 
    , @NamedQuery(name = "Persona.findPropietarioContable", query = "SELECT p FROM Persona p JOIN P.idRolContable AS a WHERE  a.nombreRol='Propietario' ORDER BY P.nombre")        
    , @NamedQuery(name = "Persona.findCorpenteUnida", query = "SELECT p FROM Persona p JOIN P.idRolContable AS a WHERE  a.nombreRol='Proveedor' and upper(p.nombre)='CORPENTEUNIDA' ORDER BY P.nombre")        
    , @NamedQuery(name = "Persona.findRolContable", query = "SELECT p FROM Persona p JOIN P.idRolContable AS a WHERE  a.tipoRol='Contable' ORDER BY P.nombre")         
    , @NamedQuery(name = "Persona.findAllCreyentes", query = "SELECT p FROM Persona p WHERE p.bautizado=TRUE AND p.estadoMembrecia='Activo' ORDER BY P.nombre")  
    , @NamedQuery(name = "Persona.findRolTercero", query = "SELECT p FROM Persona p WHERE p.idRolContable.idRol is not null ORDER BY P.nombre")          
    , @NamedQuery(name = "Persona.findRolProveedorServPubl", query = "SELECT p FROM Persona p JOIN P.idRolClasificacion AS a WHERE  a.nombreRol='Servicios Publicos' ORDER BY P.nombre")         
    , @NamedQuery(name = "Persona.findByDocumentoIdentidad", query = "SELECT p FROM Persona p WHERE p.documentoIdentidad = :documentoIdentidad")    
    , @NamedQuery(name = "Persona.findByNombre", query = "SELECT p FROM Persona p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Persona.findByPastor", query = "SELECT p FROM Persona p JOIN P.idRol AS a WHERE  a.nombreRol='Pastor' ")        
    , @NamedQuery(name = "Persona.findByApellidos", query = "SELECT p FROM Persona p WHERE p.apellidos = :apellidos")
    , @NamedQuery(name = "Persona.findBySexo", query = "SELECT p FROM Persona p WHERE p.sexo = :sexo AND p.estadoMembrecia='Activo' AND p.bautizado=TRUE AND p.estadoCivil='Soltero(a)' ORDER BY P.nombre")
    , @NamedQuery(name = "Persona.findByDireccion", query = "SELECT p FROM Persona p WHERE p.direccion = :direccion")
    , @NamedQuery(name = "Persona.findByTelefono", query = "SELECT p FROM Persona p WHERE p.telefono = :telefono")
    , @NamedQuery(name = "Persona.findByNumeroCelular", query = "SELECT p FROM Persona p WHERE p.numeroCelular = :numeroCelular")
    , @NamedQuery(name = "Persona.findByEstadoCivil", query = "SELECT p FROM Persona p WHERE p.estadoCivil = :estadoCivil")
    , @NamedQuery(name = "Persona.findByFechaNacimiento", query = "SELECT p FROM Persona p WHERE p.fechaNacimiento = :fechaNacimiento")
    , @NamedQuery(name = "Persona.findByBautizado", query = "SELECT p FROM Persona p WHERE p.bautizado = :bautizado")
    , @NamedQuery(name = "Persona.findByFechaBautismo", query = "SELECT p FROM Persona p WHERE p.fechaBautismo = :fechaBautismo")
    , @NamedQuery(name = "Persona.findByEstadoMembrecia", query = "SELECT p FROM Persona p WHERE p.estadoMembrecia = :estadoMembrecia")
    , @NamedQuery(name = "Persona.findBySelladoEs", query = "SELECT p FROM Persona p WHERE p.selladoEs = :selladoEs")
    , @NamedQuery(name = "Persona.findByFechaEs", query = "SELECT p FROM Persona p WHERE p.fechaEs = :fechaEs")
    , @NamedQuery(name = "Persona.findByFechaMembreciaLocal", query = "SELECT p FROM Persona p WHERE p.fechaMembreciaLocal = :fechaMembreciaLocal")
    , @NamedQuery(name = "Persona.findByfechaInactivo", query = "SELECT p FROM Persona p WHERE p.fechaInactivo = :fechaInactivo")        
    , @NamedQuery(name = "Persona.findByCorreo", query = "SELECT p FROM Persona p WHERE p.correo = :correo")
    , @NamedQuery(name = "Persona.findByNivelEstudio", query = "SELECT p FROM Persona p WHERE p.nivelEstudio = :nivelEstudio")
    , @NamedQuery(name = "Persona.findByParejaAsisteIglesia", query = "SELECT p FROM Persona p WHERE p.parejaAsisteIglesia = :parejaAsisteIglesia")
    , @NamedQuery(name = "Persona.findByNumeroHijos", query = "SELECT p FROM Persona p WHERE p.numeroHijos = :numeroHijos")
    , @NamedQuery(name = "Persona.findByHabilidades", query = "SELECT p FROM Persona p WHERE p.habilidades = :habilidades")
    , @NamedQuery(name = "Persona.findByTrasfondoReligioso", query = "SELECT p FROM Persona p WHERE p.trasfondoReligioso = :trasfondoReligioso")
    , @NamedQuery(name = "Persona.findByComoLlegoIglesia", query = "SELECT p FROM Persona p WHERE p.comoLlegoIglesia = :comoLlegoIglesia")
    , @NamedQuery(name = "Persona.findByPrimeraVisita", query = "SELECT p FROM Persona p WHERE p.primeraVisita = :primeraVisita")
    , @NamedQuery(name = "Persona.findByTiempoDeVisitar", query = "SELECT p FROM Persona p WHERE p.tiempoDeVisitar = :tiempoDeVisitar")
    , @NamedQuery(name = "Persona.findByParticipoDiscipulado", query = "SELECT p FROM Persona p WHERE p.participoDiscipulado = :participoDiscipulado")
    , @NamedQuery(name = "Persona.findByLugarDiscipulado", query = "SELECT p FROM Persona p WHERE p.lugarDiscipulado = :lugarDiscipulado")
    , @NamedQuery(name = "Persona.findByDeseaDiscipulado", query = "SELECT p FROM Persona p WHERE p.deseaDiscipulado = :deseaDiscipulado")
    , @NamedQuery(name = "Persona.findByRazonesAsistencia", query = "SELECT p FROM Persona p WHERE p.razonesAsistencia = :razonesAsistencia")
    , @NamedQuery(name = "Persona.findByTipoDocumento", query = "SELECT p FROM Persona p WHERE p.tipoDocumento = :tipoDocumento")        
    , @NamedQuery(name = "Persona.findByObservacionesBautismo", query = "SELECT p FROM Persona p WHERE p.observacionesBautismo = :observacionesBautismo")           
    , @NamedQuery(name = "Persona.findByObservacionesInactivo", query = "SELECT p FROM Persona p WHERE p.observacionesInactivo = :observacionesInactivo")         
    , @NamedQuery(name = "Persona.findByTiempoConversion", query = "SELECT p FROM Persona p WHERE p.tiempoConversion = :tiempoConversion") 
    , @NamedQuery(name = "Persona.findByContactoVisita", query = "SELECT p FROM Persona p WHERE p.contactovisita = :contactovisita")        
    , @NamedQuery(name = "Persona.findByNombrePadres", query = "SELECT p FROM Persona p WHERE p.nombrePadres = :nombrePadres")         
    , @NamedQuery(name = "Persona.findByFolio", query = "SELECT p FROM Persona p WHERE p.folio = :folio")  
    , @NamedQuery(name = "Persona.findByPastorOficiante", query = "SELECT p FROM Persona p WHERE p.pastorOficiante = :pastorOficiante")                 
    , @NamedQuery(name = "Persona.findByLuchasEspirituales", query = "SELECT p FROM Persona p WHERE p.luchasEspirituales = :luchasEspirituales")
    , @NamedQuery(name = "Persona.findByRecibirInformacion", query = "SELECT p FROM Persona p WHERE p.recibirInformacion = :recibirInformacion")})

public class Persona implements Serializable, Comparable<Persona>  {

    private static final long serialVersionUID = 1L;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_persona")
    private Integer idPersona;
    @Column(name = "documento_identidad")
    private String documentoIdentidad;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "apellidos")
    private String apellidos;
    @Size(max = 50)
    @Column(name = "sexo")
    private String sexo;
    @Size(max = 350)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 50)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 50)
    @Column(name = "numero_celular")
    private String numeroCelular;
    @Size(max = 50)
    @Column(name = "estado_civil")
    private String estadoCivil;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "bautizado")
    private Boolean bautizado;
    @Column(name = "fecha_bautismo")
    @Temporal(TemporalType.DATE)
    private Date fechaBautismo;
    @Column(name = "fecha_inactivo")
    @Temporal(TemporalType.DATE)
    private Date fechaInactivo;    
    @Size(max = 150)
    @Column(name = "estado_membrecia")
    private String estadoMembrecia;
    @Column(name = "sellado_es")
    private Boolean selladoEs;
    @Column(name = "fecha_es")
    @Temporal(TemporalType.DATE)
    private Date fechaEs;
    @Column(name = "fecha_membrecia_local")
    @Temporal(TemporalType.DATE)
    private Date fechaMembreciaLocal;
    @Column(name = "correo")
    private String correo;
    @Size(max = 50)
    @Column(name = "nivel_estudio")
    private String nivelEstudio;
    @Column(name = "pareja_asiste_iglesia")
    private Boolean parejaAsisteIglesia;
    @Column(name = "numero_hijos")
    private Integer numeroHijos;
    @Size(max = 100)
    @Column(name = "habilidades")
    private String habilidades;
    @Size(max = 100)
    @Column(name = "trasfondo_religioso")
    private String trasfondoReligioso;
    @Size(max = 100)
    @Column(name = "como_llego_iglesia")
    private String comoLlegoIglesia;
    @Column(name = "primera_visita")
    private Boolean primeraVisita;
    @Size(max = 100)
    @Column(name = "tiempo_de_visitar")
    private String tiempoDeVisitar;
    @Column(name = "participo_discipulado")
    private Boolean participoDiscipulado;
    @Size(max = 100)
    @Column(name = "lugar_discipulado")
    private String lugarDiscipulado;
    @Column(name = "desea_discipulado")
    private Boolean deseaDiscipulado;
    @Size(max = 100)
    @Column(name = "razones_asistencia")
    private String razonesAsistencia;
    @Size(max = 100)
    @Column(name = "luchas_espirituales")
    private String luchasEspirituales; 
    @Size(max = 50)
    @Column(name = "tipo_documento")
    private String tipoDocumento;    
    @Size(max = 250)
    @Column(name = "observaciones_bautismo")
    private String observacionesBautismo;    
    @Size(max = 250)
    @Column(name = "observaciones_inactivo")
    private String observacionesInactivo;  
    @Size(max = 250)
    @Column(name = "nombre_padres")
    private String nombrePadres;     
    @Size(max = 50)
    @Column(name = "tiempo_conversion")
    private String tiempoConversion;     
    @Column(name = "recibir_informacion")
    private Boolean recibirInformacion;
    @Size(max = 250)
    @Column(name = "contacto_visita")
    private String contactovisita;   
    @Size(max = 250)
    @Column(name = "pastor_oficiante")
    private String pastorOficiante;
    @Size(max = 45)
    @Column(name = "folio")
    private String folio;    
    @Size(max = 150)
    @Column(name = "iglesia_anterior")
    private String iglesiaAnterior;   
    @Column(name = "tiene_recomendacion")
    private Boolean tieneRecomendacion;  
    
    @OneToMany(mappedBy = "idPersona")
    private Collection<Diezmo> diezmoCollection;
    @OneToMany(mappedBy = "idResponsablePpal")
    private Collection<Diezmo> diezmoCollection1;
    @OneToMany(mappedBy = "idResponsableSec")
    private Collection<Diezmo> diezmoCollection2;
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    @ManyToOne
    private Rol idRol;
    @JoinColumn(name = "id_rol_contable", referencedColumnName = "id_rol")
    @ManyToOne
    private Rol idRolContable;  
    @JoinColumn(name = "id_rol_clasificacion", referencedColumnName = "id_rol")
    @ManyToOne
    private Rol idRolClasificacion;     
    @JoinColumn(name = "id_barrio", referencedColumnName = "id_barrio")
    @ManyToOne
    private Barrio idBarrio;
    @JoinColumn(name = "id_lugar_nacimiento", referencedColumnName = "id_ciudad")
    @ManyToOne
    private Ciudad idLugarNacimiento;
    @JoinColumn(name = "id_ocupacion", referencedColumnName = "id_ocupacion")
    @ManyToOne
    private Ocupacion idOcupacion;
    @JoinColumn(name = "id_ciudad_bautismo", referencedColumnName = "id_ciudad")
    @ManyToOne
    private Ciudad idCiudadBautismo;
    @JoinColumn(name = "id_iglesia_bautismo", referencedColumnName = "id_iglesia")
    @ManyToOne
    private Iglesia idIglesiaBautismo;
    @JoinColumn(name = "id_iglesia_anterior", referencedColumnName = "id_iglesia")
    @ManyToOne
    private Iglesia idIglesiaAnterior;
    @OneToMany(mappedBy = "idResponsablePpal")
    private Collection<Asistencia> asistenciaCollection;
    @OneToMany(mappedBy = "idResponsablePpal")
    private Collection<Ofrenda> ofrendaCollection;
    @OneToMany(mappedBy = "idResponsableSec")
    private Collection<Ofrenda> ofrendaCollection1;
    @JoinColumn(name = "id_persona_pastor", referencedColumnName = "id_persona")
    @ManyToOne 
    private Persona idPersonaPastor;
    
    public Persona() {
    }

    public Persona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Persona(Integer idPersona, String documentoIdentidad, String nombre, String apellidos) {
        this.idPersona = idPersona;
        this.documentoIdentidad = documentoIdentidad;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public Persona getIdPersonaPastor() {
        return idPersonaPastor;
    }

    public void setIdPersonaPastor(Persona idPersonaPastor) {
        this.idPersonaPastor = idPersonaPastor;
    }

    public Rol getIdRolContable() {
        return idRolContable;
    }

    public void setIdRolContable(Rol idRolContable) {
        this.idRolContable = idRolContable;
    }
    
    public Rol getIdRolClasificacion() {
        return idRolClasificacion;
    }

    public void setIdRolClasificacion(Rol idRolClasificacion) {
        this.idRolClasificacion = idRolClasificacion;
    }     

    public String getContactovisita() {
        return contactovisita;
    }

    public void setContactovisita(String contactovisita) {
        this.contactovisita = contactovisita;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public String getNombrePadres() {
        return nombrePadres;
    }

    public void setNombrePadres(String nombrePadres) {
        this.nombrePadres = nombrePadres;
    }

    public String getPastorOficiante() {
        return pastorOficiante;
    }

    public void setPastorOficiante(String pastorOficiante) {
        this.pastorOficiante = pastorOficiante;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }
    
    public String getTiempoConversion() {
        return tiempoConversion;
    }

    public void setTiempoConversion(String tiempoConversion) {
        this.tiempoConversion = tiempoConversion;
    }

    
    
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getObservacionesBautismo() {
        return observacionesBautismo;
    }

    public void setObservacionesBautismo(String observacionesBautismo) {
        this.observacionesBautismo = observacionesBautismo;
    }

    public String getObservacionesInactivo() {
        return observacionesInactivo;
    }

    public void setObservacionesInactivo(String observacionesInactivo) {
        this.observacionesInactivo = observacionesInactivo;
    }

    
    
    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
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

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaInactivo() {
        return fechaInactivo;
    }

    public void setFechaInactivo(Date fechaInactivo) {
        this.fechaInactivo = fechaInactivo;
    }
    
    public Boolean getBautizado() {
        return bautizado;
    }

    public void setBautizado(Boolean bautizado) {
        this.bautizado = bautizado;
    }

    public Date getFechaBautismo() {
        return fechaBautismo;
    }

    public void setFechaBautismo(Date fechaBautismo) {
        this.fechaBautismo = fechaBautismo;
    }

    public String getEstadoMembrecia() {
        return estadoMembrecia;
    }

    public void setEstadoMembrecia(String estadoMembrecia) {
        this.estadoMembrecia = estadoMembrecia;
    }

    public Boolean getSelladoEs() {
        return selladoEs;
    }

    public void setSelladoEs(Boolean selladoEs) {
        this.selladoEs = selladoEs;
    }

    public Date getFechaEs() {
        return fechaEs;
    }

    public void setFechaEs(Date fechaEs) {
        this.fechaEs = fechaEs;
    }

    public Date getFechaMembreciaLocal() {
        return fechaMembreciaLocal;
    }
    
public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNivelEstudio() {
        return nivelEstudio;
    }

    public void setNivelEstudio(String nivelEstudio) {
        this.nivelEstudio = nivelEstudio;
    }

    public Boolean getParejaAsisteIglesia() {
        return parejaAsisteIglesia;
    }

    public void setParejaAsisteIglesia(Boolean parejaAsisteIglesia) {
        this.parejaAsisteIglesia = parejaAsisteIglesia;
    }

    public Integer getNumeroHijos() {
        return numeroHijos;
    }

    public void setNumeroHijos(Integer numeroHijos) {
        this.numeroHijos = numeroHijos;
    }

    public String getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
    }

    public String getTrasfondoReligioso() {
        return trasfondoReligioso;
    }

    public void setTrasfondoReligioso(String trasfondoReligioso) {
        this.trasfondoReligioso = trasfondoReligioso;
    }

    public String getComoLlegoIglesia() {
        return comoLlegoIglesia;
    }

    public void setComoLlegoIglesia(String comoLlegoIglesia) {
        this.comoLlegoIglesia = comoLlegoIglesia;
    }

    public Boolean getPrimeraVisita() {
        return primeraVisita;
    }

    public void setPrimeraVisita(Boolean primeraVisita) {
        this.primeraVisita = primeraVisita;
    }

    public String getTiempoDeVisitar() {
        return tiempoDeVisitar;
    }

    public void setTiempoDeVisitar(String tiempoDeVisitar) {
        this.tiempoDeVisitar = tiempoDeVisitar;
    }

    public Boolean getParticipoDiscipulado() {
        return participoDiscipulado;
    }

    public void setParticipoDiscipulado(Boolean participoDiscipulado) {
        this.participoDiscipulado = participoDiscipulado;
    }

    public String getLugarDiscipulado() {
        return lugarDiscipulado;
    }

    public void setLugarDiscipulado(String lugarDiscipulado) {
        this.lugarDiscipulado = lugarDiscipulado;
    }

    public Boolean getDeseaDiscipulado() {
        return deseaDiscipulado;
    }

    public void setDeseaDiscipulado(Boolean deseaDiscipulado) {
        this.deseaDiscipulado = deseaDiscipulado;
    }

    public String getRazonesAsistencia() {
        return razonesAsistencia;
    }

    public void setRazonesAsistencia(String razonesAsistencia) {
        this.razonesAsistencia = razonesAsistencia;
    }

    public String getLuchasEspirituales() {
        return luchasEspirituales;
    }

    public void setLuchasEspirituales(String luchasEspirituales) {
        this.luchasEspirituales = luchasEspirituales;
    }

    public Boolean getRecibirInformacion() {
        return recibirInformacion;
    }

    public void setRecibirInformacion(Boolean recibirInformacion) {
        this.recibirInformacion = recibirInformacion;
    }    

    public String getIglesiaAnterior() {
        return iglesiaAnterior;
    }

    public void setIglesiaAnterior(String iglesiaAnterior) {
        this.iglesiaAnterior = iglesiaAnterior;
    }

    public Boolean getTieneRecomendacion() {
        return tieneRecomendacion;
    }

    public void setTieneRecomendacion(Boolean tieneRecomendacion) {
        this.tieneRecomendacion = tieneRecomendacion;
    }

    public void setFechaMembreciaLocal(Date fechaMembreciaLocal) {
        this.fechaMembreciaLocal = fechaMembreciaLocal;
    }

    @XmlTransient
    public Collection<Diezmo> getDiezmoCollection() {
        return diezmoCollection;
    }

    public void setDiezmoCollection(Collection<Diezmo> diezmoCollection) {
        this.diezmoCollection = diezmoCollection;
    }

    @XmlTransient
    public Collection<Diezmo> getDiezmoCollection1() {
        return diezmoCollection1;
    }

    public void setDiezmoCollection1(Collection<Diezmo> diezmoCollection1) {
        this.diezmoCollection1 = diezmoCollection1;
    }

    @XmlTransient
    public Collection<Diezmo> getDiezmoCollection2() {
        return diezmoCollection2;
    }

    public void setDiezmoCollection2(Collection<Diezmo> diezmoCollection2) {
        this.diezmoCollection2 = diezmoCollection2;
    }

    public Rol getIdRol() {
        return idRol;
    }

    public void setIdRol(Rol idRol) {
        this.idRol = idRol;
    }

    public Barrio getIdBarrio() {
        return idBarrio;
    }

    public void setIdBarrio(Barrio idBarrio) {
        this.idBarrio = idBarrio;
    }

    public Ciudad getIdLugarNacimiento() {
        return idLugarNacimiento;
    }

    public void setIdLugarNacimiento(Ciudad idLugarNacimiento) {
        this.idLugarNacimiento = idLugarNacimiento;
    }

    public Ocupacion getIdOcupacion() {
        return idOcupacion;
    }

    public void setIdOcupacion(Ocupacion idOcupacion) {
        this.idOcupacion = idOcupacion;
    }

    public Ciudad getIdCiudadBautismo() {
        return idCiudadBautismo;
    }

    public void setIdCiudadBautismo(Ciudad idCiudadBautismo) {
        this.idCiudadBautismo = idCiudadBautismo;
    }

    public Iglesia getIdIglesiaBautismo() {
        return idIglesiaBautismo;
    }

    public void setIdIglesiaBautismo(Iglesia idIglesiaBautismo) {
        this.idIglesiaBautismo = idIglesiaBautismo;
    }

    public Iglesia getIdIglesiaAnterior() {
        return idIglesiaAnterior;
    }

    public void setIdIglesiaAnterior(Iglesia idIglesiaAnterior) {
        this.idIglesiaAnterior = idIglesiaAnterior;
    }

    @XmlTransient
    public Collection<Asistencia> getAsistenciaCollection() {
        return asistenciaCollection;
    }

    public void setAsistenciaCollection(Collection<Asistencia> asistenciaCollection) {
        this.asistenciaCollection = asistenciaCollection;
    }

    @XmlTransient
    public Collection<Ofrenda> getOfrendaCollection() {
        return ofrendaCollection;
    }

    public void setOfrendaCollection(Collection<Ofrenda> ofrendaCollection) {
        this.ofrendaCollection = ofrendaCollection;
    }

    @XmlTransient
    public Collection<Ofrenda> getOfrendaCollection1() {
        return ofrendaCollection1;
    }

    public void setOfrendaCollection1(Collection<Ofrenda> ofrendaCollection1) {
        this.ofrendaCollection1 = ofrendaCollection1;
    }
   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersona != null ? idPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.idPersona == null && other.idPersona != null) || (this.idPersona != null && !this.idPersona.equals(other.idPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nellehsoft.software_sail.persistencia.Persona[ idPersona=" + idPersona + " ]";
    }

    @Override
    public int compareTo(Persona o) {
    return this.nombre.compareTo(o.getNombre());
    }
    
}
