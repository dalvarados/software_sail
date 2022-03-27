package com.nellehsoft.software_sail.controlador;
  
import com.nellehsoft.software_sail.bean.AsientoContableTotales;
import com.nellehsoft.software_sail.persistencia.AsientoContable;
import com.nellehsoft.software_sail.controlador.util.JsfUtil;
import com.nellehsoft.software_sail.controlador.util.JsfUtil.PersistAction;
import com.nellehsoft.software_sail.negocio.AsientoContableFacade;
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
import javax.annotation.PostConstruct;
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

@Named("asientoContableController")
@SessionScoped
public class AsientoContableController implements Serializable {
    @EJB
    private com.nellehsoft.software_sail.negocio.AsientoContableFacade ejbFacade;
    @EJB 
    private com.nellehsoft.software_sail.negocio.PersonaFacade ejbFacadePersona;
    @EJB
    private com.nellehsoft.software_sail.negocio.TipoContableFacade ejbFacadeTipoContable;    
    @EJB    
    private com.nellehsoft.software_sail.negocio.IglesiaFacade ejbFacadeIglesia;
    @EJB    
    private com.nellehsoft.software_sail.negocio.CuentasPucFacade ejbFacadeCuentasPuc;    
    
    private List<AsientoContable> items = null;
    private List<AsientoContable> list_numeroAsientoContable= null;    
    private AsientoContable selected;
    private AsientoContable selectedDetalle;
    private AsientoContable selectedSaldoInicial; 
    private AsientoContable selectedEgresoLegalizaciones;     
    private AsientoContable validacionAsientocontable;    
    private List<Persona> rol_tercero_servPublico;
    private List<Persona> rol_tercero;
    private Persona tercero_propietario;    
    private List<CuentasPuc> list_cuenta_activo_caja;
    private List<CuentasPuc> list_cuenta_activo_caja_menor;    
    private List<AsientoContable> list_egresos;    
    private List<AsientoContable> list_ingresos;
    private List<AsientoContable> list_egresos_legalizacion;    
    private List<AsientoContable> list_AsientoContableTRF;   
    private List<AsientoContable> list_AsientoContableSaldoIncial; 
    private List<TipoContable>  listaTipoContable;
    private List<TipoContable>  listaTipoContableEgreso;    
    private TipoContable tipoContable;
    private TipoContable tipoContableId;
    private CuentasPuc idIpucCajaMenor;    
    private Iglesia iglesia; 
    private String tipoValor="";    
    private String tipoIngreso="";
    private Ciudad ciudad;     
    private String egreso="Egreso";
    private String ingreso="Ingreso";    
    private String iglesiaLocal="Local";  
    private String numero_tipo_contable;    
    private String descripcionEgreso;        
    private int saldoFondoLocal; 
    private int diferenciaNumeroEgreso;     
    private int diferenciaNumeroDocumentoContableEdit;    
    private int saldoCajaMenor;
    private Usuario user;
    private AsientoContableTotales asientoTotales;
    private Integer Debito;
    private Integer Credito;
    private Integer Diferencia;    
    private boolean EsiIglesiaLocal=true; 
    private int NumeroEgresoIngreso=0;
    private CuentasPuc cuentaPucSaldoInicial;
    private CuentasPuc  cuentasPucAuxiliar;
    
    
    public AsientoContableController() {
    }
    
    @PostConstruct  
    public void init(){ 
     getList_obtener_AsientoContableTipoContableEgreso();;
     getList_obtener_AsientoContableTipoContableIngreso();
     getList_obtener_AsientoContableSaldoInicial();     
     getList_obtener_AsientoContableTipoContableEgresoLegalizacion();
    }

    public AsientoContable getSelectedEgresoLegalizaciones() {
        return selectedEgresoLegalizaciones;
    }

    public void setSelectedEgresoLegalizaciones(AsientoContable selectedEgresoLegalizaciones) {
        this.selectedEgresoLegalizaciones = selectedEgresoLegalizaciones;
    }        

    public CuentasPuc getCuentaPucSaldoInicial() {
        return cuentaPucSaldoInicial;
    }

    public CuentasPuc getCuentasPucAuxiliar() {
        return cuentasPucAuxiliar;
    }

    public void setCuentasPucAuxiliar(CuentasPuc cuentasPucAuxiliar) {
        this.cuentasPucAuxiliar = cuentasPucAuxiliar;
    }


    public List<TipoContable> getListaTipoContable() {
        return listaTipoContable;
    }

    public void setListaTipoContable(List<TipoContable> listaTipoContable) {
        this.listaTipoContable = listaTipoContable;
    }

    public List<TipoContable> getListaTipoContableEgreso() {
        return listaTipoContableEgreso;
    }

    public void setListaTipoContableEgreso(List<TipoContable> listaTipoContableEgreso) {
        this.listaTipoContableEgreso = listaTipoContableEgreso;
    }    

    public void setCuentaPucSaldoInicial(CuentasPuc cuentaPucSaldoInicial) {
        this.cuentaPucSaldoInicial = cuentaPucSaldoInicial;
    }

    public AsientoContable getSelectedSaldoInicial() {
        return selectedSaldoInicial;
    }

