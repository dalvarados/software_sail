package com.nellehsoft.software_sail.controlador;

import com.nellehsoft.software_sail.persistencia.Calendario;
import com.nellehsoft.software_sail.controlador.util.JsfUtil;
import com.nellehsoft.software_sail.controlador.util.JsfUtil.PersistAction;
import com.nellehsoft.software_sail.negocio.CalendarioFacade;
import com.nellehsoft.software_sail.persistencia.Usuario;
import java.util.Date;
import java.io.Serializable;
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
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;


@Named("calendarioController")
@SessionScoped
public class CalendarioController implements Serializable {

    @EJB
    private com.nellehsoft.software_sail.negocio.CalendarioFacade ejbFacade;
    private List<Calendario> items;
    private Calendario selected;
    private ScheduleModel eventModel;
    private Calendario calendario;
    private Usuario userRol;
    private String tipo; 
    private List<Calendario> list_tipo= null;

    public CalendarioController() {
     userRol =(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
    }
    
    private boolean isPostback(){
     boolean rpta;
     rpta= FacesContext.getCurrentInstance().isPostback();
     return rpta;
    }
                    
   public void inicializarCalendario(){
     if (isPostback() == false) {
       calendario=new Calendario();
       eventModel=new DefaultScheduleModel();   
       items = getFacade().obtener_calendarioRol(userRol.getIdRol().getIdRol());
       for(Calendario  ev: items){
           DefaultScheduleEvent evt =new DefaultScheduleEvent();
           evt.setEndDate(ev.getFechaFin());
           evt.setStartDate(ev.getFechaInicio());
           evt.setTitle(ev.getTituloEvento());
           evt.setData(ev.getIdCalendario());
           evt.setDescription(ev.getDescripcionEvento());
           evt.setAllDay(false);
           evt.setEditable(true);
           eventModel.addEvent(evt);
       }  
      }        
   }
   
    public Usuario getUserRol() {
        return userRol;
    }

    public void setUserRol(Usuario userRol) {
        this.userRol = userRol;
    }
   
    public List<Calendario> getList_tipo() {
        return list_tipo;
    }

    public void setList_tipo(List<Calendario> list_tipo) {
        this.list_tipo = list_tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public CalendarioFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(CalendarioFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public Calendario getCalendario() {
        return calendario;
    }

    public void setCalendario(Calendario calendario) {
        this.calendario = calendario;
    }
 
        
         public void obtener_ajax_tipo(AjaxBehaviorEvent event){
         list_tipo=ejbFacade.obtener_lista_tipo(tipo);  
        }
    
    public List<Calendario> getList_tipo_evento() {
     list_tipo=ejbFacade.obtener_lista_tipo(tipo); 
     return list_tipo;
    }
    
    public Calendario getSelected() {
        return selected;
    }

    public void setSelected(Calendario selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CalendarioFacade getFacade() {
        return ejbFacade;
    }

    public Calendario prepareCreate() {
        selected = new Calendario();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CalendarioCreated"));
        if (!JsfUtil.isValidationFailed()) {
            getList_tipo_evento();
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CalendarioUpdated"));
        getList_tipo_evento();
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CalendarioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            getList_tipo_evento();
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Calendario> getItems() {
           // items = getFacade().findAll();
            items = getFacade().obtener_calendarioRol(userRol.getIdRol().getIdRol());
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
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

    public Calendario getCalendario(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Calendario> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Calendario> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Calendario.class)
    public static class CalendarioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CalendarioController controller = (CalendarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "calendarioController");
            return controller.getCalendario(getKey(value));
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
            if (object instanceof Calendario) {
                Calendario o = (Calendario) object;
                return getStringKey(o.getIdCalendario());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Calendario.class.getName()});
                return null;
            }
        }

    }
   
   public void seleccionado(SelectEvent selectEvent) {
       ScheduleEvent event=(ScheduleEvent) selectEvent.getObject();
       
       for( Calendario ev : items){
           if (ev.getIdCalendario()== event.getData()){
               calendario=ev;
               break;
           }
       }    
}
   
      public void nuevoCalendar(SelectEvent selectEvent) {
       ScheduleEvent event= new DefaultScheduleEvent("",(Date)selectEvent.getObject(),(Date)selectEvent.getObject());
       calendario=new Calendario();
       calendario.setFechaInicio(new java.sql.Date(event.getStartDate().getTime()));
       calendario.setFechaFin(new java.sql.Date(event.getEndDate().getTime()));
}
      
      public void guardarCalendario(){
        if(calendario.getIdCalendario()==null){
            int resultFecha = calendario.getFechaInicio().compareTo(calendario.getFechaFin());
              if( resultFecha<=0) {
                  calendario.setIdRol(userRol.getIdRol());
                   getFacade().create(calendario);
                   inicializarCalendario();
              }else{
              mensajeFaces("Alerta: Fecha inicio menor a la fecha fin",FacesMessage.SEVERITY_WARN);
              }
          }else{
           getFacade().edit(calendario);
           inicializarCalendario();              
          }
      }
      
        public void mensajeFaces(String mensaje,Severity alerta){
             FacesMessage message = new FacesMessage(alerta , mensaje, "");
             FacesContext context = FacesContext.getCurrentInstance();
             context.addMessage("successInfo", message);
           }   

}
