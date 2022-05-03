package com.nellehsoft.software_sail.controlador;

import com.nellehsoft.software_sail.persistencia.Diezmo;
import com.nellehsoft.software_sail.controlador.util.JsfUtil;
import com.nellehsoft.software_sail.controlador.util.JsfUtil.PersistAction;
import com.nellehsoft.software_sail.negocio.DiezmoFacade;
import com.nellehsoft.software_sail.persistencia.AsientoContable;
import com.nellehsoft.software_sail.persistencia.Ciudad;
import com.nellehsoft.software_sail.persistencia.CuentasPuc;
import com.nellehsoft.software_sail.persistencia.Iglesia;
import com.nellehsoft.software_sail.persistencia.Persona;
import com.nellehsoft.software_sail.persistencia.TipoContable;
import com.nellehsoft.software_sail.persistencia.Usuario;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.context.RequestContext;

@Named("diezmoController")
@SessionScoped
public class DiezmoController implements Serializable {

    @EJB
    private com.nellehsoft.software_sail.negocio.DiezmoFacade ejbFacade;
    @EJB 
    private com.nellehsoft.software_sail.negocio.PersonaFacade ejbFacadePersona;    
    @EJB
    private com.nellehsoft.software_sail.negocio.CuentasPucFacade ejbFacadeCuentasPucFacade;    
    @EJB     
    private com.nellehsoft.software_sail.negocio.TipoContableFacade ejbFacadeTipoContable;    
    @EJB    
    private com.nellehsoft.software_sail.negocio.IglesiaFacade ejbFacadeIglesia;    
    @EJB    
    private com.nellehsoft.software_sail.negocio.AsientoContableFacade ejbFacadeAsientoContable;    
    private List<Diezmo> items = null;
    private Diezmo selected;
    private double calculoFondoLocal;
    private Persona objeto_pastor; 
    private List<CuentasPuc> list_cuenta_diezmo =new ArrayList(); 
    private List<CuentasPuc> list_cuenta_ingreso_diezmos;
    private List<CuentasPuc> list_cuenta_ingreso_fondo;     
    private CuentasPuc cuenta_activo_caja;    
    private AsientoContable selectedAsientoContable; 
    private AsientoContable selectedAsientoContableIngresoFondo;
    private AsientoContable selectedAsientoContableEgresoFondo;    
    private AsientoContable selectedAsientoContableIngresoDiezmos;    
    private Persona tercero_propietario;     
    private Persona tercero_corpenteunida;    
    private TipoContable tipoIngreso;
    private TipoContable tipoEgreso;    
    private Iglesia iglesia;    
    private Ciudad ciudad;       
    private Usuario user;
    private String numeEgresoCorpenteUnida;       
    private String numeroEgresoPastor;       
    private CuentasPuc cuentaCorpenteUnida;
    private CuentasPuc cuentaAportePastor;
    private CuentasPuc cuentaIngresoDiezmo;
    private CuentasPuc cuentaIngresoFondoDiezmo;
    private List<Persona> list_persona_ordenada;    
    private int valNumeroIngreso=0;
    private  int valNumeroEgresoPastor=0;
    private  int valNumeroEgresoCorpenteUnida=0;    
    
    public DiezmoController() {
    }

    public List<Persona> getList_persona_ordenada() {
        return list_persona_ordenada;
    }

    public void setList_persona_ordenada(List<Persona> list_persona_ordenada) {
        this.list_persona_ordenada = list_persona_ordenada;
    }

    public Persona getObjeto_pastor() {
        return objeto_pastor;
    }

    public void setObjeto_pastor(Persona objeto_pastor) {
        this.objeto_pastor = objeto_pastor;
    }

    public int getValNumeroIngreso() {
        return valNumeroIngreso;
    }

    public void setValNumeroIngreso(int valNumeroIngreso) {
        this.valNumeroIngreso = valNumeroIngreso;
    }
    
    public int getValNumeroEgresoPastor() {
        return valNumeroEgresoPastor;
    }

