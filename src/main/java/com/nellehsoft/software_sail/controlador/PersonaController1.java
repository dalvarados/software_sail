//package com.nellehsoft.software_sail.controlador;
//
//import com.nellehsoft.software_sail.persistencia.Persona;
//import com.nellehsoft.software_sail.controlador.util.JsfUtil;
//import com.nellehsoft.software_sail.controlador.util.JsfUtil.PersistAction;
//import com.nellehsoft.software_sail.negocio.PersonaFacade;
//import com.nellehsoft.software_sail.persistencia.Rol;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.ResourceBundle;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.annotation.PostConstruct;
//import javax.ejb.EJB;
//import javax.ejb.EJBException;
//import javax.inject.Named;
//import javax.enterprise.context.SessionScoped;
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.convert.Converter;
//import javax.faces.convert.FacesConverter;
//
//
//@Named("personaController")
//@SessionScoped
//public class PersonaController1 implements Serializable {
//
//
//    @EJB 
//    private com.nellehsoft.software_sail.negocio.PersonaFacade ejbFacade;
//    @EJB 
//    private com.nellehsoft.software_sail.negocio.RolFacade ejbFacadeRol;    
//    private List<Persona> items = null;
//    private Persona selected;
//    private List<Persona> list_persona =new ArrayList();
//    private List<Persona> list_persona_ordenada =new ArrayList(); 
//    private List<Persona> list_vista_ordenada =new ArrayList(); 
//    private List<Persona> list_vista_contable =new ArrayList();      
//    private List<Persona> list_pastor =new ArrayList();    
//    private List<Persona> list_esposa =new ArrayList();   
//    private List<Persona> list_esposo =new ArrayList(); 
//    private List<Rol> list_rol_contable;    
//    private List<Rol> list_rol_proveedor;     
//    private String documentoIdentidad;
//    private Persona objeto_persona;
//    private Persona objeto_persona_pastor;
//    String sexoF="Femenino";   
//    String sexoM="Masculino";
//    private String tipoRolContable="Contable"; 
//    private String tipoRolProveedor="Proveedor";     
//    
//    public PersonaController1() {
//  }
//
//    @PostConstruct  
//    public void init(){ 
//    objeto_persona=new Persona();
//    objeto_persona_pastor=new Persona();
//    obtener_lista_persona_ordenada(); 
//    obtener_lista_persona_visita();
//    obtener_lista_persona_contable(); 
//    obtener_pastor();
//    obtener_lista_esposa(sexoF);
//    obtener_lista_esposo(sexoM);
//    obtener_rol_contable();
//    obtener_rol_proveedores();    
//    }   
//
//    public List<Persona> getList_vista_ordenada() {
//        return list_vista_ordenada;
//    }
//
//    public void setList_vista_ordenada(List<Persona> list_vista_ordenada) {
//        this.list_vista_ordenada = list_vista_ordenada;
//    }
//
//    public List<Persona> getList_vista_contable() {
//        return list_vista_contable;
//    }
//
//    public void setList_vista_contable(List<Persona> list_vista_contable) {
//        this.list_vista_contable = list_vista_contable;
//    }
//
//    public List<Rol> getList_rol_contable() {
//        return list_rol_contable;
//    }
//
//    public void setList_rol_contable(List<Rol> list_rol_contable) {
//        this.list_rol_contable = list_rol_contable;
//    }
//
//    public List<Rol> getList_rol_proveedor() {
//        return list_rol_proveedor;
//    }
//
//    public void setList_rol_proveedor(List<Rol> list_rol_proveedor) {
//        this.list_rol_proveedor = list_rol_proveedor;
//    }
//    
//    public List<Persona> getList_pastor() {
//    return list_pastor;
//    }
//
//    public void setList_pastor(List<Persona> list_pastor) {
//    this.list_pastor = list_pastor;
//    }
//
//    public List<Persona> getList_esposa() {
//    return list_esposa;
//    }
//
//    public void setList_esposa(List<Persona> list_esposa) {
//    this.list_esposa = list_esposa;
//    }
//
//    public List<Persona> getList_esposo() {
//        return list_esposo;
//    }
//
//    public void setList_esposo(List<Persona> list_esposo) {
//        this.list_esposo = list_esposo;
//    }
//   
//
//    
//    public Persona getSelected() {
//        return objeto_persona;
//    }
//
//    public List<Persona> getList_persona() {
//        return list_persona;
//    }
//
//    public void setList_persona(List<Persona> list_persona) {
//        this.list_persona = list_persona;
//    }
//
//    public List<Persona> getList_persona_ordenada() {
//        return list_persona_ordenada;
//    }
//
//    public void setList_persona_ordenada(List<Persona> list_persona_ordenada) {
//        this.list_persona_ordenada = list_persona_ordenada;
//    }
//
//    
//    
//    public String getDocumentoIdentidad() {
//        return documentoIdentidad;
//    }
//
//    public void setDocumentoIdentidad(String documentoIdentidad) {
//        this.documentoIdentidad = documentoIdentidad;
//    }
//
//    public Persona getObjeto_persona_pastor() {
//        return objeto_persona_pastor;
//    }
//
//    public void setObjeto_persona_pastor(Persona objeto_persona_pastor) {
//        this.objeto_persona_pastor = objeto_persona_pastor;
//    }
//
//    
//    public Persona getObjeto_persona() {
//        return objeto_persona;
//    }
//
//    public void setObjeto_persona(Persona objeto_persona) {
//        this.objeto_persona = objeto_persona;
//    }
//
//    
//    
//    public void setSelected(Persona objeto_persona) {
//        this.objeto_persona = objeto_persona;
//    }
//
//    protected void setEmbeddableKeys() {
//    }
//
//    protected void initializeEmbeddableKey() {
//    }
//
//    private PersonaFacade getFacade() {
//        return ejbFacade;
//    }
//
//    public Persona prepareCreate() {
//        objeto_persona= new Persona();
//        initializeEmbeddableKey();
//        obtener_rol_contable();        
//       return objeto_persona;
//    }
//
//    public void create() {
//        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PersonaCreated"));
//        if (!JsfUtil.isValidationFailed()) {
//            obtener_lista_persona();
//            obtener_lista_persona_ordenada();
//            obtener_lista_persona_visita();                                
//            obtener_pastor();
//            objeto_persona= new Persona();
//            items = null;
////          objeto_persona = null; 
//            
//        }
//    }
//
//    public void update() {
//        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PersonaUpdated"));
//    }
//
//    public void destroy() {
//        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PersonaDeleted"));
//        if (!JsfUtil.isValidationFailed()) {
//            objeto_persona= new Persona(); // Remove selection
//            items = null;    // Invalidate list of items to trigger re-query.
//        }
//    }
//
//    public List<Persona> getItems() {
//        if (items == null) {
//            items = getFacade().findAll();
//        }
//        return items;
//    }
//
//    private void persist(PersistAction persistAction, String successMessage) {
//        if (objeto_persona != null) {
//            try {
//                if (persistAction != PersistAction.DELETE) {
//                      if("Contable".equals(objeto_persona.getIdRolContable().getTipoRol())){
//                         objeto_persona.setApellidos(" ");
//                      }
//                    getFacade().edit(objeto_persona);
//                    obtener_lista_persona();
//                    obtener_lista_persona_ordenada();
//                    obtener_lista_persona_visita();                    
//                    obtener_pastor();
//                    obtener_lista_persona_contable();
//                    objeto_persona= new Persona();
//                    items = null;                                         
//                    } else {
//                    getFacade().remove(objeto_persona);
//                    obtener_lista_persona();
//                    obtener_lista_persona_ordenada();
//                    obtener_lista_persona_visita();                                        
//                    obtener_pastor();
//                    obtener_lista_persona_contable();
//                    objeto_persona= new Persona();
//                    items = null;
//                   }
//                JsfUtil.addSuccessMessage(successMessage);
//            } catch (EJBException ex) {
//                String msg = ""; 
//                Throwable cause = ex.getCause();
//                if (cause != null) {
//                    msg = cause.getLocalizedMessage();
//                }
//                if (msg.length() > 0) {
//                    JsfUtil.addErrorMessage(msg);
//                } else {
//                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
//                }
//            } catch (Exception ex) {
//                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
//                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
//            }
//        }
//    }
//
//    public Persona getPersona(java.lang.Integer id) {
//        return getFacade().find(id);
//    }
//
//    public List<Persona> getItemsAvailableSelectMany() {
//        return getFacade().findAll();
//    }
//
//    public List<Persona> getItemsAvailableSelectOne() {
//        return getFacade().findAll();
//    } 
//
//    @FacesConverter(forClass=Persona.class)
//    public static class PersonaControllerConverter implements Converter {
//
//        @Override
//        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
//            if (value == null || value.length() == 0) {
//                return null;
//            }
//            PersonaController1 controller = (PersonaController1)facesContext.getApplication().getELResolver().
//                    getValue(facesContext.getELContext(), null, "personaController");
//            return controller.getPersona(getKey(value));
//        }
//
//        java.lang.Integer getKey(String value) {
//            java.lang.Integer key;
//            key = Integer.valueOf(value);
//            return key;
//        }
//
//        String getStringKey(java.lang.Integer value) {
//            StringBuilder sb = new StringBuilder();
//            sb.append(value);
//            return sb.toString();
//        }
//
//        @Override
//        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
//            if (object == null) {
//                return null;
//            }
//            if (object instanceof Persona) {
//                Persona o = (Persona) object;
//                return getStringKey(o.getIdPersona());
//            } else {
//                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Persona.class.getName()});
//                return null;
//            }
//        }
//
//    }
//    
//   public void obtener_pastor(){
//   list_pastor=ejbFacade.obtener_pastor();
//           if (list_pastor.size()>0) {
//        objeto_persona_pastor = (Persona) list_pastor.get(0);
//        } 
//   }   
//   
//   public void obtener_lista_persona_ordenada(){
//   list_persona_ordenada=ejbFacade.obtener_persona_sort();
//   }
//
//   public void obtener_lista_persona_visita(){
//   list_vista_ordenada=ejbFacade.obtener_lista_visitas();
//   }
//   
//   public void obtener_lista_persona_contable(){
//   list_vista_contable=ejbFacade.obtener_lista_contable();
//   }
//   
//   public void obtener_lista_esposa(String sexoF){
//   list_esposa=ejbFacade.obtener_esposa(sexoF);
//   }
//
//   public void obtener_lista_esposo(String sexoM){
//   list_esposo=ejbFacade.obtener_esposo(sexoM);
//   }   
//   
//    public void obtener_lista_persona(){
//        list_persona=ejbFacade.obtener_persona(documentoIdentidad);
//        if (list_persona.size()>0) {
//        objeto_persona = (Persona) list_persona.get(0);
//        } 
//        
//    }
//    
//    public void obtener_rol_contable(){ 
//    list_rol_contable=ejbFacadeRol.obtener_rol(tipoRolContable);
//    }
//    
//    public void obtener_rol_proveedores(){ 
//    list_rol_proveedor=ejbFacadeRol.obtener_rol(tipoRolProveedor);
//    }    
//}