    public TipoContable getTipoContableId() {
        return tipoContableId;
    }

    public void setTipoContableId(TipoContable tipoContableId) {
        this.tipoContableId = tipoContableId;
    }

    public void setSelectedSaldoInicial(AsientoContable selectedSaldoInicial) {
        this.selectedSaldoInicial = selectedSaldoInicial;
    }
    
    public int getSaldoFondoLocal() {
        return saldoFondoLocal;
    }

    public void setSaldoFondoLocal(int saldoFondoLocal) {
        this.saldoFondoLocal = saldoFondoLocal;
    }

    public int getSaldoCajaMenor() {
        return saldoCajaMenor;
    }

    public void setSaldoCajaMenor(int saldoCajaMenor) {
        this.saldoCajaMenor = saldoCajaMenor;
    }

    public int getNumeroEgresoIngreso() {
        return NumeroEgresoIngreso;
    }

    public int getDiferenciaNumeroEgreso() {
        return diferenciaNumeroEgreso;
    }

    public void setDiferenciaNumeroEgreso(int diferenciaNumeroEgreso) {
        this.diferenciaNumeroEgreso = diferenciaNumeroEgreso;
    }

    public int getDiferenciaNumeroDocumentoContableEdit() {
        return diferenciaNumeroDocumentoContableEdit;
    }

    public void setDiferenciaNumeroDocumentoContableEdit(int diferenciaNumeroDocumentoContableEdit) {
        this.diferenciaNumeroDocumentoContableEdit = diferenciaNumeroDocumentoContableEdit;
    }

    public void setNumeroEgresoIngreso(int NumeroEgresoIngreso) {
        this.NumeroEgresoIngreso = NumeroEgresoIngreso;
    }

    public String getDescripcionEgreso() {
        return descripcionEgreso;
    }

    public void setDescripcionEgreso(String descripcionEgreso) {
        this.descripcionEgreso = descripcionEgreso;
    }
    
    public boolean isEsiIglesiaLocal() {
        return EsiIglesiaLocal;
    }

    public String getTipoIngreso() {
        return tipoIngreso;
    }

    public CuentasPuc getIdIpucCajaMenor() {
        return idIpucCajaMenor;
    }

    public void setIdIpucCajaMenor(CuentasPuc idIpucCajaMenor) {
        this.idIpucCajaMenor = idIpucCajaMenor;
    }

    public void setTipoIngreso(String tipoIngreso) {
        this.tipoIngreso = tipoIngreso;
    }

    public List<CuentasPuc> getList_cuenta_activo_caja_menor() {
        return list_cuenta_activo_caja_menor;
    }

    public void setList_cuenta_activo_caja_menor(List<CuentasPuc> list_cuenta_activo_caja_menor) {
        this.list_cuenta_activo_caja_menor = list_cuenta_activo_caja_menor;
    }

    public List<AsientoContable> getList_AsientoContableTRF() {
        return list_AsientoContableTRF;
    }

    public void setList_AsientoContableTRF(List<AsientoContable> list_AsientoContableTRF) {
        this.list_AsientoContableTRF = list_AsientoContableTRF;
    }

    public List<AsientoContable> getList_AsientoContableSaldoIncial() {
        return list_AsientoContableSaldoIncial;
    }

    public void setList_AsientoContableSaldoIncial(List<AsientoContable> list_AsientoContableSaldoIncial) {
        this.list_AsientoContableSaldoIncial = list_AsientoContableSaldoIncial;
    }

    public Persona getTercero_propietario() { 
        return tercero_propietario;
    }

    public void setTercero_propietario(Persona tercero_propietario) {
        this.tercero_propietario = tercero_propietario;
    }

    public void setEsiIglesiaLocal(boolean EsiIglesiaLocal) {
        this.EsiIglesiaLocal = EsiIglesiaLocal;
    }
    
    public AsientoContable getSelectedDetalle() {
        return selectedDetalle;
    }

    public void setSelectedDetalle(AsientoContable selectedDetalle) {
        this.selectedDetalle = selectedDetalle;
    }

    public List<AsientoContable> getList_egresos() {
        return list_egresos;
    }

    public String getNumero_tipo_contable() {
        return numero_tipo_contable;
    }

    public void setNumero_tipo_contable(String numero_tipo_contable) {
        this.numero_tipo_contable = numero_tipo_contable;
    }

    public List<AsientoContable> getList_ingresos() {
        return list_ingresos;
    }

    public void setList_ingresos(List<AsientoContable> list_ingresos) {
        this.list_ingresos = list_ingresos;
    }

    public void setList_egresos(List<AsientoContable> list_egresos) {
        this.list_egresos = list_egresos;
    }    
    
    public Iglesia getIglesia() {
        return iglesia;
    }

    public void setIglesia(Iglesia iglesia) {
        this.iglesia = iglesia;
    }

    public List<AsientoContable> getList_numeroAsientoContable() {
        return list_numeroAsientoContable;
    }

    public void setList_numeroAsientoContable(List<AsientoContable> list_numeroAsientoContable) {
        this.list_numeroAsientoContable = list_numeroAsientoContable;
    }

    public Integer getDebito() {
        return Debito;
    }

    public void setDebito(Integer Debito) {
        this.Debito = Debito;
    }