    public void setValNumeroEgresoPastor(int valNumeroEgresoPastor) {
        this.valNumeroEgresoPastor = valNumeroEgresoPastor;
    }
    
    public int getValNumeroEgresoCorpenteUnida() {
        return valNumeroEgresoCorpenteUnida;
    }

    public void setValNumeroEgresoCorpenteUnida(int valNumeroEgresoCorpenteUnida) {
        this.valNumeroEgresoCorpenteUnida = valNumeroEgresoCorpenteUnida;
    }
    
    public AsientoContable getSelectedAsientoContableEgresoFondo() {
        return selectedAsientoContableEgresoFondo;
    }

    public void setSelectedAsientoContableEgresoFondo(AsientoContable selectedAsientoContableEgresoFondo) {
        this.selectedAsientoContableEgresoFondo = selectedAsientoContableEgresoFondo;
    }

    public CuentasPuc getCuentaIngresoDiezmo() {
        return cuentaIngresoDiezmo;
    }

    public void setCuentaIngresoDiezmo(CuentasPuc cuentaIngresoDiezmo) {
        this.cuentaIngresoDiezmo = cuentaIngresoDiezmo;
    }

    public CuentasPuc getCuentaIngresoFondoDiezmo() {
        return cuentaIngresoFondoDiezmo;
    }

    public void setCuentaIngresoFondoDiezmo(CuentasPuc cuentaIngresoFondoDiezmo) {
        this.cuentaIngresoFondoDiezmo = cuentaIngresoFondoDiezmo;
    }
    
    public AsientoContable getSelectedAsientoContableIngresoFondo() {
        return selectedAsientoContableIngresoFondo;
    }

    public void setSelectedAsientoContableIngresoFondo(AsientoContable selectedAsientoContableIngesoFondo) {
        this.selectedAsientoContableIngresoFondo = selectedAsientoContableIngesoFondo;
    }

    public AsientoContable getSelectedAsientoContableIngresoDiezmos() {
        return selectedAsientoContableIngresoDiezmos;
    }

    public void setSelectedAsientoContableIngresoDiezmos(AsientoContable selectedAsientoContableIngresoDiezmos) {
        this.selectedAsientoContableIngresoDiezmos = selectedAsientoContableIngresoDiezmos;
    }

    public List<CuentasPuc> getList_cuenta_ingreso_diezmos() {
        return list_cuenta_ingreso_diezmos;
    }

    public void setList_cuenta_ingreso_diezmos(List<CuentasPuc> list_cuenta_ingreso_diezmos) {
        this.list_cuenta_ingreso_diezmos = list_cuenta_ingreso_diezmos;
    }

    public List<CuentasPuc> getList_cuenta_ingreso_fondo() {
        return list_cuenta_ingreso_fondo;
    }

    public void setList_cuenta_ingreso_fondo(List<CuentasPuc> list_cuenta_ingreso_fondo) {
        this.list_cuenta_ingreso_fondo = list_cuenta_ingreso_fondo;
    }
    

    public CuentasPuc getCuentaCorpenteUnida() {
        return cuentaCorpenteUnida;
    }

    public CuentasPuc getCuentaAportePastor() {
        return cuentaAportePastor;
    }

    public void setCuentaAportePastor(CuentasPuc cuentaAportePastor) {
        this.cuentaAportePastor = cuentaAportePastor;
    }

    public void setCuentaCorpenteUnida(CuentasPuc cuentaCorpenteUnida) { 
        this.cuentaCorpenteUnida = cuentaCorpenteUnida;
    }

    public String getNumeEgresoCorpenteUnida() {
        return numeEgresoCorpenteUnida;
    }

    public void setNumeEgresoCorpenteUnida(String numeEgresoCorpenteUnida) {
        this.numeEgresoCorpenteUnida = numeEgresoCorpenteUnida;
    }

    public String getNumeroEgresoPastor() {
        return numeroEgresoPastor;
    }

