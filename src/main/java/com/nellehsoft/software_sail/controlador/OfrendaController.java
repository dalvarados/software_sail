package com.nellehsoft.software_sail.controlador;

import com.nellehsoft.software_sail.persistencia.Ofrenda;
import com.nellehsoft.software_sail.controlador.util.JsfUtil;
import com.nellehsoft.software_sail.controlador.util.JsfUtil.PersistAction;
import com.nellehsoft.software_sail.negocio.OfrendaFacade;
import com.nellehsoft.software_sail.persistencia.AsientoContable;
import com.nellehsoft.software_sail.persistencia.Ciudad;
import com.nellehsoft.software_sail.persistencia.CuentasPuc;
import com.nellehsoft.software_sail.persistencia.Iglesia;
import com.nellehsoft.software_sail.persistencia.Persona;
import com.nellehsoft.software_sail.persistencia.TipoContable;
import com.nellehsoft.software_sail.persistencia.Usuario;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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

@Named("ofrendaController")
@SessionScoped
public class OfrendaController implements Serializable {

    @EJB
    private com.nellehsoft.software_sail.negocio.OfrendaFacade ejbFacade;
    @EJB 
    private com.nellehsoft.software_sail.negocio.PersonaFacade ejbFacadePersona;    
    @EJB
    private com.nellehsoft.software_sail.negocio.TipoContableFacade ejbFacadeTipoContable;    
    @EJB    
    private com.nellehsoft.software_sail.negocio.IglesiaFacade ejbFacadeIglesia;    
    @EJB    
    private com.nellehsoft.software_sail.negocio.AsientoContableFacade ejbFacadeAsientoContable;    
    @EJB    
    private com.nellehsoft.software_sail.negocio.CuentasPucFacade ejbFacadeCuentasPuc;        
    private List<Ofrenda> items = null;
    private List<CuentasPuc> list_cuenta_activo_caja;  
    private List<CuentasPuc> list_cuenta_ingreso_ofrenda;    
    private Ofrenda selected;
    private AsientoContable selectedAsientoContable; 
    private AsientoContable selectedAsientoContableOfrenda;     
    private Persona tercero_propietario;
    private CuentasPuc cuentaPucOfrenda;
    private TipoContable tipoIngreso;
    private TipoContable tipoContable;    
    private Iglesia iglesia;    
    private Ciudad ciudad;     
    private String ingreso="Ingreso";
    private String iglesiaLocal="Local";    
    private Usuario user;
    private int NumeroIngreso=0;

    
    public OfrendaController() {
    }

    public CuentasPuc getCuentaPucOfrenda() {
        return cuentaPucOfrenda;
    }

    public void setCuentaPucOfrenda(CuentasPuc cuentaPucOfrenda) {
        this.cuentaPucOfrenda = cuentaPucOfrenda;
    }

    public TipoContable getTipoContable() {
        return tipoContable;
    }

    public void setTipoContable(TipoContable tipoContable) {
        this.tipoContable = tipoContable;
    }

    public int getNumeroIngreso() {
        return NumeroIngreso;
    }

    public void setNumeroIngreso(int NumeroIngreso) {
        this.NumeroIngreso = NumeroIngreso;
    }

    public AsientoContable getSelectedAsientoContable() {
        return selectedAsientoContable;
    }

    public void setSelectedAsientoContable(AsientoContable selectedAsientoContable) {
        this.selectedAsientoContable = selectedAsientoContable;
    }

    public AsientoContable getSelectedAsientoContableOfrenda() {
        return selectedAsientoContableOfrenda;
    }

    public void setSelectedAsientoContableOfrenda(AsientoContable selectedAsientoContableOfrenda) {
        this.selectedAsientoContableOfrenda = selectedAsientoContableOfrenda;
    }
        
    public Ofrenda getSelected() {
        return selected;
    }

    public List<CuentasPuc> getList_cuenta_activo_caja() {
        return list_cuenta_activo_caja;
    }

    public void setList_cuenta_activo_caja(List<CuentasPuc> list_cuenta_activo_caja) {
        this.list_cuenta_activo_caja = list_cuenta_activo_caja;
    }

    public List<CuentasPuc> getList_cuenta_ingreso_ofrenda() {
        return list_cuenta_ingreso_ofrenda;
    }

    public void setList_cuenta_ingreso_ofrenda(List<CuentasPuc> list_cuenta_ingreso_ofrenda) {
        this.list_cuenta_ingreso_ofrenda = list_cuenta_ingreso_ofrenda;
    }

    public Persona getTercero_propietario() {
        return tercero_propietario;
    }