    public Integer getCredito() {
        return Credito;
    }

    public void setCredito(Integer Credito) {
        this.Credito = Credito;
    }

    public Integer getDiferencia() {
        return Diferencia;
    }

    public void setDiferencia(Integer Diferencia) {
        this.Diferencia = Diferencia;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public String getEgreso() {
        return egreso;
    }

    public void setEgreso(String egreso) {
        this.egreso = egreso;
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

    public String getIngreso() {
        return ingreso;
    }

    public void setIngreso(String ingreso) {
        this.ingreso = ingreso;
    }    
    
    public List<CuentasPuc> getList_cuenta_activo_caja() {
        return list_cuenta_activo_caja;
    }

    public void setList_cuenta_activo_caja(List<CuentasPuc> list_cuenta_activo_caja) {
        this.list_cuenta_activo_caja = list_cuenta_activo_caja;
    }

    public AsientoContable getSelected() {
        return selected;
    }

    public void setSelected(AsientoContable selected) {
        this.selected = selected;
    }

    public AsientoContable getValidacionAsientocontable() {
        return validacionAsientocontable;
    }

    public void setValidacionAsientocontable(AsientoContable validacionAsientocontable) {
        this.validacionAsientocontable = validacionAsientocontable;
    }

    protected void setEmbeddableKeys() {
    }

    public List<Persona> getRol_tercero_servPublico() {
        return rol_tercero_servPublico;
    }

    public void setRol_tercero_servPublico(List<Persona> rol_tercero_servPublico) {
        this.rol_tercero_servPublico = rol_tercero_servPublico;
    }
    
    public List<Persona> getRol_tercero() {
        return rol_tercero;
    }

    public void setRol_tercero(List<Persona> rol_tercero) {
        this.rol_tercero = rol_tercero;
    }
    
    protected void initializeEmbeddableKey() {
    }

    private AsientoContableFacade getFacade() {
        return ejbFacade;
    }

    public String getTipoValor() {
        return tipoValor;
    }

    public void setTipoValor(String tipoValor) {
        this.tipoValor = tipoValor;
    }

    public List<AsientoContable> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<AsientoContable> getList_egresos_legalizacion() {
        return list_egresos_legalizacion;
    }

    public void setList_egresos_legalizacion(List<AsientoContable> list_egresos_legalizacion) {
        this.list_egresos_legalizacion = list_egresos_legalizacion;
    }

    public void obtener_tipoContable(){ 
    tipoContable=ejbFacadeTipoContable.obtener_tipoContable(egreso);
    }               

    public void obtener_tipoContable_ingreso(){ 
    tipoContable=ejbFacadeTipoContable.obtener_tipoContable(ingreso);
    }

    public void obtener_tipoContableId(){ 
    tipoContableId=ejbFacadeTipoContable.obtener_tipoContableId(selected.getDiferencia());
    }
    
    public void obtener_objetoCuentaPucSaldoInicial(){ 
    cuentaPucSaldoInicial=ejbFacadeCuentasPuc.obtener_ObjetoCuentasPuc("INGRESO SALDO INICIAL");
    }
    
    public void obtener_tipoIglesia(){ 
    iglesia=ejbFacadeIglesia.obtener_objeto_iglesia_local(iglesiaLocal);
    }

    public void prepareEdit() { 
        ciudad=new Ciudad();
        iglesia =new Iglesia();    
        obtener_tipoIglesia();
        ciudad.setIdCiudad(iglesia.getIdCiudad().getIdCiudad());
        getList_obtener_cuentas_puc_caja();
        obtener_tipoContable_ingreso();
        obtener_rol_propietario();
    }
    
    public void delete_asientoDocumentoContable(){
        int id=selected.getDiferencia()!= null ? selected.getDiferencia() : selected.getIdTipoContable().getIdTipoContable();        
        ejbFacade.delete_asientocontable_ingresos(id
                ,selected.getNumeroTipoContable());//se coloca difeencia por el objeto idtipocontable
    }

    public void update_asientocontable_SaldoInicial(){ 
        ejbFacade.update_asientocontable_ingresos(selected.getFechaRegistro(), selected.getValorDebito(), 
                ciudad.getIdCiudad(), null, null,tipoContable.getIdTipoContable(),selected.getNumeroTipoContable(),
                selected.getDescripcion(),selected.getIdTesorero().getIdPersona());        
        
        ejbFacade.update_asientocontable_IngresoCredito(selected.getFechaRegistro(), selected.getValorDebito(), 
                ciudad.getIdCiudad(), null, null,tipoContable.getIdTipoContable(),selected.getNumeroTipoContable(),
                selected.getDescripcion(),selected.getIdTesorero().getIdPersona());
        
    }        
    
// ****************Documentos Contables ************************
    public AsientoContable prepareCreateDocumentoContable() { 
        Date myDate = new Date(); 
        obtener_List_tipoContable();
        obtener_List_tipoContableEgreso();    
        getList_obtener_cuentas_puc_caja();
        new SimpleDateFormat("MM-dd-yyyy").format(myDate);       
        this.setTipoValor("DB");
        getobtener_saldoFondoLocal(); 
        setDebito(0);
        setCredito(0);
        setDiferencia(0);
        setNumero_tipo_contable("");
        tipoContable =new TipoContable();
        iglesia =new Iglesia();    
        ciudad=new Ciudad();       
        obtener_tipoIglesia(); 
        obtener_rol_tercero();  
        selected = new AsientoContable();
        selected.setFechaRegistro(myDate);        
        initializeEmbeddableKey();
        return selected;
    }

    public void createDocumentoContable() {
        if(selected != null){
            validacionAsientocontable=selected;}else{validacionAsientocontable=selectedDetalle;
        }            
        java.util.Date fecha = new Date();
        tipoValorDbCr();        
        user =(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario"); 
        selected.setIdUsuario(user);
        ciudad.setIdCiudad(iglesia.getIdCiudad().getIdCiudad());
        selected.setFechaTipoContable(fecha);
        selected.setIdCiudad(ciudad);
        selected.setNumeroTipoContable(getNumero_tipo_contable());
        getobtener_DiferenciaPorNumeroDocumento();          
        
        if(selected.getDiferencia()!=null){
           obtener_tipoContableId();
        }       
        
        selectedDetalle=null;
        if ( getDiferenciaNumeroEgreso()!=0 ){            
                  persistIngreso(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AsientoContableCreated"));
                   if (!JsfUtil.isValidationFailed()) {
                      getobtener_saldoFondoLocal(); 
                      selected.setIdAsiento(null);
                      selected.setDescripcion("");
                      selected.setValorDebito(null);
                      selected.setDocReferencia("");
                      selected.setIdTercero(null);
                      selected.setIdPuc(null);                      
                      items = null;    // Invalidate list of items to trigger re-query.
                     }
         }else{
         FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El numero de Ingreso '"+selected.getNumeroTipoContable()+ "' esta cuadrado");
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.addMessage("successInfo", message);         
            //RequestContext.getCurrentInstance().showMessageInDialog(message);
         selected.setDescripcion("");
         } 
  }  

    public void updateDocumentoContable() {
        persistUpdDocumentoContable(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AsientoContableUpdated"));
        if (!JsfUtil.isValidationFailed()) {
           obtenerAsitentoTotales();
           getobtener_saldoFondoLocal();
           items = null; 
           selectedDetalle=null;
        }     
    }
    
    public void destroyDocumentoContable() {     
        persistUpdDocumentoContable(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AsientoContableDeleted"));
        if (!JsfUtil.isValidationFailed()) {
           getList_asientoContable();         
           getList_obtener_AsientoContableTipoContableIngreso(); 
           getList_obtener_AsientoContableTipoContableEgreso();           
           getList_obtener_AsientoContableSaldoInicial();
           getList_obtener_AsientoContableTipoContableEgresoLegalizacion();
           resetearCamposEgreso();
           obtenerAsitentoTotales();

        }
    }
    
    public void anular_asientocontableIngreso(){ 
        obtener_tipoContable_ingreso();
        ejbFacade.anular_asientocontable(tipoContable.getIdTipoContable(),selected.getNumeroTipoContable()); 
    }
    
    public void anular_asientocontableEgreso(){ 
        obtener_tipoContable();
        ejbFacade.anular_asientocontable(tipoContable.getIdTipoContable(),selected.getNumeroTipoContable());
        getList_obtener_AsientoContableTipoContableEgresoLegalizacion();
    }    
    
    public AsientoContable prepareEditDocumentoContable() {
        tipoContable =new TipoContable();
        tipoContable.setNombre(null);
        obtener_List_tipoContable();
        iglesia =new Iglesia();    
        ciudad=new Ciudad();       
        obtener_tipoIglesia();        
        obtener_rol_tercero(); 
        obtenerAsitentoTotales();
        getList_asientoContable();      
        getobtener_saldoFondoLocal();
        getList_obtener_cuentas_puc_caja();
        getList_obtener_cuentasPucCuentaAuxiliar();
        setNumero_tipo_contable(selected.getNumeroTipoContable()); 
     return selected;
    }    

    public void cerrarDocumentoContable() {
        if (selected==null && selectedDetalle==null ){
             selected.setNumeroTipoContable("");
             resetearCamposEgreso();
             validacionAsientocontable=null;     
             selected=null;
             selectedDetalle=null;
             list_numeroAsientoContable=null;
        }else{
            getobtener_DiferenciaPorNumeroDocumento();
            if (getDiferenciaNumeroEgreso()!=0 ){            
                 delete_asientoDocumentoContable();
                 getList_obtener_AsientoContableTipoContableIngreso();
                 getList_obtener_AsientoContableTipoContableEgreso();
                 resetearCamposEgreso();
                 validacionAsientocontable=null;     
                 selected=null;
                 selectedDetalle=null; 
                 list_numeroAsientoContable=null;
                 }else{
                 getList_obtener_AsientoContableTipoContableIngreso();
                 getList_obtener_AsientoContableTipoContableEgreso();
                 resetearCamposEgreso();
                 selected.setNumeroTipoContable("");
                 validacionAsientocontable=null;     
                 selected=null;
                 selectedDetalle=null;
                 list_numeroAsientoContable=null;
                }
        }
    }

    private void persistUpdDocumentoContable(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                         if(selectedDetalle!=null){                        
                           getFacade().edit(selectedDetalle);
                           obtenerAsitentoTotales();
                           getList_asientoContable();                            
                        }                        
                }else {                    
                    if(selectedDetalle!=null){
                       getFacade().remove(selectedDetalle);
                       }else {
                        getFacade().remove(selected);
                        delete_asientoDocumentoContable();                      
                       }                    
                       getItems();                                           
                       resetearCamposEgreso();
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
   
//******************Ingresos ********************************************    
    public AsientoContable prepareCreateIngresoSaldoInicial() {  
        tipoContable =new TipoContable();
        iglesia =new Iglesia();    
        ciudad=new Ciudad();    
        obtener_objetoCuentaPucSaldoInicial();        
        obtener_rol_propietario();
        getList_obtener_cuentas_puc_caja();
        obtener_tipoIglesia();
        obtener_tipoContable_ingreso();
        obtener_rol_tercero();  
        selected = new AsientoContable();
        setTipoIngreso("SI");
        initializeEmbeddableKey();
        return selected;
    }

//******************Ingresos ********************************************    
    public AsientoContable prepareCreateEgresoLegalizaciones() {  
        tipoContable =new TipoContable();
        iglesia =new Iglesia();    
        ciudad=new Ciudad();    
        obtener_objetoCuentaPucSaldoInicial();        
        obtener_rol_propietario();
        getList_obtener_cuentas_puc_caja();
        getList_obtener_cuentasPucCuentaAuxiliar();
        obtener_tipoIglesia();
        obtener_tipoContable(); 
        obtener_rol_tercero();  
        selected = new AsientoContable();
        setTipoIngreso("EL");
        initializeEmbeddableKey();
        return selected;
    }
    
    public void createAsientosContables() {
        java.util.Date fecha = new Date();       
        user =(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario"); 
        selected.setIdUsuario(user);
        ciudad.setIdCiudad(iglesia.getIdCiudad().getIdCiudad());
        selected.setFechaTipoContable(fecha);
        selected.setIdTipoContable(tipoContable);
        selected.setIdCiudad(ciudad);        
        selected.setFechaRegistro(fecha);             
            
        if( "SI".equals(tipoIngreso)){
            selectedSaldoInicial=new AsientoContable();                      
            selectedSaldoInicial.setFechaRegistro(fecha);            
            selected.setDescripcion("SI-".concat(selected.getDescripcion()));
            selectedSaldoInicial.setDescripcion(selected.getDescripcion());
            selectedSaldoInicial.setIdUsuario(user);                      
            selectedSaldoInicial.setFechaTipoContable(fecha);        
            selectedSaldoInicial.setIdTipoContable(tipoContable);                 
            selectedSaldoInicial.setIdCiudad(ciudad);        
            selectedSaldoInicial.setValorCredito(selected.getValorDebito());
            selectedSaldoInicial.setIdPuc(cuentaPucSaldoInicial);
            selectedSaldoInicial.setNumeroTipoContable(selected.getNumeroTipoContable());        
        }

        if( "EL".equals(tipoIngreso)){
            selectedEgresoLegalizaciones=new AsientoContable();                      
            selectedEgresoLegalizaciones.setFechaRegistro(fecha);            
            selectedEgresoLegalizaciones.setDescripcion(selected.getDescripcion());
            selectedEgresoLegalizaciones.setIdUsuario(user);                      
            selectedEgresoLegalizaciones.setFechaTipoContable(fecha);        
            selectedEgresoLegalizaciones.setIdTipoContable(tipoContable);                 
            selectedEgresoLegalizaciones.setIdCiudad(ciudad);        
            selectedEgresoLegalizaciones.setValorCredito(selected.getValorDebito());
            selectedEgresoLegalizaciones.setNumeroTipoContable(selected.getNumeroTipoContable());        
            selectedEgresoLegalizaciones.setLegalizacion(1);
            selected.setLegalizacion(1);
        }
        
        if( EsiIglesiaLocal==true && selectedSaldoInicial!=null ){
            selected.setFechaRegistro(fecha);
            selectedSaldoInicial.setFechaRegistro(fecha);           
            selected.setIdTercero(tercero_propietario);
            selectedSaldoInicial.setIdTercero(selected.getIdTesorero());
        } 
         
        if( EsiIglesiaLocal==true && selectedEgresoLegalizaciones!=null ){
            selected.setFechaRegistro(fecha);
            selectedEgresoLegalizaciones.setFechaRegistro(fecha);                      
            selectedEgresoLegalizaciones.setIdTercero(selected.getIdTercero());             
            selectedEgresoLegalizaciones.setIdPuc(getCuentasPucAuxiliar());
            selected.setIdTercero(selected.getIdTesorero());
        }
        
        validaNumeroEgresoIngreso();        
        if ( getNumeroEgresoIngreso()==0 ){        
            persistIngreso(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AsientoContableCreated"));
            if (!JsfUtil.isValidationFailed()) {
             getList_obtener_AsientoContableTipoContableIngreso();
             getList_obtener_AsientoContableSaldoInicial();
             getList_obtener_AsientoContableTipoContableEgresoLegalizacion();             
             setTipoIngreso("");
                items = null;    // Invalidate list of items to trigger re-query.
            }
        }else { 
          FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El numero de Ingreso '"+selected.getNumeroTipoContable()+ "' ya fue contabilizado");
          RequestContext.getCurrentInstance().showMessageInDialog(message);
         } 
    }
    
   public void updateSaldoInicial(){
        update_asientocontable_SaldoInicial();
        getList_obtener_AsientoContableTipoContableIngreso();
        getList_obtener_AsientoContableSaldoInicial();
        selected =null;
        selectedSaldoInicial =null;
        items = null;    // Invalidate list of items to trigger re-query.
   }       
    
    public void updateIngreso() {
     persistIngreso(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AsientoContableUpdated"));
        if (!JsfUtil.isValidationFailed()) {
           getList_obtener_AsientoContableTipoContableIngreso();        
           items = null;   
        }     
    }
   
    public void destroyIngresoSaldoInicial() {
        obtener_tipoContable_ingreso();     
        persistIngreso(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AsientoContableDeleted"));
        if (!JsfUtil.isValidationFailed()) {
           getList_obtener_AsientoContableTipoContableIngreso(); 
           getList_obtener_AsientoContableTRF();
           getList_obtener_AsientoContableSaldoInicial();             
           items = null;   
           selected=null;           
        }
    }
    
    private void persistIngreso(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                   if ("CR".equals(this.tipoValor)){
                            selected.setValorDebito(null);
                        }else{
                         selected.setValorCredito(null); 
                        }                    
                        getFacade().edit(selected);
                        if( "SI".equals(tipoIngreso)){
                        getFacade().edit(selectedSaldoInicial);
                        }
                        if( "EL".equals(tipoIngreso)){
                        getFacade().edit(selectedEgresoLegalizaciones);
                        }                        
                    obtenerAsitentoTotales();
                    getList_asientoContable();                                                
                } else {
                        getFacade().remove(selected);
                        delete_asientoDocumentoContable();                        
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

// ******************Egresos *************************    
     public AsientoContable prepareCreateEgreso() {  
        list_numeroAsientoContable= null; 
        asientoTotales=null;
        tipoContable =new TipoContable();
        iglesia =new Iglesia();    
        ciudad=new Ciudad();                
        obtener_tipoIglesia();
        obtener_tipoContable();  
        obtener_rol_tercero();  
        obtener_rol_tercero_servPublico();
        getobtener_saldoFondoLocal();  
        selected = new AsientoContable();
        initializeEmbeddableKey();
        tipoIngreso="";
        return selected;
    }

    public AsientoContable prepareCreateEgresoTransferencia() {  
        tipoContable =new TipoContable();
        iglesia =new Iglesia();    
        ciudad=new Ciudad();    
        obtener_rol_propietario();
        getList_obtener_cuentas_puc_caja();      
        obtener_tipoIglesia();
        obtener_tipoContable();
        obtener_rol_tercero();  
        selected = new AsientoContable();
        setTipoIngreso("TR");
        getobtener_saldoFondoLocal();  
        getobtener_saldoCajaMenor();        
        initializeEmbeddableKey();
        return selected;
    }
     
    public AsientoContable prepareEditTransferencia() { 
        obtenerAsitentoTotales();
        getList_asientoContable(); 
        getList_obtener_cuentas_puc_caja();      
        getobtener_saldoFondoLocal();  
        getobtener_saldoCajaMenor();                
        setNumero_tipo_contable(selected.getNumeroTipoContable());
        tipoContable =new TipoContable();
        iglesia =new Iglesia();    
        ciudad=new Ciudad();                
        obtener_tipoIglesia();
        obtener_tipoContable();  
        obtener_rol_tercero_servPublico();        
        obtener_rol_tercero();  
     return selected;
    }    

    public void createEgresoEdit() {  
        java.util.Date fecha = new Date();
        selected.setFechaTipoContable(fecha);        
        tipoValorDbCr();        
        user =(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario"); 
        selected = new AsientoContable();
        selected.setIdUsuario(user);
        ciudad.setIdCiudad(iglesia.getIdCiudad().getIdCiudad());
        selected.setIdTipoContable(tipoContable);
        selected.setIdCiudad(ciudad);        
        persistUpdDocumentoContable(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AsientoContableCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public void createEgreso() {
        if(selected != null){validacionAsientocontable=selected;}else{validacionAsientocontable=selectedDetalle;}    
        java.util.Date fecha = new Date();
        tipoValorDbCr();        
        user =(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario"); 
        selected.setIdUsuario(user);
        ciudad.setIdCiudad(iglesia.getIdCiudad().getIdCiudad());
        selected.setFechaTipoContable(fecha);
        selected.setIdTipoContable(tipoContable);
        selected.setIdCiudad(ciudad);
        selected.setNumeroTipoContable(getNumero_tipo_contable());
        getobtener_DiferenciaPorNumeroDocumento();                        
            
     if ( getDiferenciaNumeroEgreso()!=0 ){
        if(saldoFondoLocal > validacionAsientocontable.getValorDebito()){                      
               persistUpdDocumentoContable(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AsientoContableCreated"));
               if (!JsfUtil.isValidationFailed()) {
                   getList_obtener_AsientoContableTRF();
                   getobtener_saldoCajaMenor();
                   tipoIngreso="";
                   descripcionEgreso="";
                   items = null;    // Invalidate list of items to trigger re-query.
               }
        }else{
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", " No hay saldo suficiente para realizar la transferencia");
           RequestContext.getCurrentInstance().showMessageInDialog(message);          
        }
    }else {
      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "El numero de Egreso '"+selected.getNumeroTipoContable()+ "' ya esta cuadrado y contabilizado");
      RequestContext.getCurrentInstance().showMessageInDialog(message);
      selected.setDescripcion("");
    }
      
  }

    public void createEgresoTransferencia() {
        java.util.Date fecha = new Date();
        user =(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario"); 
        selected.setIdUsuario(user);
        ciudad.setIdCiudad(iglesia.getIdCiudad().getIdCiudad());
        selected.setFechaTipoContable(fecha);
        selected.setIdTipoContable(tipoContable);
        selected.setIdCiudad(ciudad);
        selected.setNumeroTipoContable(getNumero_tipo_contable());
        getobtener_DiferenciaPorNumeroDocumento();                
        selected.setFechaRegistro(fecha); 
        selected.setDescripcion("TR-".concat(selected.getDescripcion()));
        selected.setValorCredito(selected.getValorDebito());
        selected.setValorDebito(null);   
        selected.setNumeroTipoContable("-999");               
        selected.setIdTercero(tercero_propietario);
        setDescripcionEgreso(selected.getDescripcion());

       if(saldoFondoLocal > selected.getValorCredito()){           
            persistUpdDocumentoContable(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AsientoContableCreated"));
               if (!JsfUtil.isValidationFailed()) {
                   selected.setIdTercero(tercero_propietario);
                   selected.setDescripcion(descripcionEgreso);                    
                   insertar_partida_debito_transferencia();
                   getList_obtener_AsientoContableTRF();
                   getobtener_saldoCajaMenor();
                   tipoIngreso="";
                   descripcionEgreso="";
                   items = null;    // Invalidate list of items to trigger re-query.
               }       
       }else{
          FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", " No hay saldo suficiente para realizar la transferencia");
          RequestContext.getCurrentInstance().showMessageInDialog(message);          
       }         
    }

    
    public void cerrarEgreso() { 
     getList_obtener_AsientoContableTipoContableEgreso();
     getList_obtener_AsientoContableTipoContableIngreso();
     validacionAsientocontable=null;     
     list_numeroAsientoContable= null;
     asientoTotales = null;
     Debito=null;
     Credito=null;
     numero_tipo_contable=null;
     Diferencia=null;
     selected=null;
     selectedDetalle=null;
    }    

    
    public void updateEgreso() {
//        obtener_rol_propietario();
//        getList_obtener_cuentas_puc_caja();
//        getList_obtener_cuentas_puc_cajaMenor() ;        
//        obtener_tipoIglesia();
//        obtener_tipoContable();
//        obtener_rol_tercero();  
//        getobtener_saldoFondoLocal();  
//        getobtener_saldoCajaMenor();              
//        obtener_rol_tercero();  
//        obtenerAsitentoTotales();
//        getList_asientoContable();                
        persistUpdDocumentoContable(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AsientoContableUpdated"));
        if (!JsfUtil.isValidationFailed()) {
           obtenerAsitentoTotales();
           getobtener_saldoFondoLocal();
           //String descTR=selected.getDescripcion().substring(0,3 );
//                if( !"TR-".equals(descTR)){ 
//                    resetearCamposEgreso(); 
//                }
           items = null; 
           selectedDetalle=null;
        }     
    }

    public void destroyListEgreso() {
        persistUpdDocumentoContable(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AsientoContableDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            list_numeroAsientoContable= null;            
        }
    }
    
    public void destroyEgreso() {
        persistUpdDocumentoContable(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AsientoContableDeleted"));
        if (!JsfUtil.isValidationFailed()) {
           items = null;             
        }
    }

    public void cancelarEgreso() {
           selectedDetalle= null;
           items=null;
    }
        
    public AsientoContable getAsientoContable(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<AsientoContable> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<AsientoContable> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = AsientoContable.class)
    public static class AsientoContableControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AsientoContableController controller = (AsientoContableController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "asientoContableController");
            return controller.getAsientoContable(getKey(value));
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
            if (object instanceof AsientoContable) {
                AsientoContable o = (AsientoContable) object;
                return getStringKey(o.getIdAsiento());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AsientoContable.class.getName()});
                return null;
            }
        }

    }   
    
    public void obtener_rol_tercero_servPublico(){ 
    rol_tercero_servPublico=ejbFacadePersona.obtener_rol_tercero_servPublico();
    }
    
    public void obtener_rol_tercero(){ 
    rol_tercero=ejbFacadePersona.obtener_rol_tercero();
    }
    
    public List<CuentasPuc> getList_obtener_cuentas_puc_caja() {           
        list_cuenta_activo_caja=ejbFacade.obtener_cuentasPucActivos();    
    return list_cuenta_activo_caja;      
    }
 
    
    public List<CuentasPuc> getList_obtener_cuentasPucCuentaAuxiliar() {           
        list_cuenta_activo_caja_menor=ejbFacade.obtener_cuentasPucCuentaAuxiliar();    
    return list_cuenta_activo_caja_menor;      
    }    
    
    public List<AsientoContable> getList_obtener_AsientoContableTipoContableIngreso() {           
        list_ingresos=ejbFacade.obtener_ListAsientoContableTipoContable(ingreso);       
        return list_ingresos;      
    }

    public List<AsientoContable> getList_obtener_AsientoContableTipoContableEgreso() {           
        list_egresos=ejbFacade.obtener_ListAsientoContableTipoContable(egreso);       
        return list_egresos;      
    }
    
    public List<AsientoContable> getList_obtener_AsientoContableTipoContableEgresoLegalizacion() {           
        list_egresos_legalizacion=ejbFacade.obtener_ListAsientoContableTipoContableLegalizacion(egreso);       
        return list_egresos_legalizacion;      
    }  
    
    public void resetearCamposEgreso(){
      selected.setDescripcion("");
      selected.setValorDebito(null);
      selected.setValorCredito(null);      
      selected.setDocReferencia("");
      selected.setIdTercero(null);
      selected.setIdPuc(null);
    }
    
    public void tipoValorDbCr(){
        if ("CR".equals(this.tipoValor) && selected!=null){
            selected.setValorCredito(selected.getValorDebito());
            selected.setValorDebito(null);                        
            validacionAsientocontable.setValorDebito(selected.getValorCredito());
        }
    }
    
    public void obtenerAsitentoTotales(){
      int id=selected.getDiferencia()!= null ? selected.getDiferencia() : selected.getIdTipoContable().getIdTipoContable();
        asientoTotales=ejbFacade.obtener_AsientoContableTotales(selected.getNumeroTipoContable(),id); 
        setDiferencia(asientoTotales.getDiferencia());
        setDebito(asientoTotales.getValor_debito());
        setCredito(asientoTotales.getValor_credito());        
    }
    
    public void obtener_rol_propietario(){ 
    tercero_propietario=ejbFacadePersona.obtener_propietario();
    }     

    public void getList_asientoContable() {
     int id=selected.getDiferencia()!= null ? selected.getDiferencia() : selected.getIdTipoContable().getIdTipoContable();
     list_numeroAsientoContable=ejbFacade.obtener_AsientoContable(selected.getNumeroTipoContable(),id);  
    }
    
    public void getList_obtener_AsientoContableTRF() {
    list_AsientoContableTRF=ejbFacade.obtener_AsientoContableTRF();  
    }    

    public void getList_obtener_AsientoContableSaldoInicial() {
    list_AsientoContableSaldoIncial=ejbFacade.obtener_AsientoContableSaldoInicial();  
    }    
    
    
    public void insertar_partida_debito_transferencia(){ 
        ejbFacade.insertar_partida_debito(selected.getIdTercero().getIdPersona()
                ,idIpucCajaMenor.getIdPuc(),selected.getFechaRegistro(),selected.getIdTipoContable().getIdTipoContable()
                ,selected.getNumeroTipoContable(), selected.getDescripcion().substring(3,selected.getDescripcion().length() ), selected.getDocReferencia(), selected.getValorCredito()
                , selected.getIdCiudad().getIdCiudad(), selected.getIdUsuario().getIdUsuario());
    }
    
    public int getobtener_saldoFondoLocal() {           
    saldoFondoLocal =ejbFacade.obtener_saldoFondoLocal();    
    return saldoFondoLocal;      
    }    

    public int getobtener_saldoCajaMenor() {           
    saldoCajaMenor =ejbFacade.obtener_saldoCajaMenor();    
    return saldoCajaMenor;      
    }    

    public int getobtener_DiferenciaPorNumeroDocumento() {  
    int id=selected.getDiferencia()!= null ? selected.getDiferencia() : selected.getIdTipoContable().getIdTipoContable();        
    diferenciaNumeroEgreso =ejbFacade.obtener_DiferenciaPorNumeroDocumento(selected.getNumeroTipoContable(),id);    
    return diferenciaNumeroEgreso;      
    }

    public int getobtener_DiferenciaPorNumeroDocumentoContableEdit() { 
    int id=selected.getDiferencia()!= null ? selected.getDiferencia() : selected.getIdTipoContable().getIdTipoContable();        
    diferenciaNumeroDocumentoContableEdit =ejbFacade.obtener_DiferenciaPorNumeroDocumento(selected.getNumeroTipoContable(),id);    
    return diferenciaNumeroDocumentoContableEdit;      
    }
    
    public int validaNumeroEgresoIngreso() {           
    NumeroEgresoIngreso =ejbFacade.validaNumeroEgresoIngreso(selected.getNumeroTipoContable(),selected.getIdTipoContable().getIdTipoContable());    
    return NumeroEgresoIngreso;      
    }
    
    public void obtener_List_tipoContable(){ 
    listaTipoContable=ejbFacadeTipoContable.obtener_List_tipoContable("Ingreso");
    }
    
    public void obtener_List_tipoContableEgreso(){ 
    listaTipoContableEgreso=ejbFacadeTipoContable.obtener_List_tipoContable("Egreso");
    }    

    
}