    public void setNumeroEgresoPastor(String numeroEgresoPastor) {
        this.numeroEgresoPastor = numeroEgresoPastor;
    }
	
    public List<CuentasPuc> getList_cuenta_diezmo() {
        return list_cuenta_diezmo;
    }

    public void setList_cuenta_diezmo(List<CuentasPuc> list_cuenta_diezmo) {
        this.list_cuenta_diezmo = list_cuenta_diezmo;
    }   
    
    public double getCalculoFondoLocal() {
        return calculoFondoLocal;
    }

    public void setCalculoFondoLocal(double calculoFondoLocal) {
        this.calculoFondoLocal = calculoFondoLocal;
    }

    public Persona getTercero_propietario() {
        return tercero_propietario;
    }

    public void setTercero_propietario(Persona tercero_propietario) {
        this.tercero_propietario = tercero_propietario;
    }

    public Persona getTercero_corpenteunida() {
        return tercero_corpenteunida;
    }

    public void setTercero_corpenteunida(Persona tercero_corpenteunida) {
        this.tercero_corpenteunida = tercero_corpenteunida;
    }

    
    public CuentasPuc getCuenta_activo_caja() {
        return cuenta_activo_caja;
    }

    public void setCuenta_activo_caja(CuentasPuc cuenta_activo_caja) {
        this.cuenta_activo_caja = cuenta_activo_caja;
    }
    
    public Diezmo getSelected() {
        return selected;
    }