    public void setTercero_propietario(Persona tercero_propietario) {
        this.tercero_propietario = tercero_propietario;
    }

    public TipoContable getTipoIngreso() {
        return tipoIngreso;
    }

    public void setTipoIngreso(TipoContable tipoIngreso) {
        this.tipoIngreso = tipoIngreso;
    }

    public Iglesia getIglesia() { 
        return iglesia;
    }

    public void setIglesia(Iglesia iglesia) {
        this.iglesia = iglesia;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public String getIngreso() {
        return ingreso;
    }

    public void setIngreso(String ingreso) {
        this.ingreso = ingreso;
    }

    public String getIglesiaLocal() {
        return iglesiaLocal;
    }

    public void setIglesiaLocal(String iglesiaLocal) {
        this.iglesiaLocal = iglesiaLocal;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    
    
    public void setSelected(Ofrenda selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private OfrendaFacade getFacade() {
        return ejbFacade;
    }
    
    public void obtener_tipoContable_ingreso(){ 
    tipoContable=ejbFacadeTipoContable.obtener_tipoContable(ingreso);
    }    

    public Ofrenda prepareCreate() {    
        Date myDate = new Date();        
        new SimpleDateFormat("MM-dd-yyyy").format(myDate); 
        tercero_propietario=new Persona();      
        tipoIngreso =new TipoContable();
        iglesia =new Iglesia();    
        ciudad=new Ciudad();                  
        getList_obtener_cuentas_puc_caja();
        obtener_tipoIglesia();
        obtener_tipoContable();        
        selected = new Ofrenda();
        selected.setFechaOfrenda(myDate);          
        initializeEmbeddableKey();
        return selected;
    }

    
    public void prepareEdit() { 
        ciudad=new Ciudad();                  
        getList_obtener_cuentas_puc_caja();
        obtener_tipoIglesia();
        obtener_tipoContable();
    }
        
    public void create() {        
        validaNumeroIngreso();
        if ( getNumeroIngreso()==0 ){         
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("OfrendaCreated"));
            if (!JsfUtil.isValidationFailed()) {
                createAsientoContableOfrenda();
                items = null;    // Invalidate list of items to trigger re-query.
            }
        }else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El numero de Ingreso '"+selected.getNumIngreso()+ "' ya fue contabilizado");
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage("successInfo", message); 
         }            
    }

    public void update() {
        ciudad.setIdCiudad(iglesia.getIdCiudad().getIdCiudad());
        update_asientocontable_ofrenda();
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("OfrendaUpdated"));
    }