    public void setSelected(Diezmo selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DiezmoFacade getFacade() {
        return ejbFacade;
    }

    public CuentasPuc get_obtener_cuentas_puc_caja() {           
        cuenta_activo_caja=ejbFacade.obtener_cuentasPucActivos();    
    return cuenta_activo_caja;      
    }    
    
    public void obtener_rol_propietario(){ 
    tercero_propietario=ejbFacadePersona.obtener_propietario();
    } 
    
    public void obtener_tercero_corpenteunida(){ 
    tercero_corpenteunida=ejbFacadePersona.obtener_corpenteunida();
    }
    
    public void obtener_tipoContable(){ 
    tipoIngreso=ejbFacadeTipoContable.obtener_tipoContable("Ingreso");
    }               

    public void obtener_tipoContableEgreso(){ 
    tipoEgreso=ejbFacadeTipoContable.obtener_tipoContable("Egreso");
    }
    
    public void obtener_tipoIglesia(){ 
    iglesia=ejbFacadeIglesia.obtener_objeto_iglesia_local("Local");
    }

    public void obtener_objetoCuentaNombre(){ 
    cuentaCorpenteUnida=ejbFacadeCuentasPucFacade.obtener_ObjetoCuentasPuc("CORPENTEUNIDA");
    cuentaAportePastor=ejbFacadeCuentasPucFacade.obtener_ObjetoCuentasPuc("DONACIONES PASTOR"); 
    cuentaIngresoFondoDiezmo=ejbFacadeCuentasPucFacade.obtener_ObjetoCuentasPuc("INGRESO DONACIONES FONDO LOCAL DIEZMOS");    
    cuentaIngresoDiezmo=ejbFacadeCuentasPucFacade.obtener_ObjetoCuentasPuc("INGRESO DONACIONES DIEZMOS");        
    }

   
    public Diezmo prepareCreate() {
        selected = new Diezmo();
        Date myDate = new Date();        
        new SimpleDateFormat("MM-dd-yyyy").format(myDate); 
        selected.setFechaDiezmo(myDate);        
        tercero_propietario=new Persona();     
        tercero_corpenteunida=new Persona();        
        tipoIngreso =new TipoContable();
        cuenta_activo_caja=new CuentasPuc();
        iglesia =new Iglesia();    
        ciudad=new Ciudad();
        obtener_pastor();
        obtener_lista_persona_ordenada();
        obtener_tipoIglesia();
        obtener_tercero_corpenteunida();
        obtener_tipoContable();
        obtener_tipoContableEgreso();
        initializeEmbeddableKey();
        return selected;
    }

   public void obtener_lista_persona_ordenada(){
    list_persona_ordenada=ejbFacadePersona.obtener_persona_sort();
   }
   
    public void obtener_pastor(){
      objeto_pastor=ejbFacadePersona.obtener_pastor();
    }
    
    public void liquidarDiezmos(){
        double corpenteUnida=Math.round(selected.getValorDiezmo().doubleValue()*0.21);
        calculoFondoLocal=selected.getValorDiezmo() - corpenteUnida;         
        double fondoLocal=Math.round(calculoFondoLocal*0.19);
        selected.setValorCorpenteUnida(corpenteUnida);                      
        selected.setValorFondoLocal(fondoLocal);        
        selected.setValorPastor(selected.getValorDiezmo().doubleValue() - corpenteUnida - fondoLocal );                    
    }    

    public void create() {  
     validaNumeroComprobantes();
     if ( getValNumeroIngreso()==0 && getValNumeroEgresoCorpenteUnida()==0 
             && getValNumeroEgresoPastor()==0 && !getNumeEgresoCorpenteUnida().equals(getNumeroEgresoPastor())){
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DiezmoCreated"));
            if (!JsfUtil.isValidationFailed()) {
                createAsientoContableEgresoPastor();
                createAsientoContableEgresoCorpenteUnida();
                createAsientoContableIngresoFondoLocal();            
                items = null;    // Invalidate list of items to trigger re-query.
                }
        }else if(getNumeEgresoCorpenteUnida().equals(getNumeroEgresoPastor())){
                 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Los numeros de egresos no deben ser iguales");
                 RequestContext.getCurrentInstance().showMessageInDialog(message);            
        }else if(getValNumeroIngreso()>0){
                 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El numero de Ingreso '"+selected.getNumIngreso()+ "' ya fue contabilizado");
                 RequestContext.getCurrentInstance().showMessageInDialog(message);
        }else if(getValNumeroEgresoCorpenteUnida()>0) {
                 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El numero de Egreso corpenteunida '"+getNumeEgresoCorpenteUnida()+ "' ya fue contabilizado");
                 RequestContext.getCurrentInstance().showMessageInDialog(message);
        }else if(getValNumeroEgresoPastor()>0){
                 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El numero de Egreso pastor '"+getNumeroEgresoPastor()+ "' ya fue contabilizado");
                 RequestContext.getCurrentInstance().showMessageInDialog(message);           
        }else{
          FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error 2201", "Contactar Al adminisrador del sistema");
          RequestContext.getCurrentInstance().showMessageInDialog(message);
         }            
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DiezmoUpdated"));
        update_asientoContableLiquidacionDiezmos();        
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DiezmoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            obtener_tipoContable();
            delete_asientocontable_diezmo();
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Diezmo> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            liquidarDiezmos();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public void createAsientoContableEgresoPastor() {
        java.util.Date fecha = new Date();        
        user =(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");      
        obtener_rol_propietario();
        obtener_tipoContableEgreso();        
        obtener_objetoCuentaNombre();
        get_obtener_cuentas_puc_caja();
        ciudad.setIdCiudad(iglesia.getIdCiudad().getIdCiudad());
        selectedAsientoContable=new AsientoContable();
        selectedAsientoContableEgresoFondo=new AsientoContable();
        
        selectedAsientoContable.setNumeroTipoContable(numeroEgresoPastor);
        selectedAsientoContableEgresoFondo.setNumeroTipoContable(numeroEgresoPastor);
        
        selectedAsientoContable.setDiferencia(Integer.parseInt(selected.getNumIngreso()));
        selectedAsientoContableEgresoFondo.setDiferencia(Integer.parseInt(selected.getNumIngreso()));
        
        selectedAsientoContable.setIdTercero(selected.getIdPersona());
        selectedAsientoContableEgresoFondo.setIdTercero(selected.getIdResponsablePpal());
        
        selectedAsientoContable.setIdPuc(cuentaAportePastor);
        selectedAsientoContableEgresoFondo.setIdPuc(cuenta_activo_caja);
        
        selectedAsientoContable.setFechaRegistro(selected.getFechaDiezmo());
        selectedAsientoContableEgresoFondo.setFechaRegistro(selected.getFechaDiezmo());
        
        selectedAsientoContable.setFechaTipoContable(fecha);
        selectedAsientoContableEgresoFondo.setFechaTipoContable(fecha);
        
        selectedAsientoContable.setIdTipoContable(tipoEgreso);
        selectedAsientoContableEgresoFondo.setIdTipoContable(tipoEgreso);
        
        selectedAsientoContable.setNumeroTipoContable(getNumeroEgresoPastor());
        selectedAsientoContableEgresoFondo.setNumeroTipoContable(getNumeroEgresoPastor());
        
        selectedAsientoContable.setDescripcion("Liquidacion Diezmo -Aporte Pastor");
        selectedAsientoContableEgresoFondo.setDescripcion("Liquidacion Diezmo -Aporte Pastor");
        
        selectedAsientoContable.setValorDebito(selected.getValorPastor().intValue());
        selectedAsientoContableEgresoFondo.setValorCredito(selected.getValorPastor().intValue());
        
        selectedAsientoContable.setIdUsuario(user);
        selectedAsientoContableEgresoFondo.setIdUsuario(user);
        
        selectedAsientoContable.setIdCiudad(ciudad);
        selectedAsientoContableEgresoFondo.setIdCiudad(ciudad);
        
        persistAsientoContableEgresoPastor(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AsientoContableCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    private void persistAsientoContableEgresoPastor(PersistAction persistAction, String successMessage) {
        if (selectedAsientoContable != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    ejbFacadeAsientoContable.edit(selectedAsientoContable);
                    ejbFacadeAsientoContable.edit(selectedAsientoContableEgresoFondo);                    
                } 
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }    


    public void createAsientoContableEgresoCorpenteUnida() {
        java.util.Date fecha = new Date();        
        user =(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");      
        obtener_tercero_corpenteunida(); 
        ciudad.setIdCiudad(iglesia.getIdCiudad().getIdCiudad());
        selectedAsientoContable=new AsientoContable();
        
        selectedAsientoContable.setIdTercero(tercero_corpenteunida);
        selectedAsientoContableEgresoFondo.setIdTercero(selected.getIdResponsablePpal());
                
        selectedAsientoContable.setDiferencia(Integer.parseInt(selected.getNumIngreso()));
        selectedAsientoContableEgresoFondo.setDiferencia(Integer.parseInt(selected.getNumIngreso()));
        
        selectedAsientoContable.setIdPuc(cuentaCorpenteUnida);
        selectedAsientoContableEgresoFondo.setIdPuc(cuenta_activo_caja);
        
        selectedAsientoContable.setFechaRegistro(selected.getFechaDiezmo());
        selectedAsientoContableEgresoFondo.setFechaRegistro(selected.getFechaDiezmo());
        
        selectedAsientoContable.setFechaTipoContable(fecha);
        selectedAsientoContableEgresoFondo.setFechaTipoContable(fecha);
        
        selectedAsientoContable.setIdTipoContable(tipoEgreso);
        selectedAsientoContableEgresoFondo.setIdTipoContable(tipoEgreso);
        
        selectedAsientoContable.setNumeroTipoContable(getNumeEgresoCorpenteUnida());
        selectedAsientoContableEgresoFondo.setNumeroTipoContable(getNumeEgresoCorpenteUnida());
        
        selectedAsientoContable.setDescripcion("Liquidacion Diezmo -CorpenteUnida Diezmos ");
        selectedAsientoContableEgresoFondo.setDescripcion("Liquidacion Diezmo -CorpenteUnida Diezmos ");
        
        selectedAsientoContable.setValorDebito(selected.getValorCorpenteUnida().intValue());
        selectedAsientoContableEgresoFondo.setValorCredito(selected.getValorCorpenteUnida().intValue());
        
        selectedAsientoContable.setIdUsuario(user);
        selectedAsientoContableEgresoFondo.setIdUsuario(user);
        
        selectedAsientoContable.setIdCiudad(ciudad);
        selectedAsientoContableEgresoFondo.setIdCiudad(ciudad);
        
        persistAsientoContableEgresoCorpenteUnida(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AsientoContableCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    private void persistAsientoContableEgresoCorpenteUnida(PersistAction persistAction, String successMessage) {
        if (selectedAsientoContable != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    ejbFacadeAsientoContable.edit(selectedAsientoContable);
                    ejbFacadeAsientoContable.edit(selectedAsientoContableEgresoFondo);
                }
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public void createAsientoContableIngresoFondoLocal() {
        java.util.Date fecha = new Date();
        get_obtener_cuentas_puc_caja();        
        user =(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");      
        obtener_rol_propietario();
        ciudad.setIdCiudad(iglesia.getIdCiudad().getIdCiudad());
        selectedAsientoContable=new AsientoContable();
        selectedAsientoContableIngresoFondo=new AsientoContable();
        selectedAsientoContableIngresoDiezmos=new AsientoContable();
        
        selectedAsientoContable.setIdTercero(tercero_propietario);
        selectedAsientoContableIngresoFondo.setIdTercero(tercero_propietario);
        selectedAsientoContableIngresoDiezmos.setIdTercero(selected.getIdResponsablePpal());
        
        selectedAsientoContable.setIdPuc(cuenta_activo_caja);
        selectedAsientoContableIngresoFondo.setIdPuc(cuentaIngresoFondoDiezmo);
        selectedAsientoContableIngresoDiezmos.setIdPuc(cuentaIngresoDiezmo);
        
        selectedAsientoContable.setFechaRegistro(selected.getFechaDiezmo());
        selectedAsientoContableIngresoFondo.setFechaRegistro(selected.getFechaDiezmo());
        selectedAsientoContableIngresoDiezmos.setFechaRegistro(selected.getFechaDiezmo());

        selectedAsientoContable.setDiferencia(Integer.parseInt(selected.getNumIngreso()));
        selectedAsientoContableIngresoFondo.setDiferencia(Integer.parseInt(selected.getNumIngreso()));
        selectedAsientoContableIngresoDiezmos.setDiferencia(Integer.parseInt(selected.getNumIngreso()));
                
        selectedAsientoContable.setFechaTipoContable(fecha);
        selectedAsientoContableIngresoFondo.setFechaTipoContable(fecha);
        selectedAsientoContableIngresoDiezmos.setFechaTipoContable(fecha);
        
        selectedAsientoContable.setIdTipoContable(tipoIngreso);
        selectedAsientoContableIngresoFondo.setIdTipoContable(tipoIngreso);
        selectedAsientoContableIngresoDiezmos.setIdTipoContable(tipoIngreso);
        
        selectedAsientoContable.setNumeroTipoContable(selected.getNumIngreso());
        selectedAsientoContableIngresoFondo.setNumeroTipoContable(selected.getNumIngreso());
        selectedAsientoContableIngresoDiezmos.setNumeroTipoContable(selected.getNumIngreso());
        
        selectedAsientoContable.setDescripcion("Liquidacion Diezmo -Diezmos  ");
        selectedAsientoContableIngresoFondo.setDescripcion("Liquidacion Diezmo -Diezmos fondo local");
        selectedAsientoContableIngresoDiezmos.setDescripcion("Liquidacion Diezmo -Ingreso Diezmos ");
        
        selectedAsientoContable.setValorDebito(selected.getValorDiezmo());
        selectedAsientoContableIngresoFondo.setValorCredito(selected.getValorFondoLocal().intValue());
        selectedAsientoContableIngresoDiezmos.setValorCredito(selected.getValorPastor().intValue()+selected.getValorCorpenteUnida().intValue());
        
        selectedAsientoContable.setIdUsuario(user);
        selectedAsientoContableIngresoFondo.setIdUsuario(user);
        selectedAsientoContableIngresoDiezmos.setIdUsuario(user);
        
        selectedAsientoContable.setIdCiudad(ciudad);
        selectedAsientoContableIngresoFondo.setIdCiudad(ciudad);
        selectedAsientoContableIngresoDiezmos.setIdCiudad(ciudad);
        
        persistAsientoContableIngresoFondoLocal(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AsientoContableCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    private void persistAsientoContableIngresoFondoLocal(PersistAction persistAction, String successMessage) {
        if (selectedAsientoContable != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    ejbFacadeAsientoContable.edit(selectedAsientoContable);
                    ejbFacadeAsientoContable.edit(selectedAsientoContableIngresoFondo);
                    ejbFacadeAsientoContable.edit(selectedAsientoContableIngresoDiezmos);
                }
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }    

        
    public Diezmo getDiezmo(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Diezmo> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Diezmo> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Diezmo.class)
    public static class DiezmoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DiezmoController controller = (DiezmoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "diezmoController");
            return controller.getDiezmo(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Diezmo) {
                Diezmo o = (Diezmo) object;
                return getStringKey(o.getIdDiezmo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Diezmo.class.getName()});
                return null;
            }
        }

    }
    
    public List<CuentasPuc> getList_obtener_cuentas_puc_ingresoDiezmo() {           
        list_cuenta_ingreso_diezmos=ejbFacade.obtener_cuentasPuc("INGRESO DONACIONES DIEZMOS");    
    return list_cuenta_ingreso_diezmos;      
    }
        
    public List<CuentasPuc> getList_obtener_cuentas_puc_ingresoFondo() {           
        list_cuenta_ingreso_fondo=ejbFacade.obtener_cuentasPuc("INGRESO DONACIONES FONDO");    
    return list_cuenta_ingreso_fondo;      
    }
   
    
    public void validaNumeroComprobantes() {           
    valNumeroIngreso =ejbFacadeAsientoContable.validaNumeroEgresoIngreso(selected.getNumIngreso(),tipoIngreso.getIdTipoContable());    
    valNumeroEgresoPastor =ejbFacadeAsientoContable.validaNumeroEgresoIngreso(getNumeroEgresoPastor(),tipoEgreso.getIdTipoContable());    
    valNumeroEgresoCorpenteUnida =ejbFacadeAsientoContable.validaNumeroEgresoIngreso(getNumeEgresoCorpenteUnida(),tipoEgreso.getIdTipoContable());        
    }   
 
    
    public void update_asientoContableLiquidacionDiezmos() {
     ejbFacadeAsientoContable.update_asientoContableAportePastor(selected.getFechaDiezmo(),
     selected.getValorPastor().intValue(),Integer.parseInt(selected.getNumIngreso()));

     ejbFacadeAsientoContable.update_asientoContableCorpenteUnida(selected.getFechaDiezmo(),
     selected.getValorCorpenteUnida().intValue(),Integer.parseInt(selected.getNumIngreso()));
     
     ejbFacadeAsientoContable.update_asientoContableDiezmos(selected.getFechaDiezmo(),
     selected.getValorDiezmo(),Integer.parseInt(selected.getNumIngreso()));
     
     ejbFacadeAsientoContable.update_asientoContableFondoLocal(selected.getFechaDiezmo(),
     selected.getValorFondoLocal().intValue(),Integer.parseInt(selected.getNumIngreso()));
     
     ejbFacadeAsientoContable.update_asientoContableIngresosDiezmos(selected.getFechaDiezmo(),
     (selected.getValorCorpenteUnida().intValue()+selected.getValorPastor().intValue()),
     Integer.parseInt(selected.getNumIngreso()));     
    }
    
    public void delete_asientocontable_diezmo() {
     ejbFacadeAsientoContable.delete_asientocontable_diezmo(tipoIngreso.getIdTipoContable(), Integer.parseInt(selected.getNumIngreso()));
    }    
}