    public void destroy() {
        obtener_tipoContable_ingreso();
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("OfrendaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            delete_asientocontable_ofrenda();            
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public void cerrar(){ 
     selected = null; // Remove selection
     items = null;    // Invalidate list of items to trigger re-query.        
    }
    
    public List<Ofrenda> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                    mensajeConfirmacion("Registro ingresado exitosamente");
                } else {
                    getFacade().remove(selected);
                    mensajeConfirmacion("Registro eliminado exitosamente");
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

    public void mensajeConfirmacion(String mensaje){
      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", mensaje);
      FacesContext context = FacesContext.getCurrentInstance();
      context.getExternalContext().getFlash().setKeepMessages(true);
      context.addMessage("successInfo", message);                         
    }
    
    public Ofrenda getOfrenda(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Ofrenda> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Ofrenda> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Ofrenda.class)
    public static class OfrendaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            OfrendaController controller = (OfrendaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ofrendaController");
            return controller.getOfrenda(getKey(value));
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
            if (object instanceof Ofrenda) {
                Ofrenda o = (Ofrenda) object;
                return getStringKey(o.getIdOfrenda());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Ofrenda.class.getName()});
                return null;
            }
        }
    }
    
    public List<CuentasPuc> getList_obtener_cuentas_puc_caja() {           
        list_cuenta_activo_caja=ejbFacadeCuentasPuc.obtener_cuentasPuc("FONDO");    
    return list_cuenta_activo_caja;      
    }
    
    public List<CuentasPuc> getList_obtener_cuentas_puc_ingresoOfrenda() {           
        list_cuenta_ingreso_ofrenda=ejbFacadeCuentasPuc.obtener_cuentasPuc("INGRESO DONACIONES OFRENDA");    
    return list_cuenta_ingreso_ofrenda;      
    }
    
    public void obtener_rol_propietario(){ 
    tercero_propietario=ejbFacadePersona.obtener_propietario();
    } 

    public void obtener_objetoCuentaPucIngresoOfrenda(){ 
    cuentaPucOfrenda=ejbFacadeCuentasPuc.obtener_ObjetoCuentasPuc("INGRESO DONACIONES OFRENDA");
    } 
    
    public void obtener_tipoContable(){ 
    tipoIngreso=ejbFacadeTipoContable.obtener_tipoContable(ingreso);
    }               

    public void obtener_tipoIglesia(){ 
    iglesia=ejbFacadeIglesia.obtener_objeto_iglesia_local(iglesiaLocal);
    }
    
    public void createAsientoContableOfrenda() {
        java.util.Date fecha = new Date();        
        user =(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");      
        obtener_rol_propietario();
        obtener_objetoCuentaPucIngresoOfrenda();
        ciudad.setIdCiudad(iglesia.getIdCiudad().getIdCiudad());        
        selectedAsientoContable=new AsientoContable();
        selectedAsientoContableOfrenda=new AsientoContable();        
      
        selectedAsientoContable.setIdTercero(tercero_propietario);
        selectedAsientoContableOfrenda.setIdTercero(tercero_propietario); 
        
        selectedAsientoContable.setIdPuc(selected.getIdPuc());
        selectedAsientoContableOfrenda.setIdPuc(cuentaPucOfrenda);        
        
        selectedAsientoContable.setFechaRegistro(selected.getFechaOfrenda());
        selectedAsientoContableOfrenda.setFechaRegistro(selected.getFechaOfrenda()); 
        
        selectedAsientoContable.setFechaTipoContable(fecha);
        selectedAsientoContableOfrenda.setFechaTipoContable(fecha);
        
        selectedAsientoContable.setIdTipoContable(tipoIngreso);        
        selectedAsientoContableOfrenda.setIdTipoContable(tipoIngreso);
        
        selectedAsientoContable.setNumeroTipoContable(selected.getNumIngreso());
        selectedAsientoContableOfrenda.setNumeroTipoContable(selected.getNumIngreso());
        
        selectedAsientoContable.setDescripcion("Donacion - Ofrenda ");
        selectedAsientoContableOfrenda.setDescripcion("Donacion - Ofrenda ");
        
        selectedAsientoContable.setValorDebito(selected.getValorOfrenda());
        selectedAsientoContableOfrenda.setValorCredito(selected.getValorOfrenda());
        
        selectedAsientoContable.setIdUsuario(user);
        selectedAsientoContableOfrenda.setIdUsuario(user);
        
        selectedAsientoContable.setIdCiudad(ciudad);
        selectedAsientoContableOfrenda.setIdCiudad(ciudad);
        
        selectedAsientoContable.setIdComite(selected.getIdComite());
        selectedAsientoContableOfrenda.setIdComite(selected.getIdComite());
        
        selectedAsientoContable.setIdProposito(selected.getIdProposito());   
        selectedAsientoContableOfrenda.setIdProposito(selected.getIdProposito());
        
        persistAsientoContableDebito(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AsientoContableCreated"));
         if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    private void persistAsientoContableDebito(PersistAction persistAction, String successMessage) {
        if (selectedAsientoContable != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    ejbFacadeAsientoContable.edit(selectedAsientoContable);
                    ejbFacadeAsientoContable.edit(selectedAsientoContableOfrenda);
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

    public void delete_asientocontable_ofrenda(){ 
        ejbFacadeAsientoContable.delete_asientocontable_ingresos(tipoContable.getIdTipoContable()
                ,selected.getNumIngreso());
    }
    
    public void update_asientocontable_ofrenda(){ 
        ejbFacadeAsientoContable.update_asientocontable_ingresos(selected.getFechaOfrenda(), selected.getValorOfrenda(), 
                ciudad.getIdCiudad(), selected.getIdComite().getIdComite(), selected.getIdProposito().getIdProposito(), 
                tipoIngreso.getIdTipoContable(),selected.getNumIngreso(),"Donacion - Ofrenda ",null);

        ejbFacadeAsientoContable.update_asientocontable_IngresoCredito(selected.getFechaOfrenda(), selected.getValorOfrenda(), 
                ciudad.getIdCiudad(), selected.getIdComite().getIdComite(), selected.getIdProposito().getIdProposito(), 
                tipoIngreso.getIdTipoContable(),selected.getNumIngreso(),"Donacion - Ofrenda ",null);
        
    }
    
    public int validaNumeroIngreso() {           
    NumeroIngreso =ejbFacadeAsientoContable.validaNumeroEgresoIngreso(selected.getNumIngreso(),tipoIngreso.getIdTipoContable());    
    return NumeroIngreso;      
    }        
}
